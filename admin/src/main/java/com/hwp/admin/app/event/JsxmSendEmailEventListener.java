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
public class JsxmSendEmailEventListener implements ApplicationListener<JsxmSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(JsxmSendEmailEvent event) {
        final Xmxxgl xmxxgl = (Xmxxgl) event.getSource();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                params.put("cpmc", xmxxgl.getCpmc());
                params.put("gdlb", xmxxgl.getGdlb());

                //6、审批人
                if (StringHelper.isNotBlank(xmxxgl.getJsxmSpr())) {
                    JSONArray selectSpr = JSONArray.fromObject(xmxxgl.getJsxmSpr());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "jsxm", spr.get("gsyx").toString(), params);
                    }
                }
            }

        });
        thread.start();
    }
}

