package com.hwp.common.service.userManage;

import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.userAuthenticationInfo.bean.UserAuthenticationInfo;

public interface UserAuthenticationInfoService {

    UserAuthenticationInfo findById(String userAuthenticationInfoId);

    UserAuthenticationInfo findByIdNo(String userAuthenticationInfoIdNo);

    void addUserAuthenticationInfo(UserAuthenticationInfo userAuthenticationInfo);

    boolean addUserAuthenticationInfoAndUserInfo(UserInfo userInfo);

    void updateUserAuthenticationInfo(UserAuthenticationInfo userAuthenticationInfo);

    void updateUserAuthenticationInfoUploadStatus(UserAuthenticationInfo userAuthenticationInfo);

    void deleteUserAuthenticationInfoById(UserAuthenticationInfo userAuthenticationInfo);
}
