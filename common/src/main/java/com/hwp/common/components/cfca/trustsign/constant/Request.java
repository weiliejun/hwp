package com.hwp.common.components.cfca.trustsign.constant;

import com.hwp.common.util.PropertiesHelper;

public class Request {
    public static final String CHANNEL = "channel";
    public static final String LOCALE = "locale";
    public static final String DATA = "data";
    public static final String SIGNATURE = "signature";

    public static String PLAT_ID = "";// 平台ID

    static {
        PLAT_ID = PropertiesHelper.getProperty("trustSign.platId");
    }
}
