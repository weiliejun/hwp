package com.hwp.admin.app.service.dateInfo;

import com.hwp.common.model.dateInfo.bean.DateInfo;

import java.util.List;
import java.util.Map;

public interface DateInfoService {

    DateInfo addDateInfo(DateInfo dateInfo);

    Map addMoreDateInfo(Map map);

    void deleteDateInfo(String time);

    void deleteDateInfoByYear(String year);

    DateInfo findDateInfoByDate(String time);

    List findDateInfoByYear(String year);

}