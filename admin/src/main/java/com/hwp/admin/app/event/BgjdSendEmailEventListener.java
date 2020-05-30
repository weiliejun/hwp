package com.hwp.admin.app.event;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.StringHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送邮件
 *
 * @author Administrator
 */
@Component
public class BgjdSendEmailEventListener implements ApplicationListener<BgjdSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(BgjdSendEmailEvent event) {
        final Xmxxgl xmxxgl = (Xmxxgl) event.getXmxxgl();
        final String xmjd1 = (String) event.getXmjd1();
        final String xmjd2 = (String) event.getXmjd2();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                params.put("cpmc", xmxxgl.getCpmc());
                params.put("xmjd1", xmjd1);
                params.put("xmjd2", xmjd2);

                //1、项目负责人
                JSONObject xmfzr = JSONObject.fromObject(xmxxgl.getXmfzrXx());
                mailSenderService.sendEmailMessage(xmfzr.get("id").toString(), "bgjd", xmfzr.get("gsyx").toString(), params);
                //2、项目经办人
                JSONObject xmjbr = JSONObject.fromObject(xmxxgl.getXmjbrXx());
                mailSenderService.sendEmailMessage(xmjbr.get("id").toString(), "bgjd", xmjbr.get("gsyx").toString(), params);
                //3、法务负责人
                JSONObject fwfzr = JSONObject.fromObject(xmxxgl.getFwfzrXx());
                mailSenderService.sendEmailMessage(fwfzr.get("id").toString(), "bgjd", fwfzr.get("gsyx").toString(), params);
                //4、财务负责人
                JSONObject cwfzr = JSONObject.fromObject(xmxxgl.getCwfzrXx());
                mailSenderService.sendEmailMessage(cwfzr.get("id").toString(), "bgjd", cwfzr.get("gsyx").toString(), params);
                //5、其他项目成员
                JSONArray selectXmqtcy = JSONArray.fromObject(xmxxgl.getXmqtcy());
                for (int i = 0; i < selectXmqtcy.size(); i++) {
                    JSONObject xmqtcy = selectXmqtcy.getJSONObject(i);
                    mailSenderService.sendEmailMessage(xmqtcy.get("id").toString(), "bgjd", xmqtcy.get("gsyx").toString(), params);
                }
                //6、审批人
                if (StringHelper.isNotBlank(xmxxgl.getSpr())) {
                    JSONArray selectSpr = JSONArray.fromObject(xmxxgl.getSpr());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "modifyXm", spr.get("gsyx").toString(), params);
                    }
                }
            }

        });
        thread.start();
    }
}

