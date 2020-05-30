// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UploadContractVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

import java.util.Arrays;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignKeywordVO, SignLocationVO, UploadSignInfoVO

public class UploadContractVO {

    private Integer isSign;
    private String contractTypeCode;
    private String contractName;
    private SignKeywordVO signKeyword;
    private SignLocationVO[] signLocations;
    private UploadSignInfoVO[] signInfos;

    public UploadContractVO() {
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public String getContractTypeCode() {
        return contractTypeCode;
    }

    public void setContractTypeCode(String contractTypeCode) {
        this.contractTypeCode = contractTypeCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public SignKeywordVO getSignKeyword() {
        return signKeyword;
    }

    public void setSignKeyword(SignKeywordVO signKeyword) {
        this.signKeyword = signKeyword;
    }

    public SignLocationVO[] getSignLocations() {
        return signLocations;
    }

    public void setSignLocations(SignLocationVO[] signLocations) {
        this.signLocations = signLocations;
    }

    public UploadSignInfoVO[] getSignInfos() {
        return signInfos;
    }

    public void setSignInfos(UploadSignInfoVO[] signInfos) {
        this.signInfos = signInfos;
    }

    public String toString() {
        return (new StringBuilder()).append("UploadContractVO [\nisSign=").append(isSign).append("\ncontractTypeCode=").append(contractTypeCode).append("\ncontractName=").append(contractName).append("\nsignKeyword=").append(signKeyword).append("\nsignLocations=").append(Arrays.toString(signLocations)).append("\nsignInfos=").append(Arrays.toString(signInfos)).append("\n]").toString();
    }
}
