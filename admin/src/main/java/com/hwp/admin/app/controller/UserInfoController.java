package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.userInfo.UserInfoService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.user.bean.UserInfo;
import com.hwp.common.model.user.dto.UserInfoDetailDTO;
import com.hwp.common.model.userTransaction.bean.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户管理Controller
 *
 * @author 李洪斌
 * @date 2019-9-11 16:29:36
 */
@Controller
@RequestMapping("/userInfoManage")
public class UserInfoController extends AbstractBaseController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 加载用户管理页面
     * @Date 2019/9/12 9:42
     * @Param
     **/
    @GetMapping("/userDetailList")
    public String getUserInfoListPage(HttpServletRequest request, Model model) {
        logger.debug(request.getRequestURI() + "加载用户管理列表页面");
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/userInfo/list";
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 加载用户详情页面
     * @Date 2019/9/17 17:22
     * @Param
     **/
    @GetMapping("/detail/{userId}")
    public String getUserInfoDetailPage(HttpServletRequest request, Model model, @PathVariable("userId") String userId) {
        logger.debug(request.getRequestURI() + "加载用户管理详情页面");
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        try {
            UserInfoDetailDTO userInfoDetailDTO = userInfoService.getUserInfoDetailDTOByUserId(userId);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            logger.error("加载用户管理详情页面 error" + e.getMessage());
        }
        return "/app/userInfo/detail";
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取用户详细信息
     * @Date 2019/9/17 17:22
     * @Param
     **/
    @PostMapping("/detail/{userId}")
    @ResponseBody
    public Map<String, Object> getUserInfoDetail(HttpServletRequest request, @PathVariable("userId") String userId) {

        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        String flag = "true", msg = "获取成功";
        try {
            logger.debug(request.getRequestURI() + "加载用户管理列表页面");
            UserInfoDetailDTO userInfoDetailDTO = userInfoService.getUserInfoDetailDTOByUserId(userId);
            resultMap.put("data", userInfoDetailDTO);
        } catch (Exception e) {
            logger.error("异常");
            flag = "false";
            msg = "获取数据失败";
        }
        resultMap.put("flag", flag);
        resultMap.put("msg", msg);

        return resultMap;
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户工作信息列表
     * @Date 2019/9/5 13:48
     * @Param
     **/
    @PostMapping("/list")
    @ResponseBody
    public Map<String, Object> listUserWorkInfoByParams(HttpServletRequest request) {
        logger.debug(request.getRequestURI() + "加载用户管理列表");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);

        final Page<UserInfo> results = (Page<UserInfo>) userInfoService.listUserInfoByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("用户信息管理", "用户管理列表", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 根据用户id获取用户交易记录列表
     * @Date 2019/9/17 17:23
     * @Param
     **/
    @PostMapping("/userTransactionList/{userId}/{isNotEarning}")
    @ResponseBody
    public Map<String, Object> listUserTransactionByParams(HttpServletRequest request,
                                                           @PathVariable("userId") String userId,
                                                           @PathVariable("isNotEarning") Boolean isNotEarning) {
        logger.debug(request.getRequestURI() + "加载用户详情交易记录列表");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);

        // 是否包含余额宝收益
        if (isNotEarning) {
            params.put("isNotEarning", "yes");
        }

        final Page<UserTransaction> results = (Page<UserTransaction>) userInfoService.getUserTransactionByUserId(userId, params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("用户信息管理", "用户详情交易记录列表", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取指定条件用户信息
     * @Date 2019/9/17 18:18
     * @Param
     **/
    @RequestMapping("/isExist")
    @ResponseBody
    public Map<String, Object> getUserInfoByParams(HttpServletRequest request) {
        logger.debug("根据指定信息获取单个用户信息");
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        String flag = "false", msg = "获取数据失败";
        Map<String, Object> requestParams = formQueryRemember(request);
        try {
            UserInfo userInfo = userInfoService.getUserInfoByParams(requestParams);
            if (userInfo != null) {
                flag = "true";
                msg = "获取数据成功";
                resultMap.put("userId", userInfo.getId().toString());
            }
        } catch (Exception e) {
            logger.error("根据指定信息获取单个用户信息 error");
            msg = "获取数据失败 -3";
        }
        resultMap.put("flag", flag);
        resultMap.put("msg", msg);
        return resultMap;

    }


}
