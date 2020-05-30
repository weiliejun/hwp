// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3201ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;


// Referenced classes of package com.hwp.common.components.com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.ContractVO;

public class Tx3201ResVO extends Tx3ResVO {

    private ContractVO contract;

    public Tx3201ResVO() {
    }

    public ContractVO getContract() {
        return contract;
    }

    public void setContract(ContractVO contract) {
        this.contract = contract;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3201ResVO [").append(super.toString()).append("\ncontract=").append(contract).append("\n]").toString();
    }
}
