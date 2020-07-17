package com.hwp.admin.app.event;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.model.rybgsq.bean.Rybgsq;
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
public class RybgsqSendEmailEventListener implements ApplicationListener<RybgsqSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(RybgsqSendEmailEvent event) {
        final Rybgsq rybgsq = (Rybgsq) event.getRybgsq();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                params.put("creatorName", rybgsq.getCreatorName());
                params.put("sqnr", rybgsq.getSqnr());
                params.put("bgsm", rybgsq.getBgsm());
                params.put("sprName", rybgsq.getSprName());
                params.put("zhrName", rybgsq.getZhrName());

                //1、审批人
                if (StringHelper.isNotBlank(rybgsq.getSpr())) {
                    JSONArray selectSpr = JSONArray.fromObject(rybgsq.getSpr());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "rybgsq", spr.get("gsyx").toString(), params);
                    }
                }
                //2、知会人
                if (StringHelper.isNotBlank(rybgsq.getZhr())) {
                    JSONArray selectZhr = JSONArray.fromObject(rybgsq.getZhr());
                    for (int i = 0; i < selectZhr.size(); i++) {
                        JSONObject zhr = selectZhr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(zhr.get("id").toString(), "rybgzh", zhr.get("gsyx").toString(), params);
                    }
                }
            }

        });
        thread.start();
    }
}

