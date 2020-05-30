package com.hwp.admin.components.message.mail;

import java.util.Map;

public interface MailSenderService {
    /**
     * @Description 发送站内信
     * @auther: xsp
     * @UpadteDate: 2019/3/12 16:15
     */
    Map<String, Object> sendWebSiteMessage(String userId, String busiType, Map<String, String> params);

    void sendMailWithPureText(MailEntity mailEntity);
//
//    boolean sendMimeMessage(MailEntity mailEntity);

    public Map<String, Object> sendEmailMessage(String userId, String busiType, String to, Map<String, String> params);

    public boolean sendEmailMessage(String[] tos, String subject, String content);

    public Map<String, Object> sendEmailMimeMessage(String userId, String busiType, String to, Map<String, String> params, String filePath);

}
