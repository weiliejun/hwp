package com.hwp.common.service.userProdExpenseService;

import com.hwp.common.model.userprodExpense.bean.UserProdExpense;

import java.util.List;

public interface UserProdExpenseService {
    /*
     * 删除数据
     * */
    int deleteByPrimaryKey(String id);

    /*
     * 一次性加入所有数据
     * */
    UserProdExpense insert(UserProdExpense record);

    /*
     * 加入个别数据
     * */
    UserProdExpense insertSelective(UserProdExpense record);

    /*
     * 根据Id查询
     * */
    UserProdExpense selectByPrimaryKey(String id);

    /*
     * 根据usedId查询
     * */
    List<UserProdExpense> selectByUserId(String userId);

    /*
     * 根据原交易流水号查询
     * */
    UserProdExpense selectByOriJnlNo(String oriJnlNo);

    /*
     * 修改个别参数
     * */
    int updateByPrimaryKeySelective(UserProdExpense record);

    /*
     * 一次性修改所有字段
     * */
    int updateByPrimaryKeyWithBLOBs(UserProdExpense record);

    /*
     * 修改数据有效性
     * */
    int updateByDataStatus(UserProdExpense record);
}
