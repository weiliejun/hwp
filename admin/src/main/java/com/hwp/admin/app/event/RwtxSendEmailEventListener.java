package com.hwp.admin.app.event;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
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
public class RwtxSendEmailEventListener implements ApplicationListener<RwtxSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(RwtxSendEmailEvent event) {
        final Xmxxgl xmxxgl = (Xmxxgl) event.getXmxxgl();
        final Xmrwxx xmrwxx = (Xmrwxx) event.getXmrwxx();
        final String rwmc = (String) event.getRwmc();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                params.put("cpmc", xmxxgl.getCpmc());
                params.put("xmjd", xmrwxx.getXmjd());
                params.put("title", xmrwxx.getTitle());
                params.put("fzrName", xmrwxx.getFzrName());
                params.put("rwmc", rwmc);
                params.put("startDate", xmrwxx.getStartDate());
                params.put("endDate", xmrwxx.getEndDate());
                //1、复核人
                /*if (StringHelper.isNotBlank(xmrwxx.getFhrXx())) {
                    JSONArray selectSpr = JSONArray.fromObject(xmrwxx.getFhrXx());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "rwtx", spr.get("gsyx").toString(), params);
                    }
                }*/
                //2、知会人
                /*if (StringHelper.isNotBlank(xmrwxx.getZhrXx())) {
                    JSONArray selectSpr = JSONArray.fromObject(xmrwxx.getZhrXx());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "rwtx", spr.get("gsyx").toString(), params);
                    }
                }*/
                //3、任务负责人
                JSONObject fzr = JSONObject.fromObject(xmrwxx.getFzrXx());
                mailSenderService.sendEmailMessage(fzr.get("id").toString(), "rwtx", fzr.get("gsyx").toString(), params);
            }

        });
        thread.start();
    }
}

