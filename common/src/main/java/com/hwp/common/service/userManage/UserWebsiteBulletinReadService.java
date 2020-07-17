package com.hwp.common.service.userManage;

import com.hwp.common.model.userWebsiteBulletinRead.bean.UserWebsiteBulletinRead;

import java.util.Map;

public interface UserWebsiteBulletinReadService {

    void addUserWebsiteBulletinRead(UserWebsiteBulletinRead userWebsiteBulletinRead);

    UserWebsiteBulletinRead getUserWebsiteBulletinReadById(Integer id);

    UserWebsiteBulletinRead getUserWebsiteBulletinReadByBulletinId(Integer bulletinId, Integer userId);

    void updateUserWebsiteBulletinRead(UserWebsiteBulletinRead record);

    int countUserWebsiteBulletinReadByParam(Map<String, Object> params);


}