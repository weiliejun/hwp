package com.hwp.admin.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.websiteAdvertise.WebsiteAdvertiseService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.websiteAdvertise.bean.WebsiteAdvertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 首页轮播图配置
 * @Author lvjian
 * @UpdateDate 2019/8/30 10:27
 */
@Controller
@RequestMapping("websiteAdvertise")
public class WebsiteAdvertiseController extends AbstractBaseController {

    @Autowired
    private WebsiteAdvertiseService websiteAdvertiseService;

    /**
     * @Description 新增轮播图配置页面
     * @auther: cyp
     * @UpadteDate: 2019/2/27 11:08
     */
    @RequestMapping(value = {"/toadd"}, method = RequestMethod.GET)
    public String toAddWebsiteBulletin() {
        return "/app/websiteAdvertise/edit";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String toListWebsiteBulletins(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "/app/websiteAdvertise/list";
    }

    /**
     * @Description 分页查询轮播图配置信息
     * @auther: cyp
     * @UpadteDate: 2019/2/27 11:08
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> listWebsiteBulletins(HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);
        final Page<WebsiteAdvertise> results = (Page<WebsiteAdvertise>) websiteAdvertiseService.listAdvertisesByKeys(params);
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", String.valueOf(results.getTotal()));
        resultMap.put("data", results.getResult());
        return resultMap;
    }


    /**
     * @Description 保存轮播图配置信息（新增或修改）
     * @auther: cyp
     * @UpadteDate: 2019/2/27 14:44
     */
    @RequestMapping(value = "/addorupdate")
    @ResponseBody
    public Map<String, Object> addOrUpdateWebsiteAdvertise(WebsiteAdvertise websiteAdvertise) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysManager currentManager = getSessionSysManager();
        //新增
        if (websiteAdvertise.getId() == null) {
            //设置初始化值
            websiteAdvertise.setCode("0000");
            websiteAdvertise.setStatus(GlobalConstant.STATUS_VALID);//使用
            websiteAdvertise.setClicks(0);
            websiteAdvertise.setCreateTime(new Date());
            websiteAdvertise.setDataStatus(GlobalConstant.KS_DATA_VALID);
            websiteAdvertiseService.addAdvertise(websiteAdvertise);
            resultMap.put("flag", "true");
            resultMap.put("msg", "轮播图保存成功");
            return resultMap;
        } else {//编辑
            websiteAdvertise.setUpdateTime(new Date());
            websiteAdvertiseService.updateAdvertise(websiteAdvertise);
            resultMap.put("flag", "true");
            resultMap.put("msg", "轮播图修改成功");
            return resultMap;
        }
    }

    /**
     * @Description 删除、启用、禁用
     * @auther: lvjian
     * @UpadteDate: 2019/2/22 15:57
     */
    @RequestMapping(value = "/{operateType}")
    @ResponseBody
    public Map<String, Object> operate(@PathVariable String operateType, Integer id) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        WebsiteAdvertise advertise = websiteAdvertiseService.getAdvertiseById(id);
        if (advertise == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "该轮播图不存在");
            return resultMap;
        }
        WebsiteAdvertise websiteAdvertise = new WebsiteAdvertise();
        websiteAdvertise.setId(id);
        //删除
        if ("delete".equals(operateType)) {
            websiteAdvertise.setDataStatus(GlobalConstant.STATUS_INVALID);
            websiteAdvertiseService.updateAdvertise(websiteAdvertise);
            resultMap.put("flag", "true");
            resultMap.put("msg", "删除成功");
            return resultMap;
        }
        //启用（发布）-显示
        if ("enable".equals(operateType)) {
            websiteAdvertise.setStatus(GlobalConstant.STATUS_VALID);
            websiteAdvertiseService.updateAdvertise(websiteAdvertise);
            resultMap.put("flag", "true");
            resultMap.put("msg", "显示成功");
            return resultMap;
        }
        //禁用（停用）-不显示
        if ("disable".equals(operateType)) {
            websiteAdvertise.setStatus(GlobalConstant.STATUS_INVALID);
            websiteAdvertiseService.updateAdvertise(websiteAdvertise);
            resultMap.put("flag", "true");
            resultMap.put("msg", "停用成功");
            return resultMap;
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作异常");
        return resultMap;
    }

}
