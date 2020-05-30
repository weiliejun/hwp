package com.hwp.common.model.product.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.product.bean.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductMapper extends AbstractBaseDao {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public Product addProduct(Product record) {
        insert("ProductMapper.addProduct", record);
        return record;
    }

    /**
     * @Description 根据Id查找产品
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public Product getProductById(String id) {
        return (Product) queryForObject("ProductMapper.getProductById", id);
    }

    /**
     * @Description 传入对象获取产品列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<Product> selectProductListByProduct(Product record) {
        return queryForList("ProductMapper.selectProductList", record);
    }

    /**
     * @Description 传入Map获取产品初始列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public List<Product> selectProductListByMap(Map<String, Object> record) {
        return queryForList("ProductMapper.selectProductListByMap", record);
    }

    /**
     * @Description 获取产品初始列表
     * @Author 吕剑
     * @UpdateDate 2019/09/17 13:15
     */
    public List<Product> selectProductListByRePeat(Product product) {
        return queryForList("ProductMapper.selectProductListByRePeat", product);
    }


    /**
     * @Description 传入Map获取产品审核列表
     * @Author 吕剑
     * @UpdateDate 2019/08/26 10:12
     */
    public List<Product> selectProductExamListByMap(Map<String, Object> record) {
        return queryForList("ProductMapper.selectProductExamListByMap", record);
    }

    /**
     * @Description 传入Map获取产品投资-信息管理列表
     * @Author 吕剑
     * @UpdateDate 2019/08/26 10:12
     */
    public List<Product> selectProductManagerListByMap(Map<String, Object> record) {
        return queryForList("ProductMapper.selectProductManagerListByMap", record);
    }

    /**
     * @Description 传入Map获取产品发布管理列表
     * @Author 吕剑
     * @UpdateDate 2019/08/26 10:12
     */
    public List<Product> selectProductManagerIssueListByMap(Map<String, Object> record) {
        return queryForList("ProductMapper.selectProductManagerIssueListByMap", record);
    }

    /**
     * @Description 根据id修改产品
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateProductById(Product record) {
        return update("ProductMapper.updateProductById", record);
    }

    /**
     * @Description 修改产品有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    public int updateDataStatusById(Product record) {
        return update("ProductMapper.updateDataStatusById", record);
    }

}