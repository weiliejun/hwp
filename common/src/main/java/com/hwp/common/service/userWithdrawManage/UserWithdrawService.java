package com.hwp.common.service.userWithdrawManage;

import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.model.userWithdraw.bean.UserWithdraw;

import java.util.List;
import java.util.Map;

public interface UserWithdrawService {

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 添加用户提现记录
     * @Date 2019/6/26 9:27
     * @Param
     **/
    void addUserWithdraw(UserWithdraw userWithdraw);

    /**
     * @return
     * @Author lvjian
     * @Description //TODO 添加用户提现记录 同时添加交易记录
     * @Date 2019/6/27 9:47
     * @Param
     **/
    boolean addUserWithdrawAndAddUserTransaction(UserWithdraw userWithdraw, UserTransaction userTransaction);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改用户提现记录
     * @Date 2019/6/26 9:31
     * @Param
     **/
    void updateUserWithdraw(UserWithdraw userWithdraw);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取提现记录
     * @Date 2019/6/26 9:32
     * @Param
     **/
    List<UserWithdraw> listUserWithdrawByUserId(String userId);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户uuid获取提现记录
     * @Date 2019/6/26 9:33
     * @Param
     **/
    List<UserWithdraw> listUserWithdrawByUUID(String UUID);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取提现记录
     * @Date 2019/6/26 9:35
     * @Param
     **/
    List<UserWithdraw> listUserWithdrawByParams(Map<String, Object> params);
}
