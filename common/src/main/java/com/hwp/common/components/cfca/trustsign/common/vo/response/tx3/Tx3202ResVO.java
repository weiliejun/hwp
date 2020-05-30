// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3202ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CreateContractVO;

import java.util.Arrays;

// Referenced classes of package com.hwp.common.components.com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

public class Tx3202ResVO extends Tx3ResVO {

    private String batchNo;
    private CreateContractVO[] createContracts;

    public Tx3202ResVO() {
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public CreateContractVO[] getCreateContracts() {
        return createContracts;
    }

    public void setCreateContracts(CreateContractVO[] createContracts) {
        this.createContracts = createContracts;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3202ResVO [").append(super.toString()).append("\nbatchNo=").append(batchNo).append("\ncreateContracts=").append(Arrays.toString(createContracts)).append("\n]").toString();
    }
}
