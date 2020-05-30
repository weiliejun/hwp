package com.hwp.res;

import com.hwp.common.web.filter.CustomCookieFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.util.unit.DataSize;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "com.hwp")
public class ResApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ResApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(ResApplication.class);
    }

    @Bean
    public FilterRegistrationBean customCookieFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(customCookieFilter());
        registration.addUrlPatterns("/*");
        registration.setName("customCookieFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

    /**
     * 创建一个bean
     *
     * @return
     */
    @Bean(name = "customCookieFilter")
    public Filter customCookieFilter() {
        return new CustomCookieFilter();
    }


    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.ofMegabytes(30));
        //该方法已降级
        //factory.setMaxRequestSize("30MB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(120));
        return factory.createMultipartConfig();
    }

}
