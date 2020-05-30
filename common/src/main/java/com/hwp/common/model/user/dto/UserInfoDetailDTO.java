package com.hwp.common.model.user.dto;

import com.hwp.common.model.prodrevenueStatus.bean.ProdRevenueStatus;
import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.userBalance.bean.UserBalance;
import com.hwp.common.model.userBankCard.bean.UserBankCard;

import java.util.List;

/**
 * 用户详情DTO 包含用户信息 用户绑定银行卡信息 用户持仓信息 用户余额宝信息 用户理财产品列表
 *
 * @author 李洪斌
 * @date 2019-9-12 17:42:32
 */
public class UserInfoDetailDTO {
    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 用户银行卡信息
     */
    private UserBankCard userBankCard;

    /**
     * 用户余额信息
     */
    private UserBalance userBalance;

    /**
     * 基金收益记录
     * 最新收益记录
     */
    private ProdRevenueStatus newProfitInfo;

    /**
     * 理财产品List
     */
    private List<UserProductInfoDTO> productList;

    /**
     * 总收益
     */
    private String totalProfit;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserBankCard getUserBankCard() {
        return userBankCard;
    }

    public void setUserBankCard(UserBankCard userBankCard) {
        this.userBankCard = userBankCard;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(UserBalance userBalance) {
        this.userBalance = userBalance;
    }

    public ProdRevenueStatus getNewProfitInfo() {
        return newProfitInfo;
    }

    public void setNewProfitInfo(ProdRevenueStatus newProfitInfo) {
        this.newProfitInfo = newProfitInfo;
    }

    public List<UserProductInfoDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<UserProductInfoDTO> productList) {
        this.productList = productList;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }
}
