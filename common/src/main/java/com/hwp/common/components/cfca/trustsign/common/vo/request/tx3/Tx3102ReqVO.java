// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3102ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.ProxySignVO;

public class Tx3102ReqVO extends Tx3ReqVO {

    private ProxySignVO proxySign;

    public Tx3102ReqVO() {
    }

    public ProxySignVO getProxySign() {
        return proxySign;
    }

    public void setProxySign(ProxySignVO proxySign) {
        this.proxySign = proxySign;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3102ReqVO [").append(super.toString()).append("\nproxySign=").append(proxySign).append("\n]").toString();
    }
}
