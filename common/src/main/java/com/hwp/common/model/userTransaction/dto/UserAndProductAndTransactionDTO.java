package com.hwp.common.model.userTransaction.dto;

import com.hwp.common.model.userTransaction.bean.UserTransaction;

/**
 * 用户信息理财产品信息产品交易记录DTO
 *
 * @author 李洪斌
 * @date 2019/8/2 9:55:26
 */
public class UserAndProductAndTransactionDTO extends UserTransaction {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 基金代码
     */
    private String productCode;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
