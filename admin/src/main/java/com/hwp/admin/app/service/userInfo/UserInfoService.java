package com.hwp.admin.app.service.userInfo;

import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dto.UserInfoDetailDTO;
import com.hwp.common.model.userTransaction.bean.UserTransaction;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    UserInfo getUserInfoById(String id);

    List<UserInfo> listUserInfoByParams(Map<String, Object> params);

    UserInfoDetailDTO getUserInfoDetailDTOByUserId(String userId);

    List<UserTransaction> getUserTransactionByUserId(String userId, Map<String, Object> otherParams);

    UserInfo getUserInfoByParams(Map<String, Object> params);
}
