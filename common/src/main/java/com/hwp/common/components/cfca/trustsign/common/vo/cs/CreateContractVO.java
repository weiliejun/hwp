// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CreateContractVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignInfoVO

public class CreateContractVO {

    private String templateId;
    private Integer isSign;
    private String contractNo;
    private String fileId;
    private String code;
    private String message;
    private SignInfoVO[] signInfos;
    private Map investmentInfo;

    public CreateContractVO() {
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignInfoVO[] getSignInfos() {
        return signInfos;
    }

    public void setSignInfos(SignInfoVO[] signInfos) {
        this.signInfos = signInfos;
    }

    public Map getInvestmentInfo() {
        return investmentInfo;
    }

    public void setInvestmentInfo(Map investmentInfo) {
        this.investmentInfo = investmentInfo;
    }

    public String toString() {
        return (new StringBuilder()).append("CreateContractVO [\ntemplateId=").append(templateId).append("\nisSign=").append(isSign).append("\ncontractNo=").append(contractNo).append("\nfileId=").append(fileId).append("\ncode=").append(code).append("\nmessage=").append(message).append("\nsignInfos=").append(Arrays.toString(signInfos)).append("\ninvestmentInfo=").append(investmentInfo).append("\n]").toString();
    }
}
