package com.hwp.common.service.insuranceOrderService;

import com.hwp.common.model.insuranceOrder.bean.InsuranceOrder;

import java.util.List;
import java.util.Map;

public interface InsuranceOrderService {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    InsuranceOrder addInsuranceOrder(InsuranceOrder record);

    /**
     * @Description 根据Id查找保险
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    InsuranceOrder selectInsuranceOrderById(String id);

    /**
     * @Description 传入Map获取保险列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    List<InsuranceOrder> selectProductListByMap(Map<String, Object> record);

    /**
     * @Description 根据id修改保险
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateInsuranceOrderById(InsuranceOrder record);

    /**
     * @Description 修改产品有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateDataStatusById(InsuranceOrder record);


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id分页获取用户预约保险列表 根据预约时间倒序
     * @Date 2019/8/21 18:18
     * @Param
     **/
    List<InsuranceOrder> getInsuranceOrderListByUserId(String userId, String startPage, String pageSize);
}
