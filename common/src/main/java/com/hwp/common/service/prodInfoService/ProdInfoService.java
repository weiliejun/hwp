package com.hwp.common.service.prodInfoService;

import com.github.pagehelper.PageInfo;
import com.hwp.common.model.prodInfo.bean.ProdInfo;

public interface ProdInfoService {

    int deleteByPrimaryKey(String id);

    //添加任意数据并返回添加的信息
    public ProdInfo insertSelective(ProdInfo record);

    //查询某一条数据
    ProdInfo selectByPrimaryKey(String id);

    //根据基金代码，基金标识码查询基金数据
    ProdInfo selectByProdSubId(String prodSubId);

    //查询数据列表
    PageInfo<ProdInfo> ListSelectByPrimaryKey(ProdInfo record, Integer pageNum, Integer pageSize);

    //一次修改个别数据
    int updateByPrimaryKeySelective(ProdInfo record);

    //修改数据有效性
    int updateByDataStatus(ProdInfo record);

}
