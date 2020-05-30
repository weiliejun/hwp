package com.hwp.admin.app.service.productTransaction;

import com.hwp.common.model.tender.dao.TenderMapper;
import com.hwp.common.model.tender.dto.UserAndProductAndTenderDTO;
import com.hwp.common.model.userTransaction.dao.UserTransactionMapper;
import com.hwp.common.model.userTransaction.dto.UserAndProductAndTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品投标记录Service实现
 *
 * @author 李洪斌
 * @date 2019/7/26 16:04:36
 */
@Service
public class ProductTransactionServiceImpl implements ProductTransactionService {

    @Autowired
    private TenderMapper tenderMapper;

    @Autowired
    private UserTransactionMapper userTransactionMapper;

    /**
     * 根据动态参数获取用户信息和产品信息和产品投标记录
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/26 16:09
     * @Param
     **/
    @Override
    public List<UserAndProductAndTenderDTO> listUserAndProductAndTenderByParams(Map<String, Object> params) {
        return tenderMapper.listUserAndProductAndTenderByParams(params);
    }

    /**
     * 根据动态参数信息获取用户信息和理财产品信息和交易记录信息
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/8/2 13:38
     * @Param
     **/
    @Override
    public List<UserAndProductAndTransactionDTO> listUserAndProductAndTransactionByParams(Map<String, Object> params) {
        return userTransactionMapper.listUserInfoAndProductAndTransactionByParams(params);
    }
}
