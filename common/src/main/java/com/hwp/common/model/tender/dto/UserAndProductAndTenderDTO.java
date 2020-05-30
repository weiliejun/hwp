package com.hwp.common.model.tender.dto;

import com.hwp.common.model.tender.bean.Tender;

/**
 * 用户信心产品信息产品投标记录DTO
 *
 * @author 李洪斌
 * @date 2019/7/26 15:55:36
 */
public class UserAndProductAndTenderDTO extends Tender {
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
