package cn.share.emay.sdk.client.api;

import cn.share.b2m.eucp.sdkhttp.SDKServiceBindingStub;
import cn.share.b2m.eucp.sdkhttp.SDKServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class Client {

    SDKServiceBindingStub binding;
    private String softwareSerialNo;
    private String key;

    public Client(String sn, String key) {
        softwareSerialNo = sn;
        this.key = key;
        init();
    }

    public void init() {
        try {
            binding = (SDKServiceBindingStub) (new SDKServiceLocator()).getSDKService();
        } catch (ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
        }
    }

    public int chargeUp(String cardNo, String cardPass) throws RemoteException {
        int value = -1;
        value = binding.chargeUp(softwareSerialNo, key, cardNo, cardPass);
        return value;
    }

    public double getBalance() throws RemoteException {
        double value = 0.0D;
        value = binding.getBalance(softwareSerialNo, key);
        return value;
    }

    public double getEachFee() throws RemoteException {
        double value = 0.0D;
        value = binding.getEachFee(softwareSerialNo, key);
        return value;
    }

    public List getMO() throws RemoteException {
        cn.share.b2m.eucp.sdkhttp.Mo mo[] = binding.getMO(softwareSerialNo, key);
        if (mo == null) {
            return null;
        } else {
            List molist = Arrays.asList(mo);
            return molist;
        }
    }

    public List getReport() throws RemoteException {
        cn.share.b2m.eucp.sdkhttp.StatusReport sr[] = binding.getReport(softwareSerialNo, key);
        if (sr != null)
            return Arrays.asList(sr);
        else
            return null;
    }

    public int logout() throws RemoteException {
        int value = -1;
        value = binding.logout(softwareSerialNo, key);
        return value;
    }

    public int registDetailInfo(String eName, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode) throws RemoteException {
        int value = -1;
        value = binding.registDetailInfo(softwareSerialNo, key, eName, linkMan, phoneNum, mobile, email, fax, address, postcode);
        return value;
    }

    public int registEx(String password) throws RemoteException {
        int value = -1;
        value = binding.registEx(softwareSerialNo, key, password);
        return value;
    }

    public int sendSMS(String mobiles[], String smsContent, String addSerial, int smsPriority) throws RemoteException {
        int value = -1;
        value = binding.sendSMS(softwareSerialNo, key, "", mobiles, smsContent, addSerial, "gbk", smsPriority, 0L);
        return value;
    }

    public int sendScheduledSMSEx(String mobiles[], String smsContent, String sendTime, String srcCharset) throws RemoteException {
        int value = -1;
        value = binding.sendSMS(softwareSerialNo, key, sendTime, mobiles, smsContent, "", srcCharset, 3, 0L);
        return value;
    }

    public int sendSMSEx(String mobiles[], String smsContent, String addSerial, String srcCharset, int smsPriority, long smsID) throws RemoteException {
        int value = -1;
        value = binding.sendSMS(softwareSerialNo, key, "", mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
        return value;
    }

    public String sendVoice(String mobiles[], String smsContent, String addSerial, String srcCharset, int smsPriority, long smsID) throws RemoteException {
        String value = null;
        value = binding.sendVoice(softwareSerialNo, key, "", mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
        return value;
    }

    public int serialPwdUpd(String serialPwd, String serialPwdNew) throws RemoteException {
        int value = -1;
        value = binding.serialPwdUpd(softwareSerialNo, key, serialPwd, serialPwdNew);
        return value;
    }
}
