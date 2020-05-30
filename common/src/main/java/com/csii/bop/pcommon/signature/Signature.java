package com.csii.bop.pcommon.signature;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Signature.java
 * 签名类�?�提供数字签名及数字签名验证方法
 *
 * @author cuiyi
 * <p>
 * Created on 2010-11-8
 * Modification history
 * </p>
 * <p>
 * IBS Product Expert Group, CSII
 * Powered by CSII PowerEngine 6.0
 * </p>
 * @version 1.0
 * @since 1.0
 */
public class Signature {

    private static final String MD5WITHRSA_ALGORITHM = "MD5withRSA";


    /**
     * 将字节数组转换为16进制字符�?
     * 算法�?
     * 将字节数组中每个字节取出后转换为16进制，截取低八位
     * 将不�?2位的字符前补0，解析时可按2位分�?
     *
     * @param byteArray 字节数组
     * @return 16进制字符
     */
    private String byteToHex(byte[] byteArray) {
        if (byteArray == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hexString = Integer.toHexString(byteArray[i] & 0x00ff);
            if (hexString.length() != 2) {
                //如果字符长度不为2位，前补0占位
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /**
     * �?16进制字符串转换为字节数组
     * 算法�? �?2位为�?个字符，将该16进制字符转换为字�?
     *
     * @param hexString 16进制字符�?
     * @return 字符数组
     */
    private byte[] hexToByte(String hexString) {
        if (hexString == null || hexString.trim().length() == 0) {
            return new byte[0];
        }
        //�?2位计算字符个�?
        int unit = hexString.length() / 2;
        //结果字符数组，初始长度为unit
        byte[] byteArray = new byte[unit];

        for (int i = 0; i < hexString.length() / 2; i++) {
            //截取字符
            String hexChar = hexString.substring(i * 2, i * 2 + 2);
            //�?16进制转换为字�?
            byteArray[i] = (byte) Integer.parseInt(hexChar, 16);
        }
        return byteArray;
    }

    /**
     * 使用提供的算法与私钥对交易数据签�?
     *
     * @param plain      交易数据明文
     * @param algorithm  签名算法
     * @param privateKey java.security.PrivateKey 私钥
     * @return 签名
     * @throws Exception
     * @throws Exception 签名异常
     */
    public String sign(String plain, String algorithm, PrivateKey privateKey) throws Exception {
        try {
            Assert.notNull(plain, "plain is null.");
            Assert.notNull(algorithm, "algorithm is null.");
            Assert.notNull(privateKey, "private key is null.");

            java.security.Signature sign = java.security.Signature.getInstance(algorithm);
            sign.initSign(privateKey);
            sign.update(plain.getBytes("utf-8"));
            return byteToHex(sign.sign());
        } catch (Exception ex) {
            throw new Exception("OS0010");
        }
    }


    /**
     * 验证16进制签名
     *
     * @param plain     交易数据明文
     * @param signature 签名
     * @param algorithm 签名算法
     * @param publicKey java.security.PublicKey 公钥
     * @return 验证是否成功
     * @throws Exception
     */
    public boolean verify(String plain, String signature, String algorithm, PublicKey publicKey) throws Exception {
        try {
            Assert.notNull(plain, " plain is null.");
            Assert.notNull(signature, "signature is null.");
            Assert.notNull(algorithm, "algorithm is null.");
            Assert.notNull(publicKey, "public key is null.");

            java.security.Signature sign = java.security.Signature.getInstance(algorithm);
            sign.initVerify(publicKey);
            sign.update(plain.getBytes("utf-8"));
            byte[] signatureByteArray = hexToByte(signature); //转换为字节数�?
            return sign.verify(signatureByteArray);
        } catch (Exception ex) {
            throw new Exception("OS0011");
        }
    }

    /**
     * 使用MD5withRSA算法对签名进行验�?
     *
     * @param plain     交易数据明文
     * @param signature 交易签名
     * @param publicKey 公钥
     * @return 验证签名是否正确
     * @throws Exception
     */
    public boolean verifyByMD5withRSA(String plain, String signature, PublicKey publicKey) throws Exception {
        return verify(plain, signature, MD5WITHRSA_ALGORITHM, publicKey);
    }

    /**
     * 使用MD5withRSA算法对交易数据进行签�?
     *
     * @param plain      交易数据明文
     * @param privateKey 私钥
     * @return 交易签名
     * @throws Exception
     * @throws Exception 签名异常
     */
    public String signByMD5withRSA(String plain, PrivateKey privateKey) throws Exception {
        return sign(plain, MD5WITHRSA_ALGORITHM, privateKey);
    }
}
