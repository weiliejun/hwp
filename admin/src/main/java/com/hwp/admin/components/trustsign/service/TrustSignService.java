package com.hwp.admin.components.trustsign.service;

import com.alibaba.fastjson.JSONObject;
import com.hwp.common.model.cfcaContractnoRecord.bean.CfcaContractnoRecord;
import com.hwp.common.model.product.bean.Product;

import java.io.OutputStream;
import java.util.Map;

/**
 * @author 张可乐
 * @version 1.0
 * @description 安心签接口
 * @update 2017-3-15 下午2:18:58
 */
public interface TrustSignService {
    /**
     * @param json
     * @return
     * @description 个人开户
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-15 下午3:30:38
     */
    JSONObject registerPerson(JSONObject json);

    /**
     * @param json
     * @return
     * @description 企业开户
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-15 下午3:31:23
     */
    JSONObject registerEnterprise(JSONObject json);

    /**
     * @param json
     * @return
     * @description 上传合同签署
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-15 下午3:33:04
     */
    JSONObject iuploadContract(JSONObject json, OutputStream out);

    /**
     * @param
     * @return
     * @description 合同下载
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-15 下午3:35:12
     */

    byte[] downLoadContract(String contractNo);

    /**
     * @param
     * @description 平台根据产品向安心签注册出借人
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-27 下午1:50:24
     */
    void trustSignRegister(Product product);

    /**
     * @param
     * @description 平台根据产品签署合同
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-27 下午1:51:07
     */
    JSONObject trustSignContractSign(Product product);

    /**
     * @param product
     * @description 一键完成注册和签署合同
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-27 下午2:03:16
     */
    Map<String, String> trustSignRegisterAndSign(Product product);

    /**
     * @param userId
     * @return
     * @description 签署个人信用查询授权书，一个产品签署一次
     * @version 1.0
     * @author 张可乐
     * @update 2017-4-6 下午3:01:17
     */
    Map<String, String> personCreditQueryAuthorization(String userId, String productId);

    /**
     * @param businessId
     * @return
     * @description 根据业务类型和id查询签署合同记录
     * @version 1.0
     * @author 张可乐
     * @update 2017-4-6 下午3:01:17
     */
    CfcaContractnoRecord findCfcaContractnoRecordBybusinessIdAndBusinessType(String businessId, String businessType);

    /**
     * @param
     * @return
     * @description 根据产品id统计CFCA记录
     * @version 1.0
     * @author 张可乐
     * @update 2017-4-6 下午3:01:17
     */
    Map findProductCFCARecordsCount(String productId);


    /**
     * @param params
     * @return
     * @description 产品需要额外签署的合同，一个产品签署一次
     * @version 1.0
     * @author 徐赛平
     * @update 2018-9-21 下午3:01:17
     */
    Map<String, String> extraProductAssignmentAuthorization(Map<String, String> params);

    /**
     * @param product
     * @description 一个出借人对应一个合同签署
     * @version 1.0
     * @author 徐赛平
     * @update 2017-3-27 下午1:51:07
     */
    JSONObject trustSignContractSignOne(Product product);

}