package com.hwp.admin.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.event.BgjdSendEmailEvent;
import com.hwp.admin.app.event.CreateXmSendEmailEvent;
import com.hwp.admin.app.event.JsxmSendEmailEvent;
import com.hwp.admin.app.event.ModifyXmSendEmailEvent;
import com.hwp.admin.app.service.productOtherAttachFile.ProductOtherAttachFileService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.components.message.mail.MailSenderService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.productOtherAttachFile.bean.AttachFileHref;
import com.hwp.common.model.productOtherAttachFile.bean.ProductOtherAttachFile;
import com.hwp.common.model.sysManager.bean.SysManager;
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
    private FileSynchronizer FileSynchronizer;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ProductOtherAttachFileService productOtherAttachFileService;
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
    public String findXmxxglById(HttpServletRequest request, @PathVariable("type") String type, @PathVariable("id") String id, Model model) {
        Xmxxgl xmxxgl = xmxxglService.getXmxxglById(id);
        model.addAttribute("xmxxgl", xmxxgl);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("view")) {
            return "/app/xmxxgl/view";
        }else if (StringHelper.isNotBlank(type) && type.equalsIgnoreCase("jsxm")) {
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

        // 记录查看日志
        saveBusinessLog("项目信息管理", "项目信息列表", "第" + results.getPageNum() + "页list");
        // 保存翻页信息,保存查询条件，回显参数
//        savePageParams(request, params, model);

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
    public Map<String, Object> addOrUpdate2(@RequestBody Xmxxgl xmxxgl, @PathVariable("type") String type) {
        SysManager currentManager = getSessionSysManager();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //变更阶段
        if (StringHelper.isNotBlank(type)&&type.equalsIgnoreCase("bgjd")) {
            Xmxxgl xmxxgl_old = xmxxglService.getXmxxglById(xmxxgl.getId());
            String xmjd1=xmxxgl_old.getXmjd();
            String xmjd2=xmxxgl.getXmjd();
            //发送邮件
            applicationContext.publishEvent(new BgjdSendEmailEvent(this, xmjd1,xmjd2, xmxxgl));
        }else if (StringHelper.isNotBlank(type)&&type.equalsIgnoreCase("jsxm")) {
            //给审批人发送邮件
            applicationContext.publishEvent(new JsxmSendEmailEvent(xmxxgl));
        }
            xmxxglService.updateXmxxgl(xmxxgl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "项目信息修改成功");

            // 记录查看日志
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
            xmxxgl.setCreatorId(currentManager.getId().toString());
            xmxxgl.setCreatorName(currentManager.getName());
            xmxxgl.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            xmxxgl = xmxxglService.addXmxxgl(xmxxgl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "项目信息新增成功");

            // 记录查看日志
            saveBusinessLog("项目信息管理", "新增项目信息", xmxxgl);
            //发送邮件
            applicationContext.publishEvent(new CreateXmSendEmailEvent(xmxxgl));

            return resultMap;
        } else {//编辑
            Xmxxgl xmxxgl_old = xmxxglService.getXmxxglById(xmxxgl.getId());
            String diff = BeanHelper.getDifferentProperty(xmxxgl_old, xmxxgl, new String[]{"id", "xmfzrId", "xmfzrXx", "xmjbrId", "xmjbrXx", "fwfzrId", "fwfzrXx", "cwfzrId", "cwfzrXx", "xmqtcyId", "xmqtcy", "sprId", "spr"});
            Map<String, String> zdm = new HashMap<String, String>();
            zdm.put("ywlx","业务类型");
            zdm.put("cpmc","产品名称");
            zdm.put("cpgm","产品规模");
            zdm.put("cpqx","产品期限");
            zdm.put("yqsy","预期收益");
            zdm.put("xmly","项目来源");
            zdm.put("zjly","资金来源");
            zdm.put("btfw","被投范围");
            zdm.put("cjzt","承接主体");
            zdm.put("ztxm","直投项目");
            zdm.put("jtxm","间投项目");
            zdm.put("tztj","投资条件");
            zdm.put("glf","管理费");
            zdm.put("rgf","认购费");
            zdm.put("tgf","托管费");
            zdm.put("sgf","申购费");
            zdm.put("xsf","销售费");
            zdm.put("shf","赎回费");
            zdm.put("xmfzrName","项目负责人");
            zdm.put("xmjbrName ","项目经办人");
            zdm.put("fwfzrName","法务负责人");
            zdm.put("cwfzrName","财务负责人");
            zdm.put("xmqtcyName","其他成员");
            zdm.put("sprName","审批人");
            Iterator iter = zdm.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                diff=diff.replaceAll(key.toString(),val.toString());
            }

            xmxxgl.setEditorId(currentManager.getId());
            xmxxgl.setEditorName(currentManager.getName());
            xmxxgl.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            xmxxglService.updateXmxxgl(xmxxgl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "项目信息修改成功");

            // 记录查看日志
            saveBusinessLog("项目信息管理", "修改项目信息", xmxxgl);

            //给审批人发送邮件
            applicationContext.publishEvent(new ModifyXmSendEmailEvent(this, diff, xmxxgl));
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
}
