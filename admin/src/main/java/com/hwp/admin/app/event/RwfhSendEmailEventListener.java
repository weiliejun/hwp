package com.hwp.admin.app.event;

import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送邮件
 *
 * @author Administrator
 */
@Component
public class RwfhSendEmailEventListener implements ApplicationListener<RwfhSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(RwfhSendEmailEvent event) {
        final Xmxxgl xmxxgl = (Xmxxgl) event.getXmxxgl();
        final Xmrwxx xmrwxx = (Xmrwxx) event.getXmrwxx();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                params.put("cpmc", xmxxgl.getCpmc());
                params.put("xmjd", xmrwxx.getXmjd());
                params.put("title", xmrwxx.getTitle());
                params.put("fzrName", xmrwxx.getFzrName());
                params.put("status", xmrwxx.getStatus());
                params.put("startDate", xmrwxx.getStartDate());
                params.put("endDate", xmrwxx.getEndDate());
                params.put("rwxq", getRwxq(xmrwxx.getRwxq()));
                params.put("rwms", xmrwxx.getRwms());
                params.put("fhrName", xmrwxx.getFhrName());
                params.put("zhrName", xmrwxx.getZhrName());
                //1、复核人
                if (StringHelper.isNotBlank(xmrwxx.getFhrXx())) {
                    JSONArray selectSpr = JSONArray.fromObject(xmrwxx.getFhrXx());
                    for (int i = 0; i < selectSpr.size(); i++) {
                        JSONObject spr = selectSpr.getJSONObject(i);
                        mailSenderService.sendEmailMessage(spr.get("id").toString(), "rwfh", spr.get("gsyx").toString(), params);
                    }
                }
            }

        });
        thread.start();
    }

    private String getRwxq(String jsonStr) {
        net.sf.json.JSONArray selectSpr = net.sf.json.JSONArray.fromObject(jsonStr);
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < selectSpr.size(); i++) {
            net.sf.json.JSONObject rwxq = selectSpr.getJSONObject(i);
            if(i>0){
                sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            sb.append(rwxq.getString("zrwxh")).append("、").append(rwxq.getString("rwmc"));
            if(i<=selectSpr.size()-2){
                sb.append("<br>");
            }
        }
        return sb.toString();
    }
}

