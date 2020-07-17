package com.hwp.common.model.dateInfo.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.dateInfo.bean.DateInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DateInfoMapper extends AbstractBaseDao {
    public DateInfo addDateInfo(DateInfo dateInfo) {
        insert("dateInfo.insertDateInfo", dateInfo);
        return dateInfo;
    }

    public Map addMoreDateInfo(Map map) {
        insert("dateInfo.insertMoreDateInfo", map);
        return map;
    }

    public void deleteDateInfo(String time) {
        delete("dateInfo.deleteDateInfo", time);
    }

    public void deleteDateInfoByYear(String year) {
        delete("dateInfo.deleteDateInfoByYear", year);
    }

    public DateInfo findDateInfoByDate(String time) {
        DateInfo dateInfo = (DateInfo) queryForObject("dateInfo.selectDateInfoByDate", time);
        return dateInfo;
    }

    public List findDateInfoByYear(String year) {
        List dateInfoList = queryForList("dateInfo.selectDateInfoByYear", year);
        return dateInfoList;
    }

    public List<DateInfo> findNextWorkday(String time) {
        List<DateInfo> dateInfoList = queryForList("dateInfo.selectNextWorkday", time);
        return dateInfoList;
    }
}