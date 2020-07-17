package com.itech.ups.test.JunitTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class BaseDAOTEST {
    ClassPathXmlApplicationContext context = null;

    // 初始化系统环境
    public BaseDAOTEST() {
        context = new ClassPathXmlApplicationContext(new String[]{"classpath:config/spring/applicationContext*.xml"});
        // obj = context.getBean("sqlSessionFactoryBean");
        System.out.println(context);
    }

    public static void main(String[] args) {
        BaseDAOTEST test = new BaseDAOTEST();
        // ProductFundService
        // productfundservice=(ProductFundService)test.getBean("ProductFundService");
        // System.out.println(productfundservice);
        // sys
    }

    // 返回需要的bean
    public Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
