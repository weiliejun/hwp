package com.hwp.admin.app.service.userWorkInfo;

import com.hwp.common.model.userWorkInfo.bean.UserWorkInfo;

import java.util.List;
import java.util.Map;

public interface UserWorkInfoService {
    UserWorkInfo getUserWorkInfoById(String id);

    void updateUserWorkInfo(UserWorkInfo userWorkInfo);

    List<UserWorkInfo> listUserWorkInfoByParams(Map<String, Object> params);

    Integer getUnAuditUserWorkInfoSum();

    void userWorkInfoAudit(UserWorkInfo userWorkInfo);
}
