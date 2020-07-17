package com.hwp.common.service.broadcastManage;

import com.github.pagehelper.PageInfo;
import com.hwp.common.model.websiteBulletin.bean.WebsiteBulletin;

import java.util.Map;

public interface BroadcastService {

    PageInfo<WebsiteBulletin> listBroadcastByPage(Map<String, Object> params);
}
