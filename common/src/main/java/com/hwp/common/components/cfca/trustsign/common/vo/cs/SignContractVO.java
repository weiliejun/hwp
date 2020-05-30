// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignContractVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignInfoVO

public class SignContractVO {

    private String contractNo;
    private SignInfoVO signInfo;

    public SignContractVO() {
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public SignInfoVO getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(SignInfoVO signInfo) {
        this.signInfo = signInfo;
    }

    public String toString() {
        return (new StringBuilder()).append("SignContractVO [\ncontractNo=").append(contractNo).append("\nsignInfo=").append(signInfo).append("\n]").toString();
    }
}
