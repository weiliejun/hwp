package com.csii.bop.pcommon.test;

import com.csii.bop.pcommon.signature.KeyUtil;
import com.csii.bop.pcommon.signature.Signature;
import com.hwp.common.util.StringHelper;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;
import java.util.Map.Entry;

public class Test {
    public static void main(String args[]) {
        Test test = new Test();
//		Map<String, String> params = new HashMap<String,String>();
//		params.put( "_ChannelId", "ZNTG" );
//		params.put( "_SubChannelId", "ZNTG" );
//		params.put( "_ChannelJnlNo", "0000000001" );
//		params.put( "BankId", "9999" );
//		params.put( "UUID", "v6KgHpi08Q1vgL9yQEEqPA==" );
//		params.put( "LoginType", "P" );
//		String signature = test.signature(params);
//		System.out.println("");
//		System.out.println(signature);
        Map<String, String> params = new HashMap<String, String>();

        params.put("BIdNo", "KLOI0022");
        params.put("BProScanl", "50000");
        params.put("BProtocolUserName", "企业**");
        params.put("BeginDate", "2019-03-29");
        params.put("CloseDate", "2019-04-26");
        params.put("DeadLine", "28");
        params.put("EachAmt", "10");
        params.put("EndDate", "2019-04-26");
        params.put("ExpectIncome", "0.053");
        params.put("FinanceProgress", "1");
        params.put("InvestLimit", "28");
        params.put("InvestProdno", "2986");
        params.put("InvestRiskLevel", "1");
        params.put("LatestInterestDate", "2019-03-29");
        params.put("LoanPurpose", "旅游");
        params.put("MaxInvestAmt", "1000000");
        params.put("MinInvestAmt", "100");
        params.put("PayMode", "D");
        params.put("PrdType", "热销标");
        params.put("ProjStatus", "B");
        params.put("ProjectName", "5万项目");
        params.put("ProjectScale", "50000");
        params.put("ProtocolNumber", "KLJK0910290");
        params.put("Purpose", "旅游");
        params.put("RemainAmt", "0");
        params.put("ReturnCode", "000000");
        params.put("ReturnMsg", "交易成功");
        params.put("StartDate", "20190226104851");
        params.put("WintnessInfo", "");
        params.put("YRate", "5.3");
//		params.put("UUID", "9f250e5f8a4bb5cecd5ec23cc0a43f90e5a48e44454918604c3c64491bb1fb8752f910d848b31ddb7a94cf19de8572c3034336929fa1f0d0f67cf4f24425530babdea73cd624fba3493faf8e05c92a29ba335bb6309d2fa906f85c443df9a5edeca0a8281ea0e398c7d7d0689a3686e4bec4eb520b05c4ed1561ff6f5c42e8ad");
        //BIdNo=KLOI0022&BProScanl=50000&BProtocolUserName=企业**&BeginDate=2019-03-29&CloseDate=2019-04-26&DeadLine=28&EachAmt=10&EndDate=2019-04-26&ExpectIncome=0.053&FinanceProgress=1&InvestLimit=28&InvestProdno=2986&InvestRiskLevel=1&LatestInterestDate=2019-03-29&LoanPurpose=旅游&MaxInvestAmt=1000000&MinInvestAmt=100&PayMode=D&PrdType=热销标&ProjStatus=B&ProjectName=5万项目&ProjectScale=50000&ProtocolNumber=KLJK0910290&Purpose=旅游&RemainAmt=0&ReturnCode=000000&ReturnMsg=交易成功&StartDate=20190226104851&WintnessInfo=&YRate=5.3
        String signData = test.signature(params);
//		String signData="97854c925708165b25e209f682f8122407ddf88cd370e0119d1d1edf9732f728e01efc1416419535d0bd1bcccbd27099e34c858d07e9848d92de17c854466c777b6bbb344e278baf3637695b0daaac5428b45031e0273dfa6d897aa372571526066f1a903aedc54068f780ee55ebca009ad9b22d1060edda84916af5dda3ccec";
//		String signData="36436eeead8b816d6d067c2ad5f7ab2a7df0aeed17073bd805e53eaf08a62eed2c80ec551b4480bb4ec59ddbd4d4befc31a3720db2a33ec9f5a955dab515acd6ef374f07e7082d0f159766cdac1845e6ea2a49d19ad3be40eded57f5560e921799bcfc721e5a296d1ed9e9aa4882b99eb89a6f9aee47b75ef1faef680cd675db";
        params.put("SignData", signData);
        System.out.println("signData：\n" + signData);
        boolean verify = test.verify(params);
        System.out.println("\n" + verify);
    }

    public static String getURLParam(Map map, boolean isSort, Set removeKey) {
        StringBuffer param = new StringBuffer();
        List msgList = new ArrayList();
        for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            String value = (String) map.get(key);
            if (removeKey != null && removeKey.contains(key)) {
                continue;
            } else if (StringHelper.isBlank(value)) {
                continue;
            }
            msgList.add(key + "=" + value);
        }

        if (isSort) {
            // ����
            Collections.sort(msgList);
        }

        for (int i = 0; i < msgList.size(); i++) {
            String msg = (String) msgList.get(i);
            if (i > 0) {
                param.append("&");
            }
            param.append(msg);
        }

        return param.toString();
    }

    private String signature() {
        //String plain = getToSignString(map);
        //String plain="BIdNo=KLOI0022&BProScanl=50000&BProtocolUserName=企业**&BeginDate=2019-03-29&CloseDate=2019-04-26&DeadLine=28&EachAmt=10&EndDate=2019-04-26&ExpectIncome=0.053&FinanceProgress=1&InvestLimit=28&InvestProdno=2986&InvestRiskLevel=1&LatestInterestDate=2019-03-29&LoanPurpose=旅游&MaxInvestAmt=1000000&MinInvestAmt=100&PayMode=D&PrdType=热销标&ProjStatus=B&ProjectName=5万项目&ProjectScale=50000&ProtocolNumber=KLJK0910290&Purpose=旅游&RemainAmt=0&ReturnCode=000000&ReturnMsg=交易成功&StartDate=20190226104851&WintnessInfo=&YRate=5.3";
        String plain = "BIdNo=KLOI0022&BProScanl=50000&BProtocolUserName=企业**&BeginDate=2019-03-29&CloseDate=2019-04-26&DeadLine=28&EachAmt=10&EndDate=2019-04-26&ExpectIncome=0.053&FinanceProgress=1&InvestLimit=28&InvestProdno=2986&InvestRiskLevel=1&LatestInterestDate=2019-03-29&LoanPurpose=旅游&MaxInvestAmt=1000000&MinInvestAmt=100&PayMode=D&PrdType=热销标&ProjStatus=B&ProjectName=5万项目&ProjectScale=50000&ProtocolNumber=KLJK0910290&Purpose=旅游&RemainAmt=0&ReturnCode=000000&ReturnMsg=交易成功&StartDate=20190226104851&YRate=5.3";
        String signatureData = "";
        try {
            PrivateKey privateKey = KeyUtil.getKlPriKey();
            Signature signature = new Signature();
            signatureData = signature.signByMD5withRSA(plain, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("validation.signature.signature.error");
        }
        return signatureData;
    }

    //����ǩ��
    private String signature(Map map) {
        String plain = getToSignString(map);
        System.out.println("明文begin:" + plain + "明文end");
        String signatureData = "";
        try {
            PrivateKey privateKey = KeyUtil.getKlPriKey();
            Signature signature = new Signature();
            signatureData = signature.signByMD5withRSA(plain, privateKey);
            System.out.println("\n" + "密文" + signatureData);
        } catch (Exception e) {
            throw new RuntimeException("validation.signature.signature.error");
        }
        return signatureData;
    }

    //������ǩ
    private boolean verify(Map paramMap) {
        Set removeKey = new HashSet();
        removeKey.add("SignData");
        String plain = getURLParam(paramMap, true, removeKey);
        System.out.println("解密明文" + plain);
//		plain = "v6KgHpi08Q3x4CdpfpBtww==";
        String signData = (String) paramMap.get("SignData");

        Signature sign = new Signature();
        PublicKey publicKey;
        try {
            publicKey = KeyUtil.getKlPubKey();
            boolean flag = sign.verifyByMD5withRSA(plain, signData, publicKey);
            if (!flag) {
                throw new RuntimeException("kl.signature.fail");
            }
        } catch (Exception e) {
            throw new RuntimeException("validation.signature.verification.error");
        }
        return true;
    }

    /**
     * ���ɴ�ǩ�����ַ���
     */
    private String getToSignString(Map map) {

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
            String name = keyList.get(i);
            if (null != map.get(name) && !"".equals(map.get(name))) {
                sbf.append(name + "=").append(map.get(name));
                sbf.append("&");
            }
        }
        sbf.deleteCharAt(sbf.length() - 1);
        return sbf.toString();
    }
}
