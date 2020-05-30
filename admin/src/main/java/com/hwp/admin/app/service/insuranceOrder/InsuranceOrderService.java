package com.hwp.admin.app.service.insuranceOrder;

import com.hwp.common.model.insuranceOrder.bean.InsuranceOrder;

import java.util.List;
import java.util.Map;

public interface InsuranceOrderService {
    List<InsuranceOrder> listInsuranceOrderByParams(Map<String, Object> params);

    InsuranceOrder getInsuranceOrderDetailById(String id);

    void updateInsuranceOrder(InsuranceOrder insuranceOrder);
}
