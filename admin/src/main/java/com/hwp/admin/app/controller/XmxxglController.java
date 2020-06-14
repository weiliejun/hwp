package com.hwp.admin.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.event.BgjdSendEmailEvent;
import com.hwp.admin.app.event.CreateXmSendEmailEvent;
import com.hwp.admin.app.event.JsxmsqSendEmailEvent;
import com.hwp.admin.app.event.ModifyXmSendEmailEvent;
import com.hwp.admin.app.service.productOtherAttachFile.ProductOtherAttachFileService;
import com.hwp.admin.app.service.ryxxgl.RyxxglService;
import com.hwp.admin.app.service.spsqxx.SpsqxxService;
import com.hwp.admin.app.service.xmrwxx.XmrwxxService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.productOtherAttachFile.bean.AttachFileHref;
import com.hwp.common.model.productOtherAttachFile.bean.ProductOtherAttachFile;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * 项目信息
 *
 * @author 魏列军
 * @date 2020/5/15 15:30:22
 */
@Controller
@RequestMapping("/xmxxgl")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class XmxxglController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(XmxxglController.class);

    @Autowired
    private XmxxglService xmxxglService;

    @Autowired
    private XmrwxxService xmrwxxService;

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
    /**
     * 图片服务器访问地址
     */
    @Value("${resourceServer.AccessURL}")
    private String resourceServerURL;

    private static boolean deleteDir(File dir) {
        if (dir.isFile()) {
            if (dir.isDirectory()) {
                // 递归删除目录中的子目录下
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 新增项目信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/toAdd"}, method = RequestMethod.GET)
    public String toAdd(Model model, @RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
        if (StringHelper.isNotBlank(id)) {
            Xmxxgl xmxxgl = xmxxglService.getXmxxglById(id);
            model.addAttribute("xmxxgl", xmxxgl);
        } else {
            model.addAttribute("xmxxgl", new Xmxxgl());
        }
        return "/app/xmxxgl/add";
    }

    /**
     * 修改项目信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/get/{type}/{id}"}, method = RequestMethod.GET)
    public String findXmxxglById(HttpServletRequest request, @PathVariable("type") String type, @PathVariable("id") String id, @RequestParam(value = "xmjd", required = false) String xmjd, Model model) {
        Xmxxgl xmxxgl = xmxxglService.getXmxxglById(id);
        model.addAttribute("xmxxgl", xmxxgl);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("view")) {
            return "/app/xmxxgl/view";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("jsxm")) {
            ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(id);
            if (productOtherAttachFile == null) {
                model.addAttribute("message", "产品附件不存在");
            } else {
                String attachFile = productOtherAttachFile.getAttachFile();
                if (StringUtils.isNotBlank(attachFile)) {
                    //对去除空格转义符处理
                    String a = HtmlUtils.htmlUnescape(attachFile.replaceAll(" ", ""));
                    AttachFileHref attachFileHref = new AttachFileHref();
                    Map<String, String> parse = (Map<String, String>) JSON.parse(a);
                    List<AttachFileHref> fileHrefList = new ArrayList<>();
                    for (Map.Entry<String, String> entry : parse.entrySet()) {
                        attachFileHref.setFileName(entry.getKey());
                        attachFileHref.setFileUrl(entry.getValue());
                        fileHrefList.add(attachFileHref);
                    }
                    model.addAttribute("productOtherAttachFile", productOtherAttachFile);
                    model.addAttribute("AttachFileList", fileHrefList);
                }

            }
            return "/app/xmxxgl/jsxm";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("bgjd")) {
            return "/app/xmxxgl/bgjd";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("xmgl")) {
            xmxxgl.setId(id);
            xmxxgl.setXmjd(xmjd);
            model.addAttribute("xmxxgl", xmxxgl);
            return "/app/xmxxgl/xmgl";
        }
        return "/app/xmxxgl/add";
    }

    /**
     * 项目信息加载页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO 使用shiro中用户session缓存在redis中每次请求中页面都会携带jsessionid所用通过thymeleaf渲染模板时需传递请求信息
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getXmxxgl(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/xmxxgl/list";
    }

    /**
     * 项目信息列表
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listXmxxgl(HttpServletRequest request, Model model) {
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

        final Page<Xmxxgl> results = (Page<Xmxxgl>) xmxxglService.listXmxxglByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录操作日志
        saveBusinessLog("项目信息管理", "项目信息列表", params);

        return resultMap;
    }

    /**
     * 结束项目、变更阶段信息
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate2/{type}")
    @ResponseBody
    public Map<String, Object> addOrUpdate2(@RequestBody JsxmXmxxgl xmxxgl, @PathVariable("type") String type) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //变更阶段
        if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("bgjd")) {
            Xmxxgl xmxxgl_old = xmxxglService.getXmxxglById(xmxxgl.getId());
            String xmjd1 = xmxxgl_old.getXmjd();
            String xmjd2 = xmxxgl.getXmjd();
            //发送邮件
            applicationContext.publishEvent(new BgjdSendEmailEvent(this, xmjd1, xmjd2, xmxxgl));

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xmId", xmxxgl.getId());
            params.put("xmjd", xmxxgl.getXmjd());
            List<Xmrwxx> xmrwxxList = xmrwxxService.listXmrwxxByParams(params);
            if (xmrwxxList == null || xmrwxxList.size() == 0) {
                //创建模板任务
                HashMap<String, String> map = new HashMap<String, String>();
                if (StringHelper.isNotBlank(xmjd2) && xmjd2.equalsIgnoreCase("投中阶段")) {
                    map.put("1", "签署投资协议");
                    map.put("2", "投资放款条件");
                    map.put("3", "投资放款");
                    map.put("4", "资料保存");
                } else if (StringHelper.isNotBlank(xmjd2) && xmjd2.equalsIgnoreCase("投后阶段")) {
                    map.put("1", "合伙企业账户管理");
                    map.put("2", "合作企业财务管理");
                    map.put("3", "出具投后报告");
                    map.put("4", "日常监控");
                    map.put("5", "合同履约监督");
                    map.put("6", "合伙企业投委会及风控");
                    map.put("7", "资料保存");
                } else if (StringHelper.isNotBlank(xmjd2) && xmjd2.equalsIgnoreCase("清算阶段")) {
                    map.put("1", "成立清算小组成员");
                    map.put("2", "清算报告");
                    map.put("3", "清算划款");
                }
                SortedMap<String, String> sort = new TreeMap<String, String>(map);
                Set<Map.Entry<String, String>> entry1 = sort.entrySet();
                Iterator<Map.Entry<String, String>> it = entry1.iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    System.out.println("排序之后:" + entry.getKey() + " 值" + entry.getValue());
                    Xmrwxx xmrwxx = new Xmrwxx();
                    xmrwxx.setXmId(xmxxgl.getId());
                    xmrwxx.setXmjd(xmxxgl.getXmjd());
                    xmrwxx.setStatus("未开始");
                    xmrwxx.setSpyj("待审批");
                    xmrwxx.setZhzt("待处理");
                    xmrwxx.setRwxh(entry.getKey());
                    if (StringHelper.isNotBlank(xmjd2) && xmjd2.equalsIgnoreCase("投中阶段")) {
                        if (entry.getKey().equalsIgnoreCase("1")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'项目组成员必须签署保密协议'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("2")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'填写放款条件表'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("3")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'放款请求'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("4")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'资料清单'}," +
                                    "{'zrwxh':'2', 'rwmc':'上传文件'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        }
                    } else if (StringHelper.isNotBlank(xmjd2) && xmjd2.equalsIgnoreCase("投后阶段")) {
                        if (entry.getKey().equalsIgnoreCase("1")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'印鉴及证照管理：印章、证照保存在银行保险柜中，保险柜由三方共管。'}," +
                                    "{'zrwxh':'2', 'rwmc':'银行账户管理：划款指令由三方按预留印鉴用印、签字后生效。'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("2")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'划款、发票等核查；'}," +
                                    "{'zrwxh':'2', 'rwmc':'按季度对合伙企业报表核查；'}," +
                                    "{'zrwxh':'3', 'rwmc':'年度对合伙企业税务及工商核查；'}," +
                                    "{'zrwxh':'4', 'rwmc':'每年3-4月汇同会所审计师到深圳做基金的审计工作、并协助出具审计报告。'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("3")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'每年5月30日前出具上一年年报；'}," +
                                    "{'zrwxh':'2', 'rwmc':'每年9月30日前出具半年报。'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("4")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'每日合伙企业舆情、涉诉等监督；'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("5")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'梳理签署合同，监督各方对合同的执行'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("6")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'成立投委会'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("7")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'资料清单'}," +
                                    "{'zrwxh':'2', 'rwmc':'上传文件'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        }
                    } else if (StringHelper.isNotBlank(xmjd2) && xmjd2.equalsIgnoreCase("清算阶段")) {
                        if (entry.getKey().equalsIgnoreCase("1")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'成立清算小组'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("2")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'第三方出清算报告'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("3")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'清算划款'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        } else if (entry.getKey().equalsIgnoreCase("4")) {
                            String jsonMessage = "[{'zrwxh':'1', 'rwmc':'资料清单'}," +
                                    "{'zrwxh':'2', 'rwmc':'上传文件'}]";
                            net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                            xmrwxx.setRwxq(myJsonArray.toString());
                        }
                    }
                    xmrwxx.setTitle(entry.getValue());
                    xmrwxx.setDataStatus(GlobalConstant.DATA_VALID);
                    xmrwxx.setCreatorId(currentManager.getId().toString());
                    xmrwxx.setCreatorName(currentManager.getName());
                    xmrwxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                    xmrwxxService.addXmrwxx(xmrwxx);
                }
            }
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("jsxm")) {
            Xmxxgl xmxxgl_old = xmxxglService.getXmxxglById(xmxxgl.getId());
            if (StringHelper.isNotBlank(xmxxgl_old.getSpyj())) {
                xmxxgl.setSpyj("待审批");
            }
            //对附件表添加创建人数据
            ProductOtherAttachFile productOtherAttachFile = new ProductOtherAttachFile();
            productOtherAttachFile.setDataStatus(GlobalConstant.DATA_VALID);
            productOtherAttachFile.setId(RandomUtil.getSerialNumber());
            productOtherAttachFile.setProductId(xmxxgl.getId());
            //附件类型  结束项目上传文件
            productOtherAttachFile.setType("jsxmAttachFile");
            productOtherAttachFile.setAttachFile(xmxxgl.getAttachFile());
            productOtherAttachFile.setCreatorId(currentManager.getId().toString());
            productOtherAttachFile.setCreatorName(currentManager.getName());
            productOtherAttachFile.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            ProductOtherAttachFile productOtherAttachFile2 = productOtherAttachFileService.selectProductOtherAttachFileByProductId(xmxxgl.getId());
            if (productOtherAttachFile2 == null) {
                productOtherAttachFileService.addProductOtherAttachFile(productOtherAttachFile);
            } else {
                productOtherAttachFileService.updateProductOtherAttachFileById(productOtherAttachFile);
            }

            //审批信息表
            Ryxxgl ryxxgl = ryxxglService.getRyxxglByName(currentManager.getName());
            //将此项目以前提交的结束项目审批记录置为无效
            spsqxxService.updateInvalidByGlId(xmxxgl.getId());
            if (StringHelper.isNotBlank(xmxxgl.getJsxmSpr())) {
                net.sf.json.JSONArray selectSpr = net.sf.json.JSONArray.fromObject(xmxxgl.getJsxmSpr());
                for (int i = 0; i < selectSpr.size(); i++) {
                    net.sf.json.JSONObject spr = selectSpr.getJSONObject(i);
                    Spsqxx spsqxx = new Spsqxx();
                    spsqxx.setGlId(xmxxgl.getId());
                    spsqxx.setSplx("结束项目");
                    spsqxx.setTzlx("审批");
                    spsqxx.setSpyj("待审批");
                    spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
                    spsqxx.setCreatorId(ryxxgl.getId());
                    spsqxx.setCreatorName(ryxxgl.getName());
                    spsqxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                    spsqxx.setSpr(spr.toString());
                    spsqxx.setSprId(spr.getString("id"));
                    spsqxx.setSprName(spr.getString("name"));
                    spsqxx.setXmjd(xmxxgl_old.getXmjd());
                    spsqxxService.addSpsqxx(spsqxx);
                }
            }

            //给审批人发送邮件
            xmxxgl.setCreatorName(ryxxgl.getName());
            xmxxgl.setCpmc(xmxxgl_old.getCpmc());
            applicationContext.publishEvent(new JsxmsqSendEmailEvent(xmxxgl));

            xmxxgl.setGdsj(DateHelper.getYMDFormatDate(new Date()));
        }

        xmxxglService.updateXmxxgl(xmxxgl);
        resultMap.put("flag", "true");
        resultMap.put("msg", "项目信息修改成功");

        // 记录操作日志
        saveBusinessLog("项目信息管理", "修改项目信息", xmxxgl);


        return resultMap;
    }

    /**
     * 新增或修改项目信息
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateXmxxgl(@RequestBody Xmxxgl xmxxgl) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //新增
        if (StringHelper.isBlank(xmxxgl.getId())) {
            //设置初始化值
            // 数据有效
            xmxxgl.setDataStatus(GlobalConstant.DATA_VALID);
            xmxxgl.setXmjd("投前阶段");
            xmxxgl.setGdlb("运行中项目列表");
            xmxxgl.setSpyj("待审批");
            xmxxgl.setCreatorId(currentManager.getId().toString());
            xmxxgl.setCreatorName(currentManager.getName());
            xmxxgl.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            xmxxgl = xmxxglService.addXmxxgl(xmxxgl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "项目信息新增成功");

            //创建模板任务
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("1", "签署保密协议(若有)");
            map.put("2", "立项尽调");
            map.put("3", "立项会");
            map.put("4", "深入尽调");
            map.put("5", "风控会");
            map.put("6", "签署合同");
            map.put("7", "立项会");
            map.put("8", "基金成立");

            SortedMap<String, String> sort = new TreeMap<String, String>(map);
            Set<Map.Entry<String, String>> entry1 = sort.entrySet();
            Iterator<Map.Entry<String, String>> it = entry1.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                System.out.println("排序之后:" + entry.getKey() + " 值" + entry.getValue());
                Xmrwxx xmrwxx = new Xmrwxx();
                xmrwxx.setXmId(xmxxgl.getId());
                xmrwxx.setXmjd(xmxxgl.getXmjd());
                xmrwxx.setStatus("未开始");
                xmrwxx.setSpyj("待审批");
                xmrwxx.setZhzt("待处理");
                xmrwxx.setRwxh(entry.getKey());
                if (entry.getKey().equalsIgnoreCase("1")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'项目组成员必须签署保密协议'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("2")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'基础立项尽调清单'}," +
                            "{'zrwxh':'2', 'rwmc':'现场尽调'}," +
                            "{'zrwxh':'3', 'rwmc':'补充其他尽调资料'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("3")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'发起立项会议通知'}," +
                            "{'zrwxh':'2', 'rwmc':'立项会决议结果'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("4")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'深入立项尽调清单'}," +
                            "{'zrwxh':'2', 'rwmc':'现场尽调'}," +
                            "{'zrwxh':'3', 'rwmc':'补充其他尽调资料'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("5")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'发起风控会议通知'}," +
                            "{'zrwxh':'2', 'rwmc':'风控会决议'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("6")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'签署合作协议'}," +
                            "{'zrwxh':'2', 'rwmc':'签署基金合同'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("7")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'准备合格投资人资料'}," +
                            "{'zrwxh':'2', 'rwmc':'基金募集打款证明'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                } else if (entry.getKey().equalsIgnoreCase("8")) {
                    String jsonMessage = "[{'zrwxh':'1', 'rwmc':'基金成立日期'}," +
                            "{'zrwxh':'2', 'rwmc':'基金备案日期'}]";
                    net.sf.json.JSONArray myJsonArray = net.sf.json.JSONArray.fromObject(jsonMessage);
                    xmrwxx.setRwxq(myJsonArray.toString());
                }
                xmrwxx.setTitle(entry.getValue());
                xmrwxx.setDataStatus(GlobalConstant.DATA_VALID);
                xmrwxx.setCreatorId(currentManager.getId().toString());
                xmrwxx.setCreatorName(currentManager.getName());
                xmrwxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                xmrwxxService.addXmrwxx(xmrwxx);
            }
            // 记录操作日志
            saveBusinessLog("项目信息管理", "新增项目信息", xmxxgl);
            //发送邮件
            applicationContext.publishEvent(new CreateXmSendEmailEvent(xmxxgl));

            return resultMap;
        } else {//编辑
            Xmxxgl xmxxgl_old = xmxxglService.getXmxxglById(xmxxgl.getId());
            String diff = BeanHelper.getDifferentProperty(xmxxgl_old, xmxxgl, new String[]{"id", "xmfzrId", "xmfzrXx", "xmjbrId", "xmjbrXx", "fwfzrId", "fwfzrXx", "cwfzrId", "cwfzrXx", "xmqtcyId", "xmqtcy", "sprId", "spr"});
            if (StringHelper.isNotBlank(diff)) {
                Map<String, String> zdm = new HashMap<String, String>();
                zdm.put("ywlx", "业务类型");
                zdm.put("cpmc", "产品名称");
                zdm.put("cpgm", "产品规模");
                zdm.put("cpqx", "产品期限");
                zdm.put("yqsy", "预期收益");
                zdm.put("xmly", "项目来源");
                zdm.put("zjly", "资金来源");
                zdm.put("btfw", "被投范围");
                zdm.put("cjzt", "承接主体");
                zdm.put("ztxm", "直投项目");
                zdm.put("jtxm", "间投项目");
                zdm.put("tztj", "投资条件");
                zdm.put("glf", "管理费");
                zdm.put("rgf", "认购费");
                zdm.put("tgf", "托管费");
                zdm.put("sgf", "申购费");
                zdm.put("xsf", "销售费");
                zdm.put("shf", "赎回费");
                zdm.put("xmfzrName", "项目负责人");
                zdm.put("xmjbrName ", "项目经办人");
                zdm.put("fwfzrName", "法务负责人");
                zdm.put("cwfzrName", "财务负责人");
                zdm.put("xmqtcyName", "其他成员");
                zdm.put("sprName", "审批人");
                Iterator iter = zdm.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    diff = diff.replaceAll(key.toString(), val.toString());
                }

                xmxxgl.setEditorId(currentManager.getId());
                xmxxgl.setEditorName(currentManager.getName());
                xmxxgl.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
                xmxxglService.updateXmxxgl(xmxxgl);
                resultMap.put("flag", "true");
                resultMap.put("msg", "项目信息修改成功");

                // 记录操作日志
                saveBusinessLog("项目信息管理", "修改项目信息", diff);

                //给审批人发送邮件
                applicationContext.publishEvent(new ModifyXmSendEmailEvent(this, diff, xmxxgl));
            } else {
                resultMap.put("flag", "true");
                resultMap.put("msg", "项目信息没有变化");
            }
            return resultMap;
        }

    }

    /**
     * 项目信息上传图片
     *
     * @return
     * @Author 魏列军
     * @description 单文件上传
     * @Date 2019/7/23 14:31
     * @Param
     **/
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>(16);

        // 获取上传的多部件对象
        Map<String, MultipartFile> formMultipartFileMap = getFormMultipartFile(request);

        if (formMultipartFileMap == null || formMultipartFileMap.size() == 0) {
            resultMap.put("msg", "error");
            resultMap.put("code", "1");
            return resultMap;
        }

        // 获取上传的文件
        MultipartFile file = formMultipartFileMap.get("file");

        // 获取应用上下文根路径
        String rootPath = request.getSession().getServletContext()
                .getRealPath(File.separator);
        // 文件在应用中存储的路径
        String tempSaveFileDirPath = GlobalConstant.APP_JIAOKAN_TEMP_USER_PHOTO_PATH + File.separator
                + getSessionSysManager().getId() + File.separator + DateHelper.getYMDFormatDate(new Date()) + File.separator;
        // 文件存储路径在对应的File
        File tempDirPath = new File(rootPath + tempSaveFileDirPath);
        // 保存的文件名称
        String tempSaveFileName = null;


        if (!file.isEmpty()) {
            tempSaveFileName = RandomUtil.getSerialNumber()
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            try {
                if (!tempDirPath.exists()) {
                    FileUtils.forceMkdir(tempDirPath);
                }

                File tempSaveFile = new File(tempDirPath, tempSaveFileName);
                // 创建此文件
                if (tempSaveFile.createNewFile()) {
                    // 保存到本地临时文件
                    FileCopyHelper.copy(file.getBytes(), tempSaveFile);
                    // 同步到资源服务器
                    FileSynchronizer.syncFileByIs(tempSaveFileDirPath, tempSaveFileName, file.getInputStream());
                }

            } catch (Exception e) {
                logger.error("项目信息上传图片异常");
            }
            resultMap.put("msg", "ok");
            resultMap.put("code", "0");
            resultMap.put("logo", resourceServerURL + tempSaveFileDirPath + tempSaveFileName);
            return resultMap;

        }
        resultMap.put("msg", "error");
        resultMap.put("code", "1");

        return resultMap;
    }

    /**
     * @param request
     * @param model
     * @return
     * @throws IOException
     * @description 多文件上传
     * @version v1.0
     * @author 吕剑
     * @update 2019年6月24日 上午14:56:50
     */
    @RequestMapping("/uploadFiles")
    public @ResponseBody
    Map<String, Object> uploadFiles(HttpServletRequest request, Model model)
            throws IOException {
        Map<String, Object> map = new HashMap<>();

        String realPath = request.getSession().getServletContext()
                .getRealPath(File.separator);

        //拿到文件集合 并对文件进行处理
        Map<String, MultipartFile> formMultipartFile = getFormMultipartFile(request);
        if (formMultipartFile == null) {
            map.put("code", "false");
            map.put("msg", "不能上传空的文件");
        }
        //将每次文件的相关数据都存入jsonArray
        JSONArray jsonAry = null;
        for (Map.Entry<String, MultipartFile> entry : formMultipartFile.entrySet()) {
            MultipartFile uploadFile = entry.getValue();
            String fileName = FileHelper.disposeFileName(uploadFile
                    .getOriginalFilename());
            String extension = StringHelper.unqualify(fileName).toLowerCase();
            int type = ImageHelper.IMAGE_UNKNOWN;
            if (uploadFile.getSize() > 1048576) {
                map.put("code", "big");
                map.put("tmpFileName", fileName);
            } else if (extension.equals("jpg") || extension.equals("jpeg")
                    || extension.equals("gif") || extension.equals("png")
                    || "doc".equals(extension) ||
                    "docx".equals(extension) ||
                    "pdf".equals(extension) ||
                    "xls".equals(extension) ||
                    "xlsx".equals(extension) ||
                    "ppt".equals(extension) ||
                    "pptx".equals(extension)) {

                if (extension.equals("jpg") || extension.equals("jpeg")) {
                    type = ImageHelper.IMAGE_JPEG;
                } else if (extension.equals("gif") || extension.equals("png")) {
                    type = ImageHelper.IMAGE_PNG;
                    if (extension.toLowerCase().equals("gif")) {
                        extension = "png";// We cannot handle gifs
                    }
                }
                try {
                    // 临时文件名称不能有汉字，统一重命名
                    String tmpFileName = RandomUtil.getSerialNumber() + "." + extension;
                    String tmpPath = GlobalConstant.APP_QIYEBAO_TEMP_USER_PHOTO_PATH + File.separator;
                    if (uploadFile != null && !uploadFile.isEmpty()) {
                        logger.info("原临时图片不存在或已删除！");
                        logger.info("tmpPath=======================" + tmpPath);
                        File tmpFile = new File(realPath + tmpPath);
                        if (!tmpFile.exists()) {
                            tmpFile.mkdirs();
                        }
                        String tmpFilePath = realPath + tmpPath + tmpFileName;
                        if (extension.equals("jpg") || extension.equals("jpeg")
                                || extension.equals("gif") || extension.equals("png")) {
                            //保存图片
                            BufferedImage image = ImageHelper.resizeImage(
                                    uploadFile.getInputStream(), type, 226, 150);
                            ImageHelper.saveImage(image, tmpFilePath, type);
                        } else {
                            uploadFile.transferTo(new File(tmpFilePath));
                        }
                        File file = new File(tmpFilePath);
                        //数据库-资源服务器地址
                        String savePath = GlobalConstant.APP_QIYEBAO_USER_PHOTO_PATH + File.separator;
                        boolean b = FileSynchronizer.syncFile(file, savePath, file.getName());// 上传到网站端-资源服务器
                        if (b == false) {
                            map.put("tmpFileName", tmpFileName);
                            map.put("code", "lose");
                        } else {
                            jsonAry = new JSONArray();
                            JSONObject uploadFileInfo = new JSONObject();
                            uploadFileInfo.put("name", file.getName());
                            uploadFileInfo.put("path", FileSynchronizer.getReceiverUrl() + savePath + file.getName());
                            uploadFileInfo.put("type", file.getName().substring(file.getName().indexOf(".") + 1, file.getName().length()));
                            uploadFileInfo.put("thumbnail", "null");
                            jsonAry.add(uploadFileInfo);
                            logger.info("tempFilePath==" + File.separator + tmpFileName);
                            map.put("tmpFilePath", tmpPath + File.separator
                                    + tmpFileName);
                            map.put("code", "0");
                            map.put("oldFileName", fileName);
                            map.put("tmpFileName", tmpFileName);
                        }
                    } else {
                        map.put("tmpFileName", tmpFileName);
                        map.put("code", "1");
                    }
                } catch (Exception e) {
                    map.put("code", "2");
                    map.put("message", "服务忙碌请稍后重试");
                    e.printStackTrace();
                }
            } else {
                map.put("code", "false");
                map.put("message", "请选择正确格式的文件");
            }
        }
        if (jsonAry == null) {
            return map;
        }
        map.put("attachFile", jsonAry.toJSONString());
        return map;
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 加载项目管理-投前页面
     * @Date 2020/5/21 17:22
     * @Param
     **/
    @PostMapping("/xmgl/{id}/{xmjd}")
    @ResponseBody
    public Map<String, Object> xmgl(HttpServletRequest request, Model model, @PathVariable("id") String id, @PathVariable("xmjd") String xmjd) {
        logger.debug(request.getRequestURI() + "项目管理页面");
        Xmxxgl xmxxgl = xmxxglService.getXmxxglById(id);
        model.addAttribute("xmxxgl", xmxxgl);
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        String flag = "true", msg = "获取成功";
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xmId", id);
            params.put("xmjd", StringHelper.isNotBlank(xmjd) ? xmjd : xmxxgl.getXmjd());
            List<Xmrwxx> xmInfo = xmrwxxService.listXmrwxxByParams(params);
            resultMap.put("data", xmInfo);
        } catch (Exception e) {
            logger.error("异常");
            flag = "false";
            msg = "获取数据失败";
        }
        resultMap.put("flag", flag);
        resultMap.put("msg", msg);

        return resultMap;

    }
}
