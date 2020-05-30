// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignContractByKeywordVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignContractVO, SignKeywordVO

public class SignContractByKeywordVO extends SignContractVO {

    private SignKeywordVO signKeyword;

    public SignContractByKeywordVO() {
    }

    public SignKeywordVO getSignKeyword() {
        return signKeyword;
    }

    public void setSignKeyword(SignKeywordVO signKeyword) {
        this.signKeyword = signKeyword;
    }

    public String toString() {
        return (new StringBuilder()).append("SignContractByKeywordVO [").append(super.toString()).append("\nsignKeyword=").append(signKeyword).append("\n]").toString();
    }
}
