// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UploadSignInfoVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

import java.util.Arrays;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.cs:
//			SignInfoVO, SignKeywordVO, SignLocationVO

public class UploadSignInfoVO extends SignInfoVO {

    private SignKeywordVO signKeyword;
    private SignLocationVO[] signLocations;

    public UploadSignInfoVO() {
    }

    public SignKeywordVO getSignKeyword() {
        return signKeyword;
    }

    public void setSignKeyword(SignKeywordVO signKeyword) {
        this.signKeyword = signKeyword;
    }

    public SignLocationVO[] getSignLocations() {
        return signLocations;
    }

    public void setSignLocations(SignLocationVO[] signLocations) {
        this.signLocations = signLocations;
    }

    public String toString() {
        return (new StringBuilder()).append("UploadSignInfoVO [").append(super.toString()).append("\nsignKeyword=").append(signKeyword).append("\nsignLocations=").append(Arrays.toString(signLocations)).append("\n]").toString();
    }
}
