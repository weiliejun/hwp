package com.hwp.common.service.userBankCardManage;

import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.userBankCard.bean.UserBankCard;

import java.util.List;
import java.util.Map;

public interface UserBankCardService {


    /**
     * @return
     * @Author 吕剑
     * @Description //TODO 修改用户表 同时绑定因银行卡用户添加绑定银行卡信息
     * @Date 2019/6/22 9:40--
     * @Param
     **/
    void addUserBankCardAndUpdateUserInfo(UserInfo userById, Map<String, String> bankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户添加默认绑定银行卡信息
     * @Date 2019/6/22 9:40
     * @Param
     **/
    void addUserBankCard(UserBankCard userBankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户修改默认绑定银行卡信息
     * @Date 2019/6/22 9:40
     * @Param
     **/
    void updateUserBankCard(UserBankCard UserBankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户ID获取用户绑定的默认银行卡信息
     * @Date 2019/6/22 9:41
     * @Param
     **/
    UserBankCard getUserBankCardByUserId(String userId);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户UUID获取用户绑定的默认银行卡信息
     * @Date 2019/6/22 9:41
     * @Param
     **/
    UserBankCard getUserBankCardByUUID(String uuid);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据银行卡号获取用户银行卡信息
     * @Date 2019/6/22 9:41
     * @Param
     **/
    UserBankCard getUserBankCardByBankCardId(String bankCardId);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户银行卡列表
     * @Date 2019/6/24 16:10
     * @Param
     **/
    List<UserBankCard> ListUserBankCardListByParam(Map<String, Object> params);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 同步用户默认银行卡信息和添加用户绑定银行卡
     * @Date 2019/6/26 15:43
     * @Param
     **/
    void updateUserInfoAndUserBankCard(UserInfo userInfo, UserBankCard userBankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户充值签约发送短信验证码
     * @Date 2019/7/4 10:43
     * @Param
     **/
    Map<String, String> sendPaySignedVerifyCode(String uuid, UserBankCard userBankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户充值协议签约
     * @Date 2019/7/4 14:29
     * @Param
     **/
    Map<String, String> userPaySigned(String uuid, String smsCode, UserBankCard userBankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户充值签约状态
     * @Date 2019/7/5 16:08
     * @Param
     **/
    Map<String, String> getPaySignedStatus(String uuid, UserBankCard userBankCard);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 调用银行接口根据卡号获取用户银行卡信息
     * @Date 2019/7/6 15:37
     * @Param
     **/
    Map<String, String> getBankInfoByAcNo(String newAcNo);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户变更绑定银行卡
     * @Date 2019/7/9 15:35
     * @Param
     **/
    Map<String, String> userChangeBankCard(UserInfo userInfo, Map<String, String> requestMap, Map<String, String> bankCardInfo);

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO App变更银行卡获取短信验证码
     * @Date 2019/7/26 15:17
     * @Param
     **/
    Map<String, String> appChangeBankCardSendCode(String userId, String mobile);
}
