package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.userWorkInfo.UserWorkInfoService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.userWorkInfo.bean.UserWorkInfo;
import com.hwp.common.util.DateHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户工作信息管理Controller
 *
 * @author 李洪斌
 * @date 2019-9-3 15:28:23
 */
@Controller
@RequestMapping("/userWorkInfo")
public class UserWorkInfoController extends AbstractBaseController {

    private final Logger logger = LoggerFactory.getLogger(UserWorkInfoController.class);

    @Autowired
    private UserWorkInfoService userWorkInfoService;

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户工作信息列表页面
     * @Date 2019/9/5 13:03
     * @Param
     **/
    @GetMapping("/list")
    public String getUserWorkInfoListPage(HttpServletRequest request, Model model) {
        logger.debug(request.getRequestURI() + "加载用户工作信息管理列表页面");
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        try {
            model.addAttribute("unAudit", userWorkInfoService.getUnAuditUserWorkInfoSum());
        } catch (Exception e) {
            logger.error("加载用户工作信息管理列表页面, error");
            e.printStackTrace();
        }

        return "/app/userWorkInfo/list";
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取用户工作信息审核详情页面
     * @Date 2019/9/5 13:50
     * @Param
     **/
    @GetMapping("/detail/{id}")
    public String getUserWorkInfoDetailPage(HttpServletRequest request, @PathVariable("id") String id, Model model) {
        logger.debug(request.getRequestURI() + "加载用户工作信息管理审核详情列表页面");
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        try {
            UserWorkInfo userWorkInfo = userWorkInfoService.getUserWorkInfoById(id);
            model.addAttribute("userWorkInfo", userWorkInfo);
        } catch (Exception e) {
            logger.error("加载用户工作信息管理审核详情列表页面, error");
            e.printStackTrace();
        }
        return "/app/userWorkInfo/detail";
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
        logger.debug(request.getRequestURI() + "加载用户工作信息管理列表");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);

        final Page<UserWorkInfo> results = (Page<UserWorkInfo>) userWorkInfoService.listUserWorkInfoByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("用户信息管理", "白名单审核记录", "第" + results.getPageNum() + "页list");

        return resultMap;
    }


    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 用户工作信息审核
     * @Date 2019/9/5 14:56
     * @Param
     **/
    @PostMapping("/audit")
    @ResponseBody
    public Map<String, Object> userWorkInfoAudit(HttpServletRequest request, UserWorkInfo userWorkInfo) {

        logger.debug(request.getRequestURI() + "用户工作信息审核");
        Map<String, Object> resultMap = null;
        try {
            resultMap = new HashMap<String, Object>(16);
            UserWorkInfo newUserWorkInfo = new UserWorkInfo();
            // id
            newUserWorkInfo.setId(userWorkInfo.getId());
            // userId
            newUserWorkInfo.setUserId(userWorkInfo.getUserId());

            if (StringUtils.isBlank((userWorkInfo.getAuditStatus()))) {
                resultMap.put("flag", "false");
                resultMap.put("msg", "审核失败");
                return resultMap;
            }

            // 审核状态
            newUserWorkInfo.setAuditStatus(userWorkInfo.getAuditStatus());
            // 备注信息
            newUserWorkInfo.setRemark(userWorkInfo.getRemark());

            SysManager sysManager = getSessionSysManager();

            // 审核时间
            newUserWorkInfo.setAuditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            // 审核人ID
            newUserWorkInfo.setAuditorId(sysManager.getId().toString());
            // 审核人姓名
            newUserWorkInfo.setAuditorName(sysManager.getName());

            userWorkInfoService.userWorkInfoAudit(newUserWorkInfo);

            resultMap.put("flag", "true");
            resultMap.put("msg", "审核成功");


        } catch (Exception e) {
            logger.debug(request.getRequestURI() + "用户工作信息审核, error");
            e.printStackTrace();
            resultMap.put("flag", "false");
            resultMap.put("msg", "审核失败");
        }

        // 记录查看日志
        saveBusinessLog("用户信息管理", "白名单审核", userWorkInfo);

        return resultMap;
    }


}
