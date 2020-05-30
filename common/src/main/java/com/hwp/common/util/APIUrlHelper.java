package com.hwp.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 服务apiUrl辅助类
 */
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
@Component
public class APIUrlHelper {

    private static final String eec_URL = "https://zd.cf360.com";

    private static String jshBank_base_URL;

    public static String getJshBank_base_URL() {
        return jshBank_base_URL;
    }

    @Value("${jshBank.baseUrl}")
    public void setJshBank_base_URL(String jshBank_base_URL) {
        APIUrlHelper.jshBank_base_URL = jshBank_base_URL;
    }

    /**
     * 根据接口名称获取请求url
     */
    public static String getRequestUrl(APIName apiName) {
        return apiName.getHostUrl() + apiName.getApiCallUrl();
    }

    public static enum APIName {
        //众大技术服务【全国天气预报查询】
        eecWeatherQuery("weatherQuery", eec_URL, "/hwp-services/WeatherQuery"),
        //众大技术服务【手机号码归属地查询
        eecMobileNumberPlaceQuery("mobileNumberPlaceQuery", eec_URL, "/hwp-services/MobileNumberPlaceQuery"),
        //众大技术服务【全球 IP 地址查询】
        eecIpPlaceQuery("ipPlaceQuery", eec_URL, "/hwp-services/IpPlaceQuery"),
        //众大技术服务【机动车驾考公开题库查询】
        eecDrivingQuestionsQuery("drivingQuestionsQuery", eec_URL, "/hwp-services/DrivingQuestionsQuery"),
        //众大技术服务【全球快递物流查询】
        eecExpressDeliveryQuery("expressDeliveryQuery", eec_URL, "/hwp-services/ExpressDeliveryQuery"),
        //众大技术服务【全国身份证实名认证】
        eecIdentityCardIdentify("identityCardIdentify", eec_URL, "/hwp-services/IdentityCardIdentify"),
        //众大技术服务【全国银行卡二、三、四要素实名认证】
        eecBankCardIdentify("bankCardIdentify", eec_URL, "/hwp-services/BankCardIdentify"),
        //众大技术服务【全国手机号（三网）实名认证】
        eecMobileNumberIdentify("mobileNumberIdentify", eec_URL, "/hwp-services/MobileNumberIdentify"),
        //众大技术服务【云短信-发送手机验证码短消息】
        eecSmsCodeSend("smsCodeSend", eec_URL, "/mbiger-services/SmsCodeSend"),
        //众大技术服务【人工智能-智能问答助手】
        eecIntelligentAssistantQA("intelligentAssistantQA", eec_URL, "/hwp-services/IntelligentAssistantQA"),

        //=========以下为晋商提供的接口地址===========

        //发送短信验证码
        jinshangGenPhoneToken("genPhoneToken", jshBank_base_URL, "/GenPhoneTokenForChannel.do"),
        //注册交易接口
        jinshangRegisterCifInfo("registerCifInfo", jshBank_base_URL, "/RegisterCifInfoForOut.do"),
        //账户认证接口
        jinshangAcctVerify("acctVerify", jshBank_base_URL, "/AcctVerifyForOut.do"),
        //变更绑定银行卡交易接口
        jinshangRelaAcct("relaAcct", jshBank_base_URL, "/RelaAcctForOut.do"),
        //充值交易接口
        jinshangTransferIn("transferIn", jshBank_base_URL, "/TransferInForOut.do"),
        //提现交易接口
        jinshangTransferOut("transferOut", jshBank_base_URL, "/TransferOutForOut.do"),
        //交易状态查询接口
        jinshangTransferState("transferState", jshBank_base_URL, "/TransferStateForOut.do"),
        //交易明细查询接口
        jinshangTransListQuery("transListQuery", jshBank_base_URL, "/TransListQueryForOut.do"),
        //余额查询接口
        jinshangEAcctIntelQry("eAcctIntelQry", jshBank_base_URL, "/EAcctIntelQryForOut.do"),
        //风险测评结果查询接口
        jinshangRiskLevQuery("riskLevQuery", jshBank_base_URL, "/RiskLevQueryForChannel.do"),
        //风险测评接口
        jinshangRiskLevCal("riskLevCal", jshBank_base_URL, "/RiskLevCalForChannel.do"),
        //根据卡号查询卡号开户行
        jinshangQueryBankInfo("queryBankInfo", jshBank_base_URL, "/QueryBankInfoForChannel.do"),
        //根据UUID查询用户卡号和电子账户
        jinshangQueryUserAcInfo("queryUserAcInfo", jshBank_base_URL, "/QueryUserAcInfoForOut.do"),
        //查询服务协议
        jinshangInvestAgreement("investAgreement", jshBank_base_URL, "/InvestAgreementForChannel.do"),
        //充值协议查询接口
        jinshangTransferInPre("transferInPre", jshBank_base_URL, "/TransferInPreForOut.do"),
        //充值协议验证码获取接口
        jinshangSignProtocolPre("signProtocolPre", jshBank_base_URL, "/SignProtocolPreForOut.do"),
        //充值协议签约接口
        jinshangSignProtocol("signProtocol", jshBank_base_URL, "/SignProtocolForOut.do"),
        //变更手机号交易接口
        jinshangMobileModify("mobileModify", jshBank_base_URL, "/MobileModifyForOut.do"),
        //基金列表
        jinshangFundListQry("fundListQry", jshBank_base_URL, "/FundListQryForOut.do"),
        //基金状态查询接口
        jinshangFundSignQry("fundSignQry", jshBank_base_URL, "/FundSignQryForOut.do"),
        //基金签约接口
        jinshangFundSign("fundSign", jshBank_base_URL, "/FundSignForOut.do"),
        //基金申购接口
        jinshangFundBuy("fundBuy", jshBank_base_URL, "/FundBuyForOut.do"),
        //基金赎回接口
        jinshangFundRedeem("fundRedeem", jshBank_base_URL, "/FundRedeemForOut.do"),
        //基金交易明细接口
        jinshangFundTrsDetailQry("fundTrsDetailQry", jshBank_base_URL, "/FundTrsDetailQryForOut.do"),
        //基金收益查询接口
        jinshangFundProfitList("fundProfitList", jshBank_base_URL, "/FundProfitListForOut.do"),
        //基金历史收益率查询接口
        jinshangFundHistRateQry("fundHistRateQry", jshBank_base_URL, "/FundHistRateQryForOut.do"),
        //基金交易状态查询接口
        jinshangFundTrsState("fundTrsState", jshBank_base_URL, "/FundTrsStateForOut.do"),

        //========二期开发=============

        //单笔冻结
        jinshangFreezePay("freezePay", jshBank_base_URL, "/FreezePayForOut.do"),
        //单笔解冻
        jinshangUnFreezePay("unFreezePay", jshBank_base_URL, "/UnFreezePayForOut.do"),
        //批量冻结
        jinshangFreezePayBatch("freezePayBatch", jshBank_base_URL, "/FreezePayBatchForOut.do"),
        //批量解冻/批量解冻扣款
        jinshangUnFreezePayBatch("unFreezePayBatch", jshBank_base_URL, "/UnFreezePayBatchForOut.do"),
        //批量还款
        jinshangRepaymentBatch("repaymentBatch", jshBank_base_URL, "/RepaymentBatchForOut.do"),
        //批量结果查询
        jinshangBatchQry("batchQry", jshBank_base_URL, "/BatchQryForOut.do");

        /**
         * 接口名称
         */
        private String service;
        /**
         * 主路径
         */
        private String hostUrl;
        /**
         * 接口请求地址
         */
        private String apiCallUrl;

        private APIName(String service, String hostUrl, String apiCallUrl) {

            this.service = service;
            this.hostUrl = hostUrl;
            this.apiCallUrl = apiCallUrl;
        }

        /**
         * 根据接口名称获取接口枚举
         */
        public static APIName getAPIName(String service) {
            for (APIName api : APIName.values()) {
                if (api.getService().equals(service)) {
                    return api;
                }
            }
            return null;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getHostUrl() {
            return hostUrl;
        }

        public void setHostUrl(String hostUrl) {
            this.hostUrl = hostUrl;
        }

        public String getApiCallUrl() {
            return apiCallUrl;
        }

        public void setApiCallUrl(String apiCallUrl) {
            this.apiCallUrl = apiCallUrl;
        }
    }

}
