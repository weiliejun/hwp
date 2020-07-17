package com.hwp.admin.app.service.rwbzjl;

import com.hwp.common.model.rwbzjl.bean.Rwbzjl;

import java.util.List;
import java.util.Map;

public interface RwbzjlService {
    Rwbzjl addRwbzjl(Rwbzjl ryxxgl);

    Rwbzjl getRwbzjlById(String id);

    Rwbzjl getRwbzjlByName(String name);

    void updateRwbzjl(Rwbzjl ryxxgl);

    List<Rwbzjl> listRwbzjlByParams(Map<String, Object> params);

    List<Rwbzjl> listRwbzjlByModel(Rwbzjl ryxxgl);

}
