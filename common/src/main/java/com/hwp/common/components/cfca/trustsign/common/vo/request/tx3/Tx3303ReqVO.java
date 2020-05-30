// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3303ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CertApplyVO;

public class Tx3303ReqVO extends Tx3ReqVO {

    private CertApplyVO certApply;

    public Tx3303ReqVO() {
    }

    public CertApplyVO getCertApply() {
        return certApply;
    }

    public void setCertApply(CertApplyVO certApply) {
        this.certApply = certApply;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3303ReqVO [").append(super.toString()).append("\ncertApply=").append(certApply).append("\n]").toString();
    }
}
