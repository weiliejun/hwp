package com.hwp.common.model.userBankCard.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userBankCard.bean.UserBankCard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserBankCardMapper extends AbstractBaseDao {


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID查询
     * @Date 2019/6/17 17:19
     * @Param
     **/
    public UserBankCard findById(String userBankCardId) {
        UserBankCard userBankCard = (UserBankCard) queryForObject("userBankCardMapper.selectByPrimaryKey", userBankCardId);
        if (userBankCard != null) {
            return userBankCard;
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
    public void addUserBankCard(UserBankCard userBankCard) {
        insert("userBankCardMapper.insertSelective", userBankCard);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改
     * @Date 2019/6/17 17:22
     * @Param
     **/
    public void updateUserBankCard(UserBankCard userBankCard) {
        update("userBankCardMapper.updateByPrimaryKeySelective", userBankCard);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID删除 业务中删除为修改数据有效性
     * @Date 2019/6/17 17:35
     * @Param
     **/
    public void deleteUserBankCardById(UserBankCard userBankCard) {
        delete("userBankCardMapper.deleteByPrimaryKey", userBankCard.getId());
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户列表
     * @Date 2019/6/22 10:11
     * @Param
     **/
    public List<UserBankCard> ListUserBankCardByParams(Map<String, Object> params) {
        return (List<UserBankCard>) queryForList("userBankCardMapper.listUserBankCardByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据卡号获取银行卡信息 绑定账号和未绑定账号都可以查出来
     * @Date 2019/8/19 15:45
     * @Param
     **/
    public UserBankCard getUserBankCardByAcNo(String acNo) {
        return (UserBankCard) queryForObject("userBankCardMapper.getUserBankCardByAcNo", acNo);
    }
}