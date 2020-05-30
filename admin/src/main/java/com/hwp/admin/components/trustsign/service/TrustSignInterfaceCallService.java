package com.hwp.admin.components.trustsign.service;

import java.io.OutputStream;

/**
 * @author 张可乐
 * @version 1.0
 * @description 安心签接口调用
 * @update 2017-3-15 下午2:18:58
 */
public interface TrustSignInterfaceCallService {

    String sendRequest(String txCode, String url, String req, String signature);

    String sendRequestIncludeFile(String txCode, String url, String req, String signature, OutputStream out);

    /**
     * @param req
     * @return
     * @description 安心签签名
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-23 下午4:06:40
     */
    String p7SignMessageDetach(String req);
}