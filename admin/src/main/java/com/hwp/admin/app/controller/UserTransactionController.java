package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.userTransaction.UserTransactionService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.userTransaction.dto.UserAndTransactionDTO;
import com.hwp.common.model.userTransaction.dto.UserTransactionSumDTO;
import com.hwp.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户充值记录
 *
 * @author 李洪斌
 * @date 2019/7/24 09:10:22
 */
@Controller
@RequestMapping("/userTransaction")
public class UserTransactionController extends AbstractBaseController {

    @Autowired
    private UserTransactionService userTransactionService;

    /**
     * 加载用户交易记录列表页面
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/24 10:22
     * @Param
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUserTransactionList(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        try {
            UserTransactionSumDTO userTransactionSum = userTransactionService.getUserTransactionSum();
            model.addAttribute("userTransactionSum", userTransactionSum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/app/userTransaction/list";
    }

    /**
     * 用户交易记录列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/24 10:26
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listUserTransaction(HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);

        // 时间范围
        String createTimeScope = null, startTime = null, endTime = null;
        if (params.get("createTimeScope") != null) {
            createTimeScope = params.get("createTimeScope").toString();
        }

        // 时间范围处理
        if (StringHelper.isNotEmpty(createTimeScope)) {
            String[] split = createTimeScope.split(" - ");
            startTime = split[0];
            endTime = split[1];
        }

        // 交易类型多选
        String typeCodes = null;
        String[] typeArr = null;
        if (params.get("typeCodes") != null) {
            typeCodes = params.get("typeCodes").toString();
            typeArr = typeCodes.split(",");
        }

        // 移除特殊参数
        params.remove("createTimeScope");
        params.remove("typeCodes");

        // 传递新参数
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("typeArr", typeArr);

        final Page<UserAndTransactionDTO> results = (Page<UserAndTransactionDTO>) userTransactionService.listUserInfoAndTransactionByParams(params);

        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("用户交易记录管理", "联名卡交易记录", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * 获取用户联名卡转入转出 基金申购赎回 理财产品 交易总额
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/8/5 13:09
     * @Param
     **/
    @RequestMapping("/sum")
    @ResponseBody
    public Map<String, Object> getUserTransactionSum() {
        Map<String, Object> returnMap = new HashMap<String, Object>(16);

        try {
            UserTransactionSumDTO userTransactionSum = userTransactionService.getUserTransactionSum();
            returnMap.put("flag", "true");
            returnMap.put("data", userTransactionSum);
            returnMap.put("msg", "获取数据成功");
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("flag", "false");
            returnMap.put("msg", "获取数据失败");
            return returnMap;
        }
    }
}
