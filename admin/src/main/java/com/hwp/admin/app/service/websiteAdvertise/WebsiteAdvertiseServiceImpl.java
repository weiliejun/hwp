package com.hwp.admin.app.service.websiteAdvertise;

import com.hwp.common.model.websiteAdvertise.bean.WebsiteAdvertise;
import com.hwp.common.model.websiteAdvertise.dao.WebsiteAdvertiseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 首页轮播图配置
 * @Author lvjian
 * @UpdateDate 2019/8/30 10:52
 */
@Service
public class WebsiteAdvertiseServiceImpl implements WebsiteAdvertiseService {

    @Autowired
    private WebsiteAdvertiseDao websiteAdvertiseDao;

    @Override
    public WebsiteAdvertise addAdvertise(WebsiteAdvertise record) {
        return websiteAdvertiseDao.addAdvertise(record);
    }

    @Override
    public int updateAdvertise(WebsiteAdvertise record) {
        return websiteAdvertiseDao.updateAdvertise(record);
    }

    @Override
    public void updateAdvertiseClicks(String code) {
        websiteAdvertiseDao.updateAdvertiseClicks(code);
    }

    @Override
    public WebsiteAdvertise getAdvertiseById(Integer id) {
        return websiteAdvertiseDao.getAdvertiseById(id);
    }

    @Override
    public List<WebsiteAdvertise> listAdvertisesByParams(Map<String, String> params) {
        return websiteAdvertiseDao.listAdvertisesByParams(params);
    }

    @Override
    public List<WebsiteAdvertise> listAdvertisesByKeys(Map<String, Object> params) {
        return websiteAdvertiseDao.listAdvertisesByKeys(params);
    }
}
