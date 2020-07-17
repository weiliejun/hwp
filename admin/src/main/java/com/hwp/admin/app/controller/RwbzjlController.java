package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.event.RwbzSendEmailEvent;
import com.hwp.admin.app.service.rwbzjl.RwbzjlService;
import com.hwp.admin.app.service.xmrwxx.XmrwxxService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.rwbzjl.bean.Rwbzjl;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 人员信息
 *
 * @author 魏列军
 * @date 2020/5/15 15:30:22
 */
@Controller
@RequestMapping("/rwbzjl")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class RwbzjlController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(RwbzjlController.class);

    @Autowired
    private RwbzjlService rwbzjlService;
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private XmrwxxService xmrwxxService;

    /**
     * 图片服务器访问地址
     */
    @Value("${resourceServer.AccessURL}")
    private String resourceServerURL;

    /**
     * 人员信息加载页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO 使用shiro中用户session缓存在redis中每次请求中页面都会携带jsessionid所用通过thymeleaf渲染模板时需传递请求信息
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRwbzjl(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/rwbzjl/list";
    }

    /**
     * 人员信息列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listRwbzjl(HttpServletRequest request, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);

        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        Map<String, Object> params = getQureyParams(requestParams);

        final Page<Rwbzjl> results = (Page<Rwbzjl>) rwbzjlService.listRwbzjlByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("人员信息管理", "人员信息列表", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * 新增或修改任务备注
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2019/7/20 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateRwbzjl(@RequestBody Rwbzjl rwbzjl) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysManager currentManager = getSessionSysManager();
        //新增
        if (StringHelper.isBlank(rwbzjl.getId())) {
            //设置初始化值
            // 数据有效
            rwbzjl.setDataStatus(GlobalConstant.DATA_VALID);
            rwbzjl.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            rwbzjl.setCreatorId(currentManager.getId().toString());
            rwbzjl.setCreatorName(currentManager.getName());
            rwbzjl = rwbzjlService.addRwbzjl(rwbzjl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "任务备注新增成功");

            // 记录查看日志
            saveBusinessLog("任务备注管理", "新增任务备注", rwbzjl);

            //发送邮件
            Xmxxgl xmxxgl = xmxxglService.getXmxxglById(rwbzjl.getXmId());
            Xmrwxx xmrwxx = xmrwxxService.getXmrwxxById(rwbzjl.getRwId());
            if (rwbzjl.getFstz().equalsIgnoreCase("发送")) {
                applicationContext.publishEvent(new RwbzSendEmailEvent(this, xmrwxx, xmxxgl, rwbzjl));
            }

            return resultMap;
        } else {//编辑
            rwbzjlService.updateRwbzjl(rwbzjl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "任务备注修改成功");

            // 记录查看日志
            saveBusinessLog("任务备注管理", "修改任务备注", rwbzjl);

            return resultMap;
        }
    }
}
