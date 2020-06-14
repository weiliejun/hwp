package com.hwp.admin.app.service.ryxxgl;

import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.ryxxgl.bean.RyxxglSelect;

import java.util.List;
import java.util.Map;

public interface RyxxglService {
    Ryxxgl addRyxxgl(Ryxxgl ryxxgl);

    Ryxxgl getRyxxglById(String id);

    Ryxxgl getRyxxglByName(String name);

    void updateRyxxgl(Ryxxgl ryxxgl);

    List<Ryxxgl> listRyxxglByParams(Map<String, Object> params);

    List<Ryxxgl> listRyxxglByModel(Ryxxgl ryxxgl);

    List<RyxxglSelect> selectList(Map<String, Object> params);


}
