package com.hwp.common.model.userAuthenticationInfo.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userAuthenticationInfo.bean.UserAuthenticationInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthenticationInfoMapper extends AbstractBaseDao {
    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID查询
     * @Date 2019/6/17 17:19
     * @Param
     **/
    public UserAuthenticationInfo findById(String userAuthenticationInfoId) {
        UserAuthenticationInfo userAuthenticationInfo = (UserAuthenticationInfo) queryForObject("userAuthenticationInfoMapper.selectByPrimaryKey", userAuthenticationInfoId);
        if (userAuthenticationInfo != null) {
            return userAuthenticationInfo;
        }
        return null;
    }


    /**
     * @return
     * @Author 吕剑
     * @Description //TODO 根据ID查询
     * @Date 2019/6/21 17:06
     * @Param
     **/
    public UserAuthenticationInfo findByIdNo(String userAuthenticationInfoIdNo) {
        UserAuthenticationInfo userAuthenticationInfo = (UserAuthenticationInfo) queryForObject("userAuthenticationInfoMapper.selectByIdNo", userAuthenticationInfoIdNo);
        if (userAuthenticationInfo != null) {
            return userAuthenticationInfo;
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
    public void addUserAuthenticationInfo(UserAuthenticationInfo userAuthenticationInfo) {
        insert("userAuthenticationInfoMapper.insertSelective", userAuthenticationInfo);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改--根据userId
     * @Date 2019/6/17 17:22
     * @Param
     **/
    public void updateUserAuthenticationInfo(UserAuthenticationInfo userAuthenticationInfo) {
        update("userAuthenticationInfoMapper.updateByPrimaryKeySelective", userAuthenticationInfo);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID删除 业务中删除为修改数据有效性
     * @Date 2019/6/17 17:29
     * @Param
     **/
    public void deleteUserAuthenticationInfoById(UserAuthenticationInfo userAuthenticationInfo) {
        delete("userAuthenticationInfoMapper.deleteByPrimaryKey", userAuthenticationInfo.getId());
    }
}