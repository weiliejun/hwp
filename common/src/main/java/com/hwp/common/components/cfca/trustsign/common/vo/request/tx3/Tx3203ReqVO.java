// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3203ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.UploadContractVO;

public class Tx3203ReqVO extends Tx3ReqVO {

    private UploadContractVO uploadContract;

    public Tx3203ReqVO() {
    }

    public UploadContractVO getUploadContract() {
        return uploadContract;
    }

    public void setUploadContract(UploadContractVO uploadContract) {
        this.uploadContract = uploadContract;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3201ReqVO [").append(super.toString()).append("\nuploadContract=").append(uploadContract).append("\n]").toString();
    }
}
