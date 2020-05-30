package com.hwp.common.model.user.dao;

import com.hwp.common.db.AbstractBaseDao;
import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dto.UserProductInfoDTO;
import com.hwp.common.model.userTransaction.bean.UserTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户信息扩展查询dao
 *
 * @author 李洪斌
 * @date 2019-9-12 16:14:26
 */
@Repository
public class UserInfoExtendMapper extends AbstractBaseDao {


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据特殊参数查询用户信息
     * @Date 2019/9/12 16:57
     * @Param
     **/
    public List<UserInfo> listUserInfoBySpecificParams(Map<String, Object> specifParams) {
        return (List<UserInfo>) queryForList("userInfoExtendMapper.listUserInfoBySpecificParams", specifParams);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户交易信息列表
     * @Date 2019/9/16 17:46
     * @Param
     **/
    public List<UserTransaction> listUserTransactionByParams(Map<String, Object> params) {
        return (List<UserTransaction>) queryForList("userInfoExtendMapper.listUserTransactionByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户信息
     * @Date 2019/9/19 18:17
     * @Param
     **/
    public List<UserInfo> listUserInfoByParams(Map<String, Object> params) {
        return (List<UserInfo>) queryForList("userInfoExtendMapper.listUserInfoByParams", params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取用户产品投资UserProductInfoDTO列表
     * @Date 2019/9/20 16:11
     * @Param
     **/
    public List<UserProductInfoDTO> listUserProductInfoDTOByUserId(String userId) {
        return (List<UserProductInfoDTO>) queryForList("userInfoExtendMapper.listUserProductInfoDTOByUserId", userId);
    }
}
