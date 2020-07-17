package com.hwp.common.service.productService;


import com.github.pagehelper.PageInfo;
import com.hwp.common.model.product.bean.Product;

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

    List<Product> selectProductList(Map<String, Object> map);

    /**
     * @Description 根据id修改产品
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateProductById(Product record);

    /**
     * @Description 修改产品有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateDataStatusById(Product record);
}
