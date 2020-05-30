package com.hwp.common.components.cfca.trustsign.connector;

import com.hwp.common.components.cfca.trustsign.constant.Request;
import com.hwp.common.components.cfca.trustsign.constant.SystemConst;
import com.hwp.common.components.cfca.trustsign.util.CommonUtil;
import com.hwp.common.components.com.hwp.common.components.cfca.trustsign.constant.MIMEType;
import com.hwp.common.util.PropertiesHelper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;


@Component
@PropertySource(value = {"classpath:/application.properties"}, encoding = "utf-8")
public class HttpConnector {
    public static String JKS_PATH;// 通讯证书JKS储存路径，最好为绝对地址
    public static String JKS_PWD;// JKS文件密码（JKS外壳和私钥的密码应该一致）
    public static String ALIAS;// 证书别名
    public static String url;// CS连接地址
    private static Logger logger = Logger.getLogger(HttpConnector.class);

    /*@Value("${trustSign.jksPath}")
    public void setJksPath(String jksPath) {
        JKS_PATH = jksPath;
    }

    @Value("${trustSign.jksPwd}")
    public void setJksPwd(String jksPwd) {
        JKS_PWD = jksPwd;
    }

    @Value("${trustSign.alias}")
    public void setALIAS(String ALIAS) {
        HttpConnector.ALIAS = ALIAS;
    }

    @Value("${trustSign.csUrl}")
    public void setUrl(String url) {
        HttpConnector.url = url;
    }*/

    static {
        JKS_PATH = PropertiesHelper.getProperty("trustSign.jksPath");
        JKS_PWD = PropertiesHelper.getProperty("trustSign.jksPwd");
        ALIAS = PropertiesHelper.getProperty("trustSign.alias");
        url = PropertiesHelper.getProperty("trustSign.csUrl");
    }

    public int connectTimeout = 3000;
    public int readTimeout = 10000;
    public String channel = "vjinke";// 生产这里最好写客户的英文名或拼音
    public boolean isSSL = true;
    private HttpClient httpClient;

    public HttpConnector() {
        init();
    }

    public void init() {
        httpClient = new HttpClient();
        httpClient.config.connectTimeout = connectTimeout;
        httpClient.config.readTimeout = readTimeout;
        httpClient.httpConfig.userAgent = "TrustSign FEP";
        httpClient.httpConfig.contentType = MIMEType.FORM;
        httpClient.httpConfig.accept = MIMEType.JSON;
        try {
            if (isSSL) {
                httpClient.initSSL(JKS_PATH, JKS_PWD.toCharArray(), JKS_PATH, JKS_PWD.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
    }

    public String post(String uri, String data, String signature) {
        return deal(uri, "POST", prepare(data, signature, null));
    }

    public String post(String uri, String data, String signature, Map<String, String> map) {
        return deal(uri, "POST", prepare(data, signature, map));
    }

    public String post(String uri, String data, String signature, File file) {
        return deal(uri, "POST", data, file, signature);
    }

    public String post(String uri, String data, String signature, OutputStream out) {
        return deal(uri, "POST", data, out, signature);
    }

    public byte[] getFile(String uri) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, "GET");
            int responseCode = httpClient.send(connection, null);
            logger.info("responseCode:" + responseCode);
            if (responseCode != 200) {
                logger.info(CommonUtil.getString(httpClient.receive(connection)));
            }

            return httpClient.receive(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            httpClient.disconnect(connection);
        }
    }

    private String prepare(String data, String signature, Map<String, String> map) {
        try {
            StringBuilder request = new StringBuilder();
            request.append(Request.CHANNEL).append("=").append(URLEncoder.encode(channel, SystemConst.DEFAULT_CHARSET));
            if (CommonUtil.isNotEmpty(data)) {
                request.append("&").append(Request.DATA).append("=").append(URLEncoder.encode(data, SystemConst.DEFAULT_CHARSET));
            }
            if (CommonUtil.isNotEmpty(signature)) {
                request.append("&").append(Request.SIGNATURE).append("=").append(URLEncoder.encode(signature, SystemConst.DEFAULT_CHARSET));
            }
            if (CommonUtil.isNotEmpty(map)) {
                for (Entry<String, String> pair : map.entrySet()) {
                    request.append("&").append(pair.getKey()).append("=").append(pair.getValue() == null ? "" : URLEncoder.encode(pair.getValue(), SystemConst.DEFAULT_CHARSET));
                }
            }
            return request.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private String deal(String uri, String method, String request) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, method);
            logger.info(url + uri);
            logger.info(method);
            logger.info(request);
            int responseCode = httpClient.send(connection, request == null ? null : CommonUtil.getBytes(request));
            logger.info("responseCode:" + responseCode);
            return CommonUtil.getString(httpClient.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            httpClient.disconnect(connection);
        }
    }

    private String deal(String uri, String method, String request, File file, String signature) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, method);
            logger.info(url + uri);
            logger.info(method);
            logger.info(request);
            int responseCode = httpClient.send(connection, request == null ? null : CommonUtil.getBytes(request), file, signature);
            logger.info("responseCode:" + responseCode);
            return CommonUtil.getString(httpClient.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            httpClient.disconnect(connection);
        }
    }

    private String deal(String uri, String method, String request, OutputStream out, String signature) {
        HttpURLConnection connection = null;
        try {
            connection = httpClient.connect(url + uri, method);
            int responseCode = httpClient.send(connection, request == null ? null : CommonUtil.getBytes(request), out, signature);
            return CommonUtil.getString(httpClient.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            httpClient.disconnect(connection);
        }
    }

}
