package com.hwp.admin.app.event.test;

import com.hwp.admin.app.event.CreateXmSendEmailEvent;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SendEmailEventHTTPTest {
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 创建项目发邮件
     *
     * @param xmxxglId
     * @return
     */
    @RequestMapping("/xmxxgl/test")
    public @ResponseBody
    Map<String, String> execute(String xmxxglId) {
        Map<String, String> map = new HashMap<String, String>();
        Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmxxglId);
        map.put("xmxxglId", xmxxgl.getId());
        try {
            applicationContext.publishEvent(new CreateXmSendEmailEvent(xmxxgl));
            map.put("flag", "success");
        } catch (Exception e) {
            map.put("flag", "fail");
            e.printStackTrace();
        }
        return map;
    }


}
