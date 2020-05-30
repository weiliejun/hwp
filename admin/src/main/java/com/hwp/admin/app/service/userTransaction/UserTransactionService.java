package com.hwp.admin.app.service.userTransaction;

import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userTransaction.dto.UserAndTransactionDTO;
import com.hwp.common.model.userTransaction.dto.UserTransactionSumDTO;

import java.util.List;
import java.util.Map;

public interface UserTransactionService {
    List<UserTransaction> listUserTransactionByParams(Map<String, Object> params);

    List<UserAndTransactionDTO> listUserInfoAndTransactionByParams(Map<String, Object> params);

    UserTransactionSumDTO getUserTransactionSum();
}
