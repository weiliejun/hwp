// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3302ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;


// Referenced classes of package com.hwp.common.components.com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CertUnbindingVO;

public class Tx3302ResVO extends Tx3ResVO {

    private CertUnbindingVO certUnbinding;

    public Tx3302ResVO() {
    }

    public CertUnbindingVO getCertUnbinding() {
        return certUnbinding;
    }

    public void setCertUnbinding(CertUnbindingVO certUnbinding) {
        this.certUnbinding = certUnbinding;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3302ResVO [").append(super.toString()).append("\ncertUnbinding=").append(certUnbinding).append("\n]").toString();
    }
}
