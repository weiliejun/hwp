package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.event.RybgsqSendEmailEvent;
import com.hwp.admin.app.service.rybgsq.RybgsqService;
import com.hwp.admin.app.service.ryxxgl.RyxxglService;
import com.hwp.admin.app.service.spsqxx.SpsqxxService;
import com.hwp.admin.app.service.xmrwxx.XmrwxxService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.rybgsq.bean.Rybgsq;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.sysManager.bean.SysManager;
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
@RequestMapping("/rybgsq")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class RybgsqController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(RybgsqController.class);

    @Autowired
    private RybgsqService rybgsqService;
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private XmrwxxService xmrwxxService;
    @Autowired
    private RyxxglService ryxxglService;
    @Autowired
    private SpsqxxService spsqxxService;

    /**
     * 图片服务器访问地址
     */
    @Value("${resourceServer.AccessURL}")
    private String resourceServerURL;

    /**
     * 新增人员信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/toAdd"}, method = RequestMethod.GET)
    public String toAdd(Model model, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "ryId", required = false) String ryId, HttpServletRequest request) {

        Rybgsq rybgsq = rybgsqService.selectRybgsqByRyId(ryId);

        if (rybgsq != null) {
        } else {
            rybgsq = new Rybgsq();
            rybgsq.setRyId(ryId);
        }
        model.addAttribute("rybgsq", rybgsq);

        return "/app/ryxxgl/rybgsq";
    }

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
    public String getRybgsq(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/rybgsq/list";
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
    public Map<String, Object> listRybgsq(HttpServletRequest request, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);

        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        Map<String, Object> params = getQureyParams(requestParams);

        final Page<Rybgsq> results = (Page<Rybgsq>) rybgsqService.listRybgsqByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("人员信息管理", "人员信息列表", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * 新增或修改人员变更申请
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2019/7/20 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateRybgsq(@RequestBody Rybgsq rybgsq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysManager currentManager = getSessionSysManager();
        //新增
        if (StringHelper.isBlank(rybgsq.getId())) {
            //设置初始化值
            // 数据有效
            rybgsq.setDataStatus(GlobalConstant.DATA_VALID);
            rybgsq.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            rybgsq.setCreatorId(currentManager.getId().toString());
            rybgsq.setCreatorName(currentManager.getName());
            rybgsq.setSpyj("待审批");
            rybgsq = rybgsqService.addRybgsq(rybgsq);
            resultMap.put("flag", "true");
            resultMap.put("msg", "人员变更申请新增成功");

            // 记录查看日志
            saveBusinessLog("人员变更申请管理", "新增人员变更申请", rybgsq);
        } else {//编辑
            rybgsqService.updateRybgsq(rybgsq);
            resultMap.put("flag", "true");
            resultMap.put("msg", "人员变更申请修改成功");

            // 记录查看日志
            saveBusinessLog("人员变更申请管理", "修改人员变更申请", rybgsq);
        }

        //审批信息表
        Ryxxgl ryxxgl = ryxxglService.getRyxxglByName(currentManager.getName());
        //将此项目以前提交的人员变更申请审批记录置为无效
        spsqxxService.updateInvalidByGlId(rybgsq.getId());
        if (StringHelper.isNotBlank(rybgsq.getSpr())) {
            net.sf.json.JSONArray selectSpr = net.sf.json.JSONArray.fromObject(rybgsq.getSpr());
            for (int i = 0; i < selectSpr.size(); i++) {
                net.sf.json.JSONObject spr = selectSpr.getJSONObject(i);
                Spsqxx spsqxx = new Spsqxx();
                spsqxx.setGlId(rybgsq.getId());
                spsqxx.setSplx("人员变更申请");
                spsqxx.setTzlx("审批");
                spsqxx.setSpyj("待审批");
                spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
                spsqxx.setCreatorId(ryxxgl.getId());
                spsqxx.setCreatorName(ryxxgl.getName());
                spsqxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                spsqxx.setSpr(spr.toString());
                spsqxx.setSprId(spr.getString("id"));
                spsqxx.setSprName(spr.getString("name"));
                spsqxxService.addSpsqxx(spsqxx);
            }
        }

        if (StringHelper.isNotBlank(rybgsq.getZhr())) {
            net.sf.json.JSONArray selectZhr = net.sf.json.JSONArray.fromObject(rybgsq.getZhr());
            for (int i = 0; i < selectZhr.size(); i++) {
                net.sf.json.JSONObject zhr = selectZhr.getJSONObject(i);
                Spsqxx spsqxx = new Spsqxx();
                spsqxx.setGlId(rybgsq.getId());
                spsqxx.setSplx("人员变更申请");
                spsqxx.setTzlx("知会");
                spsqxx.setSpyj("待处理");
                spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
                spsqxx.setCreatorId(ryxxgl.getId());
                spsqxx.setCreatorName(ryxxgl.getName());
                spsqxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                spsqxx.setSpr(zhr.toString());
                spsqxx.setSprId(zhr.getString("id"));
                spsqxx.setSprName(zhr.getString("name"));
                spsqxxService.addSpsqxx(spsqxx);
            }
        }

        //发送邮件
        rybgsq.setCreatorName(currentManager.getName());
        applicationContext.publishEvent(new RybgsqSendEmailEvent(this, rybgsq));

        return resultMap;
    }
}
