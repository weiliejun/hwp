package com.hwp.common.model.prodrevenueStatus.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.prodrevenueStatus.bean.ProdRevenueStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdRevenueStatusDao extends AbstractBaseDao {

    public int deleteByPrimaryKey(String id) {
        return delete("ProdRevenueStatus.deleteByPrimaryKey", id);
    }

    /**
     * @Description 根据动态参数插入表
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */

    public ProdRevenueStatus insertSelective(ProdRevenueStatus record) {
        insert("ProdRevenueStatus.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据ID查询数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    public ProdRevenueStatus selectByPrimaryKey(String id) {
        return (ProdRevenueStatus) queryForObject("ProdRevenueStatus.selectByPrimaryKey", id);
    }

    /**
     * @Description 根据UserID查询数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    public ProdRevenueStatus selectByUserId(ProdRevenueStatus prodRevenueStatus) {
        return (ProdRevenueStatus) queryForObject("ProdRevenueStatus.selectByUserId", prodRevenueStatus);
    }

    /**
     * @Description 根据UUID查询数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    public List<ProdRevenueStatus> selectByUUId(String uuid) {
        return queryForList("ProdRevenueStatus.selectByUUId", uuid);
    }

    /**
     * @Description 根据动态参数修改表数据
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    public int updateByPrimaryKeySelective(ProdRevenueStatus record) {
        return update("ProdRevenueStatus.updateByPrimaryKeySelective", record);
    }

    /**
     * @Description 修改数据有效性
     * @auther: 吕剑
     * @UpadteDate: 2019/6/29 16:59
     */
    public int updateByDateStatus(ProdRevenueStatus record) {
        return update("ProdRevenueStatus.updateByDateStatus", record);
    }
}