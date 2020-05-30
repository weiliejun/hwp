package com.hwp.common.model.user.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userName;
    private String nickName;
    private String mobile;
    private String password;
    private String portrait;
    private String authenticationStatus;
    private String terminal;
    private String appkey;
    private String dataStatus;
    private String createTime;
    private String updateTime;
    private String remark;
    private BigDecimal userAccountBalance;
    private String appsecret;
    private String uuid;
    private String referrNo;
    private String idNo;
    private String acNo;
    private String userDuty;
    private String education;
    private String trsPassword;
    private String eaccountTrsPwd;
    private String eaccountNo;
    private String status;
    private String returnCode;
    private String returnMsg;
    private String personCode;
    private BigDecimal integraAmount;
    private String recommendCode;
    private String wxopenid;
    private String trustSignId;
    private String appid;
    private String authorizationQqId;
    private String authorizationWeiboId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus == null ? null : authenticationStatus.trim();
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getUserAccountBalance() {
        return userAccountBalance;
    }

    public void setUserAccountBalance(BigDecimal userAccountBalance) {
        this.userAccountBalance = userAccountBalance;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getReferrNo() {
        return referrNo;
    }

    public void setReferrNo(String referrNo) {
        this.referrNo = referrNo == null ? null : referrNo.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo == null ? null : acNo.trim();
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty == null ? null : userDuty.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getTrsPassword() {
        return trsPassword;
    }

    public void setTrsPassword(String trsPassword) {
        this.trsPassword = trsPassword == null ? null : trsPassword.trim();
    }

    public String getEaccountTrsPwd() {
        return eaccountTrsPwd;
    }

    public void setEaccountTrsPwd(String eaccountTrsPwd) {
        this.eaccountTrsPwd = eaccountTrsPwd == null ? null : eaccountTrsPwd.trim();
    }

    public String getEaccountNo() {
        return eaccountNo;
    }

    public void setEaccountNo(String eaccountNo) {
        this.eaccountNo = eaccountNo == null ? null : eaccountNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public BigDecimal getIntegraAmount() {
        return integraAmount;
    }

    public void setIntegraAmount(BigDecimal integraAmount) {
        this.integraAmount = integraAmount;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid == null ? null : wxopenid.trim();
    }

    public String getTrustSignId() {
        return trustSignId;
    }

    public void setTrustSignId(String trustSignId) {
        this.trustSignId = trustSignId == null ? null : trustSignId.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAuthorizationQqId() {
        return authorizationQqId;
    }

    public void setAuthorizationQqId(String authorizationQqId) {
        this.authorizationQqId = authorizationQqId == null ? null : authorizationQqId.trim();
    }

    public String getAuthorizationWeiboId() {
        return authorizationWeiboId;
    }

    public void setAuthorizationWeiboId(String authorizationWeiboId) {
        this.authorizationWeiboId = authorizationWeiboId == null ? null : authorizationWeiboId.trim();
    }
}