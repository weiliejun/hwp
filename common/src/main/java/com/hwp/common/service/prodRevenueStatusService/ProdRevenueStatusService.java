package com.hwp.common.service.prodRevenueStatusService;

import com.hwp.common.model.prodrevenueStatus.bean.ProdRevenueStatus;

import java.util.List;

public interface ProdRevenueStatusService {

    int deleteByPrimaryKey(String id);

    /**
     * @Description 根据动态参数插入表
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    ProdRevenueStatus insertSelective(ProdRevenueStatus record);

    /**
     * @Description 根据ID查询数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    ProdRevenueStatus selectByPrimaryKey(String id);

    /**
     * @Description 根据UserID查询数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    ProdRevenueStatus selectByUserId(String userId, String creatTime);

    /**
     * @Description 根据UUID查询数据列表
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    List<ProdRevenueStatus> selectByUUId(String uuid);

    /**
     * @Description 根据动态参数修改表数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    int updateByPrimaryKeySelective(ProdRevenueStatus record);

    /**
     * @Description 修改数据有效性
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    int updateByDateStatus(ProdRevenueStatus record);

}
