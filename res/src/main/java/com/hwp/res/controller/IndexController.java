package com.hwp.res.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName UserTopUpController
 * @Description 用一句话说明这个方法做什么
 * @Version 1.0
 * @Author 徐赛平
 * @UpdateDate 2019/3/5 10:22
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex() {

        return "/index";
    }


}
