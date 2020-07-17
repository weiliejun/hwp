package com.hwp.common.model.userTransaction.dto;

import com.hwp.common.model.userTransaction.bean.UserTransaction;

/**
 * 用户信息和基金信息和用户交易记录DTO
 *
 * @author 李洪斌
 * @date 2019/7/25 17:47:28
 */
public class UserAndFundAndTransactionDTO extends UserTransaction {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 基金代码
     */
    private String prodSubId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProdSubId() {
        return prodSubId;
    }

    public void setProdSubId(String prodSubId) {
        this.prodSubId = prodSubId;
    }
}
