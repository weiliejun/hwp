package com.hwp.common.service.userWorkInfo;

import com.hwp.common.model.userWorkInfo.bean.UserWorkInfo;

import java.util.List;
import java.util.Map;

public interface UserWorkInfoService {
    UserWorkInfo getUserWorkInfoById(String id);

    void updateUserWorkInfo(UserWorkInfo userWorkInfo);

    List<UserWorkInfo> listUserWorkInfoByParams(Map<String, Object> params);

    Integer getUnAuditUserWorkInfoSum();

    void userWorkInfoAudit(UserWorkInfo userWorkInfo);

    /**
     * @return
     * @Author lvjian
     * @Description // 根据身份证号获取用户工作信息
     * @Date 2019/9/17 14:31
     * @Param
     **/
    UserWorkInfo selectWorkInfoByIdNo(UserWorkInfo userWorkInfo);
}
