package com.hwp.common.model.userTransaction.dto;

import java.math.BigDecimal;

/**
 * 用户充值或提现详情记录DTO
 * 此类中封装充值或提现公共字段获取充值或者提现详情时封装成此DTO对象即可
 *
 * @author 李洪斌
 * @date 2019/8/20 13:54:21
 */
public class UserRechargeOrWithdrawDetailDTO {
    /**
     * 交易id 对应交易记录表中的orderId
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 用户电子账户
     */
    private String eaccountNo;

    /**
     * 用户绑定银行卡号
     */
    private String acNo;

    /**
     * 交易金额
     */
    private BigDecimal trsAmount;

    /**
     * 渠道流水对应用户交易记录表中transId
     */
    private String channelJnlNo;

    /**
     * 晋商流水
     */
    private String jsJnlNo;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 交易时间
     */
    private String txnTime;

    /**
     * 交易完成时间
     */
    private String trsEndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEaccountNo() {
        return eaccountNo;
    }

    public void setEaccountNo(String eaccountNo) {
        this.eaccountNo = eaccountNo;
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }

    public BigDecimal getTrsAmount() {
        return trsAmount;
    }

    public void setTrsAmount(BigDecimal trsAmount) {
        this.trsAmount = trsAmount;
    }

    public String getChannelJnlNo() {
        return channelJnlNo;
    }

    public void setChannelJnlNo(String channelJnlNo) {
        this.channelJnlNo = channelJnlNo;
    }

    public String getJsJnlNo() {
        return jsJnlNo;
    }

    public void setJsJnlNo(String jsJnlNo) {
        this.jsJnlNo = jsJnlNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getTrsEndTime() {
        return trsEndTime;
    }

    public void setTrsEndTime(String trsEndTime) {
        this.trsEndTime = trsEndTime;
    }
}
