package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.fund.FundTransactionService;
import com.hwp.admin.app.service.userTransaction.UserTransactionService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.userTransaction.dto.UserAndFundAndTransactionDTO;
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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 货基投资记录Service实现
 *
 * @author 李洪斌
 * @date 2019/7/25 09:10:22
 */
@Controller
@RequestMapping("/fundTransaction")
public class FundTransactionController extends AbstractBaseController {

    @Autowired
    private FundTransactionService fundTransactionService;

    @Autowired
    private UserTransactionService userTransactionService;

    /**
     * 加载基金交易记录列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/25 20:04
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

        return "/app/fund/list";
    }

    /**
     * 用户基金交易记录列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/25 20:46
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listUserAndFundAndTransaction(HttpServletRequest request) {
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


        // 移除特殊参数
        params.remove("createTimeScope");

        // 传递新参数
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        final Page<UserAndFundAndTransactionDTO> results = (Page<UserAndFundAndTransactionDTO>) fundTransactionService.listUserInfoAndFundAndTransactionByParams(params);

        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("用户交易记录管理", "货基交易记录", "第" + results.getPageNum() + "页list");
        return resultMap;
    }
}
