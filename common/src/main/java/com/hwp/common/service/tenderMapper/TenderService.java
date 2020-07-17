package com.hwp.common.service.tenderMapper;

import com.github.pagehelper.PageInfo;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.tender.bean.Tender;
import com.hwp.common.model.tender.pojo.MyProduct;
import com.hwp.common.model.user.bean.UserInfo;

import java.math.BigDecimal;
import java.util.List;

public interface TenderService {

    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    Tender addTender(Tender record);

    /**
     * @Description 同时向投资表，交易记录表添加数据并修改账户余额
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int addTenderAndTransactionAndUpdateBalance(Tender tender, Product product, UserInfo userInfo, BigDecimal trsBalance);

    /**
     * @Description 根据Id查找产品投标交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    Tender selectTenderById(String id);

    /**
     * @Description 查找用户第一次购买产品时间
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    Tender selectFistBuyTimeByUserIdAndProductId(Tender tender);

    /**
     * @Description 查询用户 持有中 理财产品列表
     * @Author 吕剑
     * @UpdateDate 2019/08/8 15:12
     */
    List<MyProduct> selectMyProductByUserId(MyProduct myProduct);

    PageInfo<MyProduct> selectMyProductByUserId(MyProduct myProduct, Integer pageNum, Integer pageSize);

    /**
     * @Description 查询用户 已回款 理财产品列表
     * @Author 吕剑
     * @UpdateDate 2019/08/9 9:20
     */
    List<MyProduct> selectReturnMyProductByUserId(MyProduct myProduct);

    PageInfo<MyProduct> selectReturnMyProductByUserId(MyProduct myProduct, Integer pageNum, Integer pageSize);

    /**
     * @Description 查询 回款中---持有总额
     * @Author 吕剑
     * @UpdateDate 2019/08/9 10:12
     */
    String selectMyProductAllProductSumByUserId(MyProduct myProduct);

    /**
     * @Description 查询用户 回款中 理财产品列表
     * @Author 吕剑
     * @UpdateDate 2019/08/9 10:20
     */
    List<MyProduct> selectReturnByUserId(MyProduct myProduct);

    PageInfo<MyProduct> selectReturnByUserId(MyProduct myProduct, Integer pageNum, Integer pageSize);

    /**
     * @Description 查询每个产品的冻结总额
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    List<Tender> selectTrsAmountSumByProductId();

    /**
     * @Description 获取产品投标交易信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    List<Tender> selectTenderList(Tender record);

    PageInfo<Tender> selectTenderList(Tender record, Integer pageNum, Integer pageSize);

    /**
     * @Description 根据id修改产品投标交易信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateTenderById(Tender record);

    /**
     * @Description 修改产品投标交易信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateDataStatusById(Tender record);

}
