// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3201ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CreateContractVO;

public class Tx3201ReqVO extends Tx3ReqVO {

    private CreateContractVO createContract;

    public Tx3201ReqVO() {
    }

    public CreateContractVO getCreateContract() {
        return createContract;
    }

    public void setCreateContract(CreateContractVO createContract) {
        this.createContract = createContract;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3201ReqVO [").append(super.toString()).append("\ncreateContract=").append(createContract).append("\n]").toString();
    }
}
