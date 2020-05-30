package com.csii.bop.pcommon.signature;

import org.apache.commons.codec.binary.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 杩欎釜鏄痥ey鐨勫伐鍏风被锛岀敤鏉ョ鐞嗘垜浠殑key
 *
 * @author sheng.liuzs
 */
public class KeyUtil {
    /**
     * 杩欎釜绫荤敤鏉ュ緱鍒版敮浠樺疂鐨勫叕閽ワ紝杩斿洖锟�涓狿ublicKey
     *
     * @return
     * @throws Exception
     */
    public static PublicKey getAlipayPubKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 娉ㄦ剰杩欓噷java鍦ㄨ鍙栨枃浠惰矾寰勪腑鏈夌┖鏍肩殑鎯呭喌涓嬫槸浼氭姏寮傚父鐨勶拷??
        String filename = keyReader.getClass().getResource("alipay.cer").getFile();
        PublicKey pubKey = (PublicKey) keyReader.fromCerStoredFile(filename);
        // System.out.println("PublicKey => " + new String(Base64.encodeBase64(pubKey.getEncoded())));
        return pubKey;
    }

    /**
     * 杩欎釜绫荤敤鏉ュ緱鍒版ā鎷熼摱琛岀殑鍏挜锛岃繑鍥炰竴涓狿ublicKey
     *
     * @return
     * @throws Exception
     */
    public static PublicKey getMOCKPubKey(String flag) throws Exception {
        KeyReader keyReader = new KeyReader();
        // 娉ㄦ剰杩欓噷java鍦ㄨ鍙栨枃浠惰矾寰勪腑鏈夌┖鏍肩殑鎯呭喌涓嬫槸浼氭姏寮傚父鐨勶拷??
        String filename = null;
        if ("1".equals(flag)) {
            //姘戝晢鍏挜
            filename = keyReader.getClass().getResource("mock.cer").getFile();
        } else {
            //鏅嬪晢E浠樺叕锟�
            filename = keyReader.getClass().getResource("mock2.cer").getFile();
        }
        System.out.print("filename=====>" + filename);
        PublicKey pubKey = (PublicKey) keyReader.fromCerStoredFile(filename);
        // System.out.println("PublicKey => " + new String(Base64.encodeBase64(pubKey.getEncoded())));
        return pubKey;
    }

    /**
     * 杩欎釜绫荤敤鏉ュ緱鍒版ā鎷熺钃濈殑鍏挜锛岃繑鍥炰竴涓狿ublicKey
     *
     * @return
     * @throws Exception
     */
    public static PublicKey getKlPubKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 娉ㄦ剰杩欓噷java鍦ㄨ鍙栨枃浠惰矾寰勪腑鏈夌┖鏍肩殑鎯呭喌涓嬫槸浼氭姏寮傚父鐨勶拷??
//        String filename = keyReader.getClass().getResource("E:\\Workspaces\\hwp\\common\\src\\main\\java\\com\\csii\\bop\\pcommon\\signature\\pubKeyJKGF.cer").getFile();
//       String filename = keyReader.getClass().getResource("jinshang-prod.cer").getFile();
//        System.out.print("filename=====>" + filename);
        PublicKey pubKey = (PublicKey) keyReader.fromKlStoredFile("/config/rsaKey/pubKeyJKGF.cer");
//       PublicKey pubKey = (PublicKey) keyReader.fromKlStoredFile("jinshang-prod.cer");
        // System.out.println("PublicKey => " + new String(Base64.encodeBase64(pubKey.getEncoded())));
        return pubKey;
    }


    /**
     * 杩欎釜绫荤敤鏉ュ緱鍒版ā鎷熼摱琛岀殑鍏挜锛岃繑鍥炰竴涓狿ublicKey
     *
     * @return
     * @throws Exception
     */
    public static PublicKey getKATONGubKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 娉ㄦ剰杩欓噷java鍦ㄨ鍙栨枃浠惰矾寰勪腑鏈夌┖鏍肩殑鎯呭喌涓嬫槸浼氭姏寮傚父鐨勶拷??
        String filename = keyReader.getClass().getResource("Katong.cer").getFile();
        PublicKey pubKey = (PublicKey) keyReader.fromCerStoredFile(filename);
        // System.out.println("PublicKey => " + new String(Base64.encodeBase64(pubKey.getEncoded())));
        return pubKey;
    }


    /**
     * 杩欎釜绫荤敤鏉ュ緱鍒版ā鎷熼摱琛岀殑绉侀挜锛岃繑鍥炰竴涓狿rivateKey
     *
     * @return
     * @throws Exception
     */
    public static PrivateKey getMOCKPriKey() throws Exception {
        KeyReader keyReader = new KeyReader();
        // 娉ㄦ剰杩欓噷java鍦ㄨ鍙栨枃浠惰矾寰勪腑鏈夌┖鏍肩殑鎯呭喌涓嬫槸浼氭姏寮傚父鐨勶拷??
        String filename = keyReader.getClass().getResource("mock_pri.pfx").getFile();
        System.out.print("filename=====>" + filename);
        PrivateKey priKey = keyReader.readPrivateKeyfromPKCS12StoredFile("", "111111");

        //鐢熶骇绉侀挜	瀵嗙爜123123
        //PrivateKey priKey = keyReader.readPrivateKeyfromPKCS12StoredFile("", "123123");
        //System.out.println("privateKey => " + new String(Base64.encodeBase64(priKey.getEncoded())));
        return priKey;
    }

    /**
     * 杩欎釜绫荤敤鏉ュ緱鍒版ā鎷熼摱琛岀殑绉侀挜锛岃繑鍥炰竴涓钃濋噾绂廝rivateKey
     *
     * @return
     * @throws Exception
     */
    public static PrivateKey getKlPriKey() throws Exception {
        KeyReader keyReader = new KeyReader();
//        String filename = keyReader.getClass().getResource("E:\Workspaces\hwp\common\src\main\resources\config\rsaKey\priKeyJKGF.pfx").getFile();
//        System.out.print("filename=====>" + filename);
        PrivateKey priKey = keyReader.readKlPrivateKeyfromPKCS12StoredFile("/config/rsaKey/priKeyJKGF.pfx", "206302");
        return priKey;
    }

    public static void main(String args[]) throws Exception {
        PrivateKey klPriKey = KeyUtil.getKlPriKey();
        byte[] privateKeyByte = klPriKey.getEncoded();
        String privateKeyStr = Base64.encodeBase64String(privateKeyByte);
        System.out.println("密匙：" + privateKeyStr);
    }
//   public static void main(String args[]){
//	   
//	   Map map = new HashMap();
//	   map.put("LoginId", "13903510001");
//	   map.put("Password", "a1111111");
//	   map.put("BankId", "9999");
//	   map.put("_ChannelId", "KLJF");
//	   map.put("VerCodeFlag", "0");
//	   map.put("LoginType", "P");
//	   Set<Entry<String,Object>> paramSet = map.entrySet();
//		Iterator<Entry<String,Object>> iter = paramSet.iterator();
//		while(iter.hasNext()){
//			Entry<String,Object> entry = iter.next();
//			Object value = entry.getValue();
//			if(value==null||"".equals(value)){
//				iter.remove();
//				map.remove(entry.getKey());
//			}
//		}
//		
//		Set set = map.keySet();
//		
//		List<String> keyList = new ArrayList<String>(set);
//		
//		Comparator comparator = new Comparator(){
//			public int compare(Object arg0, Object arg1) {
//				return arg0.toString().compareTo(arg1.toString());
//			}
//		};
//		
//		Collections.sort(keyList, comparator);
//		
//		StringBuffer sbf = new StringBuffer();
//		for(int i = 0; i < keyList.size(); i++){
//			String name = keyList.get(i).toString();
//			if(null!=map.get(name)&&!"".equals(map.get(name))){
//				sbf.append(name+"=").append(map.get(name));
//				sbf.append("&");
//			}
//		}
//		sbf.deleteCharAt(sbf.length()-1);
//		String signatureData = "";
//		try {
//			PrivateKey privateKey = KeyUtil.getKlPriKey();
//			Signature signature = new Signature();
//			signatureData = signature.signByMD5withRSA(sbf.toString(), privateKey);
//		} catch (Exception e) {
//			throw new RuntimeException("validation.signature.signature.error");
//		}
//		System.out.println();
//		System.out.println(signatureData);
//   }
}
