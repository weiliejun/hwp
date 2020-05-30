// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ContractVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

import java.util.Arrays;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignatoryVO

public class ContractVO {

    private String contractNo;
    private String contractTypeCode;
    private String contractName;
    private Integer contractState;
    private String createTime;
    private String expiredDate;
    private String fileId;
    private SignatoryVO[] signatories;

    public ContractVO() {
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public Integer getContractState() {
        return contractState;
    }

    public void setContractState(Integer contractState) {
        this.contractState = contractState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public SignatoryVO[] getSignatories() {
        return signatories;
    }

    public void setSignatories(SignatoryVO[] signatories) {
        this.signatories = signatories;
    }

    public String toString() {
        return (new StringBuilder()).append("ContractVO [\ncontractNo=").append(contractNo).append("\ncontractTypeCode=").append(contractTypeCode).append("\ncontractName=").append(contractName).append("\ncontractState=").append(contractState).append("\ncreateTime=").append(createTime).append("\nexpiredDate=").append(expiredDate).append("\nfileId=").append(fileId).append("\nsignatories=").append(Arrays.toString(signatories)).append("\n]").toString();
    }
}
