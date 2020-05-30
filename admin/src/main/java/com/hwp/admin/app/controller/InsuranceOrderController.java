package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.insuranceOrder.InsuranceOrderService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.insuranceOrder.bean.InsuranceOrder;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
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
 * 保险预约controller
 *
 * @author 李洪斌
 * @date 2019-8-30 13:11:21
 */
@Controller
@RequestMapping("/insuranceOrder")
public class InsuranceOrderController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(InsuranceOrderController.class);
    /**
     * 车险类型
     */
    private final String CAR_INSU = "1";
    /**
     * 重大疾病险类型
     */
    private final String ILLNESS_INSU = "2";
    @Autowired
    private InsuranceOrderService insuranceOrderService;

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 获取保险预约列表页面
     * @Date 2019/8/30 17:11
     * @Param
     **/
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String getInsuranceOrderListPage(HttpServletRequest request, Model model) {
        logger.debug(request.getRequestURI() + "加载保险预约列表页面");
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/insuranceOrder/list";
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 保险预约列表
     * @Date 2019/9/2 9:13
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listInsuranceOrderByParams(HttpServletRequest request) {
        logger.debug(request.getRequestURI() + "加载保险预约列表");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);

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

        final Page<InsuranceOrder> results = (Page<InsuranceOrder>) insuranceOrderService.listInsuranceOrderByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("保险预约管理", "保险预约记录", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 预约保险详情页面
     * @Date 2019/9/3 10:00
     * @Param
     **/
    @GetMapping("/detail/{id}")
    public String getInsuranceOrderDetailById(HttpServletRequest request, @PathVariable("id") String id, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        try {
            logger.debug(request.getRequestURI() + "加载预约保险详情");

            InsuranceOrder insuranceOrder = insuranceOrderService.getInsuranceOrderDetailById(id);

            // 记录查看日志
            saveBusinessLog("保险预约管理", "预约保险详情", insuranceOrder);

            // 车险
            if (CAR_INSU.equals(insuranceOrder.getOrderInsuranceType())) {
                model.addAttribute("insuranceOrder", insuranceOrder);
                return "/app/insuranceOrder/carInsuDetail";
            }
            // 重大疾病险
            if (ILLNESS_INSU.equals(insuranceOrder.getOrderInsuranceType())) {
                model.addAttribute("insuranceOrder", insuranceOrder);
                return "/app/insuranceOrder/illnessInsuDetail";
            }
        } catch (Exception e) {
            logger.error(request.getRequestURI() + "加载预约保险详情error");
            e.printStackTrace();
        }
        return "/app/insuranceOrder/carInsuDetail";
    }

    /**
     * @return
     * @Author 李洪斌
     * @Description //TODO 保险预约审核
     * @Date 2019/9/3 14:51
     * @Param
     **/
    @PostMapping("/audit")
    @ResponseBody
    public Map<String, Object> insuranceOrderAudit(HttpServletRequest request, InsuranceOrder insuranceOrder) {

        logger.debug(request.getRequestURI() + "保险预约审核");
        Map<String, Object> resultMap = null;
        try {
            InsuranceOrder newInsuranceOrder = new InsuranceOrder();
            // id
            newInsuranceOrder.setId(insuranceOrder.getId());
            // 审核状态
            newInsuranceOrder.setStatus(insuranceOrder.getStatus());
            // 备注信息
            newInsuranceOrder.setRemark(insuranceOrder.getRemark());
            // 审核时间
            newInsuranceOrder.setAuditTime(DateHelper.getYMDHMSFormatDate(new Date()));

            SysManager sysManager = getSessionSysManager();

            // 审核人Id
            newInsuranceOrder.setAuditorId(sysManager.getId().toString());
            // 审核人姓名
            newInsuranceOrder.setAuditorName(sysManager.getName());
            insuranceOrderService.updateInsuranceOrder(newInsuranceOrder);


            resultMap = new HashMap<String, Object>(16);
            resultMap.put("flag", "true");
            resultMap.put("msg", "审核成功");


        } catch (Exception e) {
            logger.debug(request.getRequestURI() + "保险预约审核error");
            e.printStackTrace();
            resultMap.put("flag", "false");
            resultMap.put("msg", "审核失败");
        }


        // 记录查看日志
        saveBusinessLog("保险预约管理", "预约保险审核", insuranceOrder);

        return resultMap;
    }

}
