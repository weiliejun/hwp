package com.hwp.admin.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.event.JsxmspSendEmailEvent;
import com.hwp.admin.app.event.RwfhspSendEmailEvent;
import com.hwp.admin.app.event.RybgspSendEmailEvent;
import com.hwp.admin.app.service.productOtherAttachFile.ProductOtherAttachFileService;
import com.hwp.admin.app.service.rybgsq.RybgsqService;
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
import com.hwp.common.model.rybgsq.bean.Rybgsq;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.spsqxx.bean.XmSpsqxx;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.*;
import net.sf.json.JSONObject;
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
@RequestMapping("/spsqxx")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class SpsqxxController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(SpsqxxController.class);

    @Autowired
    private SpsqxxService spsqxxService;

    @Autowired
    private XmxxglService xmxxglService;

    @Autowired
    private XmrwxxService xmrwxxService;

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
    private RybgsqService rybgsqService;
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
            Spsqxx spsqxx = spsqxxService.getSpsqxxById(id);
            model.addAttribute("spsqxx", spsqxx);
        } else {
            model.addAttribute("spsqxx", new Spsqxx());
        }
        return "/app/spsqxx/add";
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
    @RequestMapping(value = {"/get/{type}/{id}/{glId}"}, method = RequestMethod.GET)
    public String findSpsqxxById(HttpServletRequest request, @PathVariable("type") String type, @PathVariable("id") String id, @PathVariable("glId") String glId, @RequestParam(value = "cxmk", required = false) String cxmk, Model model) {
        SysManager currentManager = getSessionSysManager();
        Spsqxx spsqxx = spsqxxService.getSpsqxxById(id);
        model.addAttribute("spsqxx", spsqxx);
        model.addAttribute("cxmk", cxmk);

        if (StringHelper.isNotBlank(type) && (type.equalsIgnoreCase("jsxmsp") || type.equalsIgnoreCase("jsxmView"))) {
            Xmxxgl xmxxgl = xmxxglService.getXmxxglById(glId);
            model.addAttribute("xmxxgl", xmxxgl);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glId", glId);
            List<XmSpsqxx> spsqxxList = spsqxxService.listSpsqxxByParams(params);
            model.addAttribute("spsqxxList", spsqxxList);
        } else if (StringHelper.isNotBlank(type) && (type.equalsIgnoreCase("rwfhsp") || type.equalsIgnoreCase("rwfhView")  || type.equalsIgnoreCase("rwzhView") || type.equalsIgnoreCase("rwzh"))) {
            Xmrwxx xmrwxx = xmrwxxService.getXmrwxxById(glId);
            model.addAttribute("xmrwxx", xmrwxx);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glId", glId);
            List<XmSpsqxx> spsqxxList = spsqxxService.listSpsqxxRwfhByParams(params);
            model.addAttribute("spsqxxList", spsqxxList);
        } else if (StringHelper.isNotBlank(type) && (type.equalsIgnoreCase("rybgsp") || type.equalsIgnoreCase("rybgView") || type.equalsIgnoreCase("rybgzhView") || type.equalsIgnoreCase("rybgzh"))) {
            Rybgsq rybgsq = rybgsqService.getRybgsqById(glId);
            model.addAttribute("rybgsq", rybgsq);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glId", glId);
            List<XmSpsqxx> spsqxxList = spsqxxService.listSpsqxxRybgByParams(params);
            model.addAttribute("spsqxxList", spsqxxList);
        }

        Ryxxgl ryxxgl = ryxxglService.getRyxxglByName(currentManager.getName());
        model.addAttribute("ryxxgl", ryxxgl);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(glId);
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

        }
        if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("jsxmsp")) {
            return "/app/spsqxx/jsxmsp";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("jsxmView")) {
            return "/app/spsqxx/jsxmView";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rwfhsp")) {
            return "/app/spsqxx/rwfhsp";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rwfhView")) {
            return "/app/spsqxx/rwfhView";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rybgsp")) {
            return "/app/spsqxx/rybgsp";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rybgView")) {
            return "/app/spsqxx/rybgView";
        }else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rwzh")) {
            return "/app/spsqxx/rwzh";
        }else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rybgzh")) {
            return "/app/spsqxx/rybgzh";
        }else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rwzhView")) {
            return "/app/spsqxx/rwzhView";
        } else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("rybgzhView")) {
            return "/app/spsqxx/rybgzhView";
        }
        return "/app/spsqxx/add";
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
    public String getSpsqxx(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        model.addAttribute("cxmk", request.getParameter("cxmk"));
        return "/app/spsqxx/list";
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
    public Map<String, Object> listSpsqxx(HttpServletRequest request, Model model) {
        SysManager currentManager = getSessionSysManager();
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

        Page<XmSpsqxx> results = new Page<XmSpsqxx>();
        String cxmk = params.get("cxmk").toString();
        if (StringHelper.isNotBlank(cxmk) && cxmk.equalsIgnoreCase("我申请的")) {
            params.put("creatorName", currentManager.getName());
            params.put("tzlx", "审批");
            // 结束项目审批
            List<XmSpsqxx> results1 = spsqxxService.listSpsqxxByParams(params);
            //任务复核审批
            List<XmSpsqxx> results2 = spsqxxService.listSpsqxxRwfhByParams(params);
            //人员变更审批
            List<XmSpsqxx> results3 = spsqxxService.listSpsqxxRybgByParams(params);
            results.addAll(results1);
            results.addAll(results2);
            results.addAll(results3);
        } else if (StringHelper.isNotBlank(cxmk) && cxmk.equalsIgnoreCase("结束项目审批")) {
            // 结束项目审批
            params.put("sprName", currentManager.getName());
            params.put("tzlx", "审批");
            results = (Page<XmSpsqxx>) spsqxxService.listSpsqxxByParams(params);
        } else if (StringHelper.isNotBlank(cxmk) && cxmk.equalsIgnoreCase("任务复核审批")) {
            // 任务复核审批
            params.put("sprName", currentManager.getName());
            params.put("tzlx", "审批");
            results = (Page<XmSpsqxx>) spsqxxService.listSpsqxxRwfhByParams(params);
        } else if (StringHelper.isNotBlank(cxmk) && cxmk.equalsIgnoreCase("人员变更审批")) {
            // 人员变更审批
            params.put("sprName", currentManager.getName());
            params.put("tzlx", "审批");
            results = (Page<XmSpsqxx>) spsqxxService.listSpsqxxRybgByParams(params);
        } else if (StringHelper.isNotBlank(cxmk) && cxmk.equalsIgnoreCase("任务知会")) {
            // 任务知会
            params.put("sprName", currentManager.getName());
            params.put("tzlx", "知会");
            results = (Page<XmSpsqxx>) spsqxxService.listSpsqxxRwfhByParams(params);
        }else if (StringHelper.isNotBlank(cxmk) && cxmk.equalsIgnoreCase("人员变更知会")) {
            // 人员变更知会
            params.put("sprName", currentManager.getName());
            params.put("tzlx", "知会");
            results = (Page<XmSpsqxx>) spsqxxService.listSpsqxxRybgByParams(params);
        }

        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录操作日志
        saveBusinessLog("项目信息管理", "项目信息列表", params);

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
    public Map<String, Object> addOrUpdateSpsqxx(@RequestBody Spsqxx spsqxx) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //新增
        if (StringHelper.isBlank(spsqxx.getId())) {
            //设置初始化值
            // 数据有效
            spsqxx.setDataStatus(GlobalConstant.DATA_VALID);
            spsqxx.setCreatorId(currentManager.getId().toString());
            spsqxx.setCreatorName(currentManager.getName());
            spsqxx.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            spsqxx = spsqxxService.addSpsqxx(spsqxx);
            resultMap.put("flag", "true");
            resultMap.put("msg", "审批申请信息新增成功");

            // 记录操作日志
            saveBusinessLog("审批申请信息管理", "审批申请项目信息", spsqxx);

            return resultMap;
        } else {//编辑
            spsqxx.setEditorId(currentManager.getId());
            spsqxx.setEditorName(currentManager.getName());
            spsqxx.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            spsqxxService.updateSpsqxx(spsqxx);
            resultMap.put("flag", "true");
            resultMap.put("msg", "审批申请信息修改成功");

            // 记录操作日志
            saveBusinessLog("审批申请信息管理", "审批申请项目信息", spsqxx);

            String splx = spsqxx.getSplx();
            String tzlx = spsqxx.getTzlx();
            if (StringHelper.isNotBlank(splx) && splx.equalsIgnoreCase("结束项目")) {
                //修改项目信息表
                Xmxxgl xmxxgl = xmxxglService.getXmxxglById(spsqxx.getGlId());
                xmxxgl.setSpyj(spsqxx.getSpyj());
                JSONObject jsonObject;
                if (StringHelper.isNotBlank(xmxxgl.getSpms())) {
                    jsonObject = JSONObject.fromObject(xmxxgl.getSpms());
                } else {
                    jsonObject = new JSONObject();
                }
                jsonObject.put("姓名", currentManager.getName());
                jsonObject.put("审批意见", spsqxx.getSpyj());
                jsonObject.put("审批批语", spsqxx.getSpms());
                xmxxgl.setSpms(jsonObject.toString());
                xmxxglService.updateXmxxgl(xmxxgl);

                //给项目所有人员发送邮件
                applicationContext.publishEvent(new JsxmspSendEmailEvent(xmxxgl));
            } else if (StringHelper.isNotBlank(splx) && splx.equalsIgnoreCase("复核申请")) {
                //修改任务信息表
                Xmrwxx xmrwxx = xmrwxxService.getXmrwxxById(spsqxx.getGlId());
                xmrwxx.setSpyj(spsqxx.getSpyj());
                JSONObject jsonObject;
                if (StringHelper.isNotBlank(xmrwxx.getSpms())) {
                    jsonObject = JSONObject.fromObject(xmrwxx.getSpms());
                } else {
                    jsonObject = new JSONObject();
                }
                jsonObject.put("姓名", currentManager.getName());
                jsonObject.put("审批意见", spsqxx.getSpyj());
                jsonObject.put("审批批语", spsqxx.getSpms());
                xmrwxx.setSpms(jsonObject.toString());
                xmrwxxService.updateXmrwxx(xmrwxx);

                Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmrwxx.getXmId());
                spsqxx = spsqxxService.getSpsqxxById(spsqxx.getId());
                //给申请人员发送邮件
                if(tzlx.equalsIgnoreCase("审批")) {
                    applicationContext.publishEvent(new RwfhspSendEmailEvent(this, xmrwxx, xmxxgl, spsqxx));
                }else{
                    if(tzlx.equalsIgnoreCase("知会")) {
                        //目前不发邮件
                    }
                }
            } else if (StringHelper.isNotBlank(splx) && splx.equalsIgnoreCase("人员变更申请")) {
                //修改人员变更申请表
                Rybgsq rybgsq = rybgsqService.getRybgsqById(spsqxx.getGlId());
                rybgsq.setSpyj(spsqxx.getSpyj());
                JSONObject jsonObject;
                if (StringHelper.isNotBlank(rybgsq.getSpms())) {
                    jsonObject = JSONObject.fromObject(rybgsq.getSpms());
                } else {
                    jsonObject = new JSONObject();
                }
                jsonObject.put("姓名", currentManager.getName());
                jsonObject.put("审批意见", spsqxx.getSpyj());
                jsonObject.put("审批批语", spsqxx.getSpms());
                rybgsq.setSpms(jsonObject.toString());
                rybgsqService.updateRybgsq(rybgsq);

                spsqxx = spsqxxService.getSpsqxxById(spsqxx.getId());
                //给申请人员发送邮件
                if(tzlx.equalsIgnoreCase("审批")) {
                    applicationContext.publishEvent(new RybgspSendEmailEvent(this, spsqxx));
                }else{
                    if(tzlx.equalsIgnoreCase("知会")) {
                        //目前不发邮件
                    }
                }
            }
        }
        return resultMap;
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

}
