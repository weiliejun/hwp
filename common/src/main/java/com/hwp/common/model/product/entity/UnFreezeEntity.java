package com.hwp.common.model.product.entity;

import com.hwp.common.thirdparty.util.EecServiceUtil;
import com.hwp.common.util.RandomUtil;

import java.math.BigDecimal;

/**
 * @Description 批量流标，放款列表
 * @Author lvjian
 * @UpdateDate 2019/7/25 17:24
 */
public class UnFreezeEntity {
    private String UnFreezeJnlNo;
    private String FreezeJnlNo;//原冻结流水号
    private String AcNo;//电子账户
    private BigDecimal TrsAmount;//交易金额
    private String productId;
    private String tenderId;

    public UnFreezeEntity() {//自动生成解冻流水号 YC0000000004 渠道名+流水号
        UnFreezeJnlNo = EecServiceUtil.get_ChannelId() + RandomUtil.getSerialNumber();
    }

    public String getUnFreezeJnlNo() {
        return UnFreezeJnlNo;
    }

    public void setUnFreezeJnlNo(String unFreezeJnlNo) {
        UnFreezeJnlNo = unFreezeJnlNo;
    }

    public String getFreezeJnlNo() {
        return FreezeJnlNo;
    }

    public void setFreezeJnlNo(String freezeJnlNo) {
        FreezeJnlNo = freezeJnlNo;
    }

    public String getAcNo() {
        return AcNo;
    }

    public void setAcNo(String acNo) {
        AcNo = acNo;
    }

    public BigDecimal getTrsAmount() {
        return TrsAmount;
    }

    public void setTrsAmount(BigDecimal trsAmount) {
        TrsAmount = trsAmount;
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
}
