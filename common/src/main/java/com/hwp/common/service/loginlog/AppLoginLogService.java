package com.hwp.common.service.loginlog;

import com.hwp.common.model.loginlog.bean.AppLoginLog;
import com.hwp.common.web.bean.CurrentUser;

import java.util.List;

public interface AppLoginLogService {
    // 记录登录日志
    void saveLoginLog(CurrentUser currentUser);

    // 登出日志
    void saveLogoffLog(CurrentUser currentUser);

    // 登出日志
    void saveLogoffLogByChannelId(CurrentUser currentUser);

    List<AppLoginLog> selectByManagerId(String managerId);

    AppLoginLog selectLastLoginByManagerId(String managerId);

    AppLoginLog selectLastAppLoginByManagerId(String managerId);
}
