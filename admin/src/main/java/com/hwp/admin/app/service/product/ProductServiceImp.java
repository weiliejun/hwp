package com.hwp.admin.app.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.product.dao.ProductMapper;
import com.hwp.common.model.websiteBulletin.bean.WebsiteBulletin;
import com.hwp.common.model.websiteBulletin.dao.WebsiteBulletinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 理财产品
 * @Author 吕剑
 * @UpdateDate 2019/7/18 17:10
 */
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private WebsiteBulletinDao WebsiteBulletinDao;

    @Transactional
    @Override
    public Product addProduct(Product record) {
        return productMapper.addProduct(record);
    }

    /**
     * @Description 根据id修改产品上, 下架状态并添加公告
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    @Transactional
    @Override
    public int updateProductByIdAndAddWebsiteBulletin(Product record, WebsiteBulletin websiteBulletin) {
        try {
            //修改产品发布状态
            updateProductById(record);
            //添加公告
            WebsiteBulletinDao.addWebsiteBulletin(websiteBulletin);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.getProductById(id);
    }

    @Override
    public List<Product> selectProductList(Product record) {
        return productMapper.selectProductListByProduct(record);
    }

    @Override
    public List<Product> selectProductList(Map<String, Object> map) {
        return productMapper.selectProductListByMap(map);
    }

    @Override
    public List<Product> selectProductListByRePeat(Product product) {
        return productMapper.selectProductListByRePeat(product);
    }

    @Override
    public List<Product> selectProductExamListByMap(Map<String, Object> record) {
        return productMapper.selectProductExamListByMap(record);
    }

    @Override
    public List<Product> selectProductManagerListByMap(Map<String, Object> record) {
        return productMapper.selectProductManagerListByMap(record);
    }

    @Override
    public List<Product> selectProductManagerIssueListByMap(Map<String, Object> record) {
        return productMapper.selectProductManagerIssueListByMap(record);
    }

    @Override
    public PageInfo<Product> selectProductListByProduct(Product record, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectProductListByProduct(record);
        return new PageInfo<>(products);
    }

    @Override
    public PageInfo<Product> selectProductListByMap(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectProductListByMap(map);
        return new PageInfo<>(products);
    }

    @Transactional
    @Override
    public int updateProductById(Product record) {
        return productMapper.updateProductById(record);
    }

    @Transactional
    @Override
    public int updateDataStatusById(Product record) {
        return productMapper.updateProductById(record);
    }
}
