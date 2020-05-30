// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetContractSignatureAttrVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignInfoVO, SignLocationVO

public class GetContractSignatureAttrVO {

    private String contractNo;
    private String signatureAttr;
    private SignInfoVO signInfo;
    private SignLocationVO signLocation;

    public GetContractSignatureAttrVO() {
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getSignatureAttr() {
        return signatureAttr;
    }

    public void setSignatureAttr(String signatureAttr) {
        this.signatureAttr = signatureAttr;
    }

    public SignInfoVO getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(SignInfoVO signInfo) {
        this.signInfo = signInfo;
    }

    public SignLocationVO getSignLocation() {
        return signLocation;
    }

    public void setSignLocation(SignLocationVO signLocation) {
        this.signLocation = signLocation;
    }

    public String toString() {
        return (new StringBuilder()).append("GetContractSignatureAttrVO [\ncontractNo=").append(contractNo).append("\nsignatureAttr=").append(signatureAttr).append("\nsignInfo=").append(signInfo).append("\n]").toString();
    }
}
