// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignContractByCoordinateVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

import java.util.Arrays;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignContractVO, SignLocationVO

public class SignContractByCoordinateVO extends SignContractVO {

    private SignLocationVO[] signLocations;

    public SignContractByCoordinateVO() {
    }

    public SignLocationVO[] getSignLocations() {
        return signLocations;
    }

    public void setSignLocations(SignLocationVO[] signLocations) {
        this.signLocations = signLocations;
    }

    public String toString() {
        return (new StringBuilder()).append("SignContractByCoordinateVO [").append(super.toString()).append("\nsignLocations=").append(Arrays.toString(signLocations)).append("\n]").toString();
    }
}
