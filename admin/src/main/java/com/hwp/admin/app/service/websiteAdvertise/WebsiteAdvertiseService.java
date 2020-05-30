package com.hwp.admin.app.service.websiteAdvertise;

import com.hwp.common.model.websiteAdvertise.bean.WebsiteAdvertise;

import java.util.List;
import java.util.Map;


public interface WebsiteAdvertiseService {
    /**
     * @Description 添加轮播图
     * @Author lvjian
     * @UpdateDate 2019/8/30 10:27
     */
    WebsiteAdvertise addAdvertise(WebsiteAdvertise record);

    /**
     * @Description 修改轮播图信息
     * @Author lvjian
     * @UpdateDate 2019/8/30 10:27
     */
    int updateAdvertise(WebsiteAdvertise record);

    /**
     * @Description 轮播图点击量
     * @Author lvjian
     * @UpdateDate 2019/8/30 10:27
     */
    void updateAdvertiseClicks(String code);

    /**
     * @Description 查询轮播图具体信息
     * @Author lvjian
     * @UpdateDate 2019/8/30 10:27
     */
    WebsiteAdvertise getAdvertiseById(Integer id);

    /**
     * @Description 轮播图信息列表
     * @Author lvjian
     * @UpdateDate 2019/8/30 10:27
     */
    List<WebsiteAdvertise> listAdvertisesByParams(Map<String, String> params);

    /**
     * @Description 轮播图信息列表
     * @Author lvjian
     * @UpdateDate 2019/8/30 10:27
     */
    List<WebsiteAdvertise> listAdvertisesByKeys(Map<String, Object> params);
}
