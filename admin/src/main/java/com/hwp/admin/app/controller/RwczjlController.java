package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.rwczjl.RwczjlService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.components.filesync.FileSynchronizer;
import com.hwp.common.model.rwczjl.bean.Rwczjl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
 * 人员信息
 *
 * @author 魏列军
 * @date 2020/5/15 15:30:22
 */
@Controller
@RequestMapping("/rwczjl")
@PropertySource(value = {"classpath:config/resource.properties"}, encoding = "utf-8")
public class RwczjlController extends AbstractBaseController {

    private static Logger logger = LoggerFactory.getLogger(RwczjlController.class);

    @Autowired
    private RwczjlService rwczjlService;
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private FileSynchronizer FileSynchronizer;

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
    public String getRwczjl(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/rwczjl/list";
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
    public Map<String, Object> listRwczjl(HttpServletRequest request, Model model) {
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

        // 记录查看日志
        saveBusinessLog("人员信息管理", "人员信息列表", "第" + results.getPageNum() + "页list");

        return resultMap;
    }
}
