package com.hwp.admin.system.service;


import com.hwp.common.model.sysMessage.bean.SysMessage;

import java.util.List;
import java.util.Map;

public interface SysMessageService {

    void addSysMessage(SysMessage sysMessage);

    int updateSysMessage(SysMessage sysMessage);

    SysMessage getSysMessageById(String id);

    List<SysMessage> listSysMessagesByParams(Map<String, Object> params);

}
