package com.hwp.common.model.insuranceOrder.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.insuranceOrder.bean.InsuranceOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InsuranceOrderMapper extends AbstractBaseDao {

    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public InsuranceOrder addInsuranceOrder(InsuranceOrder record) {
        insert("InsuranceOrderMapper.addInsuranceOrder", record);
        return record;
    }

    /**
     * @Description 根据Id查找保险
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public InsuranceOrder selectInsuranceOrderById(String id) {
        return (InsuranceOrder) queryForObject("InsuranceOrderMapper.selectInsuranceOrderById", id);
    }

    /**
     * @Description 传入Map获取保险列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<InsuranceOrder> selectProductListByMap(Map<String, Object> record) {
        return queryForList("InsuranceOrderMapper.selectInsuranceOrderListByMap", record);
    }

    /**
     * @Description 根据id修改保险
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateInsuranceOrderById(InsuranceOrder record) {
        return update("InsuranceOrderMapper.updateInsuranceOrderById", record);
    }

    /**
     * @Description 修改产品有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateDataStatusById(InsuranceOrder record) {
        return update("InsuranceOrderMapper.updateDataStatusById", record);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取预约保险列表按照创建时间降序排列
     * @Date 2019/8/22 10:31
     * @Param
     **/
    public List<InsuranceOrder> getInsuranceOrderListByOrderByCreateTimeDesc(Map<String, Object> params) {
        return (List<InsuranceOrder>) queryForList("InsuranceOrderMapper.getInsuranceOrderListByOrderByCreateTimeDesc", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取预约保险列表详情按照创建时间降序排列
     * @Date 2019/9/3 11:05
     * @Param
     **/
    public List<InsuranceOrder> getInsuranceOrderListDetailByOrderByCreateTimeDesc(Map<String, Object> params) {
        return (List<InsuranceOrder>) queryForList("InsuranceOrderMapper.getInsuranceOrderListDetailByOrderByCreateTimeDesc", params);
    }


}