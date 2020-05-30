// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3299ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.EnterpriseVO;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

public class Tx3299ReqVO extends Tx3ReqVO {

    private EnterpriseVO enterprise;
    private String beginIndex;
    private String endIndex;

    public Tx3299ReqVO() {
    }

    public EnterpriseVO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseVO enterprise) {
        this.enterprise = enterprise;
    }

    public String getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(String beginIndex) {
        this.beginIndex = beginIndex;
    }

    public String getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(String endIndex) {
        this.endIndex = endIndex;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3299ReqVO [").append(super.toString()).append("\nenterprise=").append(enterprise).append("\nbeginIndex=").append(beginIndex).append("\nendIndex=").append(endIndex).append("\n]").toString();
    }
}
