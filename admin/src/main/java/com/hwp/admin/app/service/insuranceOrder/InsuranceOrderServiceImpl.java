package com.hwp.admin.app.service.insuranceOrder;

import com.hwp.common.model.insuranceOrder.bean.InsuranceOrder;
import com.hwp.common.model.insuranceOrder.dao.InsuranceOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 保险预约Service实现
 *
 * @author 李洪斌
 * @date 2019-8-30 13:16:25
 */
@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService {

    @Autowired
    private InsuranceOrderMapper insuranceOrderMapper;


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取保险预约列表详情数据
     * @Date 2019/9/3 13:56
     * @Param
     **/
    @Override
    public List<InsuranceOrder> listInsuranceOrderByParams(Map<String, Object> params) {
        return insuranceOrderMapper.getInsuranceOrderListDetailByOrderByCreateTimeDesc(params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据id获取保险预约列表
     * @Date 2019/9/3 13:56
     * @Param
     **/
    @Override
    public InsuranceOrder getInsuranceOrderDetailById(String id) {
        return insuranceOrderMapper.selectInsuranceOrderById(id);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改保险预约列表
     * @Date 2019/9/3 13:56
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInsuranceOrder(InsuranceOrder insuranceOrder) {
        insuranceOrderMapper.updateInsuranceOrderById(insuranceOrder);
    }
}
