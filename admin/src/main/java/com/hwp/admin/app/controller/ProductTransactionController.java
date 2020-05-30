package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.productTransaction.ProductTransactionService;
import com.hwp.admin.app.service.userTransaction.UserTransactionService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.userTransaction.dto.UserAndProductAndTransactionDTO;
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
 * 产品投资记录
 *
 * @author 李洪斌
 * @date 2019/7/26 10:27:50
 */
@Controller
@RequestMapping("/productTransaction")
public class ProductTransactionController extends AbstractBaseController {

    @Autowired
    private ProductTransactionService productTransactionService;

    @Autowired
    private UserTransactionService userTransactionService;

    /**
     * 加载产品投资记录列表页
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/26 10:33
     * @Param
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getProductTransactionList(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        try {
            UserTransactionSumDTO userTransactionSum = userTransactionService.getUserTransactionSum();
            model.addAttribute("userTransactionSum", userTransactionSum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/app/productTransaction/list";
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

        final Page<UserAndProductAndTransactionDTO> results = (Page<UserAndProductAndTransactionDTO>) productTransactionService.listUserAndProductAndTransactionByParams(params);

        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("用户交易记录管理", "产品投资记录", "第" + results.getPageNum() + "页list");

        return resultMap;
    }
}
