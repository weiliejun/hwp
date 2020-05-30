package com.hwp.admin.system.service;

import com.hwp.common.web.bean.CurrentManager;

import java.util.List;
import java.util.Map;

public interface MonitorService {

    List findBusinessLogs(Map params);

    void saveBusinessLog(CurrentManager currentManager, String functionModule, String functionDescription, String operationData);


}
