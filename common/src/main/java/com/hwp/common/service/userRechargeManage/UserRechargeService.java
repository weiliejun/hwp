package com.hwp.common.service.userRechargeManage;

import com.hwp.common.model.userRecharge.bean.UserRecharge;

import java.util.List;
import java.util.Map;

public interface UserRechargeService {

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 增加用户充值记录
     * @Date 2019/6/24 15:28
     * @Param
     **/
    void addUserRecharge(UserRecharge userRecharge);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改用户充值记录
     * @Date 2019/6/24 15:29
     * @Param
     **/
    void updateUserRecharge(UserRecharge userRecharge);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取充值记录列表
     * @Date 2019/6/24 15:30
     * @Param
     **/
    List<UserRecharge> listUserRechargeByUserId(String userId);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户UUID获取充值记录列表
     * @Date 2019/6/24 15:33
     * @Param
     **/
    List<UserRecharge> listUserRechargeByUUID(String uuid);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户绑定的银行卡号获取充值记录列表
     * @Date 2019/6/24 15:33
     * @Param
     **/
    List<UserRecharge> listUserRechargeByAcNo(String uuid);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户充值记录列表
     * @Date 2019/6/24 15:50
     * @Param
     **/
    List<UserRecharge> listUserRechargeByParams(Map<String, Object> params);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 调用第三方接口用户添加充值信息 添加充值记录和交易记录
     * @Date 2019/6/27 13:58
     * @Param
     **/
    Map<String, Object> addUserRechargeInfo(Map<String, Object> paramInfo);
}
