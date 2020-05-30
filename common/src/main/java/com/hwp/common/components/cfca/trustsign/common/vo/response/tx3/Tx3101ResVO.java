// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3101ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.ProxySignVO;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

public class Tx3101ResVO extends Tx3ResVO {

    private ProxySignVO proxySign;

    public Tx3101ResVO() {
    }

    public ProxySignVO getProxySign() {
        return proxySign;
    }

    public void setProxySign(ProxySignVO proxySign) {
        this.proxySign = proxySign;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3101ResVO [").append(super.toString()).append("\nproxySign=").append(proxySign).append("\n]").toString();
    }
}
