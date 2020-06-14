package com.hwp.admin.quarz;

import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.product.dao.ProductMapper;
import com.hwp.common.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description 定时开启募集时间
 * @Author lvjian
 * @UpdateDate 2019/8/26 18:03
 */
@Component(value = "productQuartz")
public class ProductQuartz {

    @Autowired
    private ProductMapper productMapper;

//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        //创建工作详情
//        JobDetail detail=context.getJobDetail();
//        //获取当前时间
//        Product product=new Product();
//        product.setStatus("init");
//        List<Product> products = productService.selectProductList(product);
//        String today = DateHelper.getYMDFormatDate(new Date());
//        //获取工作的名称
//        String name=detail.getJobDataMap().getString("name");
//        String job=detail.getKey().getGroup();
//        for (int i = 0; i < products.size(); i++) {
//            if (today.equalsIgnoreCase(products.get(i).getTenderStartTime())){
//                product.setId(products.get(i).getId());
//                product.setStatus("tender");
//                productService.updateProductById(product);
//                System.out.println("任务调度：组："+job+",工作名："+name+"产品状态修改完成！");
//            }
//        }
//    }

    //    @Scheduled(cron = "* 5 14 * * ?")
    public void tenderProductStatusTB() {
        System.out.println("定时任务执行中----------------");
        //获取当前时间
        Product product = new Product();
        product.setStatus("init");
        product.setTenderAuditStatus("finish");//审核完成
        product.setAuditStatus("success");//审核成功
        List<Product> products = productMapper.selectProductListByProduct(product);
        String today = DateHelper.getYMDFormatDate(new Date());
        for (int i = 0; i < products.size(); i++) {
            if (today.equalsIgnoreCase(products.get(i).getTenderStartTime())) {
                product.setId(products.get(i).getId());
                product.setStatus("tender");
                productMapper.updateProductById(product);
                System.out.println("任务调度---------------------------------产品状态修改完成！");
            }
        }
    }

}
