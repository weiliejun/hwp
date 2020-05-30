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
 * @description 标的审核第四步，签署电子合同
 * @update 2017-3-22 下午3:00:03
 */
@Component
public class TrustSignUploadContractListener implements ApplicationListener<TrustSignUploadContractEvent> {
    private final Logger logger = Logger.getLogger(TrustSignUploadContractListener.class);
    @Autowired
    private TrustSignService trustSignService;

    @Override
    public void onApplicationEvent(TrustSignUploadContractEvent event) {
        final Product product = (Product) event.getSource();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 签署产品借款合同
                trustSignService.trustSignContractSign(product);
            }

        });
        thread.start();
    }
}
