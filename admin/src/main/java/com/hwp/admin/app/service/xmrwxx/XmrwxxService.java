package com.hwp.admin.app.service.xmrwxx;

import com.hwp.common.model.xmrwxx.bean.Xmrwxx;

import java.util.List;
import java.util.Map;

public interface XmrwxxService {
    Xmrwxx addXmrwxx(Xmrwxx xmrwxx);

    Xmrwxx getXmrwxxById(String id);

    void updateXmrwxx(Xmrwxx xmrwxx);

    List<Xmrwxx> listXmrwxxByParams(Map<String, Object> params);

    List<Xmrwxx> listXmrwxxByModel(Xmrwxx xmrwxx);

}
