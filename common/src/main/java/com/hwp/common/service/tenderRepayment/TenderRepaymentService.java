package com.hwp.common.service.tenderRepayment;

import com.hwp.common.model.tenderRepayment.bean.TenderRepayment;

import java.util.List;

/**
 * @Description 还款计划表
 * @Author lvjian
 * @UpdateDate 2019/9/2 15:12
 */
public interface TenderRepaymentService {

    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    TenderRepayment addTenderRepayment(TenderRepayment record);

    /**
     * @Description 根据Id查找投标还款计划交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    TenderRepayment selectTenderRepaymentById(String id);

    /**
     * @Description 根据tenderId查找投标还款计划交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    TenderRepayment selectTenderRepaymentByTenderId(String tenderId);

    /**
     * @Description 获取投标还款计划交易信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    List<TenderRepayment> selectTenderRepaymentList(TenderRepayment record);


    /**
     * @Description 查询每个产品的购买用户和每个用户的购买本金, 购买笔数
     * @Author 吕剑
     * @UpdateDate 2019/07/23 11:02
     */
    List<TenderRepayment> selectUserByProductId(String productId);

    /**
     * @Description 查询某个产品某个用户购买信息
     * @Author 吕剑
     * @UpdateDate 2019/07/23 11:25
     */
    List<TenderRepayment> selectUserInfoByProductIdAndUserId(TenderRepayment tenderRepayment);

    /**
     * @Description 根据id修改投标还款计划交易交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    int updateTenderRepaymentById(TenderRepayment record);

    /**
     * @Description 根据产品id修改投标还款计划交易交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    int updateTenderRepaymentByProductId(TenderRepayment record);

    /**
     * @Description 修改投标还款计划交易信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 16:02
     */
    int updateDataStatusById(TenderRepayment record);
}
