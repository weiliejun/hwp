package com.hwp.common.thirdparty.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwp.common.constant.JinShangInterfaceConstant;
import com.hwp.common.util.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 第三方接口调用Service装饰者
 */
@Component
public class ThirdPartyCallServiceDecorator {

    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyCallServiceDecorator.class);

    @Autowired
    private ThirdPartyCallService thirdPartyCallService;

    public String callThirdPartyAPI(Map<String, String> params) {
        return thirdPartyCallService.callJshBankAPI(params);
    }


    public String callJshBankAPI(Map<String, String> params) {
        return thirdPartyCallService.callJshBankAPI(params);
    }


    public JSONObject callJshBankApiDecorator(String service, Map<String, String> params) {
        Map<String, String> returnMap = null;
        try {
            params.put("service", service);
            // 生成渠道流水
            String _ChannelJnlNo = RandomUtil.getSerialNumber();
            // 添加请求参数
            params.put(JinShangInterfaceConstant.CHANNEL_JNL_NO, _ChannelJnlNo);
            logger.debug("请求参数：" + JSON.toJSONString(params));
            // 调用第三方接口
            String jsonData = thirdPartyCallService.callJshBankAPI(params);
            logger.debug("返回参数：" + JSON.toJSONString(jsonData));
            if (StringUtils.isBlank(jsonData)) {
                return null;
            }

            JSONObject jsonObject = JSON.parseObject(jsonData);
            String returnCode = jsonObject.getString(JinShangInterfaceConstant.RETURN_CODE);
            String returnMsg = jsonObject.getString(JinShangInterfaceConstant.RETURN_MSG);

            // 发送成功
            if (JinShangInterfaceConstant.RETURN_CODE_VALUE.equals(returnCode)) {
                return jsonObject;
            }
            // 请求接口失败
            return null;


        } catch (Exception e) {
            e.printStackTrace();
            // 调用出现异常
            return null;
        }
    }
}
