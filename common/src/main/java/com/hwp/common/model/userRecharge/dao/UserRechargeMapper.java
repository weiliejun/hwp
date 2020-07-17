package com.hwp.common.model.userRecharge.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userRecharge.bean.UserRecharge;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRechargeMapper extends AbstractBaseDao {
    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID查询
     * @Date 2019/6/17 17:19
     * @Param
     **/
    public UserRecharge findById(String userRechargeId) {
        UserRecharge userRecharge = (UserRecharge) queryForObject("userRechargeMapper.selectByPrimaryKey", userRechargeId);
        if (userRecharge != null) {
            return userRecharge;
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
    public void addUserRecharge(UserRecharge userRecharge) {
        insert("userRechargeMapper.insertSelective", userRecharge);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改
     * @Date 2019/6/17 17:22
     * @Param
     **/
    public void updateUserRecharge(UserRecharge userRecharge) {
        update("userRechargeMapper.updateByPrimaryKeySelective", userRecharge);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID删除 业务中删除为修改数据有效性
     * @Date 2019/6/17 17:35
     * @Param
     **/
    public void deleteUserRechargeById(UserRecharge userRecharge) {
        delete("userRechargeMapper.deleteByPrimaryKey", userRecharge.getId());
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户充值记录列表
     * @Date 2019/6/24 16:22
     * @Param
     **/
    public List<UserRecharge> listUserRechargeByParams(Map<String, Object> params) {
        return (List<UserRecharge>) queryForList("userRechargeMapper.listUserRechargeByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据渠道流水获取用户充值记录
     * @Date 2019/7/3 10:13
     * @Param channelJnlNo渠道流水号
     **/
    public UserRecharge getUserRechargeByChannelJnlNo(String channelJnlNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("channelJnlNo", channelJnlNo);
        List<UserRecharge> userRechargeList = listUserRechargeByParams(params);
        if (userRechargeList != null && userRechargeList.size() > 0) {
            return userRechargeList.get(0);
        }
        return null;
    }
}