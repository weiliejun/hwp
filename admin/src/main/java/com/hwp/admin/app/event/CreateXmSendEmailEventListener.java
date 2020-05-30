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
public class CreateXmSendEmailEventListener implements ApplicationListener<CreateXmSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(CreateXmSendEmailEvent event) {
        final Xmxxgl xmxxgl = (Xmxxgl) event.getSource();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                params.put("cpmc", xmxxgl.getCpmc());
                params.put("xmfzr", xmxxgl.getXmfzrName());
                params.put("xmjbr", xmxxgl.getXmjbrName());
                params.put("fwfzr", xmxxgl.getFwfzrName());
                params.put("cwfzr", xmxxgl.getCwfzrName());
                params.put("xmqtcy", xmxxgl.getXmqtcyName());
                params.put("spr", xmxxgl.getSprName());
           /* //循环取出项目其他成员
            StringBuilder xmqtcyName = new StringBuilder();
            JSONArray selectXmqtcy = JSONArray.fromObject(xmxxgl.getXmqtcy());
            for(int i=0;i<selectXmqtcy.size();i++){
                JSONObject job = selectXmqtcy.getJSONObject(i);
                xmqtcyName.append(job.getString("name")).append(",");
            }
            params.put("qtxmcy",xmqtcyName.toString());
            //循环取出项目其他成员
            StringBuilder sprName = new StringBuilder();
            JSONArray selectSpr = JSONArray.fromObject(xmxxgl.getSpr());
            for(int i=0;i<selectSpr.size();i++){
                JSONObject job = selectSpr.getJSONObject(i);
                sprName.append(job.getString("name")).append(",");
            }
            params.put("spr",sprName.toString());*/
                //1、项目负责人
                JSONObject xmfzr = JSONObject.fromObject(xmxxgl.getXmfzrXx());
                mailSenderService.sendEmailMessage(xmfzr.get("id").toString(), "createXm", xmfzr.get("gsyx").toString(), params);
                //2、项目经办人
                JSONObject xmjbr = JSONObject.fromObject(xmxxgl.getXmjbrXx());
                mailSenderService.sendEmailMessage(xmjbr.get("id").toString(), "createXm", xmjbr.get("gsyx").toString(), params);
                //3、法务负责人
                JSONObject fwfzr = JSONObject.fromObject(xmxxgl.getFwfzrXx());
                mailSenderService.sendEmailMessage(fwfzr.get("id").toString(), "createXm", fwfzr.get("gsyx").toString(), params);
                //4、财务负责人
                JSONObject cwfzr = JSONObject.fromObject(xmxxgl.getCwfzrXx());
                mailSenderService.sendEmailMessage(cwfzr.get("id").toString(), "createXm", cwfzr.get("gsyx").toString(), params);
                //5、其他项目成员
                JSONArray selectXmqtcy = JSONArray.fromObject(xmxxgl.getXmqtcy());
                for (int i = 0; i < selectXmqtcy.size(); i++) {
                    JSONObject xmqtcy = selectXmqtcy.getJSONObject(i);
                    mailSenderService.sendEmailMessage(xmqtcy.get("id").toString(), "createXm", xmqtcy.get("gsyx").toString(), params);
                }
                //6、审批人
                if (StringHelper.isNotBlank(xmxxgl.getSpr())) {
                    JSONArray selectSpr = JSONArray.fromObject(xmxxgl.getSpr());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "createXm", spr.get("gsyx").toString(), params);
                    }
                }
            }

        });
        thread.start();
    }
}

