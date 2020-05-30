package com.hwp.admin.app.service.xmxxgl;

import com.hwp.common.model.xmxxgl.bean.Xmxxgl;

import java.util.List;
import java.util.Map;

public interface XmxxglService {
    Xmxxgl addXmxxgl(Xmxxgl xmxxgl);

    Xmxxgl getXmxxglById(String id);

    void updateXmxxgl(Xmxxgl xmxxgl);

    List<Xmxxgl> listXmxxglByParams(Map<String, Object> params);

    List<Xmxxgl> listXmxxglByModel(Xmxxgl xmxxgl);

}
