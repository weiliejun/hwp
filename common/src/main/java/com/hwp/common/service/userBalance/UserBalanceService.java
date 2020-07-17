package com.hwp.common.service.userBalance;

import com.hwp.common.model.userBalance.bean.UserBalance;
import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userprodExpense.bean.UserProdExpense;

import java.math.BigDecimal;

public interface UserBalanceService {
    int deleteByid(String id);

    UserBalance insertAll(UserBalance record);

    UserBalance insertSelective(UserBalance record);

    UserBalance getFindById(String id);

    UserBalance getFindByUserId(String userId);

    int updateByReduceBalance(BigDecimal decimal, Integer userId);

    //申购工资宝 计算金额
    int updateByTrsAmount(UserBalance record, UserProdExpense userProdExpense, UserTransaction userTransaction);

    int updateByPrimaryKeySelective(UserBalance record);

    int updateByPrimaryKeyWithBLOBs(UserBalance record);

    //修改用户状态为有效
    int updateByDataStatus(UserBalance record);
}
