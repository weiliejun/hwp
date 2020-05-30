// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3208ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.SignContractByCoordinateVO;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

public class Tx3208ResVO extends Tx3ResVO {

    private SignContractByCoordinateVO signContractByCoordinate;

    public Tx3208ResVO() {
    }

    public SignContractByCoordinateVO getSignContractByCoordinate() {
        return signContractByCoordinate;
    }

    public void setSignContractByCoordinate(SignContractByCoordinateVO signContractByCoordinate) {
        this.signContractByCoordinate = signContractByCoordinate;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3208ResVO [").append(super.toString()).append("\nsignContractByCoordinate=").append(signContractByCoordinate).append("\n]").toString();
    }
}
