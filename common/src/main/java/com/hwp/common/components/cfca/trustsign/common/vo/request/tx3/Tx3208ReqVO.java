// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3208ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.SignContractByCoordinateVO;

public class Tx3208ReqVO extends Tx3ReqVO {

    private SignContractByCoordinateVO signContractByCoordinate;

    public Tx3208ReqVO() {
    }

    public SignContractByCoordinateVO getSignContractByCoordinate() {
        return signContractByCoordinate;
    }

    public void setSignContractByCoordinate(SignContractByCoordinateVO signContractByCoordinate) {
        this.signContractByCoordinate = signContractByCoordinate;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3208ReqVO [").append(super.toString()).append("\nsignContractByCoordinate=").append(signContractByCoordinate).append("\n]").toString();
    }
}
