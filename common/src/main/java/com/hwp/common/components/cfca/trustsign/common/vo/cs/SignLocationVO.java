// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignLocationVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class SignLocationVO {

    private String signOnPage;
    private String signLocationLBX;
    private String signLocationLBY;
    private String signLocationRUX;
    private String signLocationRUY;

    public SignLocationVO() {
    }

    public String getSignOnPage() {
        return signOnPage;
    }

    public void setSignOnPage(String signOnPage) {
        this.signOnPage = signOnPage;
    }

    public String getSignLocationLBX() {
        return signLocationLBX;
    }

    public void setSignLocationLBX(String signLocationLBX) {
        this.signLocationLBX = signLocationLBX;
    }

    public String getSignLocationLBY() {
        return signLocationLBY;
    }

    public void setSignLocationLBY(String signLocationLBY) {
        this.signLocationLBY = signLocationLBY;
    }

    public String getSignLocationRUX() {
        return signLocationRUX;
    }

    public void setSignLocationRUX(String signLocationRUX) {
        this.signLocationRUX = signLocationRUX;
    }

    public String getSignLocationRUY() {
        return signLocationRUY;
    }

    public void setSignLocationRUY(String signLocationRUY) {
        this.signLocationRUY = signLocationRUY;
    }

    public String toString() {
        return (new StringBuilder()).append("SignLocationVO [\nsignOnPage=").append(signOnPage).append("\nsignLocationLBX=").append(signLocationLBX).append("\nsignLocationLBY=").append(signLocationLBY).append("\nsignLocationRUX=").append(signLocationRUX).append("\nsignLocationRUY=").append(signLocationRUY).append("\n]").toString();
    }
}
