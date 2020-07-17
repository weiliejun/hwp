package com.hwp.admin.app.service.rwczjl;

import com.hwp.common.model.rwczjl.bean.Rwczjl;

import java.util.List;
import java.util.Map;

public interface RwczjlService {
    Rwczjl addRwczjl(Rwczjl ryxxgl);

    Rwczjl getRwczjlById(String id);

    Rwczjl getRwczjlByName(String name);

    void updateRwczjl(Rwczjl ryxxgl);

    List<Rwczjl> listRwczjlByParams(Map<String, Object> params);

    List<Rwczjl> listRwczjlByModel(Rwczjl ryxxgl);

}
