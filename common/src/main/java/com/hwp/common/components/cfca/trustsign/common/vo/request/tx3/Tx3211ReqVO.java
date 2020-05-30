// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3211ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

public class Tx3211ReqVO extends Tx3ReqVO {

    private String batchNo;

    public Tx3211ReqVO() {
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3211ReqVO [").append(super.toString()).append("\nbatchNo=").append(batchNo).append("\n]").toString();
    }
}
