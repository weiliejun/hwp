package com.hwp.admin.components.message.sms;

import cn.exclusive.emay.sdk.client.api.Client;
import com.hwp.admin.components.message.exception.MessageSenderFailedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component
public class SmsClient {

    private String softwareSerialNo;

    private String key;

    private Client client = null;

//    @Value("${wjkInvestPortalSystemUrl}")
//    private String wjkInvestPortalSystemUrl;

    public synchronized Client getClient() {
        if (client == null) {
            try {
                client = new Client(softwareSerialNo, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSoftwareSerialNo() {
        return softwareSerialNo;
    }

    public void setSoftwareSerialNo(String softwareSerialNo) {
        this.softwareSerialNo = softwareSerialNo;
    }

    public boolean sendSms(String mobile, String smsContent) throws MessageSenderFailedException {
        boolean result = false;
        /*if (!wjkInvestPortalSystemUrl.equalsIgnoreCase("https://www.vjinke.com")) {
            return true;
        }*/
        try {
            int i = this.getClient().sendSMS(new String[]{mobile}, smsContent, "", 5);
            if (i == 0) {
                result = true;
            }
            if (i != 0) {
                throw new MessageSenderFailedException("信息发送失败!errorCode:" + i);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new MessageSenderFailedException("信息发送失败!" + e.getMessage());
        }

        return result;
    }

    public boolean sendVoice(String[] mobiles, String smsContent) {
        boolean result = false;
        /*if (!wjkInvestPortalSystemUrl.equalsIgnoreCase("https://www.vjinke.com")) {
            return true;
        }*/
        try {
            String resultMsg = this.getClient().sendVoice(mobiles, smsContent, "", "GBK", 5, 0);
            if (StringUtils.isBlank(resultMsg)) {
                throw new MessageSenderFailedException("信息发送失败，返回码为空！");
            } else {
                String resultCode = resultMsg.split(":")[0];
                if (resultCode.equals("0")) {
                    result = true;
                } else {
                    throw new MessageSenderFailedException("信息发送失败！errorCode:" + resultCode);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return result;
    }
}