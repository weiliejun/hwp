package com.hwp.admin.components.trustsign.service;

import cfca.sadk.algorithm.common.PKIException;
import com.hwp.common.components.cfca.trustsign.connector.HttpConnector;
import com.hwp.common.components.cfca.trustsign.util.SecurityUtil;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service("trustSignInterfaceCallService")
public class TrustSignInterfaceCallServiceImp implements TrustSignInterfaceCallService {

    private final Logger logger = Logger.getLogger(TrustSignInterfaceCallServiceImp.class);

    public static void main(String[] args) {
        /*
         * String inNo = "410825198912257719";
         * System.out.println("-----------"+StringHelper.replaceWithStr(inNo,
         * "*", 1, 16));
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:config/spring/applicationContext*.xml"});
        context.start();
        TrustSignService trustsignService = (TrustSignService) context.getBean("trustSignService");
		/*final TrustSignUploadContractListener trustSignUploadContractListener = (TrustSignUploadContractListener) context.getBean("trustSignUploadContractListener");
		final ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
		Product product = productRepository.findProduct("17050214321733130260");
		TrustSignUploadContractEvent event = new TrustSignUploadContractEvent(product);
		trustSignUploadContractListener.onApplicationEvent(event);*/
//		trustsignService.creditAssignmentAuthorization("17081416363296950730");
        /*
         * com.alibaba.fastjson.JSONObject json = new
         * com.alibaba.fastjson.JSONObject ();
         *
         * com.alibaba.fastjson.JSONObject platForm = new
         * com.alibaba.fastjson.JSONObject (); platForm.put("keyWord","法定代表人：");
         * json.put("platForm", platForm); com.alibaba.fastjson.JSONArray
         * signUsers = new com.alibaba.fastjson.JSONArray ();
         * com.alibaba.fastjson.JSONObject signUser = new
         * com.alibaba.fastjson.JSONObject (); signUser.put("keyWord", "*可乐");
         * signUser.put("trustSignId", "4B49309B37AF5590E05311016B0A353F");
         * signUsers.add(signUser); json.put("signUsers", signUsers);
         * json.put("productId", "17032411301027687093");
         * json.put("projectCode", "JYJH27687093"); json.put("contractName",
         * "静音计划JYJH27687093");
         */
        // trustsignService.registerPerson(json);

        // trustsignService.personCreditQueryAuthorization("15111810261866324719","17040517240976287777");
        /*
         * String url =
         * "http://localhost:9999/customerResource/product/agreement/personcreditauthorization/download?borrowerId=17031613411788842364";
         * OutputStream out = HttpClientHelper.downLoad(url);
         * trustsignService.iuploadContract(json,out);
         */
    }

    /**
     * @param url
     * @param req
     * @param signature
     * @return
     * @description 发起安心签接口调用
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-17 下午3:53:46
     */
    @Override
    public String sendRequest(String txCode, String url, String req, String signature) {
        HttpConnector httpConnector = new HttpConnector();
        String res = httpConnector.post(url, req, signature);
        return res;
    }

    @Override
    public String sendRequestIncludeFile(String txCode, String url, String req, String signature, OutputStream out) {
        HttpConnector httpConnector = new HttpConnector();
        String res = httpConnector.post(url, req, signature, out);
        return res;
    }

    @Override
    public String p7SignMessageDetach(String req) {
        String signature = "";
        try {
            signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        } catch (PKIException e) {
            e.printStackTrace();
            logger.error("安心签请求数据签名异常:" + req);
            throw new RuntimeException("安心签请求数据签名异常:" + req);
        }
        return signature;
    }

}