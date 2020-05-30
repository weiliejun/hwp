// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3001ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.PersonVO;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

public class Tx3001ResVO extends Tx3ResVO {

    private PersonVO person;
    private Integer notSendPwd;

    public Tx3001ResVO() {
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
        return (new StringBuilder()).append("Tx3001ResVO [").append(super.toString()).append("\nperson=").append(person).append("\nnotSendPwd=").append(notSendPwd).append("\n]").toString();
    }
}
