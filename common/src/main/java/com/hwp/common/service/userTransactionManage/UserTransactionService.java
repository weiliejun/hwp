package com.hwp.common.service.userTransactionManage;

import com.github.pagehelper.PageInfo;
import com.hwp.common.model.userTransaction.bean.UserTransaction;

import java.util.List;
import java.util.Map;

public interface UserTransactionService {

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 添加用户交易记录
     * @Date 2019/6/25 14:00
     * @Param
     **/
    void addUserTransaction(UserTransaction userTransaction);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改用户交易记录
     * @Date 2019/6/25 14:01
     * @Param
     **/
    void updateUserTransaction(UserTransaction userTransaction);

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---银行卡交易记录-分页
     * @Date 2019/8/13 14:27
     * @Param
     **/
    PageInfo<UserTransaction> selectBillBankCardByUserId(Map<String, Object> params, Integer pageNum, Integer pageSize);

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---基金 交易记录-分页
     * @Date 2019/8/13 14:28
     * @Param
     **/
    PageInfo<UserTransaction> selectBillFundByUserId(Map<String, Object> params, Integer pageNum, Integer pageSize);

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---理财产品交易记录-分页
     * @Date 2019/8/13 14:29
     * @Param
     **/
    PageInfo<UserTransaction> selectBillProductByUserId(Map<String, Object> params, Integer pageNum, Integer pageSize);

    /**
     * @return
     * @Author lvjian
     * @Description //账单中心---全部交易记录-分页
     * @Date 2019/8/13 14:29
     * @Param
     **/
    PageInfo<UserTransaction> selectBillAllByUserId(Map<String, Object> params, Integer pageNum, Integer pageSize);


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取用户交易记录列表
     * @Date 2019/6/25 14:01
     * @Param
     **/
    List<UserTransaction> listUserTransactionByUserId(String userId);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户UUID获取用户交易记录列表
     * @Date 2019/6/25 14:01
     * @Param
     **/
    List<UserTransaction> listUserTransactionByUUID(String uuid);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户交易记录列表
     * @Date 2019/6/25 14:02
     * @Param
     **/
    List<UserTransaction> listUserTransactionByParams(Map<String, Object> params);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取电子账户交易详情
     * @Date 2019/7/2 13:20
     * @Param
     **/
    Map<String, Object> getUserEAccountTransactionDetail(String uuid, String oriJnlNo);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据渠道流水获取对应的用户交易记录
     * @Date 2019/7/3 15:13
     * @Param
     **/
    UserTransaction getUserTransactionByTransId(String oriJnlNo);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据交易流水获取充值或提现详情
     * @Date 2019/8/19 16:19
     * @Param
     **/
    public Map<String, Object> getUserRechargeOrWithdrawDetail(String oriJnlNo);
}
