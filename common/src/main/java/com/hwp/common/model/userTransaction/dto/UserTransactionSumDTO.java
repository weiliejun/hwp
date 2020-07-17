package com.hwp.common.model.userTransaction.dto;

/**
 * 用户联名卡转入转出 基金申购赎回 理财产品 交易总金额 结果统计DTO
 *
 * @author 李洪斌
 * @date 2019/8/5 11:20:25
 */
public class UserTransactionSumDTO {
    /**
     * 联名卡转出总额
     */
    private String bankCardOut;

    /**
     * 联名卡转入总额
     */
    private String bankCardIn;

    /**
     * 基金申购总额
     */
    private String fundBuy;

    /**
     * 基金赎回总额
     */
    private String fundSell;

    /**
     * 产品满标-冻结总额
     */
    private String productBuyYFreeze;

    /**
     * 产品流标-解冻总额
     */
    private String productBuyNUnfreeze;

    /**
     * 产品满标-扣款总额
     */
    private String productBuyYUnfreeze;

    /**
     * 产品回款总额
     */
    private String productBuyYBack;

    public String getBankCardOut() {
        return bankCardOut;
    }

    public void setBankCardOut(String bankCardOut) {
        this.bankCardOut = bankCardOut;
    }

    public String getBankCardIn() {
        return bankCardIn;
    }

    public void setBankCardIn(String bankCardIn) {
        this.bankCardIn = bankCardIn;
    }

    public String getFundBuy() {
        return fundBuy;
    }

    public void setFundBuy(String fundBuy) {
        this.fundBuy = fundBuy;
    }

    public String getFundSell() {
        return fundSell;
    }

    public void setFundSell(String fundSell) {
        this.fundSell = fundSell;
    }

    public String getProductBuyYFreeze() {
        return productBuyYFreeze;
    }

    public void setProductBuyYFreeze(String productBuyYFreeze) {
        this.productBuyYFreeze = productBuyYFreeze;
    }

    public String getProductBuyNUnfreeze() {
        return productBuyNUnfreeze;
    }

    public void setProductBuyNUnfreeze(String productBuyNUnfreeze) {
        this.productBuyNUnfreeze = productBuyNUnfreeze;
    }

    public String getProductBuyYUnfreeze() {
        return productBuyYUnfreeze;
    }

    public void setProductBuyYUnfreeze(String productBuyYUnfreeze) {
        this.productBuyYUnfreeze = productBuyYUnfreeze;
    }

    public String getProductBuyYBack() {
        return productBuyYBack;
    }

    public void setProductBuyYBack(String productBuyYBack) {
        this.productBuyYBack = productBuyYBack;
    }
}
