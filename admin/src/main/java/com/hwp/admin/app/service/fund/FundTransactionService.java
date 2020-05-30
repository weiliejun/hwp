package com.hwp.admin.app.service.fund;

import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userTransaction.dto.UserAndFundAndTransactionDTO;

import java.util.List;
import java.util.Map;

public interface FundTransactionService {
    List<UserTransaction> listUserTransactionByParams(Map<String, Object> params);

    List<UserAndFundAndTransactionDTO> listUserInfoAndFundAndTransactionByParams(Map<String, Object> params);
}
