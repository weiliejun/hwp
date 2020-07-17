package com.hwp.common.model.prodSigning.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.prodSigning.bean.ProdSigning;
import org.springframework.stereotype.Repository;

@Repository
public class ProdSigningDao extends AbstractBaseDao {
    /**
     * @Description 删除数据
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public int deleteByPrimaryKey(String id) {
        return delete("ProdSigning.deleteByPrimaryKey", id);
    }

    /**
     * @Description 一次添加所有字段
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public ProdSigning insert(ProdSigning record) {
        insert("ProdSigning.deleteByPrimaryKey", record);
        return record;
    }

    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public ProdSigning insertSelective(ProdSigning record) {
        insert("ProdSigning.insertSelective", record);
        return record;
    }

    /**
     * @Description 根据id查询签约基金信息
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public ProdSigning selectByPrimaryKey(String id) {
        return (ProdSigning) queryForObject("ProdSigning.selectByPrimaryKey", id);
    }

    /**
     * @Description 根据用户ID查询签约基金信息
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public ProdSigning selectProdByUserId(String userId) {
        return (ProdSigning) queryForObject("ProdSigning.selectProdByUserId", userId);
    }

    /**
     * @Description 根据用户ID查询签约基金信息
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public ProdSigning selectProdByUUId(String uuid) {
        return (ProdSigning) queryForObject("ProdSigning.selectProdByUUId", uuid);
    }

    /**
     * @Description 修改部分字段
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public int updateByPrimaryKeySelective(ProdSigning record) {
        return update("ProdSigning.updateByPrimaryKeySelective", record);
    }

    /**
     * @Description 一次修改所有字段
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public int updateByPrimaryKeyWithBLOBs(ProdSigning record) {
        return update("ProdSigning.updateByPrimaryKeyWithBLOBs", record);
    }

    /**
     * @Description 修改数据有效性
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    public int updateByDataStatus(ProdSigning record) {
        return update("ProdSigning.updateByDataStatus");
    }
}