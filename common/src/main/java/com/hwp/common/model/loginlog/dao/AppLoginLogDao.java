package com.hwp.common.model.loginlog.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.loginlog.bean.AppLoginLog;
import com.hwp.common.util.RandomUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class AppLoginLogDao extends AbstractBaseDao {

    public void saveLoginLog(AppLoginLog loginLog) {
        loginLog.setId(RandomUtil.getSerialNumber());
        insert("apploginLog.insertLoginLog", loginLog);
    }

    public void saveLogoffLog(String sessionId, String date, String optionType) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("logoffTime", date);
        params.put("sessionId", sessionId);
        params.put("optionType", optionType);
        update("apploginLog.updateLoginLog", params);
    }

    public void saveLogoffLog(String userId, String channelId, String date, String optionType) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("logoffTime", date);
        params.put("userId", userId);
        params.put("channelId", channelId);
        params.put("optionType", optionType);
        update("apploginLog.updateLoginLogByChannelId", params);
    }

    public List<AppLoginLog> selectByManagerId(String managerId) {
        return (List<AppLoginLog>) queryForList(
                "apploginLog.selectByManagerId", managerId);
    }

    // 查询最后一次登录的日志信息
    public AppLoginLog selectLastLoginByManagerId(String managerId) {
        return (AppLoginLog) queryForObject(
                "apploginLog.selectLastLoginByManagerId", managerId);
    }

    // 查询APP最后一次登录的日志信息
    public AppLoginLog selectLastAppLoginByManagerId(String managerId) {
        return (AppLoginLog) queryForObject(
                "apploginLog.selectLastAppLoginByManagerId", managerId);
    }
}