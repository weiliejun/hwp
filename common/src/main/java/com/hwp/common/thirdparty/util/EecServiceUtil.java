package com.hwp.common.thirdparty.util;

import com.alibaba.fastjson.JSON;
import com.csii.bop.pcommon.signature.KeyReader;
import com.csii.bop.pcommon.signature.Signature;
import com.hwp.common.util.AESUtil;
import com.hwp.common.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.*;
import java.util.Map.Entry;

/**
 * Http请求工具类
 */
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
@Component
public class EecServiceUtil {
    private static final String TOKEN = "UeIG3iiNMwDj7o4p";
    private static final String KEY = "Pb5SJeJQ2lWRDaYA";
    private static Logger logger = Logger.getLogger(EecServiceUtil.class);
    private static String priKeyName;

    private static String _ChannelId;
    private static String _SubChannelId;

    private static String BankId;

    public static String get_ChannelId() {
        return _ChannelId;
    }

    @Value("${jshBank.SubChannelId}")
    public void set_ChannelId(String _ChannelId) {
        EecServiceUtil._ChannelId = _ChannelId;
    }

    /**
     * 实名认证发送请求
     */
    public static String httpPost(String apiUrl, Map<String, String> params) {
        String data = JSON.toJSONString(params);
        //请求参数需要加密
        data = AESUtil.encryptGBK(data, KEY);
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        apiUrl = apiUrl + "?token=" + TOKEN;
        HttpRequest request = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String respContent = "";
        try {
            HttpPost httpPost = (HttpPost) request;
            httpPost.setEntity(new StringEntity(data, ContentType.create("text/plain", "UTF-8")));
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    respContent = EntityUtils.toString(entityRes, "UTF-8");
                    return AESUtil.decryptGBK(respContent, KEY);
                }
            }
            return null;
        } catch (IOException e) {
            logger.info("请求路径:" + apiUrl);
            logger.info("请求参数:" + JSON.toJSONString(params));
            logger.error("post请求异常", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("释放链接异常", e);
                }
            }
        }
        return null;
    }

    /**
     * 请求参数添加公共参数
     */
    public static Map<String, String> addCommonParams(String apiName, Map<String, String> param) {
        //APIUrlHelper.APIName api = APIUrlHelper.APIName.valueOf(apiName);
        param.put("_ChannelId", _ChannelId);
        param.put("_SubChannelId", _SubChannelId);
        param.put("BankId", BankId);
        param.put("LoginType", "M");

        return param;
    }

    /**
     * 向晋商银行发送请求
     */
    public static String httpPostToJshBank(String apiUrl, Map<String, String> params) {
        // 构建请求参数
        String plain = getToSignString(params);
        System.out.println("明文begin:" + plain + "明文end");
        String signData = "";
        try {
            PrivateKey privateKey = getKlPriKey();
            Signature signature = new Signature();
            signData = signature.signByMD5withRSA(plain, privateKey);
            System.out.println("\n" + "密文" + signData);
        } catch (Exception e) {
            throw new RuntimeException("validation.signature.signature.error");
        }
        params.put("SignData", signData);
        /*StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, String> entry : params.entrySet()) {
                if (StringHelper.isNotBlank(entry.getValue())) {
                    sbParams.append(entry.getKey());
                    sbParams.append("=");
                    sbParams.append(entry.getValue());
                    sbParams.append("&");
                }
            }
        }
        try {
            String SignData = SecureLink.sign(plain, priKeyName);
            params.put("SignData", SignData);
        } catch (AddSignErrorException e) {
            logger.info("验签发生错误:" + priKeyName);
            e.printStackTrace();
        }*/
        String data = JSON.toJSONString(params);
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        HttpRequest request = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String respContent = "";
        try {
            HttpPost httpPost = (HttpPost) request;
            httpPost.setEntity(new StringEntity(data, ContentType.create("application/json", "UTF-8")));
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    respContent = EntityUtils.toString(entityRes, "UTF-8");
                    return respContent;
                }
            }
            return null;
        } catch (IOException e) {
            logger.info("请求路径:" + apiUrl);
            logger.info("请求参数:" + JSON.toJSONString(params));
            logger.error("post请求异常", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("释放链接异常", e);
                }
            }
        }
        return null;
    }

    private static String getToSignString(Map map) {

        Set<Entry<String, Object>> paramSet = map.entrySet();
        Iterator<Entry<String, Object>> iter = paramSet.iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            Object value = entry.getValue();
			/*if(value==null||"".equals(value)){
				iter.remove();
				map.remove(entry.getKey());
			}*/
        }

        Set set = map.keySet();

        List<String> keyList = new ArrayList<String>(set);

        Comparator comparator = new Comparator() {
            public int compare(Object arg0, Object arg1) {
                return arg0.toString().compareTo(arg1.toString());
            }
        };

        Collections.sort(keyList, comparator);

        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < keyList.size(); i++) {
            String name = keyList.get(i).toString();
            if (null != map.get(name) && !"".equals(map.get(name))) {
                sbf.append(name + "=").append(map.get(name));
                sbf.append("&");
            }
        }
        sbf.deleteCharAt(sbf.length() - 1);
        return sbf.toString();
    }

    public static PrivateKey getKlPriKey() throws Exception {
        KeyReader keyReader = new KeyReader();
//        String filename = keyReader.getClass().getResource("E:\Workspaces\hwp\common\src\main\resources\config\rsaKey\priKeyJKGF.pfx").getFile();
//        System.out.print("filename=====>" + filename);
        PrivateKey priKey = keyReader.readKlPrivateKeyfromPKCS12StoredFile(priKeyName, "206302");
        return priKey;
    }

    @Value("${jshBank.ChannelId}")
    public void set_SubChannelId(String _SubChannelId) {
        EecServiceUtil._SubChannelId = _SubChannelId;
    }

    @Value("${jshBank.bankId}")
    public void setBankId(String bankId) {
        BankId = bankId;
    }

    @Value("${jshBank.prikey}")
    public void setPriKeyName(String priKeyName) {
        EecServiceUtil.priKeyName = priKeyName;
    }
}
