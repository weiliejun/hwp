package com.hwp.admin.components.message;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 张可乐
 * @version 1.0
 * @description 微信模板消息模型
 * @update 2017-1-16 下午4:28:05
 */
public class TemplateMessage {
    // 标题颜色
    public static final String TOPCOLOR = "#FF0000";
    // 关键词颜色
    public static final String COLOR = "#173177";
    // 详情urlMap
    public static final Map<String, String> urlMap = new HashMap<String, String>();
    // 模板消息templateIdMap
    public static final Map<String, String> templateIdMap = new HashMap<String, String>();

    static {

        /**
         *  测试模板
         */
        // 出借结果通知模板id
        urlMap.put(MessageBusiType.investment.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.investment.name(), "S4E3u4Ox1FLowmnLPy63SBXeuXTV-VR7q_iYHACo65o");
        // 充值通知模板id
        urlMap.put(MessageBusiType.drawingCash.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.drawingCash.name(), "dUPk4bvkSuNth1ReRF1usgaL-1fBRKWU0J0eaUdHeKA");
        // 提现通知模板id
        urlMap.put(MessageBusiType.withdrawingCash.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.withdrawingCash.name(), "tK184xX6Fb0zvqxHwi9QDIibm1niZBLKpe0XC9faMew");
        // 债权转让通知模板id
        urlMap.put(MessageBusiType.sellCredit.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.sellCredit.name(), "IiXnv5qA94ck0VCcaUdXW4QNkrpWjfrfTJarFwQ_gQE");
        // 密码重置通知模板id
        urlMap.put(MessageBusiType.passwordRes.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.passwordRes.name(), "fI9XAbZhOpJo3m7aPZSvPpmWj3tunItu--l8Gdo4754");
        // 修改密码通知模板id
        urlMap.put(MessageBusiType.passwordEdit.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.passwordEdit.name(), "5x3r8OeynTMW1iBxgRcHkcqmLwkCgNd2pNj25KISVxU");
        //账户开通通知模板id
        urlMap.put(MessageBusiType.openAccount.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.openAccount.name(), "oI7KE-idjS8khxFnudstVj-bWsoXpXu8jqft5wJmtoE");
        //奖品兑换通知模板id
        urlMap.put(MessageBusiType.integralExchangeGift.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.integralExchangeGift.name(), "v3GXsFqwljfm58isIR7vlkZf3UZXVmIj9ubVzVhSgGc");

        // 满标放款通知模板id
        urlMap.put(MessageBusiType.paymentSuccess.name(), "");
        templateIdMap.put(MessageBusiType.paymentSuccess.name(), "e4OYKK7AWNxEq211ytDyuD6twGHmudPbN63ftw77j1A");
        //新标发布通知模板的id
        urlMap.put(MessageBusiType.productPublish.name(), "https://m.vjinke.com//product/index");
        templateIdMap.put(MessageBusiType.productPublish.name(), "YMQpCWH9XWAStk5v87EN1PR_fwq2RetgZi0Gx7OEL1o");

        /**
         *  正式模板
         */
		/*// 出借结果通知模板id
		urlMap.put(MessageBusiType.investment.name(), "https://m.vjinke.com/account/index");
		templateIdMap.put(MessageBusiType.investment.name(), "r-FWIEwF-Ua0lpFDVx9bKzVWmuleuyFs-SCNFhySe1w");
        // 充值通知模板id
        urlMap.put(MessageBusiType.drawingCash.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.drawingCash.name(), "_aiUaLFAQiD-y5AQF4BaRNwVTNCHHMNextoGzviq-eY");
        // 提现通知模板id
        urlMap.put(MessageBusiType.withdrawingCash.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.withdrawingCash.name(), "hMBybi3mYbhN2GA8XLnubEseqAyYDC1hSVpv250RB64");
        // 债权转让通知模板id
        urlMap.put(MessageBusiType.sellCredit.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.sellCredit.name(), "06lZUrWHCvx3382MDqkhUq7UMq53Zpm9aW_O_jBPNBs");
        // 撤标提醒通知模板id
        urlMap.put(MessageBusiType.revocationTender.name(), "https://m.vjinke.com/");
        templateIdMap.put(MessageBusiType.revocationTender.name(), "EfP4Nwn3AucEszMCyqMNOM_DL4KuCHFsZvuDcTubMk4");
        // 修改手机号通知模板id
        urlMap.put(MessageBusiType.mobileEditConfirm.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.mobileEditConfirm.name(), "VecS6Wqq0pAKg-HbOey_oEfIClhOHfUkQ-awbpqfJUU");
        // 密码重置通知模板id
        urlMap.put(MessageBusiType.passwordRes.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.passwordRes.name(), "WOQhiivAaG2aGUIbw6LiXeGa5yPLkh6ivoXqTX3241I");
        // 修改密码通知模板id
        urlMap.put(MessageBusiType.passwordEdit.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.passwordEdit.name(), "a9r2Kq9I7fpEEhNZgTR7fvL0LqGK54PCCOGcd6cw8r8");
        //账户开通通知模板id
        urlMap.put(MessageBusiType.openAccount.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.openAccount.name(), "wDdsFwif2e9rSlBOiWrcZGt0Fr-w-yvBYFW80HVeBOA");
        //奖品兑换通知模板id
        urlMap.put(MessageBusiType.integralExchangeGift.name(), "https://m.vjinke.com/account/index");
        templateIdMap.put(MessageBusiType.integralExchangeGift.name(), "v3GXsFqwljfm58isIR7vlkZf3UZXVmIj9ubVzVhSgGc");

        // 满标放款通知模板id
        urlMap.put(MessageBusiType.paymentSuccess.name(), "");
        templateIdMap.put(MessageBusiType.paymentSuccess.name(), "WLJyLpwMWFkBbP7UN1FAshnrjsQU6El_q3DD31zlA1A");
        // 项目流标通知模板id
        urlMap.put(MessageBusiType.failTenderToInvestor.name(), "");
        templateIdMap.put(MessageBusiType.failTenderToInvestor.name(), "dTRDAeV592k6JDeHP1CdYa2eCxhOhIo-YIO5-eI23no");
        //提前回款到账通知模板id
        urlMap.put(MessageBusiType.investmentDebtService.name(), "");
        templateIdMap.put(MessageBusiType.investmentDebtService.name(), "I1AYKmaMgYufuIHw5Ha7qQPrMWql56YMdg4hC1657GY");
        //正常回款到账通知模板id
        urlMap.put(MessageBusiType.investmentDebtServiceNormal.name(), "");
        templateIdMap.put(MessageBusiType.investmentDebtServiceNormal.name(), "I1AYKmaMgYufuIHw5Ha7qQPrMWql56YMdg4hC1657GY");
        //新标发布通知模板的id
        urlMap.put(MessageBusiType.productPublish.name(), "https://m.vjinke.com//product/index");
        templateIdMap.put(MessageBusiType.productPublish.name(), "tkOnAxEicmDw9BR8yONxBO8XG4bQ6h1Ruj0X3UaGFKA");*/
    }

    // 用户openId
    private String touser;
    // 模板数据
    private Map<String, String> data;
    // 通知类型
    private String notifyType;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * @return
     * @description 对象转换成模板消息json字符串
     * @version 1.0
     * @author 张可乐
     * @update 2017-1-16 下午4:37:45
     */
    public String toJsonString() {
        JSONObject json = new JSONObject();
        json.put("touser", getTouser());
        json.put("template_id", templateIdMap.get(getNotifyType()));
        json.put("url", urlMap.get(getNotifyType()));
        json.put("topcolor", TOPCOLOR);
        JSONObject dataJson = new JSONObject();
        Map<String, String> dataMap = getData();
        for (Entry<String, String> entry : dataMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            JSONObject jsonTemp = new JSONObject();
            jsonTemp.put("value", value);
            jsonTemp.put("color", COLOR);
            dataJson.put(key, jsonTemp);
        }
        json.put("data", dataJson);
        return json.toJSONString();
    }

    public static class TemplateTitleConstant {

        // 微信业务命名
        public static final String DRAWING_CASH_FIRST = "尊敬的用户，充值金额已成功到账";

        public static final String WITHDRAWING_CASH_FIRST = "尊敬的用户，提现申请已提交";

        public static final String OPEN_ACCOUNT_FIRST = "尊敬的用户，恭喜您银行存管账户开通成功";

        public static final String EDIT_PASSWORD_FIRST = "尊敬的用户，登录密码修改成功";

        public static final String EDIT_MOBILE_FIRST = "尊敬的用户，手机号码修改成功";

        public static final String REVOCATION_TENDER_FIRST = "尊敬的用户，很抱歉，您出借的项目抢标失败，资金已返还至您的账户";

        public static final String FAIL_TENDER = "尊敬的用户，很抱歉，您出借的项目已流标，资金已返还至您的账户";

        public static final String CREDITASSIGNMENT_SELL_FIRST = "尊敬的用户，您发布的债权已转让成功，资金已入账到您的账户";

        public static final String RESET_PASSWORD_FIRST = "尊敬的用户，登录密码重置成功。";

        public static final String INTEGRAL_EXCHANGE_GIFT_FIRST = "尊敬的用户，您参加了积分换好礼活动";

        public static final String RECEIVABLE_ACCOUNT_NORMAL_FIRST = "尊敬的用户，您有一笔回款已到账";

        public static final String PAYMENT_SUCCESS = "尊敬的用户，您出借的项目已满标";

        public static final String INVESTMENT_SUCCESS = "尊敬的用户，您已成功出借";
    }

}
