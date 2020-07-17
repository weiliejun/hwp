package com.hwp.common.model.userWithdraw.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userWithdraw.bean.UserWithdraw;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserWithdrawMapper extends AbstractBaseDao {


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID查询
     * @Date 2019/6/17 17:19
     * @Param
     **/
    public UserWithdraw findById(String userWithdrawId) {
        UserWithdraw userWithdraw = (UserWithdraw) queryForObject("userWithdrawMapper.selectByPrimaryKey", userWithdrawId);
        if (userWithdraw != null) {
            return userWithdraw;
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
    public void addUserWithdraw(UserWithdraw userWithdraw) {
        insert("userWithdrawMapper.insertSelective", userWithdraw);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改
     * @Date 2019/6/17 17:22
     * @Param
     **/
    public void updateUserWithdraw(UserWithdraw userWithdraw) {
        update("userWithdrawMapper.updateByPrimaryKeySelective", userWithdraw);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID删除 业务中删除为修改数据有效性
     * @Date 2019/6/17 17:35
     * @Param
     **/
    public void deleteUserWithdrawById(UserWithdraw userWithdraw) {
        delete("userWithdrawMapper.deleteByPrimaryKey", userWithdraw.getId());
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取提现记录列表
     * @Date 2019/6/26 11:20
     * @Param
     **/
    public List<UserWithdraw> listUserWithdrawByParams(Map<String, Object> params) {
        return (List<UserWithdraw>) queryForList("userWithdrawMapper.listUserWithdrawByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据渠道流水号获取对应的提现记录
     * @Date 2019/7/3 10:52
     * @Param
     **/
    public UserWithdraw getUserWithdrawByChannelJnlNo(String channelJnlNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("channelJnlNo", channelJnlNo);
        List<UserWithdraw> userWithdrawList = listUserWithdrawByParams(params);
        if (userWithdrawList != null && userWithdrawList.size() > 0) {
            return userWithdrawList.get(0);
        }
        return null;
    }

}