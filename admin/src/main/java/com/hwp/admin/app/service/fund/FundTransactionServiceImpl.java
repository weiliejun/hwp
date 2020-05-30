package com.hwp.admin.app.service.fund;

import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userTransaction.dao.UserTransactionMapper;
import com.hwp.common.model.userTransaction.dto.UserAndFundAndTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 货基投资记录Service实现
 *
 * @author 李洪斌
 * @date 2019/7/25 10:28:00
 */
@Service
public class FundTransactionServiceImpl implements FundTransactionService {

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
     * 根据动态参数获取用户和用户所购基金和用户充值记录列表信息
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/24 16:00
     * @Param
     **/
    @Override
    public List<UserAndFundAndTransactionDTO> listUserInfoAndFundAndTransactionByParams(Map<String, Object> params) {
        return userTransactionMapper.listUserInfoAndFundAndTransactionMapByParams(params);
    }
}
