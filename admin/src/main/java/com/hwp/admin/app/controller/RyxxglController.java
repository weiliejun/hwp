package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.ryxxgl.RyxxglService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.ryxxgl.bean.Ryxxgl;
import com.hwp.common.model.ryxxgl.bean.RyxxglSelect;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
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
import java.util.*;


/**
 * 人员信息
 *
 * @author 魏列军
 * @date 2020/5/15 15:30:22
 */
@Controller
@RequestMapping("/ryxxgl")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class RyxxglController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(RyxxglController.class);

    @Autowired
    private RyxxglService ryxxglService;
    @Autowired
    private XmxxglService xmxxglService;
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
     * 新增人员信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/toAdd"}, method = RequestMethod.GET)
    public String toAdd(Model model, @RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
        if (id != null) {
            Ryxxgl ryxxgl = ryxxglService.getRyxxglById(id);
            model.addAttribute("ryxxgl", ryxxgl);
        }else{
            model.addAttribute("ryxxgl", new Ryxxgl());
        }
        return "/app/ryxxgl/add";
    }

    /**
     * 修改人员信息页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 17:31
     * @Param
     **/
    @RequestMapping(value = {"/get/{type}/{id}"}, method = RequestMethod.GET)
    public String findRyxxglById(HttpServletRequest request, @PathVariable("type") String type, @PathVariable("id") String id, Model model) {
        Ryxxgl ryxxgl = ryxxglService.getRyxxglById(id);
        model.addAttribute("ryxxgl", ryxxgl);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        if(StringHelper.isNotBlank(type)&&type.equalsIgnoreCase("view")){
            return "/app/ryxxgl/view";
        }
        return "/app/ryxxgl/add";
    }

    /**
     * @return
     * @Author 魏列军
     * @Description //TODO 加载用户详情页面的参与项目
     * @Date 2020/5/21 17:22
     * @Param
     **/
    @PostMapping("/detail/{id}")
    @ResponseBody
    public Map<String, Object> getUserInfoDetailPage(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        logger.debug(request.getRequestURI() + "加载用户详情页面");
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        String flag = "true", msg = "获取成功";
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("ryid",id);
            List<Xmxxgl> xmInfo=xmxxglService.listXmxxglByParams(params);
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
    public String getRyxxgl(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/ryxxgl/list";
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
    public Map<String, Object> listRyxxgl(HttpServletRequest request, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);

        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        Map<String, Object> params = getQureyParams(requestParams);

        final Page<Ryxxgl> results = (Page<Ryxxgl>) ryxxglService.listRyxxglByParams(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());

        // 记录查看日志
        saveBusinessLog("人员信息管理", "人员信息列表", "第" + results.getPageNum() + "页list");

        return resultMap;
    }

    /**
     * 人员信息查询负责人页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO 使用shiro中用户session缓存在redis中每次请求中页面都会携带jsessionid所用通过thymeleaf渲染模板时需传递请求信息
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    public String selectListGet(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        Map<String, Object> params = formQueryRemember(request);
        if (params.get("cxmk").equals("selectXmfzr")) {
            model.addAttribute("type", "radio");
            model.addAttribute("cxmk", "selectXmfzr");
            return "app/ryxxgl/selectList";
        } else if (params.get("cxmk").equals("selectFwfzr")) {
            model.addAttribute("type", "radio");
            model.addAttribute("cxmk", "selectFwfzr");
            return "app/ryxxgl/selectList";
        } else if (params.get("cxmk").equals("selectCwfzr")) {
            model.addAttribute("type", "radio");
            model.addAttribute("cxmk", "selectCwfzr");
            return "app/ryxxgl/selectList";
        } else if (params.get("cxmk").equals("selectSpr")) {
            model.addAttribute("type", "checkbox");
            model.addAttribute("cxmk", "selectSpr");
            return "app/ryxxgl/selectList";
        }else if (params.get("cxmk").equals("selectXmjbr")) {
            model.addAttribute("type", "radio");
            model.addAttribute("cxmk", "selectXmjbr");
            return "app/ryxxgl/selectList";
        }else if (params.get("cxmk").equals("selectXmqtcy")) {
            model.addAttribute("type", "checkbox");
            model.addAttribute("cxmk", "selectXmqtcy");
            return "app/ryxxgl/selectList";
        }else if (params.get("cxmk").equals("selectJsxmSpr")) {
            model.addAttribute("type", "checkbox");
            model.addAttribute("cxmk", "selectJsxmSpr");
            return "app/ryxxgl/selectList";
        }
        return "/app/ryxxgl/selectList";
    }
    /**
     * 人员信息查询负责人页面
     *
     * @return
     * @Author 魏列军
     * @Description //TODO 使用shiro中用户session缓存在redis中每次请求中页面都会携带jsessionid所用通过thymeleaf渲染模板时需传递请求信息
     * @Date 2020/5/15 9:45
     * @Param
     **/
    @PostMapping(value = "/selectList")
    @ResponseBody
    public Map<String, Object> selectList(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);

        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        Map<String, Object> params = getQureyParams(requestParams);

        final Page<RyxxglSelect> results = (Page<RyxxglSelect>) ryxxglService.selectList(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());


        return resultMap;
    }

    /**
     * 新增或修改人员信息
     *
     * @return
     * @Author 魏列军
     * @Description //TODO
     * @Date 2020/5/15 15:07
     * @Param
     **/
    @PostMapping(value = "/addOrUpdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateRyxxgl(@RequestBody Ryxxgl ryxxgl) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysManager currentManager = getSessionSysManager();
        //新增
        if (StringHelper.isBlank(ryxxgl.getId())) {
            //设置初始化值
            // 数据有效
            ryxxgl.setDataStatus(GlobalConstant.DATA_VALID);
            ryxxgl.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            ryxxgl=ryxxglService.addRyxxgl(ryxxgl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "人员信息新增成功");

            // 记录查看日志
            saveBusinessLog("人员信息管理", "新增人员信息", ryxxgl);
            return resultMap;
        } else {//编辑
            ryxxglService.updateRyxxgl(ryxxgl);
            resultMap.put("flag", "true");
            resultMap.put("msg", "人员信息修改成功");

            // 记录查看日志
            saveBusinessLog("人员信息管理", "修改人员信息", ryxxgl);
            return resultMap;
        }

    }
}
