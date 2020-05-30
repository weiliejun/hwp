package com.hwp.admin.app.service.userWorkInfo;

import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dao.UserInfoDao;
import com.hwp.common.model.userWorkInfo.bean.UserWorkInfo;
import com.hwp.common.model.userWorkInfo.dao.UserWorkInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户工作信息管理Service
 *
 * @author 李洪斌
 * @date 2019-9-3 15:33:22
 */
@Service
public class UserWorkInfoServiceImpl implements UserWorkInfoService {

    /**
     * 用户工作信息审核通过
     */
    private final String AUDIT_STATUS_PASS = "1";
    /**
     * 用户工作信息审核未通过
     */
    private final String AUDIT_STATUS_NOT_PASS = "2";
    @Autowired
    private UserWorkInfoMapper userWorkInfoMapper;
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据id获取用户工作信息
     * @Date 2019/9/5 11:18
     * @Param
     **/
    @Override
    public UserWorkInfo getUserWorkInfoById(String id) {
        return userWorkInfoMapper.findUserWorkInfoById(id);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 修改用户工作信息
     * @Date 2019/9/5 11:20
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserWorkInfo(UserWorkInfo userWorkInfo) {
        userWorkInfoMapper.updateUserWorkInfo(userWorkInfo);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户工作信息列表
     * @Date 2019/9/5 11:19
     * @Param
     **/
    @Override
    public List<UserWorkInfo> listUserWorkInfoByParams(Map<String, Object> params) {
        return userWorkInfoMapper.listUserWorkInfoByParams(params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户工作信息未审核总人数
     * @Date 2019/9/5 11:36
     * @Param
     **/
    @Override
    public Integer getUnAuditUserWorkInfoSum() {
        return userWorkInfoMapper.getUnAuditUserWorkInfoSum();
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户工作信息审核 审核通过 修改用户表status为Y 不通过 修改用户表status为N 并同步用户工作信息表
     * @Date 2019/9/5 15:20
     * @Param
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userWorkInfoAudit(UserWorkInfo userWorkInfo) {
        UserInfo userInfo = userInfoDao.getUserInfoById(Integer.valueOf(userWorkInfo.getUserId()));

        // 审核通过
        if (AUDIT_STATUS_PASS.equals(userWorkInfo.getAuditStatus())) {
            userInfo.setStatus("Y");
        }
        // 审核未通过
        if (AUDIT_STATUS_NOT_PASS.equals(userWorkInfo.getAuditStatus())) {
            userInfo.setStatus("N");
        }
        userInfoDao.updateUserInfo(userInfo);
        updateUserWorkInfo(userWorkInfo);
    }
}
