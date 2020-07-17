package com.hwp.common.model.tenderRepayment.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.tenderRepayment.bean.TenderRepayment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenderRepaymentMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public TenderRepayment addTenderRepayment(TenderRepayment record) {
        insert("TenderRepaymentMapper.addTenderRepayment", record);
        return record;
    }

    /**
     * @Description 根据Id查找投标还款计划交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public TenderRepayment selectTenderRepaymentById(String id) {
        return (TenderRepayment) queryForObject("TenderRepaymentMapper.selectTenderRepaymentById", id);
    }


    /**
     * @Description 根据tenderId查找投标还款计划交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public TenderRepayment selectTenderRepaymentByTenderId(String tenderId) {
        return (TenderRepayment) queryForObject("TenderRepaymentMapper.selectTenderRepaymentById", tenderId);
    }

    /**
     * @Description 获取投标还款计划交易信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public List<TenderRepayment> selectTenderRepaymentList(TenderRepayment record) {
        return queryForList("TenderRepaymentMapper.selectTenderRepaymentList", record);
    }


    /**
     * @Description 查询每个产品的购买用户和每个用户的购买本金, 购买笔数
     * @Author 吕剑
     * @UpdateDate 2019/07/23 11:02
     */
    public List<TenderRepayment> selectUserByProductId(String productId) {
        return queryForList("TenderRepaymentMapper.selectUserByProductId", productId);
    }

    /**
     * @Description 查询某个产品某个用户购买信息
     * @Author 吕剑
     * @UpdateDate 2019/07/23 11:25
     */
    public List<TenderRepayment> selectUserInfoByProductIdAndUserId(TenderRepayment tenderRepayment) {
        return queryForList("TenderRepaymentMapper.selectUserInfoByProductIdAndUserId", tenderRepayment);
    }

    /**
     * @Description 根据id修改投标还款计划交易交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public int updateTenderRepaymentById(TenderRepayment record) {
        return update("TenderRepaymentMapper.updateTenderRepaymentById", record);
    }

    /**
     * @Description 根据产品id修改投标还款计划交易交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public int updateTenderRepaymentByProductId(TenderRepayment record) {
        return update("TenderRepaymentMapper.updateTenderRepaymentByProductId", record);
    }

    /**
     * @Description 修改投标还款计划交易信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    public int updateDataStatusById(TenderRepayment record) {
        return update("TenderRepaymentMapper.updateDataStatusById", record);
    }

}