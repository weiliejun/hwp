package com.hwp.common.model.product.entity;

import com.hwp.common.thirdparty.util.EecServiceUtil;
import com.hwp.common.util.RandomUtil;

import java.math.BigDecimal;

/**
 * @Description 批量还款实体类
 * @Author lvjian
 * @UpdateDate 2019/7/25 17:20
 */

public class PayEntity {
    private String PayJnlNo;
    private String AcNo;
    private BigDecimal TrsAmount;//还款金额=本金+利息
    private BigDecimal Principal;//还款本金
    private String productId;

    public PayEntity() {
        PayJnlNo = EecServiceUtil.get_ChannelId() + RandomUtil.getSerialNumber();
    }

    public String getPayJnlNo() {
        return PayJnlNo;
    }

    public void setPayJnlNo(String payJnlNo) {
        PayJnlNo = payJnlNo;
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

    public BigDecimal getPrincipal() {
        return Principal;
    }

    public void setPrincipal(BigDecimal principal) {
        Principal = principal;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
