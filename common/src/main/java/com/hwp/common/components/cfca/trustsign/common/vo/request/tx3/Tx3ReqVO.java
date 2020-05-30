// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.HeadVO;
import com.hwp.common.components.cfca.trustsign.common.vo.request.ReqVO;

public abstract class Tx3ReqVO extends ReqVO {

    private HeadVO head;

    public Tx3ReqVO() {
    }

    public HeadVO getHead() {
        return head;
    }

    public void setHead(HeadVO head) {
        this.head = head;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3ReqVO [\nhead=").append(head).append("\n]").toString();
    }
}
