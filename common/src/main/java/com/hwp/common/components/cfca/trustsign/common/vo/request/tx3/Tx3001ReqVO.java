// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3001ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.PersonVO;

public class Tx3001ReqVO extends Tx3ReqVO {

    private PersonVO person;
    private Integer notSendPwd;

    public Tx3001ReqVO() {
    }

    public PersonVO getPerson() {
        return person;
    }

    public void setPerson(PersonVO person) {
        this.person = person;
    }

    public Integer getNotSendPwd() {
        return notSendPwd;
    }

    public void setNotSendPwd(Integer notSendPwd) {
        this.notSendPwd = notSendPwd;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3001ReqVO [").append(super.toString()).append("\nperson=").append(person).append("\nnotSendPwd=").append(notSendPwd).append("\n]").toString();
    }
}
