package com.hwp.admin.app.service.productOtherAttachFile;

import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.product.dao.ProductMapper;
import com.hwp.common.model.productOtherAttachFile.bean.ProductOtherAttachFile;
import com.hwp.common.model.productOtherAttachFile.dao.ProductOtherAttachFileMapper;
import com.hwp.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description 产品附件信息
 * @Author 吕剑
 * @UpdateDate 2019/7/31 10:19
 */
@Service
public class ProductOtherAttachFileServiceImpl implements ProductOtherAttachFileService {

    @Autowired
    private ProductOtherAttachFileMapper productOtherAttachFileMapper;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public int addProductAndProductOtherAttachFile(Product product, ProductOtherAttachFile productOtherAttachFile) {
        try {
            product.setTimeLimitUnit("day");//month：月   day：天
            product.setTenderAuditStatus("init");//审核状态 init: 未审核  processing：审核处理中 finish 审核完成
            product.setDataStatus(GlobalConstant.KS_DATA_VALID);
            product.setStatus("init");//初始状态
            product.setAuditStatus("init");//审核状态-init：未提交审核
            product.setTenderAuditStatus("init");//产品标的审核状态-init: 未审核
            product.setPublishStatus("notIssue");//发布状态-暂不发布
            product.setIfPrepay("yes");//默认可提前还款 yes/no
            product.setTenderAmount(new BigDecimal(0.00));//投标总额初始为0.00
            product.setTenderUsers(new BigDecimal(0.00));//投标人数初始为0.00
            productMapper.addProduct(product);
            productOtherAttachFile.setId(RandomUtil.getSerialNumber());
            productOtherAttachFile.setProductId(product.getId());
            productOtherAttachFile.setDataStatus(GlobalConstant.KS_DATA_VALID);
            //附件类型  合同协议
            productOtherAttachFile.setType("productOtherAttachFile");
            addProductOtherAttachFile(productOtherAttachFile);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public ProductOtherAttachFile addProductOtherAttachFile(ProductOtherAttachFile record) {
        return productOtherAttachFileMapper.addProductOtherAttachFile(record);
    }

    @Override
    public ProductOtherAttachFile selectProductOtherAttachFileById(String id) {
        return productOtherAttachFileMapper.selectProductOtherAttachFileById(id);
    }

    @Override
    public ProductOtherAttachFile selectProductOtherAttachFileByProductId(String productId) {
        return productOtherAttachFileMapper.selectProductOtherAttachFileByProductId(productId);
    }

    @Override
    public List<ProductOtherAttachFile> selectProductOtherAttachFileList(ProductOtherAttachFile record) {
        return productOtherAttachFileMapper.selectProductOtherAttachFileList(record);
    }

    @Transactional
    @Override
    public int updateProductAndProductOtherAttachFileById(Product product, ProductOtherAttachFile productOtherAttachFile) {
        try {
            productMapper.updateProductById(product);
            //根据产品id查询产品附件信息
            ProductOtherAttachFile file = selectProductOtherAttachFileByProductId(product.getId());
            file.setId(productOtherAttachFile.getId());
            //附件类型
            productOtherAttachFileMapper.updateProductOtherAttachFileById(file);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public int addProductAndFile(Product product, ProductOtherAttachFile productOtherAttachFile) {
        try {
            productMapper.addProduct(product);
            addProductOtherAttachFile(productOtherAttachFile);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public int updateProductOtherAttachFileById(ProductOtherAttachFile record) {
        return productOtherAttachFileMapper.updateProductOtherAttachFileById(record);
    }

    @Transactional
    @Override
    public int updateDataStatusById(ProductOtherAttachFile record) {
        return productOtherAttachFileMapper.updateDataStatusById(record);
    }

    @Override
    public List<ProductOtherAttachFile> listWdmbByParams(Map<String, Object> params) {
        return productOtherAttachFileMapper.listWdmbByParams(params);
    }
}
