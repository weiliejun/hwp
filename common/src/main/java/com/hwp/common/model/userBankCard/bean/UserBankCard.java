package com.hwp.common.model.userBankCard.bean;

import java.io.Serializable;

/**
 * 用户银行卡
 */
public class UserBankCard implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String uuid;
    private String acNo;
    private String acUserName;
    private String acIdNo;
    private String mobilePhone;
    private String bindTime;
    private String unbindTime;
    private String isBindCard;
    private String bankName;
    private String bankId;
    private String bankInner;
    private String cardLen;
    private String cardType;
    private String limitAmount;
    private String limitAmountPerDay;
    private String signState;
    private String pin;
    private String returnCode;
    private String returnMsg;
    private String dataStatus;
    private String createTime;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo == null ? null : acNo.trim();
    }

    public String getAcUserName() {
        return acUserName;
    }

    public void setAcUserName(String acUserName) {
        this.acUserName = acUserName == null ? null : acUserName.trim();
    }

    public String getAcIdNo() {
        return acIdNo;
    }

    public void setAcIdNo(String acIdNo) {
        this.acIdNo = acIdNo == null ? null : acIdNo.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime == null ? null : bindTime.trim();
    }

    public String getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(String unbindTime) {
        this.unbindTime = unbindTime == null ? null : unbindTime.trim();
    }

    public String getIsBindCard() {
        return isBindCard;
    }

    public void setIsBindCard(String isBindCard) {
        this.isBindCard = isBindCard == null ? null : isBindCard.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getBankInner() {
        return bankInner;
    }

    public void setBankInner(String bankInner) {
        this.bankInner = bankInner == null ? null : bankInner.trim();
    }

    public String getCardLen() {
        return cardLen;
    }

    public void setCardLen(String cardLen) {
        this.cardLen = cardLen == null ? null : cardLen.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(String limitAmount) {
        this.limitAmount = limitAmount == null ? null : limitAmount.trim();
    }

    public String getLimitAmountPerDay() {
        return limitAmountPerDay;
    }

    public void setLimitAmountPerDay(String limitAmountPerDay) {
        this.limitAmountPerDay = limitAmountPerDay == null ? null : limitAmountPerDay.trim();
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState == null ? null : signState.trim();
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin == null ? null : pin.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}