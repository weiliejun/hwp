package com.hwp.admin.components.trustsign.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwp.admin.components.trustsign.TrustsignConstant;
import com.hwp.common.components.cfca.trustsign.common.vo.cs.*;
import com.hwp.common.components.cfca.trustsign.common.vo.request.tx3.Tx3001ReqVO;
import com.hwp.common.components.cfca.trustsign.common.vo.request.tx3.Tx3002ReqVO;
import com.hwp.common.components.cfca.trustsign.common.vo.request.tx3.Tx3203ReqVO;
import com.hwp.common.components.cfca.trustsign.connector.HttpConnector;
import com.hwp.common.components.cfca.trustsign.constant.Request;
import com.hwp.common.components.cfca.trustsign.converter.JsonObjectMapper;
import com.hwp.common.model.cfcaContractnoRecord.bean.CfcaContractnoRecord;
import com.hwp.common.model.cfcaContractnoRecord.dao.CfcaContractnoRecordMapper;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.product.dao.ProductMapper;
import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dao.UserInfoDao;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.HttpClientHelper;
import com.hwp.common.util.StringHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("trustSignService")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class TrustSignServiceImp implements TrustSignService {

    private final Logger logger = Logger.getLogger(TrustSignServiceImp.class);

    @Autowired
    public UserInfoDao UserInfoDao;
    @Autowired
    public ProductMapper ProductMapper;

    @Autowired
    public CfcaContractnoRecordMapper CfcaContractnoRecordMapper;
    @Autowired
    TrustSignInterfaceCallService trustSignInterfaceCallService;

    // @Value("${trustSign.authenticationMode}")
    private String authenticationMode = "微金客实名认证";

    @Value("${resourceServer.AccessURL}")
    private String pictureServerURL;

    @Override
    public JSONObject registerPerson(JSONObject json) {
        Tx3001ReqVO tx3001ReqVO = new Tx3001ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));

        String userId = json.getString("userId");
        String personName = json.getString("personName");
        String identNo = json.getString("identNo");
        String mobilePhone = json.getString("mobilePhone");
        PersonVO person = new PersonVO();
        person.setPersonName(personName);
        person.setIdentTypeCode("0");
        person.setIdentNo(identNo);
        person.setMobilePhone(mobilePhone);
        person.setAddress("");
        person.setAuthenticationMode(authenticationMode);

        tx3001ReqVO.setHead(head);
        tx3001ReqVO.setPerson(person);
        tx3001ReqVO.setNotSendPwd(1);// 不发送密码短信

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3001ReqVO);

        String txCode = "3001";
        String signature = trustSignInterfaceCallService.p7SignMessageDetach(req);
        // 通过aop保存接口调用参数记录
        String res = trustSignInterfaceCallService.sendRequest(txCode, "platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        JSONObject resultJson = JSON.parseObject(res);
        JSONObject headJsonObj = resultJson.getJSONObject("head");
        String retCode = "";
        if (headJsonObj != null) {
            retCode = headJsonObj.getString("retCode");
        } else {
            retCode = resultJson.getString("errorCode");// 异常码
        }
        if (TrustsignConstant.RETCODE_SUCCESS.equals(retCode)) {
            JSONObject personJsonObj = resultJson.getJSONObject("person");
            String trustSignId = personJsonObj.getString("userId");
            // 增加安心签Id
            UserInfo user = new UserInfo();
            user.setId(new Integer(userId));
            user.setTrustSignId(trustSignId);
            UserInfoDao.updateUserInfo(user);
        }
        return resultJson;
    }

    @Override
    public JSONObject registerEnterprise(JSONObject json) {
        Tx3002ReqVO tx3002ReqVO = new Tx3002ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));

        String userId = json.getString("userId");
        String enterpriseName = json.getString("enterpriseName");
        String identTypeCode = json.getString("identTypeCode");
        String identNo = json.getString("identNo");
        String mobilePhone = json.getString("mobilePhone");
        String landlinePhone = json.getString("landlinePhone");
        String email = json.getString("email");

        EnterpriseVO enterprise = new EnterpriseVO();
        enterprise.setEnterpriseName(enterpriseName);
        enterprise.setIdentTypeCode(identTypeCode);
        enterprise.setIdentNo(identNo);
        enterprise.setMobilePhone(mobilePhone);
        enterprise.setLandlinePhone(landlinePhone);
        enterprise.setEmail(email);
        enterprise.setAuthenticationMode(authenticationMode);

        // 经办人信息
        String transactorName = json.getString("transactorName");
        String transactorIdentTypeCode = json.getString("transactorIdentTypeCode");
        String transactorIdentNo = json.getString("transactorIdentNo");
        String transactorAddress = json.getString("transactorAddress");

        EnterpriseTransactorVO enterpriseTransactor = new EnterpriseTransactorVO();
        enterpriseTransactor.setTransactorName(transactorName);
        enterpriseTransactor.setIdentTypeCode(transactorIdentTypeCode);
        enterpriseTransactor.setIdentNo(transactorIdentNo);
        enterpriseTransactor.setAddress(transactorAddress);

        tx3002ReqVO.setHead(head);
        tx3002ReqVO.setEnterprise(enterprise);
        tx3002ReqVO.setEnterpriseTransactor(enterpriseTransactor);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3002ReqVO);

        String txCode = "3002";
        String signature = trustSignInterfaceCallService.p7SignMessageDetach(req);
        // 通过aop保存接口调用参数记录
        String res = trustSignInterfaceCallService.sendRequest(txCode, "platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
        JSONObject resultJson = JSON.parseObject(res);
        JSONObject headJsonObj = resultJson.getJSONObject("head");
        String retCode = headJsonObj.getString("retCode");

        if (TrustsignConstant.RETCODE_SUCCESS.equals(retCode)) {
            JSONObject enterpriseJsonObj = resultJson.getJSONObject("enterprise");
            String trustSignId = enterpriseJsonObj.getString("userId");
            // 增加安心签Id
            UserInfo user = new UserInfo();
            user.setId(new Integer(userId));
            user.setTrustSignId(trustSignId);
            UserInfoDao.updateUserInfo(user);
        }
        return resultJson;
    }

    @Override
    public JSONObject iuploadContract(JSONObject json, OutputStream out) {
        Tx3203ReqVO tx3203ReqVO = new Tx3203ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
        // 请求json参数的组织
        UploadContractVO uploadContract = new UploadContractVO();
        // 出借人签署位置信息
        JSONArray signUsers = json.getJSONArray("signUsers");
        UploadSignInfoVO[] signInfos = null;
        if (signUsers != null && signUsers.size() > 0) {
            int size = signUsers.size();
            signInfos = new UploadSignInfoVO[size];
            JSONObject jsonTemp = null;
            UploadSignInfoVO signInfoVO = null;
            SignKeywordVO signKeywordVO = null;
            String projectCode = json.getString("projectCode");
            String roleType = "";
            for (int i = 0; i < size; i++) {
                jsonTemp = signUsers.getJSONObject(i);
                signInfoVO = new UploadSignInfoVO();
                signKeywordVO = new SignKeywordVO();
                if (StringHelper.isNotEmpty(jsonTemp.getString("keyWord"))) {
                    signKeywordVO.setKeyword(jsonTemp.getString("keyWord"));
                }
                roleType = jsonTemp.getString("roleType");
                if (TrustsignConstant.ROLE_INVESTOR.equals(roleType)) {
                    signKeywordVO.setOffsetCoordX("15");
                    signKeywordVO.setOffsetCoordY("0");
                    signKeywordVO.setImageWidth("85");
                    signKeywordVO.setImageHeight("35");
                    signInfoVO.setSignKeyword(signKeywordVO);
                } else if (TrustsignConstant.ROLE_BORROWER.equals(roleType)) {
                    signKeywordVO.setOffsetCoordX("5");
                    signKeywordVO.setOffsetCoordY("15");
                    signKeywordVO.setImageWidth("120");
                    signKeywordVO.setImageHeight("90");
                    signInfoVO.setSignKeyword(signKeywordVO);
                } else {
                    signKeywordVO.setOffsetCoordX("15");
                    signKeywordVO.setOffsetCoordY("0");
                    signKeywordVO.setImageWidth("85");
                    signKeywordVO.setImageHeight("35");
                    signInfoVO.setSignKeyword(signKeywordVO);
                }

                if (StringHelper.isNotEmpty(jsonTemp.getString("trustSignId"))) {
                    signInfoVO.setUserId(jsonTemp.getString("trustSignId"));
                }
                signInfoVO.setIsProxySign(1);
                signInfoVO.setLocation("微金客平台");
                signInfoVO.setProjectCode(projectCode);
                signInfoVO.setAuthorizationTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
                signInfos[i] = signInfoVO;
            }
            // 平台签名位置信息
            JSONObject platForm = json.getJSONObject("platForm");
            uploadContract.setSignInfos(signInfos);
            // 居间合同
            uploadContract.setContractTypeCode("JJ");
            uploadContract.setContractName(json.getString("contractName"));

            uploadContract.setIsSign(1);
            SignKeywordVO signKeyword = new SignKeywordVO();
            if (StringHelper.isNotEmpty(platForm.getString("keyWord"))) {
                signKeyword.setKeyword(platForm.getString("keyWord"));
                signKeyword.setOffsetCoordX("-45");
                signKeyword.setOffsetCoordY("-35");
                signKeyword.setImageWidth("150");
                signKeyword.setImageHeight("150");
            }

            uploadContract.setSignKeyword(signKeyword);
            tx3203ReqVO.setHead(head);
            tx3203ReqVO.setUploadContract(uploadContract);
            // 调用3203接口
            JSONObject resultJson = trustSignInterface3203Invoke(tx3203ReqVO, out);
            JSONObject headJsonObj = resultJson.getJSONObject("head");
            String retCode = "";
            if (headJsonObj != null) {
                retCode = headJsonObj.getString("retCode");
            } else {
                retCode = resultJson.getString("errorCode");// 异常码
            }
            // 保存安心签合同编码
            if (TrustsignConstant.RETCODE_SUCCESS.equals(retCode)) {
                JSONObject contractJsonObj = resultJson.getJSONObject("contract");
                String contractNo = contractJsonObj.getString("contractNo");
                String productId = json.getString("productId");
                // 作为冗余字段保存到产品表中
                Product product = new Product();
                product.setId(productId);
                product.setTrustContractNo(contractNo);
                ProductMapper.updateProductById(product);

                CfcaContractnoRecord cfcaContractnoRecord = new CfcaContractnoRecord();
                cfcaContractnoRecord.setBusinessId(productId);
                cfcaContractnoRecord.setBusinessType(TrustsignConstant.BUSINESS_TYPE_PRODUCT);
                cfcaContractnoRecord.setContractNo(contractNo);
                // 合同json字符串保存到remark字段中
                cfcaContractnoRecord.setRemark(JSON.toJSONString(contractJsonObj));
                CfcaContractnoRecordMapper.addOrUpdateCfcaContractnoRecord(cfcaContractnoRecord);
            }
            return resultJson;
        }
        return null;
    }

    @Override
    public byte[] downLoadContract(String contractNo) {
        HttpConnector httpConnector = new HttpConnector();
        byte[] fileBtye = httpConnector.getFile("platId/" + Request.PLAT_ID + "/contractNo/" + contractNo + "/downloading");
        if (fileBtye == null || fileBtye.length == 0) {
            return null;
        }
        return fileBtye;
    }

    @Override
    public void trustSignRegister(Product product) {
        String productId = product.getId();
        // 出借人
        List<Map> productTenders = UserInfoDao.findUsersOfTenderSuccess(productId);
        if (productTenders != null && productTenders.size() > 0) {
            String userId = "";
            String name = "";
            String idNo = "";
            String mobile = "";
            String trustSignId = "";
            JSONObject registerPersonJson = null;
            for (Map map : productTenders) {
                userId = (String) map.get("ID");
                name = (String) map.get("NAME");
                mobile = (String) map.get("MOBILE");
                idNo = (String) map.get("ID_NO");
                trustSignId = (String) map.get("TRUST_SIGN_ID");
                // 向安心签注册用户信息
                if (StringHelper.isEmpty(trustSignId)) {
                    registerPersonJson = new JSONObject();
                    registerPersonJson.put("userId", userId);
                    registerPersonJson.put("personName", name);
                    registerPersonJson.put("identNo", idNo);
                    registerPersonJson.put("mobilePhone", mobile);
                    this.registerPerson(registerPersonJson);
                }
            }
        }


    }

    @Override
    public JSONObject trustSignContractSign(Product product) {
        JSONObject resJson = new JSONObject();
        String productId = product.getId();
        String trustContractNo = product.getTrustContractNo();
        /*
         * if(StringHelper.isNotEmpty(trustContractNo)){//已经签署过，退出方法
         * resJson.put("flag", "false"); resJson.put("msg", "该产品已经签署过合同");
         * return resJson; }
         */
        // 出借人
        List<Map> productTenders = UserInfoDao.findUsersOfTenderSuccess(productId);
        if (productTenders != null && productTenders.size() > 0) {
            String idNo = "";
            String trustSignId = "";
            JSONObject json = new JSONObject();
            // 平台签章关键词
            JSONObject platForm = new JSONObject();
            platForm.put("keyWord", "北京微金客科技有限公司");
            json.put("platForm", platForm);

            JSONArray signUsers = new JSONArray();
            JSONObject signUser = null;
            for (Map map : productTenders) {
                idNo = (String) map.get("ID_NO");
                trustSignId = (String) map.get("TRUST_SIGN_ID");
                // 出借人签章关键词
                if (StringHelper.isNotEmpty(trustSignId)) {
                    signUser = new JSONObject();
                    signUser.put("keyWord", StringHelper.replaceWithStr(idNo, "*", 6, 9));
                    signUser.put("trustSignId", trustSignId);
                    signUser.put("roleType", "investor");
                    signUsers.add(signUser);
                }
            }


            json.put("signUsers", signUsers);
            json.put("productId", productId);
            // 产品编号
            String productCode = product.getCode();
            json.put("projectCode", productCode);
            // 产品名称
            String productName = product.getName();
            json.put("contractName", productName + "借贷合同");

            // 产品协议类型
            String productAgreementType = product.getProductAgreementType();
            String url = pictureServerURL + "/product/agreement/" + productAgreementType.toLowerCase() + "/download";
            String param = "?productId=" + productId + "&isTemplate=false&roleType=investor&userId=" /*+ borrowUserId*/;

            // 上传并签署合同
            OutputStream out = HttpClientHelper.downLoad(url + param);
            try {
                JSONObject resultJson = this.iuploadContract(json, out);
                JSONObject headJsonObj = resultJson.getJSONObject("head");
                String message = "";
                if (headJsonObj != null) {
                    resJson.put("flag", "true");
                    resJson.put("msg", "签署合同成功");
                    message = headJsonObj.getString("retMessage");
                } else {
                    resJson.put("flag", "false");
                    resJson.put("msg", "签署合同失败");
                    message = resultJson.getString("errorMessage");// 异常码
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("调用安心签3203接口失败");
            }
        }
        return resJson;
    }

    @Override
    public JSONObject trustSignContractSignOne(Product product) {
        JSONObject resJson = new JSONObject();
        String productId = product.getId();
        String trustContractNo = product.getTrustContractNo();
        // 出借人
        List<Map> productTenders = UserInfoDao.findUsersOfTenderSuccess(productId);
        if (productTenders != null && productTenders.size() > 0) {
            String idNo = "";
            String trustSignId = "";
            // 产品协议类型
            String productAgreementType = product.getProductAgreementType();
            String url = pictureServerURL + "/product/agreement/" + productAgreementType.toLowerCase() + "/download";

            JSONArray signUsers = null;
            JSONObject signBorrowerUser = new JSONObject();
            JSONObject signInvestorUser = new JSONObject();

            for (Map map : productTenders) {
                String investorId = (String) map.get("ID");
                // 业务ID
                StringBuffer businessId = new StringBuffer(productId);
                businessId.append(investorId);
                CfcaContractnoRecord cfcaContractnoRecord = CfcaContractnoRecordMapper.findCfcaContractnoRecordBybusinessIdAndBusinessType(businessId.toString(), "product");
                if (cfcaContractnoRecord != null) {
                    logger.info("安心签签署错误信息：用户（" + investorId + "）已经签署过，签署合同编号：" + cfcaContractnoRecord.getContractNo() + "，不需再进行签署！");
                    continue;
                }

                signUsers = new JSONArray();
                signUsers.add(signBorrowerUser);
                idNo = (String) map.get("ID_NO");
                trustSignId = (String) map.get("TRUST_SIGN_ID");
                // 出借人签章关键词
                if (StringHelper.isNotEmpty(trustSignId)) {
                    signInvestorUser.put("keyWord", StringHelper.replaceWithStr(idNo, "*", 6, 9));
                    signInvestorUser.put("trustSignId", trustSignId);
                    signInvestorUser.put("roleType", "investor");
                    signUsers.add(signInvestorUser);
                }

                JSONObject json = new JSONObject();
                // 平台签章关键词
                JSONObject platForm = new JSONObject();
                platForm.put("keyWord", "：北京微金客科技有限公司");
                json.put("platForm", platForm);
                json.put("signUsers", signUsers);
                json.put("productId", businessId.toString());
                // 产品编号
                String productCode = product.getCode();
                json.put("projectCode", productCode);
                // 产品名称
                String productName = product.getName();
                json.put("contractName", productName + "借贷合同");

                String param = "?productId=" + productId + "&isTemplate=false&roleType=investor&userId=" + investorId;
                // 上传并签署合同
                OutputStream out = HttpClientHelper.downLoad(url + param);
                try {
                    JSONObject resultJson = this.iuploadContract(json, out);
                    JSONObject headJsonObj = resultJson.getJSONObject("head");
                    String message = "";
                    if (headJsonObj != null) {
                        resJson.put("flag", "true");
                        resJson.put("msg", "签署合同成功");
                        message = headJsonObj.getString("retMessage");
                    } else {
                        resJson.put("flag", "false");
                        resJson.put("msg", "签署合同失败");
                        message = resultJson.getString("errorMessage");// 异常码
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("调用安心签3203接口失败");
                }
            }
        }
        return resJson;
    }

    @Override
    public Map<String, String> trustSignRegisterAndSign(Product product) {
        Map<String, String> resultMap = new HashMap<String, String>();
        JSONObject resJson = new JSONObject();
        resultMap.put("flag", "false");
        resultMap.put("msg", "该产品暂时不能签署合同");
        /**
         * 一键签署
         */
        this.trustSignRegister(product);// 安心签注册
        // 协议类型为 优信贷-借款协议 （investAgreementCreditLoan）出借人分开签署 2018.09.25
        String agreementType = product.getProductAgreementType();
        if ("investAgreementCreditLoan".equals(agreementType)) {
            resJson = this.trustSignContractSignOne(product);
        } else {
            resJson = this.trustSignContractSign(product);// 安心签合同签署
        }
        resultMap.put("flag", resJson.getString("flag"));
        resultMap.put("msg", resJson.getString("msg"));

        // 判断是否签署个人信用查询授权书
        /**注释掉，现在业务不需要签署个人信用授权书***/
		/*int signCnt = CfcaContractnoRecordMapper.findCfcaContractnoRecordsTotalCount(product.getId(), TrustsignConstant.BUSINESS_TYPE_PERSONCREDIT);
		if (signCnt == 0 && "true".equals(resJson.getString("flag"))) {// 未签署
			// 签署个人信用查询授权书
			personCreditQueryAuthorization(product.getBorrowerUserInfoId(), product.getId());
		}*/

        return resultMap;
    }

    @Override
    public Map<String, String> personCreditQueryAuthorization(String userId, String productId) {
        UserInfo user = UserInfoDao.getUserInfoById(new Integer(userId));
        if (user == null) {
            return null;
        }
        String trustSignId = user.getTrustSignId();
        // 注册安心签用户
        if (StringHelper.isEmpty(trustSignId)) {
            JSONObject registerJson = new JSONObject();
            registerJson.put("userId", user.getId());
            registerJson.put("personName", user.getUserName());
            registerJson.put("identNo", user.getIdNo());
            registerJson.put("mobilePhone", user.getMobile());
            try {
                JSONObject resultJson = registerPerson(registerJson);
                JSONObject personJsonObj = resultJson.getJSONObject("person");
                trustSignId = personJsonObj.getString("userId");
            } catch (Exception e) {
                logger.error("调用安心签3001接口失败：" + e.getMessage());
                return null;
            }
        }
        // 签署个人信用查询授权书
        Tx3203ReqVO tx3203ReqVO = new Tx3203ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
        // 请求json参数的组织
        UploadContractVO uploadContract = new UploadContractVO();
        UploadSignInfoVO[] signInfos = null;
        signInfos = new UploadSignInfoVO[1];
        // 签署合同信息
        UploadSignInfoVO signInfoVO = new UploadSignInfoVO();
        SignKeywordVO signKeywordVO = new SignKeywordVO();
        signKeywordVO.setKeyword(StringHelper.replaceWithStr(user.getIdNo(), "*", 1, 16));
        signKeywordVO.setOffsetCoordX("15");
        signKeywordVO.setOffsetCoordY("0");
        signKeywordVO.setImageWidth("85");
        signKeywordVO.setImageHeight("35");
        signInfoVO.setSignKeyword(signKeywordVO);
        signInfoVO.setUserId(trustSignId);
        signInfoVO.setIsProxySign(1);
        signInfoVO.setLocation("微金客平台");
        // 这个自定义的，暂时没有业务含义
        signInfoVO.setProjectCode("CreditQuery_" + userId);
        signInfoVO.setAuthorizationTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
        signInfos[0] = signInfoVO;
        uploadContract.setSignInfos(signInfos);
        // 其他合同
        uploadContract.setContractTypeCode("QT");
        uploadContract.setContractName("个人信用报告查询授权书(" + user.getUserName() + ")");

        uploadContract.setIsSign(2);
        /*
         * SignKeywordVO signKeyword = new SignKeywordVO();
         * signKeyword.setKeyword(""); signKeyword.setOffsetCoordX("35");
         * signKeyword.setOffsetCoordY("10"); signKeyword.setImageWidth("150");
         * signKeyword.setImageHeight("150");
         * uploadContract.setSignKeyword(signKeyword);
         */
        tx3203ReqVO.setHead(head);
        tx3203ReqVO.setUploadContract(uploadContract);
        // 调用3203接口
        /**
         * 个人信用查询授权书 一个产品签署一次
         */
        String url = pictureServerURL + TrustsignConstant.PERSON_CREDIT_URL;
        String param = "?userId=" + userId;

        // 上传并签署合同
        OutputStream out = HttpClientHelper.downLoad(url + param);
        JSONObject resultJson = trustSignInterface3203Invoke(tx3203ReqVO, out);
        JSONObject headJsonObj = resultJson.getJSONObject("head");
        String retCode = "";
        if (headJsonObj != null) {
            retCode = headJsonObj.getString("retCode");
        } else {
            retCode = resultJson.getString("errorCode");// 异常码
        }
        // 保存安心签合同编码
        if (TrustsignConstant.RETCODE_SUCCESS.equals(retCode)) {
            JSONObject contractJsonObj = resultJson.getJSONObject("contract");
            String contractNo = contractJsonObj.getString("contractNo");
            CfcaContractnoRecord cfcaContractnoRecord = new CfcaContractnoRecord();
            cfcaContractnoRecord.setBusinessId(productId);
            cfcaContractnoRecord.setBusinessType(TrustsignConstant.BUSINESS_TYPE_PERSONCREDIT);
            cfcaContractnoRecord.setContractNo(contractNo);
            // 合同json字符串保存到remark字段中
            cfcaContractnoRecord.setRemark(JSON.toJSONString(contractJsonObj));
            CfcaContractnoRecordMapper.addOrUpdateCfcaContractnoRecord(cfcaContractnoRecord);

        }
        return null;
    }

    @Override
    public CfcaContractnoRecord findCfcaContractnoRecordBybusinessIdAndBusinessType(String businessId, String businessType) {
        return CfcaContractnoRecordMapper.findCfcaContractnoRecordBybusinessIdAndBusinessType(businessId, businessType);
    }

    @Override
    public Map findProductCFCARecordsCount(String productId) {
        return CfcaContractnoRecordMapper.findProductCFCARecordsCount(productId);
    }


    // 产品需要额外签署的合同
    @Override
    public synchronized Map<String, String> extraProductAssignmentAuthorization(Map<String, String> params) {
        String userId = params.get("userId");
        String productId = params.get("productId");
        // 是否需要平台签章
        String platFormFlag = params.get("platFormFlag");
        // 签署合同类型
        String signType = params.get("signType");
        Map<String, String> resultMap = new HashMap<String, String>();
        UserInfo user = UserInfoDao.getUserInfoById(new Integer(userId));
        if (user == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "签署合同失败！");
            return resultMap;
        }
        String trustSignId = user.getTrustSignId();
        // 1、如果用户没有注册，先注册安心签用户
        if (StringHelper.isEmpty(trustSignId)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "签署合同失败！");
            return resultMap;
        }
        // 2、签署协议
        Tx3203ReqVO tx3203ReqVO = new Tx3203ReqVO();
        // 协议模板地址
        String url = "";
        // 获取协议模板所需参数
        String param = "";
        HeadVO head = new HeadVO();
        head.setTxTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
        // 请求json参数的组织
        UploadContractVO uploadContract = new UploadContractVO();
        String contractName = "";
        if (TrustsignConstant.BUSINESS_TYPE_CREDIT_UNDERTAKING.equals(signType)) {
            params.put("userName", user.getUserName());
            contractName = "信用承诺书(" + user.getUserName() + ")";
            url = pictureServerURL + TrustsignConstant.CREDIT_UNDERTAKING_URL;
            param = "?productId=" + productId + "&isTemplate=false&roleType=borrower&userId=" + userId;
        } else if (TrustsignConstant.BUSINESS_TYPE_INFO_CONSULTATION.equals(signType)) {
            contractName = "信息咨询服务协议(" + user.getUserName() + ")";
            url = pictureServerURL + TrustsignConstant.INFO_CONSULTATION_URL;
            param = "?productId=" + productId + "&isTemplate=false&roleType=borrower&userId=" + userId;
        }
        params.put("trustSignId", trustSignId);
        UploadSignInfoVO[] signInfos = packSignInfos(params);
        uploadContract.setSignInfos(signInfos);
        uploadContract.setContractName(contractName);
        // 其他合同
        uploadContract.setContractTypeCode("QT");
        if ("true".equals(platFormFlag)) {
            uploadContract.setIsSign(1);
            SignKeywordVO signKeyword = new SignKeywordVO();
            signKeyword.setKeyword("乙方（服务方）（盖章）");
            signKeyword.setOffsetCoordX("35");
            signKeyword.setOffsetCoordY("-35");
            signKeyword.setImageWidth("150");
            signKeyword.setImageHeight("150");
            uploadContract.setSignKeyword(signKeyword);
        } else {
            uploadContract.setIsSign(2);
        }
        tx3203ReqVO.setHead(head);
        tx3203ReqVO.setUploadContract(uploadContract);
        // 上传并签署合同
        OutputStream out = HttpClientHelper.downLoad(url + param);
        // 调用3203接口
        try {
            JSONObject resultJson = trustSignInterface3203Invoke(tx3203ReqVO, out);
            JSONObject headJsonObj = resultJson.getJSONObject("head");
            String message = "";
            if (headJsonObj != null) {
                resultMap.put("flag", "true");
                resultMap.put("msg", "签署合同成功");
                message = headJsonObj.getString("retMessage");
            } else {
                message = resultJson.getString("errorMessage");// 异常码
                resultMap.put("flag", "false");
                resultMap.put("msg", StringHelper.isNotBlank(message) ? message : "签署合同失败");
            }
            String retCode = "";
            if (headJsonObj != null) {
                retCode = headJsonObj.getString("retCode");
            } else {
                retCode = resultJson.getString("errorCode");// 异常码
            }
            // 保存安心签合同编码
            if (TrustsignConstant.RETCODE_SUCCESS.equals(retCode)) {
                JSONObject contractJsonObj = resultJson.getJSONObject("contract");
                String contractNo = contractJsonObj.getString("contractNo");
                CfcaContractnoRecord cfcaContractnoRecord = new CfcaContractnoRecord();
                cfcaContractnoRecord.setBusinessId(productId);
                cfcaContractnoRecord.setBusinessType(signType);
                cfcaContractnoRecord.setContractNo(contractNo);
                // 合同json字符串保存到remark字段中
                cfcaContractnoRecord.setRemark(JSON.toJSONString(contractJsonObj));
                CfcaContractnoRecordMapper.addOrUpdateCfcaContractnoRecord(cfcaContractnoRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用安心签3203接口失败");
        }
        return resultMap;
    }

    /**
     * @param param
     * @return
     * @description 组装合同信息
     * @version 1.0
     * @author 徐赛平
     * @update 2018-9-21 下午3:19:22
     */
    private UploadSignInfoVO[] packSignInfos(Map<String, String> param) {
        String trustSignId = param.get("trustSignId");
        String signType = param.get("signType");
        UploadSignInfoVO[] signInfos = null;
        if (TrustsignConstant.BUSINESS_TYPE_CREDIT_UNDERTAKING.equals(signType)) {
            // 签署信用承诺书
            String productId = param.get("productId");
            String userName = param.get("userName");
            // 组装签署合同信息
            signInfos = new UploadSignInfoVO[1];
            UploadSignInfoVO signInfoVO = new UploadSignInfoVO();
            SignKeywordVO signKeywordVO = new SignKeywordVO();
            signKeywordVO.setKeyword(userName);
            signKeywordVO.setOffsetCoordX("5");
            signKeywordVO.setOffsetCoordY("-10");
            signKeywordVO.setImageWidth("85");
            signKeywordVO.setImageHeight("35");
            signInfoVO.setSignKeyword(signKeywordVO);
            signInfoVO.setUserId(trustSignId);
            signInfoVO.setIsProxySign(1);
            signInfoVO.setLocation("微金客平台");
            // 这个自定义的，暂时没有业务含义
            signInfoVO.setProjectCode("creditUndertaking_" + productId);
            signInfoVO.setAuthorizationTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
            signInfos[0] = signInfoVO;
        }
        if (TrustsignConstant.BUSINESS_TYPE_INFO_CONSULTATION.equals(signType)) {
            // 签署信息咨询服务协议
            String productId = param.get("productId");
            // 组装签署合同信息
            signInfos = new UploadSignInfoVO[1];
            UploadSignInfoVO signInfoVO = new UploadSignInfoVO();
            SignKeywordVO signKeywordVO = new SignKeywordVO();
            signKeywordVO.setKeyword("甲方（借款人）（签章）");
            signKeywordVO.setOffsetCoordX("35");
            signKeywordVO.setOffsetCoordY("-15");
            signKeywordVO.setImageWidth("105");
            signKeywordVO.setImageHeight("55");
            signInfoVO.setSignKeyword(signKeywordVO);
            signInfoVO.setUserId(trustSignId);
            signInfoVO.setIsProxySign(1);
            signInfoVO.setLocation("微金客平台");
            // 这个自定义的，暂时没有业务含义
            signInfoVO.setProjectCode("infoConsultation_" + productId);
            signInfoVO.setAuthorizationTime(DateHelper.getNoSeparatorYMDHMSFormatDate(new Date()));
            signInfos[0] = signInfoVO;
        }
        return signInfos;
    }

    /**
     * @param tx3203ReqVO
     * @return
     * @description 安心签3203接口调用
     * @version 1.0
     * @author 张可乐
     * @update 2017-4-6 下午3:19:22
     */
    private JSONObject trustSignInterface3203Invoke(Tx3203ReqVO tx3203ReqVO, OutputStream out) {
        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3203ReqVO);

        String txCode = "3203";
        String signature = trustSignInterfaceCallService.p7SignMessageDetach(req);
        // 通过aop保存接口调用参数记录
        String res = trustSignInterfaceCallService.sendRequestIncludeFile(txCode, "platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature, out);
        JSONObject resultJson = JSON.parseObject(res);
        return resultJson;
    }


}