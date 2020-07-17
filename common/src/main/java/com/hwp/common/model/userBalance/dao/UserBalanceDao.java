package com.hwp.common.model.userBalance.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userBalance.bean.UserBalance;
import org.springframework.stereotype.Repository;

@Repository
public class UserBalanceDao extends AbstractBaseDao {
    public int deleteByPrimaryKey(String id) {
        return delete("UserBalance.deleteByPrimaryKey", id);
    }

    public UserBalance insert(UserBalance record) {
        insert("UserBalance.insert", record);
        return record;
    }

    public UserBalance insertSelective(UserBalance record) {
        insert("UserBalance.insertSelective", record);
        return record;
    }

    public UserBalance getFindById(String id) {
        return (UserBalance) queryForObject("UserBalance.selectByPrimaryKey", id);
    }

    public UserBalance getFindByUserId(String userId) {
        return (UserBalance) queryForObject("UserBalance.selectByUserId", userId);
    }

    public int updateByPrimaryKeySelective(UserBalance record) {
        return update("UserBalance.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKeyWithBLOBs(UserBalance record) {
        return update("UserBalance.updateByPrimaryKeyWithBLOBs", record);
    }

    public int updateByDataStatus(UserBalance record) {
        return update("UserBalance.updateByDataStatus", record);
    }
}