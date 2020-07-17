package com.hwp.common.model.prodInfo.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.prodInfo.bean.ProdInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdInfoDao extends AbstractBaseDao {

    //删除表中数据一般不用
    public int deleteByPrimaryKey(String id) {
        return delete("ProdInfoMapper.deleteByPrimaryKey", id);
    }

    //添加任意数据
    public ProdInfo insertSelective(ProdInfo record) {
        insert("ProdInfoMapper.insertSelective", record);
        return record;
    }

    //查询某一条数据
    public ProdInfo selectByPrimaryKey(String id) {
        return (ProdInfo) queryForObject("ProdInfoMapper.selectByPrimaryKey", id);
    }

    //根据基金代码查询基金数据
    public ProdInfo selectByProdSubId(String prodSubId) {
        return (ProdInfo) queryForObject("ProdInfoMapper.selectByProdSubId", prodSubId);
    }

    //查询数据列表
    public List<ProdInfo> ListSelectByPrimaryKey(ProdInfo record) {
        return queryForList("ProdInfoMapper.ListSelectByPrimaryKey", record);
    }

    //一次修改个别数据
    public int updateByPrimaryKeySelective(ProdInfo record) {
        return update("ProdInfoMapper.updateByPrimaryKeySelective", record);
    }

    //修改数据有效性
    public int updateByDataStatus(ProdInfo record) {
        return update("ProdInfoMapper.updateByPrimaryKey", record);
    }
}