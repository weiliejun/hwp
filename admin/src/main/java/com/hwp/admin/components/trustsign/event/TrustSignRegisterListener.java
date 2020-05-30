package com.hwp.admin.components.trustsign.event;

import com.hwp.admin.components.trustsign.service.TrustSignService;
import com.hwp.common.model.product.bean.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author 张可乐
 * @version 1.0
 * @description 标的审核第一步，安心签用户注册事件
 * @update 2017-3-22 下午3:00:03
 */
@Component
public class TrustSignRegisterListener implements ApplicationListener<TrustSignRegisterEvent> {
    private final Logger logger = Logger.getLogger(TrustSignRegisterListener.class);
    @Autowired
    private TrustSignService trustSignService;

    @Override
    public void onApplicationEvent(TrustSignRegisterEvent event) {
        final Product product = (Product) event.getSource();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                trustSignService.trustSignRegister(product);
            }

        });
        thread.start();
    }
}
