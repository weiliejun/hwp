package com.hwp.common.model.userWorkInfo.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userWorkInfo.bean.UserWorkInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户工作信息dao
 *
 * @author 李洪斌
 * @date 2019-9-8 10:23:26
 */
@Repository
public class UserWorkInfoMapper extends AbstractBaseDao {

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据id获取用户工作信息
     * @Date 2019/9/5 10:29
     * @Param
     **/
    public UserWorkInfo findUserWorkInfoById(String id) {
        return (UserWorkInfo) queryForObject("userWorkInfoMapper.selectByPrimaryKey", id);
    }


    /**
     * @return
     * @Author lvjian
     * @Description // 根据身份证号获取用户工作信息
     * @Date 2019/9/17 14:31
     * @Param
     **/
    public UserWorkInfo selectWorkInfoByIdNo(UserWorkInfo userWorkInfo) {
        return (UserWorkInfo) queryForObject("userWorkInfoMapper.selectWorkInfoByIdNo", userWorkInfo);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 添加用户工作信息
     * @Date 2019/9/5 10:30
     * @Param
     **/
    public void addUserWorkInfo(UserWorkInfo userWorkInfo) {
        insert("userWorkInfoMapper.insertSelective", userWorkInfo);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据id修改用户信息
     * @Date 2019/9/5 10:32
     * @Param
     **/
    public void updateUserWorkInfo(UserWorkInfo userWorkInfo) {
        update("userWorkInfoMapper.updateByPrimaryKeySelective", userWorkInfo);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户工作信息列表
     * @Date 2019/9/5 11:16
     * @Param
     **/
    public List<UserWorkInfo> listUserWorkInfoByParams(Map<String, Object> params) {
        return queryForList("userWorkInfoMapper.listUserWorkInfoByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户工作信息未审核总人数
     * @Date 2019/9/5 11:34
     * @Param
     **/
    public Integer getUnAuditUserWorkInfoSum() {
        return queryForObject("userWorkInfoMapper.getUnAuditUserWorkInfoSum");
    }
}