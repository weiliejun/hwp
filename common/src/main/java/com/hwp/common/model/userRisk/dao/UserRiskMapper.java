package com.hwp.common.model.userRisk.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.userRisk.bean.UserRisk;
import org.springframework.stereotype.Repository;

@Repository
public class UserRiskMapper extends AbstractBaseDao {


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID查询
     * @Date 2019/6/17 17:19
     * @Param
     **/
    public UserRisk findById(String userRiskId) {
        UserRisk userRisk = (UserRisk) queryForObject("userRiskMapper.selectByPrimaryKey", userRiskId);
        if (userRisk != null) {
            return userRisk;
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
    public void addUserRisk(UserRisk userRisk) {
        insert("userRiskMapper.insertSelective", userRisk);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改
     * @Date 2019/6/17 17:22
     * @Param
     **/
    public void updateUserRisk(UserRisk userRisk) {
        update("userRiskMapper.updateByPrimaryKeySelective", userRisk);
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据ID删除 业务中删除为修改数据有效性
     * @Date 2019/6/17 17:35
     * @Param
     **/
    public void deleteUserRiskById(UserRisk userRisk) {
        delete("userRiskMapper.deleteByPrimaryKey", userRisk.getId());
    }


}