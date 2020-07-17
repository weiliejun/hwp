package com.hwp.common.model.userTransaction.dto;

import com.hwp.common.model.userTransaction.bean.UserTransaction;

/**
 * 用户信息和用户交易记录DTO
 *
 * @author 李洪斌
 * @date 2019/7/24 17:47:28
 */
public class UserAndTransactionDTO extends UserTransaction {
    private String userName;
    private String mobile;

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

}