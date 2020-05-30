// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3002ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;

import com.hwp.common.components.cfca.trustsign.common.vo.cs.EnterpriseTransactorVO;
import com.hwp.common.components.cfca.trustsign.common.vo.cs.EnterpriseVO;

// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

public class Tx3002ReqVO extends Tx3ReqVO {

    private EnterpriseVO enterprise;
    private EnterpriseTransactorVO enterpriseTransactor;
    private Integer notSendPwd;

    public Tx3002ReqVO() {
    }

    public EnterpriseVO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseVO enterprise) {
        this.enterprise = enterprise;
    }

    public EnterpriseTransactorVO getEnterpriseTransactor() {
        return enterpriseTransactor;
    }

    public void setEnterpriseTransactor(EnterpriseTransactorVO enterpriseTransactor) {
        this.enterpriseTransactor = enterpriseTransactor;
    }

    public Integer getNotSendPwd() {
        return notSendPwd;
    }

    public void setNotSendPwd(Integer notSendPwd) {
        this.notSendPwd = notSendPwd;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3002ReqVO [").append(super.toString()).append("\nenterprise=").append(enterprise).append("\nenterpriseTransactor=").append(enterpriseTransactor).append("\nnotSendPwd=").append(notSendPwd).append("\n]").toString();
    }
}
