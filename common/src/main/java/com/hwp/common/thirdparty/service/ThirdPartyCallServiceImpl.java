package com.hwp.common.thirdparty.service;


import com.hwp.common.thirdparty.util.EecServiceUtil;
import com.hwp.common.util.APIUrlHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description 第三方接口调用实现
 * @Author zhangkele
 * @UpdateDate 2019/1/23 12:40
 */
@Service
public class ThirdPartyCallServiceImpl implements ThirdPartyCallService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String callThirdPartyAPI(Map<String, String> params) {
        String serivce = params.get("service");
        APIUrlHelper.APIName apiName = APIUrlHelper.APIName.getAPIName(serivce);
        //移除参数
        params.remove("userId");
        params.remove("appKey");
        params.remove("ordId");
        params.remove("service");
        params.remove("rateLimitKey");
        params.remove("expenseType");
        return EecServiceUtil.httpPost(APIUrlHelper.getRequestUrl(apiName), params);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String callJshBankAPI(Map<String, String> params) {
        String serivce = params.get("service");
        APIUrlHelper.APIName apiName = APIUrlHelper.APIName.getAPIName(serivce);
        //添加公共参数
        params = EecServiceUtil.addCommonParams(serivce, params);
        //移除参数
        params.remove("appKey");
        params.remove("service");

        return EecServiceUtil.httpPostToJshBank(APIUrlHelper.getRequestUrl(apiName), params);
    }
}
