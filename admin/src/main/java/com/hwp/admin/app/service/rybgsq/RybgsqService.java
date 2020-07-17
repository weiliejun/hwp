package com.hwp.admin.app.service.rybgsq;

import com.hwp.common.model.rybgsq.bean.Rybgsq;

import java.util.List;
import java.util.Map;

public interface RybgsqService {
    Rybgsq addRybgsq(Rybgsq ryxxgl);

    Rybgsq getRybgsqById(String id);

    Rybgsq getRybgsqByName(String name);

    void updateRybgsq(Rybgsq ryxxgl);

    List<Rybgsq> listRybgsqByParams(Map<String, Object> params);

    List<Rybgsq> listRybgsqByModel(Rybgsq ryxxgl);

    public Rybgsq selectRybgsqByRyId(String id);
}
