// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3301ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;


// Referenced classes of package com.hwp.common.components.com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.CertBindingVO;

public class Tx3301ResVO extends Tx3ResVO {

    private CertBindingVO certBinding;

    public Tx3301ResVO() {
    }

    public CertBindingVO getCertBinding() {
        return certBinding;
    }

    public void setCertBinding(CertBindingVO certBinding) {
        this.certBinding = certBinding;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3301ResVO [").append(super.toString()).append("\ncertBinding=").append(certBinding).append("\n]").toString();
    }
}
