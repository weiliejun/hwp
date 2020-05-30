// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3302ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CertUnbindingVO;

public class Tx3302ReqVO extends Tx3ReqVO {

    private CertUnbindingVO certUnbinding;

    public Tx3302ReqVO() {
    }

    public CertUnbindingVO getCertUnbinding() {
        return certUnbinding;
    }

    public void setCertUnbinding(CertUnbindingVO certUnbinding) {
        this.certUnbinding = certUnbinding;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3302ReqVO [").append(super.toString()).append("\ncertUnbinding=").append(certUnbinding).append("\n]").toString();
    }
}
