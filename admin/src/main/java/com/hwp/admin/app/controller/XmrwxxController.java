package com.hwp.admin.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.event.RwfhSendEmailEvent;
import com.hwp.admin.app.event.RwscSendEmailEvent;
import com.hwp.admin.app.event.RwzhSendEmailEvent;
import com.hwp.admin.app.service.productOtherAttachFile.ProductOtherAttachFileService;
import com.hwp.admin.app.service.rwbzjl.RwbzjlService;
import com.hwp.admin.app.service.rwczjl.RwczjlService;
import com.hwp.admin.app.service.ryxxgl.RyxxglService;
import com.hwp.admin.app.service.spsqxx.SpsqxxService;
import com.hwp.admin.app.service.xmrwxx.XmrwxxService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.admin.quarz.DynamicTask;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.productOtherAttachFile.bean.AttachFileHref;
import com.hwp.common.model.productOtherAttachFile.bean.ProductOtherAttachFile;
import com.hwp.common.model.rwbzjl.bean.Rwbzjl;
import com.hwp.common.model.rwczjl.bean.Rwczjl;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;


/**
 * 项目任务信息
 *
 * @author 魏列军
 * @date 2020/5/15 15:30:22
 */
@Controller
@RequestMapping("/xmrwxx")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class XmrwxxController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(XmrwxxController.class);

    @Autowired
    private XmrwxxService xmrwxxService;

    @Autowired
    private RwbzjlService rwbzjlService;

    @Autowired
    private SpsqxxService spsqxxService;

    @Autowired
    private FileSynchronizer FileSynchronizer;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ProductOtherAttachFileService productOtherAttachFileService;

    @Autowired
    private RyxxglService ryxxglService;

    @Autowired
    private XmxxglService xmxxglService;

    @Autowired
    private RwczjlService rwczjlService;

    @Autowired
    private DynamicTask dynamicTask;

    /**
     * 图片服务器访问地址
     */
    @Value("${resourceServer.AccessURL}")
    private String resourceServerURL;

    @RequestMapping(value = {"/delete/{id}/{xmId}"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteById(@PathVariable("id") String id, @PathVariable("xmId") String xmId, @RequestParam(value = "xmjd", required = false) String xmjd) {
        Xmrwxx xmrwxx = new Xmrwxx();
        xmrwxx.setId(id);
        xmrwxx.setDataStatus(GlobalConstant.DATA_INVALID);
        try {
            xmrwxxService.updateXmrwxx(xmrwxx);
            //给任务负责人发邮件
            xmrwxx = xmrwxxService.getXmrwxxById(id);
            Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmrwxx.getXmId());
            applicationContext.publishEvent(new RwscSendEmailEvent(this, xmrwxx, xmxxgl, getSessionSysManager().getName().toString()));
            //修改定时任务时间
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(xmrwxx.getRwxq());
            Map<String, String> taskConstants = dynamicTask.getTaskConstants();
            for (int i = 0; i < jsonArray.size(); i++) {
                net.sf.json.JSONObject spr = jsonArray.getJSONObject(i);
                String rwmc = spr.getString("rwmc");
                String cron = spr.getString("cron");
                if (StringHelper.isNotBlank(cron)&&CronSequenceGenerator.isValidExpression(cron)) {
//                    taskConstants.remove(rwmc);
                    taskConstants.put(rwmc,null);
                    dynamicTask.execute();
                }
            }
            dynamicTask.setTaskConstants(taskConstants);
        } catch (Exception e) {
            logger.error("/delete! id:" + id, e);
        }
        return "forward:/xmxxgl/get/xmgl/" + xmId;
    }

    /**
     * 新增项目任务信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/toAdd"}, method = RequestMethod.GET)
    public String toAdd(Model model, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "xmId", required = false) String xmId, @RequestParam(value = "xmjd", required = false) String xmjd, HttpServletRequest request) {
        if (StringHelper.isNotBlank(id)) {
            Xmrwxx xmrwxx = xmrwxxService.getXmrwxxById(id);
            if (StringHelper.isBlank(xmrwxx.getRwxq()) || xmrwxx.getRwxq().equalsIgnoreCase("[]")) {
                String jsonMessage = "[{'zrwxh':'', 'rwmc':'', 'cron':''}]";
                net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                xmrwxx.setRwxq(myJsonArray.toString());
            } else {
                List<JSONObject> list = JsonHelper.sort(JSONArray.parseArray(xmrwxx.getRwxq()), "zrwxh", true);
                JSONArray jsonArray = JSONArray.parseArray(list.toString());
                xmrwxx.setRwxq(jsonArray.toString());
            }
            model.addAttribute("xmrwxx", xmrwxx);
            //任务附件信息
            ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(id);
            if (productOtherAttachFile == null) {
                model.addAttribute("message", "产品附件不存在");
                model.addAttribute("productOtherAttachFile", new ProductOtherAttachFile());
                model.addAttribute("AttachFileList", new ArrayList<AttachFileHref>());
            } else {
                String attachFile = productOtherAttachFile.getAttachFile();
                if (StringUtils.isNotBlank(attachFile)) {
                    //对去除空格转义符处理
                    String a = HtmlUtils.htmlUnescape(attachFile.replaceAll(" ", ""));
                    Map<String, String> parse = (Map<String, String>) JSON.parse(a);
                    List<AttachFileHref> fileHrefList = new ArrayList<AttachFileHref>();
                    for (Map.Entry<String, String> entry : parse.entrySet()) {
                        AttachFileHref attachFileHref = new AttachFileHref();
                        attachFileHref.setFileName(entry.getKey());
                        attachFileHref.setFileUrl(entry.getValue());
                        fileHrefList.add(attachFileHref);
                    }
                    model.addAttribute("productOtherAttachFile", productOtherAttachFile);
                    model.addAttribute("AttachFileList", fileHrefList);
                } else {
                    model.addAttribute("productOtherAttachFile", new ProductOtherAttachFile());
                    model.addAttribute("AttachFileList", new ArrayList<AttachFileHref>());
                }
            }
        } else {
            Xmrwxx xmrwxx = new Xmrwxx();
            xmrwxx.setXmId(xmId);
            xmrwxx.setXmjd(xmjd);
            xmrwxx.setStatus("未开始");
            xmrwxx.setZhzt("待处理");
            if (StringHelper.isBlank(xmrwxx.getRwxq()) || xmrwxx.getRwxq().equalsIgnoreCase("[]")) {
                String jsonMessage = "[{'zrwxh':'', 'rwmc':'', 'cron':''}]";
                net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                xmrwxx.setRwxq(myJsonArray.toString());
            } else {
                List<JSONObject> list = JsonHelper.sort(JSONArray.parseArray(xmrwxx.getRwxq()), "zrwxh", true);
                JSONArray jsonArray = JSONArray.parseArray(list.toString());
                xmrwxx.setRwxq(jsonArray.toString());
            }
            //设置任务序号
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xmId", xmId);
            params.put("xmjd", xmjd);
            List<Xmrwxx> xmInfo = xmrwxxService.listXmrwxxByParams(params);
            xmrwxx.setRwxh(String.valueOf(xmInfo.size() + 1));

            model.addAttribute("xmrwxx", xmrwxx);
            model.addAttribute("productOtherAttachFile", new ProductOtherAttachFile());
            model.addAttribute("AttachFileList", new ArrayList<AttachFileHref>());
        }
        return "/app/xmrwxx/add";
    }

    /**
     * 新增项目任务信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/toCron"}, method = RequestMethod.GET)
    public String toCron(Model model, @RequestParam(value = "cron", required = false) String cron, @RequestParam(value = "zrwxh", required = false) String zrwxh) {
        if (StringHelper.isBlank(cron) || cron.equalsIgnoreCase("undefined")) {
            cron = "0 30 23 * * ?";
        }
        model.addAttribute("cron", cron);
        model.addAttribute("zrwxh", zrwxh);

        return "/common/cron/paramconfig/toCron";
    }

    /**
     * 修改项目任务信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/get/{type}/{id}"}, method = RequestMethod.GET)
    public String findXmrwxxById(HttpServletRequest request, @PathVariable("type") String type, @PathVariable("id") String id, Model model) {
        Xmrwxx xmrwxx = xmrwxxService.getXmrwxxById(id);
        model.addAttribute("xmrwxx", xmrwxx);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("view")) {
            return "/app/xmrwxx/view";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("xmgl")) {
            /*ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(id);
            if (productOtherAttachFile == null) {
                model.addAttribute("message", "产品附件不存在");
            } else {
                String attachFile = productOtherAttachFile.getAttachFile();
                if (StringUtils.isNotBlank(attachFile)) {
                    //对去除空格转义符处理
                    String a = HtmlUtils.htmlUnescape(attachFile.replaceAll(" ", ""));
                    Map<String, String> parse = (Map<String, String>) JSON.parse(a);
                    List<AttachFileHref> fileHrefList = new ArrayList<>();
                    for (Map.Entry<String, String> entry : parse.entrySet()) {
                    AttachFileHref attachFileHref = new AttachFileHref();
                        attachFileHref.setFileName(entry.getKey());
                        attachFileHref.setFileUrl(entry.getValue());
                        fileHrefList.add(attachFileHref);
                    }
                    model.addAttribute("productOtherAttachFile", productOtherAttachFile);
                    model.addAttribute("AttachFileList", fileHrefList);
                }

            }*/
            return "/app/xmxxgl/xmgl";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rwbz")) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xmId", xmrwxx.getXmId());
            params.put("rwId", xmrwxx.getId());
            List<Rwbzjl> rwbzjlList = rwbzjlService.listRwbzjlByParams(params);
            model.addAttribute("rwbzjlList", rwbzjlList);
            return "/app/xmrwxx/rwbz";
        }
        return "/app/xmrwxx/add";
    }

    /**
     * 项目任务信息加载页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO 使用shiro中用户session缓存在redis中每次请求中页面都会携带jsessionid所用通过thymeleaf渲染模板时需传递请求信息
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getXmrwxx(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/xmrwxx/list";
    }

    /**
     * 项目任务信息列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listXmrwxx(HttpServletRequest request, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);

        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        Map<String, Object> params = getQureyParams(requestParams);

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

        final Page<Xmrwxx> results = (Page<Xmrwxx>) xmrwxxService.listXmrwxxByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录操作日志
        saveBusinessLog("项目任务信息管理", "项目任务信息列表", params);

        return resultMap;
    }

    /**
     * 保存附件
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 15:07
     * @Param
     **/
    @PostMapping(value = "/rwfj")
    @ResponseBody
    public Map<String, Object> rwfj(@RequestBody RwfjXmrwxx xmrwxx) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Xmrwxx xmrwxx_old = xmrwxxService.getXmrwxxById(xmrwxx.getId());
        if (StringHelper.isNotBlank(xmrwxx_old.getSpyj())) {
            xmrwxx.setSpyj("待审批");
        }

        ProductOtherAttachFile productOtherAttachFile2 = productOtherAttachFileService.selectProductOtherAttachFileByProductId(xmrwxx.getId());
        if (productOtherAttachFile2 == null) {
            //对附件表添加创建人数据
            ProductOtherAttachFile productOtherAttachFile = new ProductOtherAttachFile();
            productOtherAttachFile.setDataStatus(GlobalConstant.DATA_VALID);
            productOtherAttachFile.setId(RandomUtil.getSerialNumber());
            productOtherAttachFile.setProductId(xmrwxx.getId());
            productOtherAttachFile.setCreatorId(currentManager.getId().toString());
            productOtherAttachFile.setCreatorName(currentManager.getName());
            productOtherAttachFile.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            //附件类型  复核申请上传文件
            productOtherAttachFile.setType("rwfjAttachFile");
            productOtherAttachFile.setAttachFile(xmrwxx.getAttachFile());
            productOtherAttachFileService.addProductOtherAttachFile(productOtherAttachFile);
        } else {
            productOtherAttachFile2.setEditorId(currentManager.getId().toString());
            productOtherAttachFile2.setEditorName(currentManager.getName());
            productOtherAttachFile2.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            productOtherAttachFile2.setAttachFile(xmrwxx.getAttachFile());
            productOtherAttachFileService.updateProductOtherAttachFileById(productOtherAttachFile2);
        }

        xmrwxxService.updateXmrwxx(xmrwxx);
        resultMap.put("flag", "true");
        resultMap.put("msg", "项目任务信息修改成功");

        // 记录操作日志
        saveBusinessLog("项目任务信息管理", "修改项目任务信息", xmrwxx);


        return resultMap;
    }

    /**
     * 保存附件
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 15:07
     * @Param
     **/
    @PostMapping(value = "/rwfh/{id}")
    @ResponseBody
    public Map<String, Object> rwfh(@RequestBody RwfjXmrwxx xmrwxx, @PathVariable("id") String id) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Xmrwxx xmrwxx_old = xmrwxxService.getXmrwxxById(xmrwxx.getId());
        if (StringHelper.isNotBlank(xmrwxx_old.getSpyj())) {
            xmrwxx.setSpyj("待审批");
        }

        ProductOtherAttachFile productOtherAttachFile2 = productOtherAttachFileService.selectProductOtherAttachFileByProductId(xmrwxx.getId());
        if (productOtherAttachFile2 == null) {
            //对附件表添加创建人数据
            ProductOtherAttachFile productOtherAttachFile = new ProductOtherAttachFile();
            productOtherAttachFile.setDataStatus(GlobalConstant.DATA_VALID);
            productOtherAttachFile.setId(RandomUtil.getSerialNumber());
            productOtherAttachFile.setProductId(xmrwxx.getId());
            productOtherAttachFile.setCreatorId(currentManager.getId().toString());
            productOtherAttachFile.setCreatorName(currentManager.getName());
            productOtherAttachFile.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            //附件类型  复核申请上传文件
            productOtherAttachFile.setType("rwfjAttachFile");
            productOtherAttachFile.setAttachFile(xmrwxx.getAttachFile());
            productOtherAttachFileService.addProductOtherAttachFile(productOtherAttachFile);
        } else {
            productOtherAttachFile2.setEditorId(currentManager.getId().toString());
            productOtherAttachFile2.setEditorName(currentManager.getName());
            productOtherAttachFile2.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            productOtherAttachFile2.setAttachFile(xmrwxx.getAttachFile());
            productOtherAttachFileService.updateProductOtherAttachFileById(productOtherAttachFile2);
        }

        //审批信息表
        Ryxxgl ryxxgl = ryxxglService.getRyxxglByName(currentManager.getName());
        //将此项目以前提交的复核申请审批记录置为无效
        spsqxxService.updateInvalidByGlId(xmrwxx.getId());
        if (StringHelper.isNotBlank(xmrwxx_old.getFhrXx())) {
            net.sf.json.JSONArray selectSpr = net.sf.json.JSONArray.fromObject(xmrwxx_old.getFhrXx());
            for (int i = 0; i < selectSpr.size(); i++) {
                net.sf.json.JSONObject spr = selectSpr.getJSONObject(i);
                Spsqxx spsqxx = new Spsqxx();
                spsqxx.setGlId(xmrwxx.getId());
                spsqxx.setSplx("复核申请");
                spsqxx.setTzlx("审批");
                spsqxx.setSpyj("待审批");
                spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
                spsqxx.setCreatorId(ryxxgl.getId());
                spsqxx.setCreatorName(ryxxgl.getName());
                spsqxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                spsqxx.setSpr(spr.toString());
                spsqxx.setSprId(spr.getString("id"));
                spsqxx.setSprName(spr.getString("name"));
                spsqxx.setXmjd(xmrwxx_old.getXmjd());
                spsqxxService.addSpsqxx(spsqxx);
            }
        }
        if (StringHelper.isNotBlank(xmrwxx_old.getZhrXx())) {
            net.sf.json.JSONArray selectSpr = net.sf.json.JSONArray.fromObject(xmrwxx_old.getZhrXx());
            for (int i = 0; i < selectSpr.size(); i++) {
                net.sf.json.JSONObject spr = selectSpr.getJSONObject(i);
                Spsqxx spsqxx = new Spsqxx();
                spsqxx.setGlId(xmrwxx.getId());
                spsqxx.setSplx("复核申请");
                spsqxx.setTzlx("知会");
                spsqxx.setSpyj("待处理");
                spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
                spsqxx.setCreatorId(ryxxgl.getId());
                spsqxx.setCreatorName(ryxxgl.getName());
                spsqxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                spsqxx.setSpr(spr.toString());
                spsqxx.setSprId(spr.getString("id"));
                spsqxx.setSprName(spr.getString("name"));
                spsqxx.setXmjd(xmrwxx_old.getXmjd());
                spsqxxService.addSpsqxx(spsqxx);
            }
        }
        //给复核人发送邮件
        Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmrwxx.getXmId());
        applicationContext.publishEvent(new RwfhSendEmailEvent(this, xmrwxx, xmxxgl));
        //给知会人发送邮件
        applicationContext.publishEvent(new RwzhSendEmailEvent(this, xmrwxx, xmxxgl));

        xmrwxxService.updateXmrwxx(xmrwxx);
        resultMap.put("xmId", xmrwxx_old.getXmId());
        resultMap.put("xmjd", xmrwxx_old.getXmjd());
        resultMap.put("flag", "true");
        resultMap.put("msg", "项目任务信息修改成功");

        // 记录操作日志
        saveBusinessLog("项目任务信息管理", "修改项目任务信息", xmrwxx);


        return resultMap;
    }

    /**
     * 新增或修改项目任务信息
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateXmrwxx(@RequestBody Xmrwxx xmrwxx) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, String> zdm = new HashMap<String, String>();
        zdm.put("title", "任务名称");
        zdm.put("status", "当前状态");
        zdm.put("fzrName", "负责人");
        zdm.put("startDate", "开始时间");
        zdm.put("endDate", "截止时间");
        zdm.put("rwxq", "任务详情");
        zdm.put("rwms", "任务描述");
        zdm.put("fhrName", "复核人");
        zdm.put("zhrName", "知会人");
        zdm.put("xmjd", "项目阶段");
        zdm.put("creatorName", "变更人");
        zdm.put("spyj", "审批意见");
        zdm.put("spms", "审批描述");
        zdm.put("zhzt", "知会状态");
        zdm.put("zhms", "知会描述");
        zdm.put("zrwxh", "子任务序号");
//        zdm.put("rwxh", "任务序号");
        zdm.put("rwmc", "任务名称");
        zdm.put("cron", "定时配置");
        //新增
        if (StringHelper.isBlank(xmrwxx.getId())) {
            //设置初始化值
            // 数据有效
            xmrwxx.setXmId(xmrwxx.getXmId());
            xmrwxx.setXmjd(xmrwxx.getXmjd());
            xmrwxx.setSpyj("待审批");
            xmrwxx.setZhzt("待处理");
            xmrwxx.setDataStatus(GlobalConstant.DATA_VALID);
            xmrwxx.setCreatorId(currentManager.getId().toString());
            xmrwxx.setCreatorName(currentManager.getName());
            xmrwxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            xmrwxx = xmrwxxService.addXmrwxx(xmrwxx);
            resultMap.put("flag", "true");
            resultMap.put("msg", "项目任务信息新增成功");
            resultMap.put("id", xmrwxx.getId());
//            model.addAttribute("xmrwxx",xmrwxx);

            // 记录操作日志
            saveBusinessLog("项目任务信息管理", "新增项目任务信息", xmrwxx);
            //发送邮件
//            applicationContext.publishEvent(new CreateXmSendEmailEvent(xmrwxx));

            //任务操作记录
            Rwczjl rwczjl = new Rwczjl();
            rwczjl.setRwId(xmrwxx.getId());
            rwczjl.setXmId(xmrwxx.getXmId());
            rwczjl.setBgnr("创建任务");
            rwczjl.setDataStatus(GlobalConstant.DATA_VALID);
            rwczjl.setCreatorId(currentManager.getId().toString());
            rwczjl.setCreatorName(currentManager.getName());
            rwczjl.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            //比较变更内容
            Map<String, Object> rtn = BeanHelper.getDifferentProperty(new Rwczjl(), rwczjl, new String[]{"id", "fzrId", "fzrXx", "zhrId", "zhrXx", "fhrId", "fhrXx"});
            String bgq = rtn.get("bgq").toString();
            String bgh = rtn.get("bgh").toString();
            Iterator iter = zdm.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                bgq = bgq.replaceAll(key.toString(), val.toString());
                bgh = bgh.replaceAll(key.toString(), val.toString());
            }
            rwczjl.setBgq(bgq);
            rwczjl.setBgh(bgh);

            rwczjl = rwczjlService.addRwczjl(rwczjl);

            //修改定时任务时间
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(xmrwxx.getRwxq());
            Map<String, String> taskConstants = dynamicTask.getTaskConstants();
            for (int i = 0; i < jsonArray.size(); i++) {
                net.sf.json.JSONObject spr = jsonArray.getJSONObject(i);
                String rwmc = spr.getString("rwmc");
                String cron = spr.getString("cron");
                if (StringHelper.isNotBlank(cron)&&CronSequenceGenerator.isValidExpression(cron)) {
                    taskConstants.put(rwmc,cron);
                }
            }
            dynamicTask.setTaskConstants(taskConstants);

            return resultMap;
        } else {//编辑
            Xmrwxx xmrwxx_old = xmrwxxService.getXmrwxxById(xmrwxx.getId());
            Map<String, Object> rtn = BeanHelper.getDifferentProperty(xmrwxx_old, xmrwxx, new String[]{"id", "fzrId", "fzrXx", "zhrId", "zhrXx", "fhrId", "fhrXx"});
            String diff = rtn.get("diff").toString();
            String bgq = rtn.get("bgq").toString();
            String bgh = rtn.get("bgh").toString();
            if (StringHelper.isNotBlank(diff)) {
                Iterator iter = zdm.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    diff = diff.replaceAll(key.toString(), val.toString());
                    bgq = bgq.replaceAll(key.toString(), val.toString());
                    bgh = bgh.replaceAll(key.toString(), val.toString());
                }

                xmrwxx.setEditorId(currentManager.getId());
                xmrwxx.setEditorName(currentManager.getName());
                xmrwxx.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
                if (StringHelper.isBlank(xmrwxx.getRwxq()) || xmrwxx.getRwxq().equalsIgnoreCase("[]")) {
                    String jsonMessage = "[{'zrwxh':'', 'rwmc':'', 'cron':''}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else {
                    List<JSONObject> list = JsonHelper.sort(JSONArray.parseArray(xmrwxx.getRwxq()), "zrwxh", true);
                    JSONArray jsonArray = JSONArray.parseArray(list.toString());
                    xmrwxx.setRwxq(jsonArray.toString());
                }
                xmrwxxService.updateXmrwxx(xmrwxx);

                //修改定时任务时间
                xmrwxx = xmrwxxService.getXmrwxxById(xmrwxx.getId());
                String regEx = "^\\s*($|#|\\w+\\s*=|(\\?|\\*|(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?(?:,(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?)*)\\s+(\\?|\\*|(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?(?:,(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?)*)\\s+(\\?|\\*|(?:[01]?\\d|2[0-3])(?:(?:-|\\/|\\,)(?:[01]?\\d|2[0-3]))?(?:,(?:[01]?\\d|2[0-3])(?:(?:-|\\/|\\,)(?:[01]?\\d|2[0-3]))?)*)\\s+(\\?|\\*|(?:0?[1-9]|[12]\\d|3[01])(?:(?:-|\\/|\\,)(?:0?[1-9]|[12]\\d|3[01]))?(?:,(?:0?[1-9]|[12]\\d|3[01])(?:(?:-|\\/|\\,)(?:0?[1-9]|[12]\\d|3[01]))?)*)\\s+(\\?|\\*|(?:[1-9]|1[012])(?:(?:-|\\/|\\,)(?:[1-9]|1[012]))?(?:L|W)?(?:,(?:[1-9]|1[012])(?:(?:-|\\/|\\,)(?:[1-9]|1[012]))?(?:L|W)?)*|\\?|\\*|(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?(?:,(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?)*)\\s+(\\?|\\*|(?:[0-6])(?:(?:-|\\/|\\,|#)(?:[0-6]))?(?:L)?(?:,(?:[0-6])(?:(?:-|\\/|\\,|#)(?:[0-6]))?(?:L)?)*|\\?|\\*|(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?(?:,(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?)*)(|\\s)+(\\?|\\*|(?:|\\d{4})(?:(?:-|\\/|\\,)(?:|\\d{4}))?(?:,(?:|\\d{4})(?:(?:-|\\/|\\,)(?:|\\d{4}))?)*))$";
                if (!xmrwxx.getRwxq().equalsIgnoreCase(xmrwxx_old.getRwxq())) {
                    net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(xmrwxx.getRwxq());
                    net.sf.json.JSONArray jsonArray_old = net.sf.json.JSONArray.fromObject(xmrwxx_old.getRwxq());
                    Map<String, String> taskConstants = dynamicTask.getTaskConstants();
                    for (int i = 0; i < jsonArray_old.size(); i++) {
                        net.sf.json.JSONObject spr = jsonArray.getJSONObject(i);
                        String rwmc = spr.getString("rwmc");
                        String cron = spr.getString("cron");
                        //子任务被删除
                        if (JsonArray2map(jsonArray_old).containsKey(rwmc)&&!JsonArray2map(jsonArray).containsKey(rwmc)) {
//                            taskConstants.remove(rwmc);
                            taskConstants.put(rwmc,null);
                            dynamicTask.execute();
                        }
                    }
                    for (int i = 0; i < jsonArray.size(); i++) {
                        net.sf.json.JSONObject spr = jsonArray.getJSONObject(i);
                        String rwmc = spr.getString("rwmc");
                        String cron = spr.getString("cron");
                        //cron被清空
                        if (StringHelper.isBlank(cron)&&JsonArray2map(jsonArray).containsKey(rwmc)&&JsonArray2map(jsonArray_old).containsKey(rwmc)){
//                            taskConstants.remove(rwmc);
                            taskConstants.put(rwmc,null);
                            dynamicTask.execute();
                        }
                        //更新taskConstants
                        if (StringHelper.isNotBlank(cron)&&CronSequenceGenerator.isValidExpression(cron)) {
//                        if (StringHelper.isNotBlank(cron)&&cron.matches(regEx)) {
                            taskConstants.put(rwmc,cron);
                        }
                    }
                    dynamicTask.setTaskConstants(taskConstants);
                }

                resultMap.put("flag", "true");
                resultMap.put("msg", "项目任务信息修改成功");

                // 记录操作日志
                saveBusinessLog("项目任务信息管理", "修改项目任务信息", diff);

                //任务操作记录
                Rwczjl rwczjl = new Rwczjl();
                rwczjl.setRwId(xmrwxx_old.getId());
                rwczjl.setXmId(xmrwxx_old.getXmId());
                rwczjl.setBgnr("任务信息");
                rwczjl.setDataStatus(GlobalConstant.DATA_VALID);
                rwczjl.setCreatorId(currentManager.getId().toString());
                rwczjl.setCreatorName(currentManager.getName());
                rwczjl.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                //比较变更内容
                rwczjl.setBgq(bgq);
                rwczjl.setBgh(bgh);

                rwczjl = rwczjlService.addRwczjl(rwczjl);


            } else {
                resultMap.put("flag", "true");
                resultMap.put("msg", "项目任务信息没有变化");
            }
            return resultMap;
        }

    }

    /**
     *  json to map
     */
    public static Map<String, String> JsonArray2map(net.sf.json.JSONArray jsonArray) {
        Map<String, String> map  = new HashMap<String, String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject spr = jsonArray.getJSONObject(i);
            String rwmc = spr.getString("rwmc");
            String cron = spr.getString("cron");
            if (StringHelper.isNotBlank(cron)) {
                map.put(rwmc,cron);
            }
        }

        return map;
    }
    /**
     * 项目任务操作记录信息加载页面
     *
     * @return
     * @Author 魏列军
     * @Description
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @RequestMapping(value = "/czjlList", method = RequestMethod.GET)
    public String getCzjlList(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/xmrwxx/add";
    }

    /**
     * 项目任务操作记录信息列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @PostMapping(value = "/czjlList")
    @ResponseBody
    public Map<String, Object> listCzjlList(HttpServletRequest request, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);

        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        Map<String, Object> params = getQureyParams(requestParams);

        final Page<Rwczjl> results = (Page<Rwczjl>) rwczjlService.listRwczjlByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录操作日志
        saveBusinessLog("项目任务操作记录信息管理", "项目任务操作记录信息列表", params);

        return resultMap;
    }
}
