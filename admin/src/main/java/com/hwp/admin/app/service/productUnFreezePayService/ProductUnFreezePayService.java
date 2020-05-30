package com.hwp.admin.app.service.productUnFreezePayService;

import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.product.entity.PayEntity;
import com.hwp.common.model.product.entity.UnFreezeEntity;
import com.hwp.common.model.sysManager.bean.SysManager;

import java.util.List;
import java.util.Map;

public interface ProductUnFreezePayService {
    /**
     * @Description 传入产品Id 获取解冻 放款人员购买记录
     * @Author 吕剑
     * @UpdateDate 2019/08/20 16:25
     */
    List<UnFreezeEntity> selectUnFreezeByProductId(Map<String, Object> record);

    /**
     * @Description 传入产品Id 获取还款人员名单
     * @Author 吕剑
     * @UpdateDate 2019/08/22 14:55
     */
    List<PayEntity> selectPayEntityProductId(Map<String, Object> record);

    /**
     * @Description 流标相关操作 ---一个产品多个用户的多笔操作
     * @Author 吕剑
     * @UpdateDate 2019/08/20 16:25
     */
    int updateUnFreeze(Product product, SysManager sysManager);


    /**
     * @Description 放款相关操作 ---一个产品多个用户的多笔操作
     * @Author 吕剑
     * @UpdateDate 2019/08/21 16:25
     */
    int updateFreeze(Product product, SysManager sysManager);


    /**
     * @Description 还款相关操作 ---一个产品每个用户的总还款金额操作
     * @Author 吕剑
     * @UpdateDate 2019/08/22 16:25
     */
    int payEntity(Product product, SysManager sysManager);

    /**
     * @Description 还款相关操作 ---一个产品每个用户的每一笔金额操作
     * @Author 吕剑
     * @UpdateDate 2019/08/22 16:25
     */
    int payEntityCount(Product product, SysManager sysManager);
}
