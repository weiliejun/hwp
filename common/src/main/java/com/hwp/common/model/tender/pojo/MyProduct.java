package com.hwp.common.model.tender.pojo;


import java.io.Serializable;
import java.util.Comparator;

/**
 * @Description 产品表，用户表连表查询
 * @Author LVJIAN
 * @UpdateDate 2019/8/8 14:58
 */
public class MyProduct implements Serializable, Comparator<MyProduct> {

    private String userInfoId;//用户Id

    private String productId;//产品Id

    private String name;//产品名称

    private String status;//产品状态

    private String repayStartDate;//计息开始时间

    private String repayEndDate;//计息结束时间

    private String productSum;//用户 产品持仓和

    private String yuJiPoFit;//预计收益

    private String createTime;//第一次产品购买时间

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRepayStartDate() {
        return repayStartDate;
    }

    public void setRepayStartDate(String repayStartDate) {
        this.repayStartDate = repayStartDate;
    }

    public String getRepayEndDate() {
        return repayEndDate;
    }

    public void setRepayEndDate(String repayEndDate) {
        this.repayEndDate = repayEndDate;
    }

    public String getProductSum() {
        return productSum;
    }

    public void setProductSum(String productSum) {
        this.productSum = productSum;
    }

    public String getYuJiPoFit() {
        return yuJiPoFit;
    }

    public void setYuJiPoFit(String yuJiPoFit) {
        this.yuJiPoFit = yuJiPoFit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public int compare(MyProduct o1, MyProduct o2) {
        int flag = o1.getCreateTime().compareTo(o2.getCreateTime());
        return -flag; // 不取反，则按正序排列
    }
}
