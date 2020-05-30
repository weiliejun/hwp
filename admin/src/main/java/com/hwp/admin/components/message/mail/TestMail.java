package com.hwp.admin.components.message.mail;

import com.hwp.admin.quarz.SpringUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2014-3-29
 * @author  zqs
 * ===========================================================================
 *
 */

public class TestMail extends TestCase {

    public ApplicationContext applicationContext;

    public static void main(String[] args) {
        TestMail sg = new TestMail();
        sg.test();
    }

    protected void setUp() throws Exception {
        super.setUp();
//        applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext*.xml");
    }

    @Test
    public void test() {
//        MailSenderService service = (MailSenderService) applicationContext.getBean("mailSenderService");
        MailSenderService service = (MailSenderService) SpringUtil.getBean("mailSenderService");

        MailEntity mailEntity = new MailEntity();
        mailEntity.setTo("weiliejun@163.com");
        mailEntity.setText("测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mailEntity.setSubject("测试");

        service.sendMailWithPureText(mailEntity);
    }
}
