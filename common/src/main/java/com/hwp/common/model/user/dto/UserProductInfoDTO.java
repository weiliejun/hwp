package com.hwp.common.model.user.dto;

/**
 * 用户购买的理财产品DTO
 *
 * @author 李洪斌
 * @date 2019-9-20 15:35
 */
public class UserProductInfoDTO {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 投标id
     */
    private String tenderId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 冻结金额
     */
    private String transAmount;
    /**
     * 到期日
     */
    private String repayEndTime;
    /**
     * 预计收益
     */
    private String predictEarning;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getRepayEndTime() {
        return repayEndTime;
    }

    public void setRepayEndTime(String repayEndTime) {
        this.repayEndTime = repayEndTime;
    }

    public String getPredictEarning() {
        return predictEarning;
    }

    public void setPredictEarning(String predictEarning) {
        this.predictEarning = predictEarning;
    }
}
