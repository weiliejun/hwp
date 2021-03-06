package com.hwp.common.model.sysBusinessLog.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.sysBusinessLog.bean.SysBusinessLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SysBusinessLogMapper extends AbstractBaseDao {
    public List findBusinessLogs(Map params) {
        List results = queryForList("monitor.selectBusinessLogs", params);
        return results;
    }

    public void saveBusinessLog(SysBusinessLog log) {
        insert("monitor.insertBusinessLog", log);
    }


}