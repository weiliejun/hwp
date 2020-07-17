package com.hwp.common.model.userTransaction.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userTransaction.dto.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserTransactionMapper extends AbstractBaseDao {


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID查询
     * @Date 2019/6/17 17:19
     * @Param
     **/
    public UserTransaction findById(String userTransactionId) {
        UserTransaction userTransaction = (UserTransaction) queryForObject("userTransactionMapper.selectByPrimaryKey", userTransactionId);
        if (userTransaction != null) {
            return userTransaction;
        }
        return null;
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 添加
     * @Date 2019/6/17 17:21
     * @Param
     **/
    public void addUserTransaction(UserTransaction userTransaction) {
        insert("userTransactionMapper.insertSelective", userTransaction);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改
     * @Date 2019/6/17 17:22
     * @Param
     **/
    public void updateUserTransaction(UserTransaction userTransaction) {
        update("userTransactionMapper.updateByPrimaryKeySelective", userTransaction);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID删除 业务中删除为修改数据有效性
     * @Date 2019/6/17 17:35
     * @Param
     **/
    public void deleteUserTransactionById(UserTransaction userTransaction) {
        delete("userTransactionMapper.deleteByPrimaryKey", userTransaction.getId());
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户交易记录列表
     * @Date 2019/6/25 14:27
     * @Param
     **/
    public List<UserTransaction> listUserTransactionByParams(Map<String, Object> params) {
        return (List<UserTransaction>) queryForList("userTransactionMapper.listUserTransactionByParams", params);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据渠道流水获取用户交易记录信息
     * @Date 2019/7/2 19:06
     * @Param
     **/
    public UserTransaction getUserTransactionByTransId(String transId) {
        Map<String, Object> params = new HashMap<>();
        params.put("transId", transId);
        List<UserTransaction> userTransactions = listUserTransactionByParams(params);
        if (userTransactions != null && userTransactions.size() > 0) {
            return userTransactions.get(0);
        }
        return null;
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取符合的用户交易记录个数
     * @Date 2019/7/2 9:02
     * @Param
     **/
    public int countTransactionByParams(Map<String, Object> params) {
        return (int) queryForObject("userTransactionMapper.countTransactionByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数分页获取用户交易记录列表
     * @Date 2019/7/2 9:07
     * @Param
     **/
    public List<UserTransaction> listUserTransactionByPage(Map<String, Object> params, int startPage, int pageSize) {
        params.put("startPage", startPage);
        params.put("pageSize", pageSize);
        return (List<UserTransaction>) queryForList("userTransactionMapper.listUserTransactionByPage", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户表和交易记录表联查后UserAndTransactionDTO封装
     * @Date 2019/6/25 14:27
     * @Param
     **/
    public List<UserAndTransactionDTO> listUserInfoAndTransactionMapByParams(Map<String, Object> params) {
        return (List<UserAndTransactionDTO>) queryForList("userTransactionMapper.listUserInfoAndTransactionMapByParams", params);
    }

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---银行卡交易记录
     * @Date 2019/8/13 14:27
     * @Param
     **/
    public List<UserTransaction> selectBillBankCardByUserId(Map<String, Object> params) {
        return (List<UserTransaction>) queryForList("userTransactionMapper.selectBillBankCardByUserId", params);
    }

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---基金 交易记录
     * @Date 2019/8/13 14:28
     * @Param
     **/
    public List<UserTransaction> selectBillFundByUserId(Map<String, Object> params) {
        return (List<UserTransaction>) queryForList("userTransactionMapper.selectBillFundByUserId", params);
    }

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---理财产品交易记录
     * @Date 2019/8/13 14:29
     * @Param
     **/
    public List<UserTransaction> selectBillProductByUserId(Map<String, Object> params) {
        return (List<UserTransaction>) queryForList("userTransactionMapper.selectBillProductByUserId", params);
    }

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---全部交易记录
     * @Date 2019/8/13 14:29
     * @Param
     **/
    public List<UserTransaction> selectBillAllByUserId(Map<String, Object> params) {
        return (List<UserTransaction>) queryForList("userTransactionMapper.selectBillAllByUserId", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户表和基金表和交易记录表联查后UserAndFundTransactionDTO封装
     * @Date 2019/8/2 9:59
     * @Param
     **/
    public List<UserAndFundAndTransactionDTO> listUserInfoAndFundAndTransactionMapByParams(Map<String, Object> params) {
        return (List<UserAndFundAndTransactionDTO>) queryForList("userTransactionMapper.listUserInfoAndFundAndTransactionMapByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户表和理财产品表和交易记录表联查后UserAndProductAndTransactionDTO封装
     * @Date 2019/8/2 10:05
     * @Param
     **/
    public List<UserAndProductAndTransactionDTO> listUserInfoAndProductAndTransactionByParams(Map<String, Object> params) {
        return (List<UserAndProductAndTransactionDTO>) queryForList("userTransactionMapper.listUserInfoAndProductAndTransactionByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户联名卡转入转出 基金申购赎回 理财产品 交易总额
     * @Date 2019/8/5 13:25
     * @Param
     **/
    public UserTransactionSumDTO getUserTransactionSum() {
        return (UserTransactionSumDTO) queryForObject("userTransactionMapper.getUserTransactionSum");
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据渠道流水获取用户充值或者提现记录详情DTO记录
     * @Date 2019/8/20 14:27
     * @Param
     **/
    public UserRechargeOrWithdrawDetailDTO getUserRechargeOrWithdrawDetailByOriJnlNo(String oriJnlNo) {
        return (UserRechargeOrWithdrawDetailDTO) queryForObject("userTransactionMapper.getUserRechargeOrWithdrawDetailByOriJnlNo", oriJnlNo);
    }

}