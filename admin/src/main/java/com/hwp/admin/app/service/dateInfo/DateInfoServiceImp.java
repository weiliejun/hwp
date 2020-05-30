package com.hwp.admin.app.service.dateInfo;

import com.hwp.common.model.dateInfo.bean.DateInfo;
import com.hwp.common.model.dateInfo.dao.DateInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("dateInfoService")
public class DateInfoServiceImp implements DateInfoService {

    @Autowired
    private DateInfoMapper repository;

    @Override
    @Transactional
    public DateInfo addDateInfo(DateInfo dateInfo) {
        return repository.addDateInfo(dateInfo);
    }

    @Override
    @Transactional
    public Map addMoreDateInfo(Map map) {
        return repository.addMoreDateInfo(map);
    }

    @Override
    @Transactional
    public void deleteDateInfo(String time) {
        repository.deleteDateInfo(time);
    }

    @Override
    @Transactional
    public void deleteDateInfoByYear(String year) {
        repository.deleteDateInfoByYear(year);
    }

    @Override
    public DateInfo findDateInfoByDate(String time) {
        return repository.findDateInfoByDate(time);
    }

    @Override
    public List findDateInfoByYear(String year) {
        return repository.findDateInfoByYear(year);
    }

}
