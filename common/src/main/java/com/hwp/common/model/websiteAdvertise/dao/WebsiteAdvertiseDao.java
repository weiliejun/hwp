package com.hwp.common.model.websiteAdvertise.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.websiteAdvertise.bean.WebsiteAdvertise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WebsiteAdvertiseDao extends AbstractBaseDao {

    public WebsiteAdvertise addAdvertise(WebsiteAdvertise record) {
        insert("websiteAdvertise.addAdvertise", record);
        return record;
    }

    public int updateAdvertise(WebsiteAdvertise record) {
        return update("websiteAdvertise.updateAdvertise", record);
    }

    public void updateAdvertiseClicks(String code) {
        update("websiteAdvertise.updateAdvertiseClicks", code);
    }

    public WebsiteAdvertise getAdvertiseById(Integer id) {
        return (WebsiteAdvertise) queryForObject("websiteAdvertise.getAdvertiseById", id);
    }

    public List<WebsiteAdvertise> listAdvertisesByParams(Map<String, String> params) {
        List<WebsiteAdvertise> results = queryForList("websiteAdvertise.listAdvertisesByParams", params);
        return results;
    }

    public List<WebsiteAdvertise> listAdvertisesByKeys(Map<String, Object> params) {
        List<WebsiteAdvertise> results = queryForList("websiteAdvertise.listAdvertisesByKeys", params);
        return results;
    }

}