package com.hwp.admin.app.service.userInfo;

import com.hwp.common.model.prodrevenueStatus.bean.ProdRevenueStatus;
import com.hwp.common.model.prodrevenueStatus.dao.ProdRevenueStatusDao;
import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dao.UserInfoDao;
import com.hwp.common.model.user.dao.UserInfoExtendMapper;
import com.hwp.common.model.user.dto.UserInfoDetailDTO;
import com.hwp.common.model.user.dto.UserProductInfoDTO;
import com.hwp.common.model.userBalance.bean.UserBalance;
import com.hwp.common.model.userBalance.dao.UserBalanceDao;
import com.hwp.common.model.userBankCard.bean.UserBankCard;
import com.hwp.common.model.userBankCard.dao.UserBankCardMapper;
import com.hwp.common.model.userTransaction.bean.UserTransaction;
import com.hwp.common.util.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理Service实现
 *
 * @author 李洪斌
 * @date 2019-9-11 16:56:26
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Autowired
    private ProdRevenueStatusDao prodRevenueStatusDao;

    @Autowired
    private UserInfoExtendMapper userInfoExtendMapper;

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取用户信息
     * @Date 2019/9/16 14:41
     * @Param
     **/
    @Override
    public UserInfo getUserInfoById(String id) {
        return userInfoDao.getUserInfoById(Integer.valueOf(id));
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户信息 添加参数 是否绑卡 是否实名
     * @Date 2019/9/16 14:42
     * @Param
     **/
    @Override
    public List<UserInfo> listUserInfoByParams(Map<String, Object> params) {
        return userInfoExtendMapper.listUserInfoBySpecificParams(params);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户信息获取用户详细信息DTO
     * @Date 2019/9/20 9:43
     * @Param
     **/
    @Override
    public UserInfoDetailDTO getUserInfoDetailDTOByUserId(String userId) {
        UserInfoDetailDTO userInfoDetailDTO = new UserInfoDetailDTO();
        try {

            // 1 获取用户信息
            UserInfo userInfo = getUserInfoById(userId);

            // 2 获取用户绑定银行卡信息
            UserBankCard userBankCard = null;
            Map<String, Object> userBankCardParams = new HashMap<String, Object>(16);
            // 用户id
            userBankCardParams.put("userId", userId);
            // 是绑定银行卡
            userBankCardParams.put("isBindCard", "Y");
            // 数据有效
            userBankCardParams.put("dataStatus", "1");
            List<UserBankCard> userBankCards = userBankCardMapper.ListUserBankCardByParams(userBankCardParams);
            if (userBankCards != null && userBankCards.size() > 0) {
                userBankCard = userBankCards.get(0);
            }

            // 3 用户余额信息
            UserBalance userBalance = userBalanceDao.getFindByUserId(userId);

            ProdRevenueStatus prodRevenueStatusParams = new ProdRevenueStatus();
            prodRevenueStatusParams.setUserId(userId);
            prodRevenueStatusParams.setCreateTime(DateHelper.getYMDFormatDate(new Date()));
            ProdRevenueStatus prodRevenueStatus = prodRevenueStatusDao.selectByUserId(prodRevenueStatusParams);

            // 4 用户产品列表
            List<UserProductInfoDTO> productList = userInfoExtendMapper.listUserProductInfoDTOByUserId(userId);

            userInfoDetailDTO.setUserInfo(userInfo);
            userInfoDetailDTO.setUserBankCard(userBankCard);
            userInfoDetailDTO.setUserBalance(userBalance);
            userInfoDetailDTO.setNewProfitInfo(prodRevenueStatus);
            userInfoDetailDTO.setProductList(productList);

            return userInfoDetailDTO;
        } catch (Exception e) {
            System.out.println("获取用户扩展信息详情 error");
        }

        return null;
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户交易记录列表
     * @Date 2019/9/16 17:47
     * @Param
     **/
    @Override
    public List<UserTransaction> getUserTransactionByUserId(String userId, Map<String, Object> otherParams) {
        otherParams.put("userId", userId);
        return userInfoExtendMapper.listUserTransactionByParams(otherParams);
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据动态参数获取用户信息
     * @Date 2019/9/17 17:52
     * @Param
     **/
    @Override
    public UserInfo getUserInfoByParams(Map<String, Object> params) {
        List<UserInfo> userInfos = userInfoExtendMapper.listUserInfoByParams(params);
        if (userInfos != null && userInfos.size() > 0) {
            return userInfos.get(0);
        }
        return null;

    }

}
