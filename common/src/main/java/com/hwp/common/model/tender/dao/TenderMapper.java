package com.hwp.common.model.tender.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.tender.bean.Tender;
import com.hwp.common.model.tender.dto.UserAndProductAndTenderDTO;
import com.hwp.common.model.tender.pojo.MyProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TenderMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public Tender addTender(Tender record) {
        insert("tenderMapper.addTender", record);
        return record;
    }

    /**
     * @Description 根据Id查找产品投标交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public Tender selectTenderById(String id) {
        return (Tender) queryForObject("tenderMapper.selectTenderById", id);
    }

    /**
     * @Description 查找用户第一次购买产品时间
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public Tender selectFistBuyTimeByUserIdAndProductId(Tender tender) {
        return (Tender) queryForObject("tenderMapper.selectFistBuyTimeByUserIdAndProductId", tender);
    }

    /**
     * @Description 查询每个产品的冻结总额
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<Tender> selectTrsAmountSumByProductId() {
        return queryForList("tenderMapper.selectTrsAmountSumByProductId");
    }

    /**
     * @Description 查询某个用户某个产品的冻结总额
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public Tender selectTrsAmountSumByProductIdAndUserId(Tender tender) {
        return (Tender) queryForObject("tenderMapper.selectTrsAmountSumByProductIdAndUserId");
    }

    /**
     * @Description 查询用户 持有中 理财产品列表
     * @Author 吕剑
     * @UpdateDate 2019/08/8 15:12
     */
    public List<MyProduct> selectMyProductByUserId(MyProduct myProduct) {
        return queryForList("myProductMapper.selectMyProductByUserId", myProduct);
    }

    /**
     * @Description 查询用户 回款中 理财产品列表
     * @Author 吕剑
     * @UpdateDate 2019/08/9 10:20
     */
    public List<MyProduct> selectReturnByUserId(MyProduct myProduct) {
        return queryForList("myProductMapper.selectReturnByUserId", myProduct);
    }


    /**
     * @Description 查询用户 已回款 理财产品列表
     * @Author 吕剑
     * @UpdateDate 2019/08/9 9:20
     */
    public List<MyProduct> selectReturnMyProductByUserId(MyProduct myProduct) {
        return queryForList("myProductMapper.selectReturnMyProductByUserId", myProduct);
    }

    /**
     * @Description 查询 回款中---持有总额
     * @Author 吕剑
     * @UpdateDate 2019/08/9 10:12
     */
    public String selectMyProductAllProductSumByUserId(MyProduct myProduct) {
        return (String) queryForObject("myProductMapper.selectMyProductAllProductSumByUserId", myProduct);
    }


    /**
     * @Description 获取产品投标交易信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<Tender> selectTenderList(Tender record) {
        return queryForList("tenderMapper.selectTenderList", record);
    }

    /**
     * @Description 根据id修改产品投标交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateTenderById(Tender record) {
        return update("tenderMapper.updateTenderById", record);
    }

    /**
     * @Description 根据userId 部分参数 修改产品投标交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateTenderByUserInfoId(Tender record) {
        return update("tenderMapper.updateTenderByUserInfoId", record);
    }

    /**
     * @Description 根据productId 部分参数 修改产品投标交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/23 14:12
     */
    public int updateTenderByProductId(Tender record) {
        return update("tenderMapper.updateTenderByProductId", record);
    }


    /**
     * @Description 修改产品投标交易信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateDataStatusById(Tender record) {
        return update("tenderMapper.updateDataStatusById", record);
    }

    /**
     * 根据动态参数获取用户信息产品信息和产品投标记录信息DTO
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/26 16:54
     * @Param
     **/
    public List<UserAndProductAndTenderDTO> listUserAndProductAndTenderByParams(Map<String, Object> params) {
        return (List<UserAndProductAndTenderDTO>) queryForList("tenderMapper.listUserAndProductAndTenderByParams", params);
    }
}