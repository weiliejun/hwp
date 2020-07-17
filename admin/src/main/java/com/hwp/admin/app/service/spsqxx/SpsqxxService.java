package com.hwp.admin.app.service.spsqxx;

import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.spsqxx.bean.XmSpsqxx;

import java.util.List;
import java.util.Map;

public interface SpsqxxService {
    Spsqxx addSpsqxx(Spsqxx Spsqxx);

    Spsqxx getSpsqxxById(String id);

    void updateSpsqxx(Spsqxx Spsqxx);

    List<XmSpsqxx> listSpsqxxByParams(Map<String, Object> params);

    List<XmSpsqxx> listSpsqxxByModel(Spsqxx Spsqxx);

    void updateInvalidByGlId(String glId);

    List<XmSpsqxx> listSpsqxxRwfhByParams(Map<String, Object> params);

    List<XmSpsqxx> listSpsqxxRybgByParams(Map<String, Object> params);
}
