package com.hwp.admin.app.event.test;

import com.hwp.admin.app.event.CreateXmSendEmailEvent;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath*:config/spring/applicationContext*.xml"})
public class SendEmailEventTest {
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private ApplicationContext applicationContext;


    @Test
    @Transactional
    @Rollback(false)
    public void execute() {
        String[] xmxxglIds = {"20052113514654119579"};

        for (String xmxxglId : xmxxglIds) {
            Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmxxglId);
            applicationContext.publishEvent(new CreateXmSendEmailEvent(xmxxgl));
        }
    }
}
