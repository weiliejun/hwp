// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3207ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.SignContractByKeywordVO;

public class Tx3207ReqVO extends Tx3ReqVO {

    private SignContractByKeywordVO signContractByKeyword;

    public Tx3207ReqVO() {
    }

    public SignContractByKeywordVO getSignContractByKeyword() {
        return signContractByKeyword;
    }

    public void setSignContractByKeyword(SignContractByKeywordVO signContractByKeyword) {
        this.signContractByKeyword = signContractByKeyword;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3207ReqVO [").append(super.toString()).append("\nsignContractByKeyword=").append(signContractByKeyword).append("\n]").toString();
    }
}
