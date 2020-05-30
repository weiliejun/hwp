// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3301ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CertBindingVO;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

public class Tx3301ReqVO extends Tx3ReqVO {

    private CertBindingVO certBinding;

    public Tx3301ReqVO() {
    }

    public CertBindingVO getCertBinding() {
        return certBinding;
    }

    public void setCertBinding(CertBindingVO certBinding) {
        this.certBinding = certBinding;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3301ReqVO [").append(super.toString()).append("\ncertBinding=").append(certBinding).append("]").toString();
    }
}
