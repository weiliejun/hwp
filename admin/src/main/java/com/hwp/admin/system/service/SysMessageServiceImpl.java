package com.hwp.admin.system.service;


import com.hwp.common.model.sysMessage.bean.SysMessage;
import com.hwp.common.model.sysMessage.dao.SysMessageDao;
import com.hwp.common.util.RandomUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysMessageService")
public class SysMessageServiceImpl implements SysMessageService {
    private Logger logger = Logger.getLogger(SysMessageServiceImpl.class);
    @Autowired
    private SysMessageDao sysMessageDao;

    @Autowired
    private SysMessageTmplService sysMessageTmplService;


    public void addSysMessage(SysMessage sysMessage) {
        // Id
        sysMessage.setId(RandomUtil.getSerialNumber());
        sysMessageDao.addSysMessage(sysMessage);
    }

    public int updateSysMessage(SysMessage sysMessage) {
        return sysMessageDao.updateSysMessage(sysMessage);
    }

    public SysMessage getSysMessageById(String id) {
        return sysMessageDao.getSysMessageById(id);
    }

    public List<SysMessage> listSysMessagesByParams(Map<String, Object> params) {
        return sysMessageDao.listSysMessageByParams(params);
    }


}
