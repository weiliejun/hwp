// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignKeywordVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class SignKeywordVO {

    private String keyword;
    private String offsetCoordX;
    private String offsetCoordY;
    private String imageWidth;
    private String imageHeight;

    public SignKeywordVO() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOffsetCoordX() {
        return offsetCoordX;
    }

    public void setOffsetCoordX(String offsetCoordX) {
        this.offsetCoordX = offsetCoordX;
    }

    public String getOffsetCoordY() {
        return offsetCoordY;
    }

    public void setOffsetCoordY(String offsetCoordY) {
        this.offsetCoordY = offsetCoordY;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String toString() {
        return (new StringBuilder()).append("SignKeywordVO [\nkeyword=").append(keyword).append("\noffsetCoordX=").append(offsetCoordX).append("\noffsetCoordY=").append(offsetCoordY).append("\nimageWidth=").append(imageWidth).append("\nimageHeight=").append(imageHeight).append("\n]").toString();
    }
}
