package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.investmentArticle.InvestmentArticleService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.investmentArticle.bean.InvestmentArticle;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.FileCopyHelper;
import com.hwp.common.util.RandomUtil;
import com.hwp.common.util.StringHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 投资观点
 *
 * @author 李洪斌
 * @date 2019/7/18 15:30:22
 */
@Controller
@RequestMapping("/investmentArticle")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class InvestmentArticleController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(InvestmentArticleController.class);

    @Autowired
    private InvestmentArticleService investmentArticleService;

    @Autowired
    private FileSynchronizer FileSynchronizer;

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
     * 新增投资观点页面
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/19 17:31
     * @Param
     **/
    @RequestMapping(value = {"/toAdd"}, method = RequestMethod.GET)
    public String toAddWebsiteBulletin(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/investmentArticle/add";
    }

    /**
     * 修改投资观点页面
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/19 17:31
     * @Param
     **/
    @RequestMapping(value = {"/get/{id}"}, method = RequestMethod.GET)
    public String findInvestmentArticleById(HttpServletRequest request, @PathVariable("id") String id, Model model) {
        InvestmentArticle investmentArticle = investmentArticleService.getInvestmentArticleById(id);
        model.addAttribute("investmentArticle", investmentArticle);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/investmentArticle/add";
    }

    /**
     * 投资观点加载页面
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO 使用shiro中用户session缓存在redis中每次请求中页面都会携带jsessionid所用通过thymeleaf渲染模板时需传递请求信息
     * @Date 2019/7/18 9:45
     * @Param
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getInvestmentArticle(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/investmentArticle/list";
    }

    /**
     * 投资观点列表
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/18 9:45
     * @Param
     **/
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listInvestmentArticle(HttpServletRequest request, Model model) {
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

        final Page<InvestmentArticle> results = (Page<InvestmentArticle>) investmentArticleService.listInvestmentArticleByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("投资观点管理", "投资观点列表", "第" + results.getPageNum() + "页list");
        // 保存翻页信息,保存查询条件，回显参数
//        savePageParams(request, params, model);
        // 保存查询条件，回显参数
//        request.getSession().setAttribute(request.getRequestURI(), params);
//        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        return resultMap;
    }

    /**
     * 投资观点上下架操作
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/19 11:17
     * @Param type 操作类型 up上架 down下架
     * @Param id 投资观点id
     **/
    @RequestMapping("update/{type}")
    @ResponseBody
    public Map<String, Object> investmentArticleUpAndDown(@PathVariable String type, String id) {

        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        InvestmentArticle investmentArticle = investmentArticleService.getInvestmentArticleById(id);

        // 记录查看日志
        saveBusinessLog("投资观点管理", "投资观点上下架", investmentArticle);

        // 此投资观点是否存在
        if (investmentArticle == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "此投资观点不存在");
            return resultMap;
        }
        // 上架操作
        if ("up".equalsIgnoreCase(type)) {
            // 0草稿箱 1上架 2下架
            investmentArticle.setStatus("1");
            investmentArticle.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            investmentArticleService.updateInvestmentArticle(investmentArticle);
            resultMap.put("flag", "true");
            resultMap.put("msg", "上架成功");
            return resultMap;
        }

        // 下架操作
        if ("down".equalsIgnoreCase(type)) {
            // 0草稿箱 1上架 2下架
            investmentArticle.setStatus("2");
            investmentArticle.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            investmentArticleService.updateInvestmentArticle(investmentArticle);
            resultMap.put("flag", "true");
            resultMap.put("msg", "下架成功");
            return resultMap;
        }
        // 操作异常
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作异常");
        return resultMap;
    }

    /**
     * 新增或修改投资观点
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/20 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateInvestmentArticle(@RequestBody InvestmentArticle investmentArticle) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysManager currentManager = getSessionSysManager();
        //新增
        if (StringHelper.isBlank(investmentArticle.getId())) {
            //设置初始化值
            // 数据有效
            investmentArticle.setDataStatus(GlobalConstant.DATA_VALID);
            Date date = new Date();
            investmentArticle.setCreateTime(DateHelper.getYMDHMSFormatDate(date));
            investmentArticle.setCreatorId(currentManager.getId().toString());
            investmentArticle.setCreatorName(currentManager.getName());
            investmentArticle.setEditorId(currentManager.getId().toString());
            investmentArticle.setEditorName(currentManager.getName());
            investmentArticle.setEditTime(DateHelper.getYMDHMSFormatDate(date));
            investmentArticleService.addInvestmentArticle(investmentArticle);
            resultMap.put("flag", "true");
            resultMap.put("msg", "投资观点新增成功");

            // 记录查看日志
            saveBusinessLog("投资观点管理", "新增投资观点", investmentArticle);

            return resultMap;
        } else {//编辑
            investmentArticle.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            investmentArticle.setEditorId(currentManager.getId().toString());
            investmentArticle.setEditorName(currentManager.getName());
            investmentArticleService.updateInvestmentArticle(investmentArticle);
            resultMap.put("flag", "true");
            resultMap.put("msg", "投资观点修改成功");

            // 记录查看日志
            saveBusinessLog("投资观点管理", "修改投资观点", investmentArticle);

            return resultMap;
        }
    }

    /**
     * 投资观点上传图片
     *
     * @return
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/23 14:31
     * @Param
     **/
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(HttpServletRequest request) {
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
                logger.error("投资观点上传图片异常");
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


}
