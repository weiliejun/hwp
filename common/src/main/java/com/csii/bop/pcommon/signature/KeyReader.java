package com.csii.bop.pcommon.signature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

public class KeyReader {

    private static final String FileInputStream = null;

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    public boolean isPublicKeyValid(String key, String algorithmName) {
        try {
            readPublicKey(key, true, algorithmName);
        } catch (InvalidKeySpecException e) {
            return false;
        }

        return true;
    }

    public boolean isPrivateKeyValid(String key, String algorithmName) {
        try {
            readPrivateKey(key, true, algorithmName);
        } catch (InvalidKeySpecException e) {
            return false;
        }

        return true;
    }

    /**
     * 璇诲彇绉侀挜
     *
     * @param keyStr
     * @param base64Encoded
     * @param algorithmName
     * @return
     * @throws InvalidKeySpecException
     */
    public PrivateKey readPrivateKey(String keyStr, boolean base64Encoded,
                                     String algorithmName) throws InvalidKeySpecException {
        return (PrivateKey) readKey(keyStr, false, base64Encoded, algorithmName);
    }

    /**
     * 璇诲彇鍏挜
     *
     * @param keyStr
     * @param base64Encoded
     * @param algorithmName
     * @return
     * @throws InvalidKeySpecException
     */
    public PublicKey readPublicKey(String keyStr, boolean base64Encoded,
                                   String algorithmName) throws InvalidKeySpecException {
        return (PublicKey) readKey(keyStr, true, base64Encoded, algorithmName);
    }

    /**
     * 璇诲彇瀵嗛挜锛孹509EncodedKeySpec鐨勫叕閽ヤ笌PKCS8EncodedKeySpec閮藉彲浠ヨ鍙栵紝瀵嗛挜鍐呭鍙互涓洪潪base64缂栫爜杩囩殑锟�
     *
     * @param keyStr
     * @param isPublicKey
     * @param base64Encoded
     * @param algorithmName
     * @return
     * @throws InvalidKeySpecException
     */
    private Key readKey(String keyStr, boolean isPublicKey,
                        boolean base64Encoded, String algorithmName)
            throws InvalidKeySpecException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmName);

            byte[] encodedKey = keyStr.getBytes("UTF-8");

            if (base64Encoded) {
                encodedKey = Base64.decodeBase64(encodedKey);
            }

            if (isPublicKey) {
                EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);

                return keyFactory.generatePublic(keySpec);
            } else {
                EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);

                return keyFactory.generatePrivate(keySpec);
            }
        } catch (NoSuchAlgorithmException e) {
            // 涓嶅彲鑳藉彂锟�
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 浠嶱KCS12鏍囧噯瀛樺偍鏍煎紡涓鍙栫閽ラ挜锛屽悗锟�锟�.pfx鏂囦欢锛岃鏂囦欢涓寘鍚锟�
     *
     * @param resourceName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public PrivateKey readPrivateKeyfromPKCS12StoredFile(String resourceName,
                                                         String password) throws Exception {
        InputStream istream = null;

        //istream = new FileInputStream(resourceName);
        istream = this.getClass().getResourceAsStream("mock_pri.pfx");

        // 浣跨敤榛樿鐨刱eyprovider锛屽彲鑳戒細鏈夐棶棰橈拷??
        KeyStore keystore = KeyStore.getInstance("PKCS12");

        keystore.load(istream, password.toCharArray());
        Enumeration enumeration = keystore.aliases();


        String alias = null;
        String alias1 = null;
        for (int i = 0; enumeration.hasMoreElements(); i++) {
            alias = enumeration.nextElement().toString();
            if (i == 0) {
                alias1 = alias;
            }

        }
        return (PrivateKey) keystore.getKey(alias1, password.toCharArray());

    }

    public PrivateKey readJSPrivateKeyfromPKCS12StoredFile(String resourceName,
                                                           String password) throws Exception {
        InputStream istream = null;

        //istream = new FileInputStream(resourceName);
        istream = this.getClass().getResourceAsStream("jsbprod.pfx");
//		log.debug("istream=====>"+istream);
//		log.debug("password=====>"+password.toCharArray().toString());
        // 使用默认的keyprovider，可能会有问题。
        KeyStore keystore = KeyStore.getInstance("PKCS12");
//		log.debug("keystore=====>"+keystore);

        keystore.load(istream, password.toCharArray());
        Enumeration enumeration = keystore.aliases();
//		log.debug("enumeration=====>"+enumeration);

        String alias = null;
        String alias1 = null;
        for (int i = 0; enumeration.hasMoreElements(); i++) {
            alias = enumeration.nextElement().toString();
            if (i == 0) {
                alias1 = alias;
            }
//		log.debug("此文件中含有多个证书:"+i+"="+alias);
            System.out.println("此文件中含有多个证书");
        }
        return (PrivateKey) keystore.getKey(alias1, password.toCharArray());

    }

    public PrivateKey readKlPrivateKeyfromPKCS12StoredFile(String resourceName,
                                                           String password) throws Exception {
        InputStream istream = null;

//        istream = new FileInputStream(resourceName);
        istream = this.getClass().getResourceAsStream(resourceName);

        KeyStore keystore = KeyStore.getInstance("PKCS12");

        keystore.load(istream, password.toCharArray());
        Enumeration enumeration = keystore.aliases();

        String alias = null;
        String alias1 = null;
        for (int i = 0; enumeration.hasMoreElements(); i++) {
            alias = enumeration.nextElement().toString();
            if (i == 0) {
                alias1 = alias;
            }

        }
        return (PrivateKey) keystore.getKey(alias1, password.toCharArray());

    }

    /**
     * 浠嶺509鐨勬爣鍑嗗瓨鍌ㄦ牸寮忎腑璇诲彇鍏挜
     *
     * @param resourceName  鍏挜鏂囦欢
     * @param base64Encoded 璇ユ枃浠跺瓨鍌ㄥ墠鏄惁浣跨敤base64缂栫爜锛堣浆鍖栦笉鍙瀛楃锟�
     * @return
     * @throws Exception
     */
    public Key fromX509StoredFile(String resourceName, boolean base64Encoded)
            throws Exception {

        byte[] encodedKeyByte = readByteFromFile(resourceName);
        if (base64Encoded) {
            encodedKeyByte = Base64.decodeBase64(encodedKeyByte);
        }

        // return fromByte(encodedKeyByte);
        return null;

    }

    /**
     * Base64缂栫爜X.509鏍煎紡璇佷功鏂囦欢涓鍙栧叕锟�
     *
     * @param resourceName
     * @return
     * @throws Exception
     */
    public Key fromCerStoredFile(String resourceName) throws Exception {
        InputStream inputStream = KeyReader.class.getResourceAsStream("mock.cer");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate certificate = cf.generateCertificate(inputStream);
        return (Key) (certificate != null ? certificate.getPublicKey() : null);
    }

    public Key fromKlStoredFile(String resourceName) throws Exception {
        InputStream inputStream = KeyReader.class.getResourceAsStream(resourceName);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate certificate = cf.generateCertificate(inputStream);
        return (Key) (certificate != null ? certificate.getPublicKey() : null);
    }

    /**
     * 浠嶱KCS12鏍囧噯瀛樺偍鏍煎紡涓鍙栧叕閽ワ紝鍚庣紑锟�.pfx鏂囦欢锛岃鏂囦欢涓寘鍚锟�
     *
     * @param resourceName
     * @return
     * @throws Exception
     */
    public Key fromPKCS12StoredFile(String resourceName, String password)
            throws Exception {
        InputStream istream = null;

        istream = new FileInputStream(resourceName);
        // 浣跨敤榛樿鐨刱eyprovider锛屽彲鑳戒細鏈夐棶棰橈拷??
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(istream, password.toCharArray());
        Enumeration enumeration = keystore.aliases();
        String alias = null;
        for (int i = 0; enumeration.hasMoreElements(); i++) {
            alias = enumeration.nextElement().toString();
            if (i >= 1) {
                System.out.println("姝ゆ枃浠朵腑鍚湁澶氫釜璇佷功!");
            }
        }

        Certificate certificate = keystore.getCertificate(alias);
        return certificate.getPublicKey();

    }

    /**
     * 浠庢枃浠朵腑璇诲彇瀛楄妭
     *
     * @param resourceName
     * @return
     * @throws Exception
     */
    public byte[] readByteFromFile(String resourceName) throws Exception {
        InputStream istream = null;
        ByteArrayOutputStream baos = null;

        try {
            istream = new FileInputStream(resourceName);
            baos = new ByteArrayOutputStream();

            IOUtils.copy(istream, baos);
        } catch (IOException e) {
            throw new Exception("Failed to read key file: " + resourceName, e);
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (IOException e) {
                }
            }
        }
        return baos.toByteArray();
    }

}
