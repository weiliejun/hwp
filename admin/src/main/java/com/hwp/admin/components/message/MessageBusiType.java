package com.hwp.admin.components.message;

/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2014-4-13
 * @author  zqs
 * ===========================================================================
 *
 */

public enum MessageBusiType {
    register, loginRet, mobileBind, mobileEdit, emailBind, emailEdit, defrayRet, integraConvert, borrowerDebtService, birthday, holiday, inviteCode, integralConvert, callLoan, repaymentWarn, prompt, repaymentFreeze, replaceRepayment, replaceRepaymentBorrower, productWaitAudit, noTenderAudit, failTender, fullTenderAudit, fullTenderAuditAdmin, integraForRecommendation, integraForOpenAccount, recommendFriends, withdrawingCashApply, replaceRepaymentGuarantee, cANotificationForBorrower, cANotificationForThirdCorp, withdrawAudit, integralExchangeGift, autoAttentionProduct, autoInsuranceBookings, merchantAccountRemind, autoRemindLotteryExpired,
    drawingCash("充值成功发送提醒"),
    withdrawingCash("提现申请成功发送提醒"),
    integralAward("获取积分奖励发送提醒"),
    productPublish("新产品发布时发送提醒"),
    productTender("产品募集期开始前【1小时】发送提醒"),
    investment("投标成功，账户资金冻结时发送提醒"),
    unfreezeFund("未满标，账户资金解冻后发送提醒"),
    paymentSuccess("满标放款后，资金划拨至借款方账户时发送提醒"),
    investmentDebtService("已出借项目，收款到账时发送提醒"),
    integralCashReceiving("积分红包到账时发送提醒"),
    sellCredit("债权售出时发送提醒"),
    buyCredit("债权购买成功时发送提醒"),
    creditCancelForPrepayment("债权下架（提前还款）时发送提醒"),
    creditCancelForExpiration("债权下架（到期下架）时发送提醒"),
    gainLetterword("用户获得奖券"),
    uselottery("用户使用奖券"),
    firstInvest("首投送红包"),
    firstInvestJifen("首投送积分"),

    // 注册成功
    registerSuccess("注册成功提醒"),
    // 开户成功
    openAccount("开通资金托管账户成功提醒"),
    // 风险评估
    riskAssessment("风险评估完成提醒"),
    // 修改登录密码
    passwordEdit("修改登录密码成功提醒"),
    // 重置密码
    passwordRes("用户重置密码成功提醒"),

    // 撤标
    tenderCancel("用户撤标成功提醒"),
    // 提前还款
    prepayment("用户提前还款成功提醒"),
    // 奖品发放
    exchangeGiftSend("奖品成功发放提醒");

    // 消息类型描述，用于用户消息设置表
    private String description;

    private MessageBusiType() {
        this.description = "";
    }

    private MessageBusiType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
