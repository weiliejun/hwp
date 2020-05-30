package com.hwp.admin.app.service.product;


import com.github.pagehelper.PageInfo;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.websiteBulletin.bean.WebsiteBulletin;

import java.util.List;
import java.util.Map;


public interface ProductService {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    Product addProduct(Product record);

    /**
     * @Description 根据Id查找产品
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    Product getProductById(String id);

    /**
     * @Description 获取产品列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    List<Product> selectProductList(Product record);

    /**
     * @Description 获取产品初始列表
     * @Author 吕剑
     * @UpdateDate 2019/09/17 13:15
     */
    List<Product> selectProductListByRePeat(Product product);

    /**
     * @Description 获取产品列表-分页
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    PageInfo<Product> selectProductListByProduct(Product record, Integer pageNum, Integer pageSize);

    /**
     * @Description 获取产品列表-分页
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    PageInfo<Product> selectProductListByMap(Map<String, Object> map, Integer pageNum, Integer pageSize);

    /**
     * @Description 获取产品录入列表-添加完成未提交审核
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    List<Product> selectProductList(Map<String, Object> map);

    /**
     * @Description 传入Map获取产品审核列表
     * @Author 吕剑
     * @UpdateDate 2019/08/26 10:12
     */
    List<Product> selectProductExamListByMap(Map<String, Object> record);

    /**
     * @Description 传入Map获取产品投资-信息管理列表
     * @Author 吕剑
     * @UpdateDate 2019/08/26 10:12
     */
    List<Product> selectProductManagerListByMap(Map<String, Object> record);

    /**
     * @Description 传入Map获取产品发布管理列表
     * @Author 吕剑
     * @UpdateDate 2019/08/26 10:12
     */
    List<Product> selectProductManagerIssueListByMap(Map<String, Object> record);

    /**
     * @Description 根据id修改产品
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateProductById(Product record);

    /**
     * @Description 根据id修改产品上, 下架状态并添加公告
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateProductByIdAndAddWebsiteBulletin(Product record, WebsiteBulletin websiteBulletin);

    /**
     * @Description 修改产品有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateDataStatusById(Product record);
}
