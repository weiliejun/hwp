package com.hwp.common.thirdparty.service;

import java.util.Map;

/**
 * @Description 众大服务调用
 * @auther: zhangkele
 * @UpadteDate: 2019/1/22 17:21
 */
public interface ThirdPartyCallService {

    /**
     * @Description 用一句话描述
     * @auther: zhangkele
     * @UpadteDate: 2019/1/22 17:21
     */
    String callThirdPartyAPI(Map<String, String> params);

    /**
     * @Description 调用晋商银行接口
     * @auther: weiliejun
     * @UpadteDate: 2019/6/04
     */
    String callJshBankAPI(Map<String, String> params);
}