package com.hwp.admin.components.message.mail;

import com.hwp.admin.system.service.SysMessageService;
import com.hwp.admin.system.service.SysMessageTmplService;
import com.hwp.common.components.exception.ParameterNullPointerException;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.sysMessage.bean.SysMessage;
import com.hwp.common.model.sysMessage.dao.SysMessageDao;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service("mailSenderService")
public class MailSenderServiceImp implements MailSenderService {
    private Logger logger = Logger.getLogger(MailSenderServiceImp.class);

    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private SysMessageTmplService sysMessageTmplService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器

    @Value("${spring.mail.username}")
    private String fromMail; // 定义发送邮箱地址

    public JavaMailSender getMailSender() {
        return javaMailSender;
    }

    public void setMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    /**
     * @Description 发送站内信
     * @auther: xsp
     * @UpadteDate: 2019/3/12 16:43
     */
    public Map<String, Object> sendWebSiteMessage(String userId, String busiType, Map<String, String> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String type = GlobalConstant.MessageType.WEBSITE;

        Map<String, String> tmpl = sysMessageTmplService.tmplAssignment(busiType, type, params);

        SysMessage sysMessage = new SysMessage();

        if (userId == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "参数userId不能为空");
            return resultMap;
            //throw new ParameterNullPointerException("userId");
        }
        sysMessage.setUserId(userId);
        sysMessage.setBusiType(busiType);
        sysMessage.setType(type);

        String title = tmpl.get("title");
        if (StringUtils.isBlank(title)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "未定义title模板内容！");
            return resultMap;
            //throw new TemplateInexistenceException("未定义title模板内容！");
        }
        sysMessage.setTopic(title);

        String content = tmpl.get("content");
        if (StringUtils.isBlank(content)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "未定义content模板内容！");
            return resultMap;
            //throw new TemplateInexistenceException("未定义content模板内容！");
        }
        sysMessage.setContent(content);
        sysMessage.setDataStatus(GlobalConstant.KS_DATA_VALID);
        sysMessage.setCreateTime(new Date());
        sysMessage.setStatus(GlobalConstant.STATUS_INVALID);

        sysMessageService.addSysMessage(sysMessage);

        resultMap.put("flag", "true");
        resultMap.put("msg", "发送成功");
        return resultMap;
    }

    public Map<String, Object> sendEmailMessage(String userId, String busiType, String to, Map<String, String> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String type = GlobalConstant.MessageType.EMAIL;
        MailEntity mailEntity = new MailEntity();
        Map<String, String> tmpl = sysMessageTmplService.tmplAssignment(busiType, type, params);

        SysMessage sysMessage = new SysMessage();

        if (userId == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "参数userId不能为空");
            return resultMap;
            //throw new ParameterNullPointerException("userId");
        }
        sysMessage.setUserId(userId);
        sysMessage.setBusiType(busiType);
        sysMessage.setType(type);

        if (StringUtils.isBlank(to)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "收件人不能为空");
            return resultMap;
            // throw new ParameterNullPointerException("to");
        }
        mailEntity.setTo(to);

        String title = tmpl.get("title");
        if (StringUtils.isBlank(title)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "未定义title模板内容！");
            return resultMap;
            //throw new TemplateInexistenceException("未定义title模板内容！");
        }
        sysMessage.setTopic(title);
        mailEntity.setSubject(title);

        String content = tmpl.get("content");
        if (StringUtils.isBlank(content)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "未定义content模板内容！");
            return resultMap;
            //throw new TemplateInexistenceException("未定义content模板内容！");
        }
        sysMessage.setContent(content);
        sysMessage.setDataStatus(GlobalConstant.KS_DATA_VALID);
        sysMessage.setCreateTime(new Date());
        sysMessage.setStatus(GlobalConstant.STATUS_INVALID);
        mailEntity.setText(content);
        boolean result = false;
        try {
            sendMailWithPureText(mailEntity);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        if (result) {
            sysMessageService.addSysMessage(sysMessage);
            resultMap.put("flag", "true");
            resultMap.put("msg", "发送成功");
        } else {
            resultMap.put("flag", "false");
            resultMap.put("msg", "发送失败");
        }
        return resultMap;

    }

    public boolean sendEmailMessage(String[] tos, String subject, String content) {
        boolean result = false;

        if (ArrayUtils.isEmpty(tos)) {
            return result;
            // throw new ParameterNullPointerException("tos");
        }

        for (int i = 0; i < tos.length; i++) {
            String to = tos[i];
            if (StringUtils.isNotBlank(to)) {
                MailEntity mailEntity = new MailEntity();
                mailEntity.setTo(to);
                mailEntity.setSubject(subject);
                mailEntity.setText(content);
                try {
                    sendMailWithPureText(mailEntity);
                    result = result & result;
                } catch (Exception e) {
                    result = false;
                    result = result & result;
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * @param busiType
     * @param to
     * @param params   邮件模板里面需要的参数
     * @param filePath 多文件是文件路径之间以“,”分隔
     * @return
     * @throws ParameterNullPointerException
     * @description 发送带附件的邮件
     * @version 1.0
     * @author 徐赛平
     * @update 2018年2月9日 下午3:50:15
     */
    public Map<String, Object> sendEmailMimeMessage(String userId, String busiType, String to, Map<String, String> params, String filePath) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String type = GlobalConstant.MessageType.EMAIL;
        SysMessage sysMessage = new SysMessage();

        if (userId == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "参数userId不能为空");
            return resultMap;
        }
        sysMessage.setUserId(userId);
        sysMessage.setBusiType(busiType);
        sysMessage.setType(type);
        if (StringUtils.isBlank(to)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "收件人不能为空");
            return resultMap;
        }


        MailEntity mailEntity = new MailEntity();
        mailEntity.setTo(to);

        Map<String, String> tmpl = sysMessageTmplService.tmplAssignment(busiType, type, params);
        String title = tmpl.get("title");
        if (StringUtils.isBlank(title)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "未定义title模板内容！");
            return resultMap;
            //throw new TemplateInexistenceException("未定义title模板内容！");
        }
        sysMessage.setTopic(title);
        mailEntity.setSubject(title);

        String content = tmpl.get("content");
        if (StringUtils.isBlank(content)) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "未定义content模板内容！");
            return resultMap;
        }
        sysMessage.setContent(content);
        sysMessage.setDataStatus(GlobalConstant.KS_DATA_VALID);
        sysMessage.setCreateTime(new Date());
        sysMessage.setStatus(GlobalConstant.STATUS_INVALID);
        mailEntity.setText(content);
        mailEntity.setFilePath(filePath);

        boolean result = false;
        try {
            sendMimeMailWithFile(mailEntity);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        if (result) {
            sysMessageService.addSysMessage(sysMessage);
            resultMap.put("flag", "true");
            resultMap.put("msg", "发送成功");
        } else {
            resultMap.put("flag", "false");
            resultMap.put("msg", "发送失败");
        }
        return resultMap;

    }


    private boolean sendMailBySynchronizationMode(MailEntity mailEntity) {
        Boolean isSuccess = false; // 表示发送状态
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(fromMail, "华物北京");
            helper.setTo(mailEntity.getTo());
            helper.setSubject(mailEntity.getSubject());
            helper.setText(mailEntity.getText(), true);
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
            javaMailSender.send(helper.getMimeMessage());
            isSuccess = true;
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            isSuccess = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 异步发送
     */
    public void sendMailWithPureText(final MailEntity mailEntity) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMailBySynchronizationMode(mailEntity);
                } catch (Exception e) {
                    logger.info(e);
                }
            }
        });
    }

    /**
     * 异步发送
     */
    public void sendMimeMailWithFile(final MailEntity mailEntity) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                try {
                    sendMimeMessage(mailEntity);
                } catch (Exception e) {
                    logger.info(e);
                }
            }
        });
    }

    /**
     * 发送带附件的邮件
     *
     * @param
     * @throws Exception
     */
    private boolean sendMimeMessage(final MailEntity mailEntity) {
        Boolean isSuccess = false; // 表示发送状态
        try {
            // 附件文件集合
            final List<String> files = new ArrayList<String>();
            MimeMessagePreparator mimeMail = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws javax.mail.MessagingException {
                    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailEntity.getTo()));
                    mimeMessage.setFrom(new InternetAddress(fromMail));
                    mimeMessage.setSubject(mailEntity.getSubject(), "UTF-8");

                    Multipart mp = new MimeMultipart();

                    // 设置邮件内容，创建存放能容的BodyPart对象
                    BodyPart mdp = new MimeBodyPart();
                    mdp.setContent(mailEntity.getText(), "text/html;charset=UTF-8");

                    mp.addBodyPart(mdp);
                    files.add(mailEntity.getFilePath());

                    // 向Multipart添加附件
                    Iterator<String> it = files.iterator();
                    while (it.hasNext()) {
                        MimeBodyPart attachFile = new MimeBodyPart();
                        String filename = it.next().toString();

                        FileDataSource fds = new FileDataSource(filename);
                        attachFile.setDataHandler(new DataHandler(fds));
                        try {
                            int pos = filename.lastIndexOf(File.separator);
                            filename = filename.substring(pos + 1);
                            attachFile.setFileName(MimeUtility.encodeWord(filename));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        mp.addBodyPart(attachFile);
                    }

                    files.clear();

                    mimeMessage.setContent(mp);
                    mimeMessage.setSentDate(new Date());
                }
            };
            javaMailSender.send(mimeMail);

            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }
}