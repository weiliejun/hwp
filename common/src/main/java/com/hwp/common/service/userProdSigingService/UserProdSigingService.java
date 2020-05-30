package com.hwp.common.service.userProdSigingService;

import com.hwp.common.model.prodSigning.bean.ProdSigning;

public interface UserProdSigingService {

    /**
     * @Description 删除数据
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    int deleteByPrimaryKey(String id);

    /**
     * @Description 提现时扣除余额
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    ProdSigning insert(ProdSigning record);

    /**
     * @Description 提现时扣除余额
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    ProdSigning insertSelective(ProdSigning record);

    /**
     * @Description 根据id询签约基金信息
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    ProdSigning selectByPrimaryKey(String id);

    /**
     * @Description 根据用户ID查询签约基金信息
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    ProdSigning selectProdByUserId(String userId);

    /**
     * @Description 根据UUID查询签约基金信息
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    ProdSigning selectProdByUUId(String uuid);

    /**
     * @Description 修改部分字段
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    int updateByPrimaryKeySelective(ProdSigning record);

    /**
     * @Description 一次修改所有字段
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    int updateByPrimaryKeyWithBLOBs(ProdSigning record);

    /**
     * @Description 修改数据有效性
     * @Author 吕剑
     * @UpdateDate 2019/6/24 10:12
     */
    int updateByDataStatus(ProdSigning record);
}
