package com.hwp.admin.app.event;

import com.hwp.admin.app.service.ryxxgl.RyxxglService;
import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
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
public class RybgspSendEmailEventListener implements ApplicationListener<RybgspSendEmailEvent> {

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private RyxxglService ryxxglService;

    @Override
    public void onApplicationEvent(RybgspSendEmailEvent event) {
        final Spsqxx spsqxx = (Spsqxx) event.getSpsqxx();
        Ryxxgl ryxxgl = ryxxglService.getRyxxglByName(spsqxx.getCreatorName());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给相关人员发邮件
                Map<String, String> params = new HashMap<String, String>();
                if (spsqxx.getSpyj().equalsIgnoreCase("同意")) {
                    params.put("spyj", "通过审批");
                } else {
                    params.put("spyj", "被拒绝，拒绝原因：" + spsqxx.getSpms());
                }
                //1、申请人
                mailSenderService.sendEmailMessage(spsqxx.getCreatorId(), "rybgsp", ryxxgl.getGsyx(), params);
            }

        });
        thread.start();
    }
}

