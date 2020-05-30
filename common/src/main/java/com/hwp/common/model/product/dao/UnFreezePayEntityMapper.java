package com.hwp.common.model.product.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.product.entity.PayEntity;
import com.hwp.common.model.product.entity.UnFreezeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description 用一句话说明这个方法做什么
 * @Author zhangkele
 * @UpdateDate 2019/8/20 16:02
 */
@Repository
public class UnFreezePayEntityMapper extends AbstractBaseDao {


    /**
     * @Description 传入产品Id 获取解冻 放款人员购买记录
     * @Author 吕剑
     * @UpdateDate 2019/08/20 16:19
     */
    public List<UnFreezeEntity> selectUnFreezeByProductId(Map<String, Object> record) {
        return queryForList("unFreezePayEntity.selectUnFreezeByProductId", record);
    }

    /**
     * @Description 传入产品Id 获取还款人员名单
     * @Author 吕剑
     * @UpdateDate 2019/08/21 16:19
     */
    public List<PayEntity> selectPayEntityByProductId(Map<String, Object> record) {
        return queryForList("unFreezePayEntity.selectPayEntityByProductId", record);
    }

}
