package com.hwp.common.model.userprodExpense.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userprodExpense.bean.UserProdExpense;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProdExpenseDao extends AbstractBaseDao {

    /*
     * 删除数据
     * */
    public int deleteByPrimaryKey(String id) {
        return delete("UserProdExpense.deleteByPrimaryKey");
    }

    /*
     * 一次性加入所有数据
     * */
    public UserProdExpense insert(UserProdExpense record) {
        insert("UserProdExpense.insert", record);
        return record;
    }

    /*
     * 加入个别数据
     * */
    public UserProdExpense insertSelective(UserProdExpense record) {
        insert("UserProdExpense.insertSelective", record);
        return record;
    }

    /*
     * 根据Id查询
     * */
    public UserProdExpense selectByPrimaryKey(String id) {
        return (UserProdExpense) queryForObject("UserProdExpense.selectByPrimaryKey", id);
    }

    /*
     * 根据usedId查询
     * */
    public List<UserProdExpense> selectByUserId(String userId) {
        return (List<UserProdExpense>) queryForObject("UserProdExpense.selectByUserId", userId);
    }

    /*
     * 根据原交易流水号查询
     * */
    public UserProdExpense selectByOriJnlNo(String oriJnlNo) {
        return (UserProdExpense) queryForObject("UserProdExpense.selectByOriJnlNo", oriJnlNo);
    }

    /*
     * 修改个别参数
     * */
    public int updateByPrimaryKeySelective(UserProdExpense record) {
        return update("UserProdExpense.updateByPrimaryKeySelective", record);
    }

    /*
     * 一次性修改所有字段
     * */
    public int updateByPrimaryKeyWithBLOBs(UserProdExpense record) {
        return update("UserProdExpense.updateByPrimaryKeyWithBLOBs", record);
    }

    /*
     * 修改数据有效性
     * */
    public int updateByDataStatus(UserProdExpense record) {
        return update("UserProdExpense.updateByDataStatus", record);
    }
}