package com.hwp.admin.app.service.userTransaction;

import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userTransaction.dao.UserTransactionMapper;
import com.hwp.common.model.userTransaction.dto.UserAndTransactionDTO;
import com.hwp.common.model.userTransaction.dto.UserTransactionSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户交易记录Service实现
 *
 * @author 李洪斌
 * @date 2019/7/24 10:28:00
 */
@Service
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    private UserTransactionMapper userTransactionMapper;

    /**
     * 根据动态参数获取用户交易记录列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/24 15:59
     * @Param
     **/
    @Override
    public List<UserTransaction> listUserTransactionByParams(Map<String, Object> params) {
        return userTransactionMapper.listUserTransactionByParams(params);
    }

    /**
     * 根据动态参数获取用户和用户充值记录列表信息
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/24 16:00
     * @Param
     **/
    @Override
    public List<UserAndTransactionDTO> listUserInfoAndTransactionByParams(Map<String, Object> params) {
        return userTransactionMapper.listUserInfoAndTransactionMapByParams(params);
    }

    /**
     * 用户联名卡转入转出 基金申购赎回 理财产品 交易总额
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/8/5 11:54
     * @Param
     **/
    @Override
    public UserTransactionSumDTO getUserTransactionSum() {
        return userTransactionMapper.getUserTransactionSum();
    }
}
