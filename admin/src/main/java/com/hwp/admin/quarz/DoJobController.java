package com.hwp.admin.quarz;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/quarz")
public class DoJobController {

    @RequestMapping("/autoAddDateInfo.do")
    public void autoAddDateInfo() {
        AutoAddDateInfo autoAddDateInfo = (AutoAddDateInfo) SpringUtil.getBean("autoAddDateInfo");
        try {
            autoAddDateInfo.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/productQuartz.do")
    public void productQuartz() {
        ProductQuartz productQuartz = (ProductQuartz) SpringUtil.getBean("productQuartz");
        try {
            productQuartz.tenderProductStatusTB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
