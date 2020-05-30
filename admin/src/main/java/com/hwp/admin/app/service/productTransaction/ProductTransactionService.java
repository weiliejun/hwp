package com.hwp.admin.app.service.productTransaction;

import com.hwp.common.model.tender.dto.UserAndProductAndTenderDTO;
import com.hwp.common.model.userTransaction.dto.UserAndProductAndTransactionDTO;

import java.util.List;
import java.util.Map;

public interface ProductTransactionService {
    List<UserAndProductAndTenderDTO> listUserAndProductAndTenderByParams(Map<String, Object> params);

    List<UserAndProductAndTransactionDTO> listUserAndProductAndTransactionByParams(Map<String, Object> params);
}
