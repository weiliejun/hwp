package com.hwp.admin.app.service.productUnFreezePayService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.product.dao.ProductMapper;
import com.hwp.common.model.product.dao.UnFreezePayEntityMapper;
import com.hwp.common.model.product.entity.PayEntity;
import com.hwp.common.model.product.entity.UnFreezeEntity;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.tender.bean.Tender;
import com.hwp.common.model.tender.dao.TenderMapper;
import com.hwp.common.model.tenderLoan.bean.TenderLoan;
import com.hwp.common.model.tenderLoan.dao.TenderLoanMapper;
import com.hwp.common.model.tenderRepayment.bean.TenderRepayment;
import com.hwp.common.model.tenderRepayment.dao.TenderRepaymentMapper;
import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dao.UserInfoDao;
import com.hwp.common.model.userBalance.bean.UserBalance;
import com.hwp.common.model.userBalance.dao.UserBalanceDao;
import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userTransaction.dao.UserTransactionMapper;
import com.hwp.common.model.websiteBulletin.bean.WebsiteBulletin;
import com.hwp.common.model.websiteBulletin.dao.WebsiteBulletinDao;
import com.hwp.common.thirdparty.service.ThirdPartyCallService;
import com.hwp.common.thirdparty.util.EecServiceUtil;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description 流标 放款 还款流程
 * @Author 吕剑
 * @UpdateDate 2019/8/20 16:33
 */
@Service
public class ProductUnFreezePayServiceImpl implements ProductUnFreezePayService {

    @Autowired
    private UnFreezePayEntityMapper unFreezePayEntityMapper;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private TenderMapper tenderMapper;

    @Autowired
    private TenderLoanMapper tenderLoanMapper;

    @Autowired
    private TenderRepaymentMapper tenderRepaymentMapper;

    @Autowired
    private UserTransactionMapper userTransactionMapper;

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Autowired
    private WebsiteBulletinDao websiteBulletinDao;

    @Autowired
    private ThirdPartyCallService thirdPartyCallService;

    //查询待放款，流标列表
    @Override
    public List<UnFreezeEntity> selectUnFreezeByProductId(Map<String, Object> record) {
        return unFreezePayEntityMapper.selectUnFreezeByProductId(record);
    }

    //查询待还款列表
    @Override
    public List<PayEntity> selectPayEntityProductId(Map<String, Object> record) {
        return unFreezePayEntityMapper.selectPayEntityByProductId(record);
    }

    /**
     * @Description 流标流程
     * @Author 吕剑
     * @UpdateDate 2019/8/21 14:33
     */
    @Override
    public int updateUnFreeze(Product productById, SysManager sysManager) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("productId", productById.getId());
            //得到需要解冻的流水号
            List<UnFreezeEntity> unFreezeEntityList = selectUnFreezeByProductId(map);

            //传入参数调取 批量解冻
            Map<String, String> mbankId = new HashMap<String, String>();
            String _ChannelJnlNoBank = RandomUtil.getSerialNumber();
            String BatchCode = RandomUtil.getSerialNumber();
            mbankId.put("BatchCode", EecServiceUtil.get_ChannelId() + BatchCode);//批次号
            mbankId.put("UnFreezeType", "U");//	U:解冻  P:解冻扣款
            mbankId.put("HxAcctNo", productById.getHxAcctNo());
            mbankId.put("TotalAmount", productById.getTenderAmount().toString());//总金额
            mbankId.put("TotalCount", productById.getTenderUsers().toString());//总笔数
            mbankId.put("ProNum", EecServiceUtil.get_ChannelId() + productById.getId());//还款需要上送相同的项目编号 ==渠道名+产品id
            mbankId.put("_ChannelJnlNo", EecServiceUtil.get_ChannelId() + _ChannelJnlNoBank);//流水号
            mbankId.put("service", "unFreezePayBatch");
            //-------------------------
            //将传入的list转换为json
            String UnFreezeString = JSON.toJSONString(unFreezeEntityList);
            mbankId.put("UnFreezeList", UnFreezeString);//传入连表查询某产品的投标记录
            String s = thirdPartyCallService.callJshBankAPI(mbankId);
            //调用晋商银行 根据卡号查询卡号开户行
            Map<String, String> refundInfo = (Map<String, String>) JSONObject.parse(s);
            //查看返回状态 是否成功
            if (!refundInfo.get("ReturnCode").equals("000000")) {
                //            resultMap.put("flag", "false");
                //            resultMap.put("message", "退款失败");
                return 0;
            }
            //----------遍历并修改---所有用户的投标交易状态--------------
            for (int i = 0; i < unFreezeEntityList.size(); i++) {
                //根据电子账户获取用户Id
                UserInfo userInfoByAccountNo = userInfoDao.getUserInfoByAccountNo(unFreezeEntityList.get(i).getAcNo());
                //通过ID修改用户投标交易信息 投标解冻信息
                //----1.------------产品投标交易表-----每笔一条----------------
                Tender tender = new Tender();
                tender.setUserInfoId(userInfoByAccountNo.getId().toString());
                tender.setCancelType("productFail");//投标解冻类型  productFail:未满标解冻
                tender.setCancelTime(DateHelper.getYMDHMSFormatDate(new Date()));
                tender.setCancelRemark("产品流标");
                tender.setCancelRespContent(s);//解冻返回内容串
                tender.setCancelRequestContent(JSON.toJSONString(mbankId));//调用解冻内容串
                tender.setCancelChannelJnlNo(refundInfo.get("ChannelJnlNo"));//解冻渠道流水号
                tender.setJsJnlNo(refundInfo.get("JSJnlNo"));//解冻晋商流水号
                tender.setCancelBatchCode(mbankId.get("BatchCode"));//解冻批次号
                tender.setCancelReturnCode(refundInfo.get("ReturnCode"));
                tender.setCancelReturnMsg(refundInfo.get("ReturnMsg"));
                tender.setStatus("fail");//投标交易状态==fail投标失败
                tenderMapper.updateTenderByUserInfoId(tender);

                //----------------------4.修改余额表-------------------------------------------
                UserBalance userBalanceTml = userBalanceDao.getFindByUserId(userInfoByAccountNo.getId().toString());
                //查询某产品的所有冻结金额
                Tender tender2 = new Tender();
                tender2.setProductId(productById.getId());
                tender2.setUserInfoId(userInfoByAccountNo.getId().toString());
                Tender tender3 = tenderMapper.selectTrsAmountSumByProductIdAndUserId(tender2);
                UserBalance userBalance = new UserBalance();
                userBalance.setId(userBalanceTml.getId());
                //减少冻结总额
                userBalance.setFinaBalances(userBalanceTml.getFinaBalances().subtract(tender3.getTransAmount()));
                //增加实际余额==钱
                userBalance.setLocalDepositBal(userBalanceTml.getLocalDepositBal().add(tender3.getTransAmount()));
                userBalanceDao.updateByPrimaryKeySelective(userBalance);
                //----------------------5.添加交易记录-------------------------------------------
                UserTransaction userTransaction = new UserTransaction();
                userTransaction.setId(RandomUtil.getSerialNumber());
                userTransaction.setUserId(userInfoByAccountNo.getId().toString());
                userTransaction.setUuid(userInfoByAccountNo.getUuid());
                //            userTransaction.setPayNo();//流标暂无付款账户
                userTransaction.setWithdradalNo(userInfoByAccountNo.getEaccountNo());//收款账户-产品表
                userTransaction.setTrsAmount(tender3.getTransAmount());//交易金额--------
                userTransaction.setTrsBalance(userBalance.getLocalDepositBal());//账户余额-----------
                userTransaction.setTradeStatus("0");//交易状态成功
                userTransaction.setRtxnTypeCode("CPJD");
                //暂无交易时间，暂定为当前时间
                userTransaction.setTxnTime(DateHelper.getYMDFormatDate(new Date()));
                userTransaction.setTrsEndTime(DateHelper.getYMDFormatDate(new Date()));
                userTransaction.setRexnTypeName("产品解冻");
                userTransaction.setFlowType("incoming");//资金流向 流入
                userTransaction.setOrderId(refundInfo.get("JSJnlNo"));//投标放款-晋商流水
                userTransaction.setTransId(refundInfo.get("ChannelJnlNo"));//投标交易渠道流水
                userTransaction.setReturnCode(refundInfo.get("ReturnCode"));
                userTransaction.setReturnMsg(refundInfo.get("ReturnMsg"));
                userTransaction.setDataStatus("1");
                userTransaction.setCreateTime(DateHelper.getYMDFormatDate(new Date()));
                userTransactionMapper.addUserTransaction(userTransaction);
            }

            //6.修改产品状态---------------------------------------
            Product product = new Product();
            product.setId(productById.getId());
            product.setStatus("fail");
            productMapper.updateProductById(product);
            //7.添加公告记录----------添加流标公告----------------------------
            WebsiteBulletin websiteBulletin = new WebsiteBulletin();
//        websiteBulletin.setId(Integer.valueOf(RandomUtil.getSerialNumber()));//ID自增
            //添加发布人信息
            websiteBulletin.setPublisherName(sysManager.getName());
            websiteBulletin.setPublisherId(sysManager.getId());
            websiteBulletin.setPublishStatus("0");//默认公告发布状态 发布
            websiteBulletin.setDataStatus("1");
            websiteBulletin.setClicks(0);//浏览次数
            websiteBulletin.setStatus("0");
            websiteBulletin.setType("1");//产品公告
            websiteBulletin.setTopMark("0");//默认置顶
            websiteBulletin.setUpdateTime(DateHelper.strToDate(DateHelper.getYMDHMSFormatDate(new Date())));
            websiteBulletin.setCreateTime(DateHelper.strToDate(DateHelper.getYMDHMSFormatDate(new Date())));
            //2.公告标题  公告内容
            websiteBulletin.setTopic("【" + productById.getName() + "流标】");
            websiteBulletin.setContent(productById.getName() + ":因募集期内未满标，现募集失败已下架，有任何疑问，请联系客服咨询！");
            websiteBulletinDao.addWebsiteBulletin(websiteBulletin);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @Description 放款流程
     * @Author 吕剑
     * @UpdateDate 2019/8/21 16:33
     */
    @Override
    public int updateFreeze(Product productById, SysManager sysManager) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("productId", productById.getId());
            //得到需要解冻的流水号
            List<UnFreezeEntity> unFreezeEntityList = selectUnFreezeByProductId(map);

            //传入参数调取 批量解冻
            Map<String, String> mbankId = new HashMap<String, String>();
            String _ChannelJnlNoBank = RandomUtil.getSerialNumber();
            String BatchCode = RandomUtil.getSerialNumber();
            mbankId.put("BatchCode", EecServiceUtil.get_ChannelId() + BatchCode);//批次号
            mbankId.put("UnFreezeType", "P");//	U:解冻  P:解冻扣款
            mbankId.put("HxAcctNo", productById.getHxAcctNo());
            mbankId.put("TotalAmount", productById.getTenderAmount().toString());//总金额
            mbankId.put("TotalCount", productById.getTenderUsers().toString());//总笔数
            mbankId.put("ProNum", EecServiceUtil.get_ChannelId() + productById.getId());//还款需要上送相同的项目编号 ==渠道名+产品id
            mbankId.put("_ChannelJnlNo", EecServiceUtil.get_ChannelId() + _ChannelJnlNoBank);//流水号
            mbankId.put("service", "unFreezePayBatch");
            //-------------------------
            //将传入的list转换为json
            String UnFreezeString = JSON.toJSONString(unFreezeEntityList);
            mbankId.put("UnFreezeList", UnFreezeString);//传入连表查询某产品的投标记录
            String s = thirdPartyCallService.callJshBankAPI(mbankId);
            //调用晋商银行 根据批量放款
            Map<String, String> refundInfo = (Map<String, String>) JSONObject.parse(s);
            //查看返回状态 是否成功
            if (!refundInfo.get("ReturnCode").equals("000000")) {
                //            resultMap.put("flag", "false");
                //            resultMap.put("message", "放款失败");
                return 0;
            }
            //1.修改产品----------------------------------------------------------------
            Product product = new Product();
            product.setId(productById.getId());
            product.setStatus("repaying");
            //生成计息时间
            product.setRepayStartDate(DateHelper.getYMDFormatDate(new Date()));
            //计息结束时间=计息开始时间+产品期限
            product.setRepayEndDate(DateHelper.addOrSubDays(product.getRepayStartDate(), productById.getTimeLimit().intValue()));
            productMapper.updateProductById(product);

            //----------遍历并修改---所有用户每一笔的投标交易状态-------------------------------------------------------------
            //生成还款批次号
            String rePayBatchCode = EecServiceUtil.get_ChannelId() + RandomUtil.getSerialNumber();
            //------产品总利息
            BigDecimal interestSum = productById.getAmount()
                    .multiply(productById.getAnnualRate())
                    .multiply(productById.getTimeLimit())
                    .divide(new BigDecimal(365));//预计收益=投资本金*[预期年化收益]*产品存续天数/365
            //每个人的利息-----------
            BigDecimal interestUser;
            for (int i = 0; i < unFreezeEntityList.size(); i++) {
                //根据电子账户获取用户Id
                UserInfo userInfoByAccountNo = userInfoDao.getUserInfoByAccountNo(unFreezeEntityList.get(i).getAcNo());
                //通过ID修改用户投标交易信息 投标解冻信息
                //----2.------------产品投标交易表-----每笔一条--------------------------------------------------------
                Tender tender = new Tender();
                tender.setUserInfoId(userInfoByAccountNo.getId().toString());
                tender.setCancelType("productFail");//投标解冻类型  productFail:未满标解冻
                tender.setCancelTime(DateHelper.getYMDHMSFormatDate(new Date()));
                tender.setCancelRemark("产品流标");
                tender.setCancelRespContent(s);//解冻返回内容串
                tender.setCancelRequestContent(JSON.toJSONString(mbankId));//调用解冻内容串
                tender.setCancelChannelJnlNo(refundInfo.get("ChannelJnlNo"));//解冻渠道流水号
                tender.setJsJnlNo(refundInfo.get("JSJnlNo"));//解冻晋商流水号
                tender.setCancelBatchCode(mbankId.get("BatchCode"));//解冻批次号
                tender.setCancelReturnCode(refundInfo.get("ReturnCode"));
                tender.setCancelReturnMsg(refundInfo.get("ReturnMsg"));
                tender.setStatus("repaying");//投标交易状态==fail投标失败
                tenderMapper.updateTenderByUserInfoId(tender);

                //----2.1.------------投标放款表-----每笔一条----------------------------------------------------------------
                //查询 某个产品某个用户的所有投标交易记录
//                Tender tender1=new Tender();
//                tender1.setUserInfoId(userInfoByAccountNo.getId().toString());//用户ID
//                tender1.setProductId(productById.getId());
//                List<Tender> tenders = tenderMapper.selectTenderList(tender1);
//                //遍历添加多笔放款记录
//                for (int j = 0; j < tenders.size(); j++) {
                TenderLoan tenderLoan = new TenderLoan();
                tenderLoan.setId(RandomUtil.getSerialNumber());
                tenderLoan.setTenderId(unFreezeEntityList.get(i).getTenderId());//投标ID
                tenderLoan.setUserInfoId(userInfoByAccountNo.getId().toString());//用户ID
                tenderLoan.setUuid(userInfoByAccountNo.getUuid());
                tenderLoan.setProductId(productById.getId());
                tenderLoan.setProNum(mbankId.get("ProNum"));//项目编号
                tenderLoan.setChannelJnlNo(refundInfo.get("ChannelJnlNo"));
                tenderLoan.setJsJnlNo(refundInfo.get("JSJnlNo"));//晋商流水
                tenderLoan.setBatchCode(mbankId.get("BatchCode"));
                tenderLoan.setHxAcctNo(mbankId.get("HxAcctNo"));//转账账户
                tenderLoan.setUnFreezeList(mbankId.get("UnFreezeList"));//数据循环列表
                tenderLoan.setRequestContent(JSON.toJSONString(mbankId));
                tenderLoan.setRespContent(s);
                tenderLoan.setReturnCode(refundInfo.get("ReturnCode"));
                tenderLoan.setReturnMsg(refundInfo.get("ReturnMsg"));
                tenderLoan.setCreateTime(DateHelper.getYMDEHMFormatDate(new Date()));
                tenderLoan.setCreatorId(sysManager.getId().toString());
                tenderLoan.setCreatorName(sysManager.getName());
                tenderLoan.setRemark(refundInfo.get("ReturnMsg"));
                tenderLoan.setDataStatus("1");
                tenderLoanMapper.addTenderLoan(tenderLoan);
//                }
                //---------------------3.生成还款计划-------还款计划每笔一条-----------------------------------------------------
                TenderRepayment tenderRepayment = new TenderRepayment();
                tenderRepayment.setId(RandomUtil.getSerialNumber());
                tenderRepayment.setTenderId(unFreezeEntityList.get(i).getTenderId());
                tenderRepayment.setUserInfoId(userInfoByAccountNo.getId().toString());
                tenderRepayment.setUuid(userInfoByAccountNo.getUuid());
                tenderRepayment.setProductId(productById.getId());
                tenderRepayment.setProNum(mbankId.get("ProNum"));//项目时间
                //暂无还款类型，渠道流水号，晋商流水号,实际还款时间
                tenderRepayment.setRepayPlanDate(product.getRepayEndDate());//还款计划时间=计息结束时间
                tenderRepayment.setBatchCode(rePayBatchCode);//产品还款批次号
                tenderRepayment.setHxAcctNo(productById.getHxAcctNo());//还款账号 金交所账号
                tenderRepayment.setTotalAmount(unFreezeEntityList.get(i).getTrsAmount());//每笔本金
                tenderRepayment.setTotalCount(new BigDecimal(1));
                PayEntity payEntity = new PayEntity();
                payEntity.setAcNo(unFreezeEntityList.get(i).getAcNo());
                payEntity.setPrincipal(unFreezeEntityList.get(i).getTrsAmount());//还款本金
                interestUser = payEntity.getPrincipal().multiply(productById.getAnnualRate())
                        .multiply(productById.getTimeLimit())
                        .divide(new BigDecimal(365));
                if (i == unFreezeEntityList.size() - 1) {//最后一人
                    //还款金额=本金+剩余利息
                    payEntity.setTrsAmount(interestSum.add(payEntity.getPrincipal()));
                } else {
                    payEntity.setTrsAmount(interestUser.add(payEntity.getPrincipal()));//金额=本金+利息
                }
                interestSum = interestSum.subtract(interestUser);//剩余利息
                //将payEntity转化为Json存起来
                tenderRepayment.setPayList(JSON.toJSONString(payEntity));
                tenderRepayment.setStatus("unRepay");//还款状态 1.unRepay：待还款  2.repayed：已还款   3.cancel已中止（提前还款或债权转让）
                tenderRepayment.setDataStatus("1");
                tenderRepayment.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                //-------------------------------------------4.修改余额表---------------------------------------------------
                UserBalance userBalanceTml = userBalanceDao.getFindByUserId(userInfoByAccountNo.getId().toString());
                //查询某产品的所有冻结金额
                Tender tender2 = new Tender();
                tender2.setProductId(productById.getId());
                tender2.setUserInfoId(userInfoByAccountNo.getId().toString());
                Tender tender3 = tenderMapper.selectTrsAmountSumByProductIdAndUserId(tender2);
                UserBalance userBalance = new UserBalance();
                userBalance.setId(userBalanceTml.getId());
                //减少冻结总额
                userBalance.setFinaBalances(userBalanceTml.getFinaBalances().subtract(tender3.getTransAmount()));
                //增加可用资产
                userBalance.setLocalDepositAvailbal(userBalanceTml.getLocalDepositAvailbal().add(tender3.getTransAmount()));
                userBalanceDao.updateByPrimaryKeySelective(userBalance);
                //----------------------5.添加交易记录-------------------------------------------
                UserTransaction userTransaction = new UserTransaction();
                userTransaction.setId(RandomUtil.getSerialNumber());
                userTransaction.setUserId(userInfoByAccountNo.getId().toString());
                userTransaction.setUuid(userInfoByAccountNo.getUuid());
                userTransaction.setPayNo(userInfoByAccountNo.getEaccountNo());//付款账户-用户
//                userTransaction.setWithdradalNo();//放款无收款账户
                userTransaction.setTrsAmount(tender3.getTransAmount());//交易金额--------
                userTransaction.setTrsBalance(userBalance.getLocalDepositBal());//账户余额-----------
                userTransaction.setTradeStatus("0");//交易状态成功
                userTransaction.setRtxnTypeCode("CPKK");
                //暂无交易时间，暂定为当前时间
                userTransaction.setTxnTime(DateHelper.getYMDFormatDate(new Date()));
                userTransaction.setTrsEndTime(DateHelper.getYMDFormatDate(new Date()));
                userTransaction.setRexnTypeName("产品扣款");
                userTransaction.setFlowType("incoming");//资金流向 流入
                userTransaction.setOrderId(refundInfo.get("JSJnlNo"));//投标放款-晋商流水
                userTransaction.setTransId(refundInfo.get("ChannelJnlNo"));//投标交易渠道流水
                userTransaction.setReturnCode(refundInfo.get("ReturnCode"));
                userTransaction.setReturnMsg(refundInfo.get("ReturnMsg"));
                userTransaction.setDataStatus("1");
                userTransaction.setCreateTime(DateHelper.getYMDFormatDate(new Date()));
                userTransactionMapper.addUserTransaction(userTransaction);
            }


            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @Description 还款流程--按用户还款（暂时不用）
     * @Author 吕剑
     * @UpdateDate 2019/8/22 16:33
     */
    @Override
    public int payEntity(Product product, SysManager sysManager) {
//        //查询产品相关信息
//        Product productInfo = productMapper.getProductById(product.getId());
//        //1.查询产品的每个用户还款本金，电子账户列表--------------------------------------------------
//        Map<String,Object> map=new HashMap<>();
//        map.put("productId",product.getId());
//        List<PayEntity> payEntities = selectPayEntityProductId(map);
//        //-----------------计算利息---------
//        //------总利息
//        BigDecimal interestSum = productInfo.getAmount()
//                .multiply(productInfo.getAnnualRate())
//                .multiply(productInfo.getTimeLimit())
//                .divide(new BigDecimal(365));//预计收益=投资本金*[预期年化收益]*产品存续天数/365
//        //1.1遍历添加---还款金额==还款本金+利息
//        BigDecimal interestUser;
//        for (int i = 0; i < payEntities.size(); i++) {
//             interestUser = payEntities.get(i).getPrincipal().multiply(productInfo.getAnnualRate())
//                    .multiply(productInfo.getTimeLimit())
//                    .divide(new BigDecimal(365));//每个用户的利息
//            if (i==payEntities.size()-1){//最后一人
//                //还款金额=本金+剩余利息
//                payEntities.get(i).setTrsAmount(payEntities.get(i).getPrincipal().add(interestSum));
//            }else {
//                payEntities.get(i).setTrsAmount(payEntities.get(i).getPrincipal().add(interestUser));
//            }
//            interestSum=interestSum.subtract(interestUser);//剩余利息
//        }
//        //2.请求晋商接口--------------------------------------------------------------------------
//        Map<String, String> mbankId = new HashMap<String, String>();
//        String _ChannelJnlNoBank = RandomUtil.getSerialNumber();
//        String BatchCode = RandomUtil.getSerialNumber();
//        mbankId.put("BatchCode", EecServiceUtil.get_ChannelId()+BatchCode);//批次号
//        mbankId.put("HxAcctNo",product.getHxAcctNo());//还款账号
//        mbankId.put("TotalAmount",product.getTenderAmount().toString());//总金额
//        mbankId.put("TotalCount",product.getTenderUsers().toString());//总笔数
//        //根据产品Id查询项目编号------------------
//        TenderLoan tenderLoan=new TenderLoan();
//        tenderLoan.setProductId(product.getId());
//        List<TenderLoan> tenderLoans = tenderLoanMapper.selectTenderLoanList(tenderLoan);
//        //------每个产品只有一个项目编号-------------------
//        mbankId.put("ProNum",tenderLoans.get(0).getProNum());//还款需要上送放款的项目编号 ==渠道名+产品id
//        mbankId.put("_ChannelJnlNo",EecServiceUtil.get_ChannelId()+_ChannelJnlNoBank);//流水号
//        mbankId.put("service","repaymentBatch");
//        //2.1将传入的还款list转换为json
//        String payString = JSON.toJSONString(payEntities);
//        mbankId.put("PayList",payString);//传入连表查询某产品的投标记录
//        String s = thirdPartyCallService.callJshBankAPI(mbankId);
//        //调用晋商银行 根据批量放款
//        Map<String,String> refundInfo=(Map<String,String>) JSONObject.parse(s);
//        //查看返回状态 是否成功
//        if (!refundInfo.get("ReturnCode").equals("000000")){
//            //            resultMap.put("flag", "false");
//            //            resultMap.put("message", "还款失败");
//            return 0;
//        }
//        //3.修改还款计划表
//        for (int i = 0; i < payEntities.size(); i++) {
//        }
        return 0;
    }


    /**
     * @Description 放款已生成每笔还款计划 还款流程--按用户每笔查询求和，按用户还款
     * @Author 吕剑
     * @UpdateDate 2019/8/23 10:33
     */
    @Override
    public int payEntityCount(Product product, SysManager sysManager) {
        try {
            //1.查询还款计划表---------------------------------------------------------------------
            //传入的数据循环列表
            List<PayEntity> payEntityList = new ArrayList<>();
            //查询有几个用户购买了本产品，本金，笔数
            List<TenderRepayment> tenderRepayments = tenderRepaymentMapper.selectUserByProductId(product.getId());
            TenderRepayment tenderRepayment = new TenderRepayment();
            for (int i = 0; i < tenderRepayments.size(); i++) {
                //查询每个用户的购买信息
                tenderRepayment.setProductId(product.getId());
                tenderRepayment.setUserInfoId(tenderRepayments.get(i).getUserInfoId());
                List<TenderRepayment> repaymentList = tenderRepaymentMapper.selectUserInfoByProductIdAndUserId(tenderRepayment);
                PayEntity payEntityOne = new PayEntity();
                ;//每个用户总额
                PayEntity payEntity = null;//每笔
                //遍历用户每笔
                for (int j = 0; j < repaymentList.size(); j++) {
                    //将Json转化为对象
                    payEntity = JSONObject.parseObject(repaymentList.get(j).getPayList(), PayEntity.class);
                    payEntityOne.setPayJnlNo(payEntity.getPayJnlNo());//还款流水
                    payEntityOne.setAcNo(payEntity.getAcNo());//还款流水
                    if (!repaymentList.get(j).getTotalAmount().equals(payEntity.getPrincipal())) {
                        return 3;//返回成为信息，本金出现误差
                    } else {
                        if (j == 0) {
                            payEntityOne.setPrincipal(payEntity.getPrincipal());//初始本金
                            payEntityOne.setTrsAmount(payEntity.getTrsAmount());//初始金额=本金+利息
                        } else {
                            //每笔累加
                            //本金
                            payEntityOne.setPrincipal(payEntityOne.getPrincipal().add(payEntity.getPrincipal()));
                            //金额
                            payEntityOne.setTrsAmount(payEntityOne.getPrincipal().add(payEntity.getTrsAmount()));
                        }
                    }
                }
                payEntityList.add(payEntityOne);
            }

//2.请求晋商还款接口-----------------------------------------------------------------------------------
            Map<String, String> mbankId = new HashMap<String, String>();
            String _ChannelJnlNoBank = RandomUtil.getSerialNumber();
            String BatchCode = RandomUtil.getSerialNumber();
            mbankId.put("BatchCode", EecServiceUtil.get_ChannelId() + BatchCode);//批次号
            mbankId.put("HxAcctNo", product.getHxAcctNo());//还款账号
            mbankId.put("TotalAmount", product.getTenderAmount().toString());//总金额
            mbankId.put("TotalCount", product.getTenderUsers().toString());//总笔数
            //根据产品Id查询项目编号------------------
            TenderLoan tenderLoan = new TenderLoan();
            tenderLoan.setProductId(product.getId());
            List<TenderLoan> tenderLoans = tenderLoanMapper.selectTenderLoanList(tenderLoan);
            //------每个产品只有一个项目编号-------------------
            mbankId.put("ProNum", tenderLoans.get(0).getProNum());//还款需要上送放款的项目编号 ==渠道名+产品id
            mbankId.put("_ChannelJnlNo", EecServiceUtil.get_ChannelId() + _ChannelJnlNoBank);//流水号
            mbankId.put("service", "repaymentBatch");
            //2.1将传入的还款数据循环列表转换为json
            mbankId.put("PayList", JSON.toJSONString(payEntityList));//传入连表查询某产品的投标记录
            String s = thirdPartyCallService.callJshBankAPI(mbankId);
            //调用晋商银行 根据批量放款
            Map<String, String> refundInfo = (Map<String, String>) JSONObject.parse(s);
            //查看返回状态 是否成功
            if (!refundInfo.get("ReturnCode").equals("000000")) {
                //            resultMap.put("flag", "false");
                //            resultMap.put("message", "还款失败");
                return 0;
            }
            //3.1修改还款计划表------------------------------根据产品ID进行修改--------------------------------------------
            TenderRepayment repayment = new TenderRepayment();
            repayment.setProductId(product.getId());
            repayment.setRequestContent(JSON.toJSONString(mbankId));
            repayment.setChannelJnlNo(refundInfo.get("ChannelJnlNo"));
            repayment.setJsJnlNo(refundInfo.get("JSJnlNo"));
            repayment.setReturnCode(refundInfo.get("ReturnCode"));
            repayment.setReturnMsg(refundInfo.get("ReturnMsg"));
            //获取还款当天还款日期
            String today = DateHelper.getYMDFormatDate(new Date());
            repayment.setRepayTime(today);
            //根据还款时间确定还款类型   同时修改还款状态
            Product product1 = new Product();
            Tender tender = new Tender();
            if (today.compareTo(product.getRepayEndDate()) > 0) {
                repayment.setRepayType("overdue");//延期还款
                product1.setStatus("repayed");//产品状态
                tender.setStatus("repayed");//交易状态
            } else if (today.compareTo(product.getRepayEndDate()) == 0) {
                repayment.setRepayType("normal");//正常还款
                product1.setStatus("repayed");
                tender.setStatus("repayed");
            } else {
                repayment.setRepayType("prepay");//提前还款
                product1.setStatus("prepayed");
                tender.setStatus("prepayed ");
                //            repayment.setStatus("cancel");//已提前还款---需从新生成还款计划
            }
            repayment.setStatus("repayed");//已还款
            tenderRepaymentMapper.updateTenderRepaymentByProductId(tenderRepayment);
//3.2修改产品状态----------------------------------------------------
            product1.setId(product.getId());
            productMapper.updateProductById(product1);
//3.3修改产品投标交易记录状态-------------------------------------------
            tender.setProductId(product1.getId());
            tenderMapper.updateTenderByProductId(tender);
//4.遍历修改每个用户的余额表--------------------------------------------------------------------------------------------------------
            UserInfo userInfoByAccountNo;
            UserBalance userBalance;
            for (int i = 0; i < payEntityList.size(); i++) {
                //根据电子账户查询user信息
                userInfoByAccountNo = userInfoDao.getUserInfoByAccountNo(payEntityList.get(i).getAcNo());
                //查询并修改余额表
                userBalance = userBalanceDao.getFindByUserId(userInfoByAccountNo.getId().toString());
                UserBalance newUserBalance = new UserBalance();
                //增加总资产
                newUserBalance.setLocalAvailBalall(userBalance.getLocalAvailBalall().subtract(payEntityList.get(i).getTrsAmount()));
                //增加可用资产
                newUserBalance.setLocalDepositAvailbal(userBalance.getLocalDepositAvailbal().add(payEntityList.get(i).getTrsAmount()));
                //增加实际余额
                newUserBalance.setLocalDepositBal(userBalance.getLocalDepositBal().add(payEntityList.get(i).getTrsAmount()));
                userBalanceDao.updateByPrimaryKeySelective(newUserBalance);
            }

//5.向用户交易记录表添加数据------------------------------------------------------------------------------------------------------------
            //根据产品查询列表
            TenderRepayment tenderRepayment1 = new TenderRepayment();
            List<TenderRepayment> repayments = tenderRepaymentMapper.selectTenderRepaymentList(tenderRepayment1);
            //遍历添加所有交易记录
            for (int i = 0; i < repayments.size(); i++) {
                UserTransaction userTransaction = new UserTransaction();
                userTransaction.setId(RandomUtil.getSerialNumber());
                userTransaction.setUserId(repayments.get(i).getUserInfoId());
                userTransaction.setUuid(repayments.get(i).getUuid());
                userTransaction.setPayNo(repayments.get(i).getHxAcctNo());//付款账户-用户
                //查询用户信息
                UserInfo userInfo = userInfoDao.getUserInfoById(Integer.valueOf(repayments.get(i).getUserInfoId()));
                userTransaction.setWithdradalNo(userInfo.getEaccountNo());//还款无收款账户
                userTransaction.setTrsAmount(JSON.parseObject(repayments.get(i).getPayList(), PayEntity.class).getTrsAmount());//交易金额-----还款本+息
                //            userTransaction.setTrsBalance();//暂不计算账户余额-----------
                userTransaction.setTradeStatus("0");//交易状态成功
                userTransaction.setRtxnTypeCode("CPHK");
                //暂无交易时间，暂定为当前时间
                userTransaction.setTxnTime(DateHelper.getYMDFormatDate(new Date()));
                userTransaction.setTrsEndTime(DateHelper.getYMDFormatDate(new Date()));
                userTransaction.setRexnTypeName("产品还款");
                userTransaction.setFlowType("incoming");//资金流向 流入
                userTransaction.setOrderId(refundInfo.get("JSJnlNo"));//投标放款-晋商流水
                userTransaction.setTransId(refundInfo.get("ChannelJnlNo"));//投标交易渠道流水
                userTransaction.setReturnCode(refundInfo.get("ReturnCode"));
                userTransaction.setReturnMsg(refundInfo.get("ReturnMsg"));
                userTransaction.setDataStatus("1");
                userTransaction.setCreateTime(DateHelper.getYMDFormatDate(new Date()));
                userTransactionMapper.addUserTransaction(userTransaction);
            }
            return 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
