package com.hwp.admin.app.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwp.admin.app.service.product.ProductService;
import com.hwp.admin.app.service.productOtherAttachFile.ProductOtherAttachFileService;
import com.hwp.admin.app.service.productUnFreezePayService.ProductUnFreezePayService;
import com.hwp.admin.system.service.SysManagerService;
import com.hwp.admin.system.service.WebsiteBulletinService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.constant.ApplicationSessionKeys;
import com.hwp.common.constant.GlobalConstant;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.productOtherAttachFile.bean.AttachFileHref;
import com.hwp.common.model.productOtherAttachFile.bean.ProductOtherAttachFile;
import com.hwp.common.model.sysManager.bean.SysManager;
import com.hwp.common.model.websiteBulletin.bean.WebsiteBulletin;
import com.hwp.common.redis.service.CacheService;
import com.hwp.common.thirdparty.service.ThirdPartyCallService;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.RandomUtil;
import com.hwp.common.util.StringHelper;
import com.hwp.common.web.bean.CurrentManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description 产品相关
 * @Author 吕剑
 * @UpdateDate 2019/7/18 20:01
 */
@Controller
@RequestMapping("product")
public class ProductController extends AbstractBaseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductOtherAttachFileService productOtherAttachFileService;

    @Autowired
    private SysManagerService sysManagerService;

    @Autowired
    private WebsiteBulletinService websiteBulletinService;

    @Autowired
    private ProductUnFreezePayService productUnFreezePayService;

    @Autowired
    private ThirdPartyCallService thirdPartyCallService;

    @Autowired
    private CacheService cacheService;


    @RequestMapping(value = "/addProductOne", method = RequestMethod.GET)
    public String productOne(Model model, HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            Product productById = productService.getProductById(request.getParameter("id"));
            model.addAttribute("product", productById);
        }
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/addProductOne";
    }

    /**
     * @Description 产品编辑， 数据显示
     * @Author 吕剑
     * @UpdateDate 2019/7/18 20:01
     */
    @RequestMapping(value = "/addProductOne/{id}")
    public String productOneU(@PathVariable String id, Model model, HttpServletRequest request) {
        Product productById = productService.getProductById(id);
        //对富文本进行转义处理
        productById.setReasonsPrepay(HtmlUtils.htmlUnescape(
                productById.getReasonsPrepay().replaceAll(" ", "")));//提前还款说明
        productById.setProductAdvantage(HtmlUtils.htmlUnescape(
                productById.getProductAdvantage().replaceAll(" ", "")));//收益计算
        productById.setProjectSummary(HtmlUtils.htmlUnescape(
                productById.getProjectSummary().replaceAll(" ", "")));//产品介绍
        productById.setPurchaseDesc(HtmlUtils.htmlUnescape(
                productById.getPurchaseDesc().replaceAll(" ", "")));//购买说明
        model.addAttribute("product", productById);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(productById.getId());
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
        //记录业务日志
        Map<String, Object> params = new HashMap<>();
        params.put("product", productById);
        params.put("productOtherAttachFile", productOtherAttachFile);
        saveBusinessLog("编辑产品", "查看产品信息录入页面产品信息", params);

        return "app/product/addProductOne";
    }

    /**
     * @Description 录入产品编辑， 数据显示
     * @Author 吕剑
     * @UpdateDate 2019/9/04 15:01
     */
    @RequestMapping(value = "/addProductTwo/{id}")
    public String productTwoU(@PathVariable String id, Model model, HttpServletRequest request) {
        Product productById = productService.getProductById(id);
        //对富文本进行转义处理
        productById.setReasonsPrepay(HtmlUtils.htmlUnescape(
                productById.getReasonsPrepay().replaceAll(" ", "")));//提前还款说明
        productById.setProductAdvantage(HtmlUtils.htmlUnescape(
                productById.getProductAdvantage().replaceAll(" ", "")));//收益计算
        productById.setProjectSummary(HtmlUtils.htmlUnescape(
                productById.getProjectSummary().replaceAll(" ", "")));//产品介绍
        productById.setPurchaseDesc(HtmlUtils.htmlUnescape(
                productById.getPurchaseDesc().replaceAll(" ", "")));//购买说明
        model.addAttribute("product", productById);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(productById.getId());
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
        model.addAttribute("productIssue", "issue");

        //记录业务日志
        Map<String, Object> params = new HashMap<>();
        params.put("product", productById);
        params.put("productOtherAttachFile", productOtherAttachFile);
        saveBusinessLog("编辑产品", "查看产品信息发布页面产品信息", params);

        return "app/product/addProductOne";
    }

    /**
     * @Description 审核 数据显示
     * @Author 吕剑
     * @UpdateDate 2019/7/18 20:01
     */
    @RequestMapping(value = "/addProductExam/{id}", method = RequestMethod.GET)
    public String productExamE(@PathVariable String id, Model model, HttpServletRequest request) {
        Product productById = productService.getProductById(id);
        //对富文本进行转义处理
        productById.setReasonsPrepay(HtmlUtils.htmlUnescape(
                productById.getReasonsPrepay().replaceAll(" ", "")));//提前还款说明
        productById.setProductAdvantage(HtmlUtils.htmlUnescape(
                productById.getProductAdvantage().replaceAll(" ", "")));//收益计算
        productById.setProjectSummary(HtmlUtils.htmlUnescape(
                productById.getProjectSummary().replaceAll(" ", "")));//产品介绍
        productById.setPurchaseDesc(HtmlUtils.htmlUnescape(
                productById.getPurchaseDesc().replaceAll(" ", "")));//购买说明
        ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(productById.getId());
        if (productOtherAttachFile == null) {
            model.addAttribute("message", "产品附件不存在");
        } else {
            String attachFile = productOtherAttachFile.getAttachFile();
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
        model.addAttribute("product", productById);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));

        //记录业务日志
        Map<String, Object> params = new HashMap<>();
        params.put("product", productById);
        params.put("productOtherAttachFile", productOtherAttachFile);
        saveBusinessLog("审核产品", "查看产品信息审核页面产品信息", params);

        return "app/product/updateProductExamine";
    }


    /**
     * @Description 查看产品显示
     * @Author 吕剑
     * @UpdateDate 2019/9/03 14:40
     */
    @RequestMapping(value = "/lookProduct/{id}", method = RequestMethod.GET)
    public String productLook(@PathVariable String id, Model model, HttpServletRequest request) {
        Product productById = productService.getProductById(id);
        //对富文本进行转义处理
        productById.setReasonsPrepay(HtmlUtils.htmlUnescape(
                productById.getReasonsPrepay().replaceAll(" ", "")));//提前还款说明
        productById.setProductAdvantage(HtmlUtils.htmlUnescape(
                productById.getProductAdvantage().replaceAll(" ", "")));//收益计算
        productById.setProjectSummary(HtmlUtils.htmlUnescape(
                productById.getProjectSummary().replaceAll(" ", "")));//产品介绍
        productById.setPurchaseDesc(HtmlUtils.htmlUnescape(
                productById.getPurchaseDesc().replaceAll(" ", "")));//购买说明
        ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(productById.getId());
        if (productOtherAttachFile == null) {
            model.addAttribute("message", "产品附件不存在");
        } else {
            String attachFile = productOtherAttachFile.getAttachFile();
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
        //记录业务日志
        Map<String, Object> params = new HashMap<>();
        params.put("product", productById);
        params.put("productOtherAttachFile", productOtherAttachFile);
        saveBusinessLog("查看产品", "查看产品信息发布页面产品信息", params);

        model.addAttribute("product", productById);
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/lookProduct";
    }


    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String toListproductTwo(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/listProduct";
    }

    @RequestMapping(value = {"/listExam"}, method = RequestMethod.GET)
    public String productExamineList(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/listExam";
    }

    @RequestMapping(value = {"/listManage"}, method = RequestMethod.GET)
    public String productGoEdit(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/listProductManage";
    }

    @RequestMapping(value = {"/listManageIssue"}, method = RequestMethod.GET)
    public String productGoEditIssa(HttpServletRequest request, Model model) {
        model.addAllAttributes((Map<String, Object>) request.getSession().getAttribute(request.getRequestURI()));
        return "app/product/listProductManageIssue";
    }

    /**
     * @Description 分页查询产品列表信息
     * @auther: xsp
     * @UpadteDate: 2019/8/16 10:42
     */
    @PostMapping(value = "/{appType}/list")
    @ResponseBody
    public Map<String, Object> listProduct(@PathVariable String appType, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Map<String, Object> requestParams = formQueryRemember(request);
        PageHelper.startPage(Integer.parseInt(requestParams.get("currentPage").toString()),
                Integer.parseInt(requestParams.get("pageSize").toString()));
        final Map<String, Object> params = getQureyParams(requestParams);
        String timeScope = null, startTime = null, endTime = null;
        if (params.get("timeScope") != null && params.get("timeScope").toString().length() > 20) {
            timeScope = params.get("timeScope").toString().trim();
        }
        if (StringHelper.isNotBlank(timeScope)) {
            String[] split = timeScope.split("-");
            startTime = split[0] + "-" + split[1] + "-" + split[2];
            endTime = split[3] + "-" + split[4] + "-" + split[5];
        }
        params.remove("timeScope");
        if (startTime != null) {
            params.put("startTime", startTime.replaceAll(" ", ""));
        }
        if (endTime != null) {
            params.put("endTime", endTime.replaceAll(" ", ""));
        }
        params.put("dataStatus", GlobalConstant.KS_DATA_VALID);
        Page<Product> results = null;

        Map<String, Object> map = new HashMap<>();
        if (appType.equalsIgnoreCase("exam")) {//exam
            //产品审核列表    只显示已提交，审核失败，审核处理中数据
            results = (Page<Product>) productService.selectProductExamListByMap(params);
            //记录业务日志
            map.put("request", formQueryRemember(request));
            map.put("pageNum", results.getPageNum());
            map.put("pageSize", results.getTotal());
            saveBusinessLog("产品信息审核", "查询产品信息审核列表", map);
        } else if (appType.equalsIgnoreCase("manager")) {//manager
            //产品投资管理列表  审核状态为审核成功,投标审核状态为审核完成
            results = (Page<Product>) productService.selectProductManagerListByMap(params);
            //记录业务日志
            map.put("request", formQueryRemember(request));
            map.put("pageNum", results.getPageNum());
            map.put("pageSize", results.getTotal());
            saveBusinessLog("产品信息管理", "查询产品信息管理列表", map);
        } else if (appType.equalsIgnoreCase("all")) {
            //产品录入列表
            results = (Page<Product>) productService.selectProductList(params);
            //记录业务日志
            map.put("request", formQueryRemember(request));
            map.put("pageNum", results.getPageNum());
            map.put("pageSize", results.getTotal());
            saveBusinessLog("产品信息录入", "查询产品信息录入列表", map);
        } else if (appType.equalsIgnoreCase("managerIssue")) {
            //产品发布列表
            results = (Page<Product>) productService.selectProductManagerIssueListByMap(params);
            //记录业务日志
            map.put("request", formQueryRemember(request));
            map.put("pageNum", results.getPageNum());
            map.put("pageSize", results.getTotal());
            saveBusinessLog("产品信息发布", "查询产品信息发布列表", map);
        }

        if (results == null) {
            resultMap.put("flag", "true");
            resultMap.put("msg", "查询失败");
            return resultMap;
        }
        resultMap.put("flag", "true");
        resultMap.put("msg", "查询成功");
        resultMap.put("count", results.getTotal());
        resultMap.put("data", results.getResult());
        return resultMap;
    }

    //添加修改产品
    @RequestMapping(value = "/productEditOne", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(Product product, ProductOtherAttachFile productOtherAttachFile, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //获取存入session中的id
            CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
            SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());
            //对产品表添加修改人数据===最后修改人信息
            product.setEditorId(sysManager.getId().toString());
            product.setEditorName(sysManager.getName());
            product.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            //对附件表添加修改人数据
            productOtherAttachFile.setEditorId(sysManager.getId().toString());
            productOtherAttachFile.setEditorName(sysManager.getName());
            productOtherAttachFile.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            if (product.getId() != null && product.getId().trim().length() > 0) {
                int i = productService.updateProductById(product);
//                int i = productOtherAttachFileService.updateProductAndProductOtherAttachFileById(product, productOtherAttachFile);
                if (i == 0) {
                    resultMap.put("flag", "false");
                    resultMap.put("msg", "修改失败");
                    return resultMap;
                }
                //记录业务日志
                Map<String, Object> params = new HashMap<>();
                params.put("product", product);
                params.put("user", sysManager);
                saveBusinessLog("产品编辑", "对产品录入页面产品进行编辑", params);

            } else {
                if (product.getTenderInitAmount() == null
                        || product.getTenderInitAmount().compareTo(product.getAmount().divide(new BigDecimal(200))) < 0) {
                    resultMap.put("flag", "false");
                    resultMap.put("msg", "请输入正确的起购金额");
                    return resultMap;
                }
                if (StringUtils.isBlank(product.getName())
                        || StringUtils.isBlank(product.getGeName())
                        || StringUtils.isBlank(product.getGeCode())
                        || StringUtils.isBlank(product.getCode())
                        || StringUtils.isBlank(product.getContractNo())) {
                    if (StringUtils.isBlank(product.getName())) {
                        resultMap.put("errName", "name");
                    }
                    if (StringUtils.isBlank(product.getGeName())) {
                        resultMap.put("errGeName", "geName");
                    }
                    if (StringUtils.isBlank(product.getGeCode())) {
                        resultMap.put("errGeCode", "geCode");
                    }
                    if (StringUtils.isBlank(product.getCode())) {
                        resultMap.put("errCode", "code");
                    }
                    if (StringUtils.isBlank(product.getContractNo())) {
                        resultMap.put("errContractNo", "contractNo");
                    }
                    resultMap.put("flag", "false");
                    resultMap.put("res", "notFind");
                    resultMap.put("msg", "产品编号,姓名,金交所编号,名称,合同编号不能为空");
                    return resultMap;
                }
                //查看是否重复------------------------------------
                if (product.getName() != null && product.getName().length() > 0) {
                    Product product1 = new Product();
                    product1.setName(product.getName());
                    List<Product> products = productService.selectProductListByRePeat(product1);
                    if (products != null && products.size() > 0) {
                        resultMap.put("flag", "false");
                        resultMap.put("res", "reName");
                        resultMap.put("msg", "产品名称重复");
                        return resultMap;
                    }
                }
                if (product.getCode() != null && product.getCode().length() > 0) {
                    Product product1 = new Product();
                    product1.setCode(product.getCode());
                    List<Product> products = productService.selectProductListByRePeat(product1);
                    if (products != null && products.size() > 0) {
                        resultMap.put("flag", "false");
                        resultMap.put("res", "reCode");
                        resultMap.put("msg", "产品编号重复");
                        return resultMap;
                    }
                }
                if (product.getGeName() != null && product.getGeName().length() > 0) {
                    Product product1 = new Product();
                    product1.setGeName(product.getGeName());
                    List<Product> products = productService.selectProductListByRePeat(product1);
                    if (products != null && products.size() > 0) {
                        resultMap.put("flag", "false");
                        resultMap.put("res", "reGeName");
                        resultMap.put("msg", "金交所产品名称重复");
                        return resultMap;
                    }
                }
                if (product.getGeCode() != null && product.getGeCode().length() > 0) {
                    Product product1 = new Product();
                    product1.setGeCode(product.getGeCode());
                    List<Product> products = productService.selectProductListByRePeat(product1);
                    if (products != null && products.size() > 0) {
                        resultMap.put("flag", "false");
                        resultMap.put("res", "reGeCode");
                        resultMap.put("msg", "金交所产品编号重复");
                        return resultMap;
                    }
                }
                if (product.getContractNo() != null && product.getContractNo().length() > 0) {
                    Product product1 = new Product();
                    product1.setContractNo(product.getContractNo());
                    List<Product> products = productService.selectProductListByRePeat(product1);
                    if (products != null && products.size() > 0) {
                        resultMap.put("flag", "false");
                        resultMap.put("res", "reContractNo");
                        resultMap.put("msg", "产品合同号重复");
                        return resultMap;
                    }
                }
                //对产品表添加创建人数据
                product.setCreatorId(sysManager.getId().toString());
                product.setCreatorName(sysManager.getName());
                product.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                //对附件表添加创建人数据
                productOtherAttachFile.setCreatorId(sysManager.getId().toString());
                productOtherAttachFile.setCreatorName(sysManager.getName());
                productOtherAttachFile.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
                //产品ID用于传回
                product.setId(RandomUtil.getSerialNumber());
                int i = productOtherAttachFileService.addProductAndProductOtherAttachFile(product, productOtherAttachFile);
                if (i == 0) {
                    resultMap.put("flag", "false");
                    resultMap.put("msg", "操作错误");
                    return resultMap;
                }
                //记录业务日志
                Map<String, Object> params = new HashMap<>();
                params.put("product", product);
                params.put("user", sysManager);
                saveBusinessLog("产品添加", "产品信息录入", params);
            }
            resultMap.put("flag", "true");
            resultMap.put("productId", product.getId());
            resultMap.put("msg", "操作成功");
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作失败");
        return resultMap;
    }

    //产品审核处理 ----修改产品审核状态---------------------------------------------------------------------------------------
    @RequestMapping(value = "/productEditExam", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProductUpdateExam(Product product, ProductOtherAttachFile productOtherAttachFile, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //获取存入session中的id
            CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
            SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());
            if (product.getId() != null && product.getId().trim().length() > 0) {
                product.setTenderAuditStatus("finish");//审核完成
                //产品标审核人信息
                //如果审核不通过产品从新回到未提交状态，并返回产品录入界面
                if (product.getAuditStatus().equalsIgnoreCase("fail")) {
                    product.setTenderAuditStatus("init");//审核未提交状态
                }
                product.setTenderAuditorName(sysManager.getName());
                product.setTenderAuditorId(sysManager.getId().toString());
                product.setTenderAuditTime(DateHelper.getYMDHMSFormatDate(new Date()));
                //最后修改人信息
                product.setEditorId(sysManager.getId().toString());
                product.setEditorName(sysManager.getName());
                product.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
                productService.updateProductById(product);
                resultMap.put("flag", "true");
                resultMap.put("msg", "操作成功");
            } else {
                resultMap.put("flag", "false");
                resultMap.put("msg", "操作错误");
            }
            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品审核", "产品审核处理", params);

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作失败");
        return resultMap;
    }


    /**
     * @Description 删除-作废、启用-上架、禁用-下架
     * @auther: lv jian
     * @UpadteDate: 2019/8/01 15:57
     */
    @RequestMapping(value = "/update/{operateType}")
    @ResponseBody
    public Map<String, Object> operateProduct(@PathVariable String operateType, String id, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Product productById = productService.getProductById(id);
        if (productById == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "该产品不存在");
            return resultMap;
        }
        Product product = new Product();
        product.setId(id);
        //提交审核
        if ("subExam".equals(operateType)) {
            //获取存入session中的id
            CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
            SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());
            //保存审核人信息 设置审核状态
            product.setAuditorName(sysManager.getName());
            product.setAuditorId(sysManager.getId().toString());
            product.setAuditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            product.setAuditDesc("确认产品信息正确，提交审核。");
            product.setAuditStatus("submit");
            //最后修改人信息
            product.setEditorId(sysManager.getId().toString());
            product.setEditorName(sysManager.getName());
            product.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            productService.updateDataStatusById(product);

            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品提交审核", "对录入产品提交审核", params);

            resultMap.put("flag", "true");
            resultMap.put("msg", "审核提交成功");
            return resultMap;
        }
        //删除
        if ("delete".equals(operateType)) {
            product.setDataStatus(GlobalConstant.KS_DATA_INVALID);//设置有效性-无效
            productService.updateDataStatusById(product);
            resultMap.put("flag", "true");
            resultMap.put("msg", "删除成功");

            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            saveBusinessLog("产品作废", "产品信息作废", params);

            return resultMap;
        }
        //获取存入session中的id
        CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
        //查询用户信息
        SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());

        //添加产品公告
        WebsiteBulletin websiteBulletin = new WebsiteBulletin();
//        websiteBulletin.setId(Integer.valueOf(RandomUtil.getSerialNumber())); 主键自增不需要id
        websiteBulletin.setStatus("1");
        //添加发布人信息
        websiteBulletin.setPublisherName(sysManager.getName());
        websiteBulletin.setPublisherId(sysManager.getId());
        websiteBulletin.setPublishStatus("0");//默认公告发布状态 发布
        websiteBulletin.setDataStatus(GlobalConstant.KS_DATA_VALID);
        websiteBulletin.setClicks(0);//浏览次数
        websiteBulletin.setType("1");//产品公告
        websiteBulletin.setTopMark("0");//默认置顶
        websiteBulletin.setUpdateTime(DateHelper.strToDate(DateHelper.getYMDHMSFormatDate(new Date())));
        websiteBulletin.setCreateTime(DateHelper.strToDate(DateHelper.getYMDHMSFormatDate(new Date())));
        //启用（发布-上架）
        if ("enable".equals(operateType)) {
            product.setPublishStatus("issue");
//公告标题
            websiteBulletin.setTopic("【" + productById.getName() + "上架】");
//公告内容
            websiteBulletin.setContent("预计" + productById.getName() + "于" + DateHelper.getYMDEHMFormatDate(new Date()) + "发售，起购金额" + productById.getTenderInitAmount().divide(new BigDecimal(10000)) + "万，募集资金" + productById.getAmount().divide(new BigDecimal(10000)) + "万，请提前对勘设联名卡进行充值，有任何疑问，请联系客服咨询！");
//修改发布状态并添加公告记录
            int i = productService.updateProductByIdAndAddWebsiteBulletin(product, websiteBulletin);
            if (i == 1) {
                resultMap.put("flag", "true");
                resultMap.put("msg", "发布成功");
            } else {
                resultMap.put("flag", "false");
                resultMap.put("msg", "发布失败");
            }
            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品发布", "对未发布的产品进行上架发布", params);


            return resultMap;
        }
        //禁用（停用-下架）
        if ("disable".equals(operateType)) {
            product.setPublishStatus("stop");
            websiteBulletin.setTopic("【" + productById.getName() + "下架】");
            websiteBulletin.setContent(productById.getName() + ":因产品原因现已下架，有任何疑问，请联系客服咨询！");
            int i = productService.updateProductByIdAndAddWebsiteBulletin(product, websiteBulletin);
            if (i == 1) {
                resultMap.put("flag", "true");
                resultMap.put("msg", "停用成功");
            } else {
                resultMap.put("flag", "false");
                resultMap.put("msg", "停用失败");
            }

            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品停用", "对已发布产品进行下架停用", params);

            return resultMap;
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作失败");
        return resultMap;
    }


    /**
     * @Description 流标-退款、满标-扣款放款、回款
     * @auther: lv jian
     * @UpadteDate: 2019/8/06 10:03
     */
    @RequestMapping(value = "/updateStatus/{operateType}")
    @ResponseBody
    public Map<String, Object> operateProductTwo(@PathVariable String operateType, String id, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Product productById = productService.getProductById(id);
        if (productById == null) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "该产品不存在");
            return resultMap;
        }
        //获取存入session中的id
        CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
        //查询用户信息
        SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());

        Product product = new Product();
        product.setId(id);
        //放款（满标）---- success均可进行扣款
        if ("loan".equals(operateType)) {
            int i = productUnFreezePayService.updateFreeze(productById, sysManager);
            if (i == 0) {
                resultMap.put("flag", "true");
                resultMap.put("msg", "放款成功");
                return resultMap;
            }
            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品放款", "对放款操作产品进行放款", params);
        }
        //退款（流标）----tender success均可进行流标
        if ("refund".equals(operateType)) {
            int i = productUnFreezePayService.updateUnFreeze(productById, sysManager);
            //---------1.修改产品状态，2.添加公告记录，3.添加投标放款记录，4.修改投资交易记录 5.修改用户余额表 6.添加用户交易记录
            if (i == 1) {
                resultMap.put("flag", "true");
                resultMap.put("msg", "退款成功");
                return resultMap;
            }
            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品退款", "对流标操作产品进行退款", params);

        }
        //回款----回款中repaying可进行回款操作
        if ("moneyBack".equals(operateType)) {
            int i = productUnFreezePayService.payEntityCount(productById, sysManager);
            if (i == 1) {
                resultMap.put("flag", "true");
                resultMap.put("msg", "回款成功");
                return resultMap;
            }
            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("user", sysManager);
            saveBusinessLog("产品回款", "对产品购买用户进行回款", params);

        }

        resultMap.put("flag", "false");
        resultMap.put("msg", "操作异常");
        return resultMap;
    }


    /**
     * @Description 发布产品编辑 重新提交审核
     * @auther: lv jian
     * @UpadteDate: 2019/9/04 10:03
     */
    @RequestMapping(value = "/productEditTwo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> productIssueTwo(Product product, ProductOtherAttachFile productOtherAttachFile, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            if (product.getTenderInitAmount() == null
                    || product.getTenderInitAmount().compareTo(product.getAmount().divide(new BigDecimal(200))) < 0) {
                resultMap.put("flag", "false");
                resultMap.put("msg", "请输入正确的起购金额");
                return resultMap;
            }
            //获取存入session中的id
            CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
            SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());
            //对产品表添加修改人数据===最后修改人信息
            product.setEditorId(sysManager.getId().toString());
            product.setEditorName(sysManager.getName());
            product.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            //对附件表添加修改人数据
            productOtherAttachFile.setEditorId(sysManager.getId().toString());
            productOtherAttachFile.setEditorName(sysManager.getName());
            productOtherAttachFile.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            if (product.getId() != null && product.getId().trim().length() > 0) {
                product.setAuditStatus("submit");//刚提交审核状态
                product.setTenderAuditStatus("init");//未审核
                product.setPublishStatus("notIssue");//待上架
                productOtherAttachFileService.updateProductAndProductOtherAttachFileById(product, productOtherAttachFile);
            } else {
                resultMap.put("flag", "false");
                resultMap.put("msg", "产品不存在");
                return resultMap;
            }

            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", product);
            params.put("productOtherAttachFile", productOtherAttachFile);
            saveBusinessLog("修改发布产品", "修改发布产品信息并重新审核", params);

            resultMap.put("flag", "true");
            resultMap.put("msg", "操作成功");
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作失败");
        return resultMap;
    }


    /**
     * @Description 复制当前产品到--入录列表 只复制产品不复制附件
     * @auther: lv jian
     * @UpadteDate: 2019/9/04 16:51
     */
    @RequestMapping(value = "/productCope", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> productCope(String id, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //查询产品信息
            Product productById = productService.getProductById(id);
            if (productById == null) {
                resultMap.put("flag", "false");
                resultMap.put("msg", "产品不存在");
                return resultMap;
            }
            //查询产品附件信息
            ProductOtherAttachFile productOtherAttachFile = productOtherAttachFileService.selectProductOtherAttachFileByProductId(productById.getId());
            if (productOtherAttachFile == null) {
                resultMap.put("flag", "false");
                resultMap.put("msg", "产品附件信息不存在");
                return resultMap;
            }
            if (cacheService.get(id) == null) {
                cacheService.set(id, "0");
            }
            String s = cacheService.get(id);
            Integer value = Integer.valueOf(s);
            ++value;
            cacheService.set(id, value.toString());
            productById.setGeName(productById.getGeName() + "_" + s);
            productById.setGeCode(productById.getGeCode() + "_" + s);
            productById.setName(productById.getName() + "_" + s);
            productById.setCode(productById.getCode() + "_" + s);
            productById.setContractNo(productById.getContractNo() + "_" + s);
            productById.setId(RandomUtil.getSerialNumber());
            productById.setAuditStatus("init");//初始状态 未提交
            productById.setTenderAuditStatus("init");//未审核
            productById.setPublishStatus("notIssue");//待上架
            productById.setEditTime(DateHelper.getYMDHMSFormatDate(new Date()));
            productById.setCreateTime(DateHelper.getYMDHMSFormatDate(new Date()));
            productOtherAttachFile.setId(RandomUtil.getSerialNumber());
            productOtherAttachFile.setProductId(productById.getId());
            Product product = productService.addProduct(productById);
            if (product == null) {
                resultMap.put("flag", "false");
                resultMap.put("msg", "复制失败");
                return resultMap;
            }
            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("product", productById);
            params.put("productOtherAttachFile", productOtherAttachFile);
            saveBusinessLog("复制产品", "复制产品信息", params);

            resultMap.put("flag", "true");
            resultMap.put("msg", "操作成功");
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "操作失败");
        return resultMap;
    }


    /**
     * @Description 修改附件（产品点击下一步，默认添加附件）
     * @auther: lv jian
     * @UpadteDate: 2019/9/16 9:13
     */
    @RequestMapping(value = "/productFile/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> productTemporaryStorage(@PathVariable String productId, ProductOtherAttachFile productOtherAttachFile, HttpServletRequest request) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //获取存入session中的id
            CurrentManager currentManager = (CurrentManager) request.getSession().getAttribute(ApplicationSessionKeys.CURRENT_USER);
            SysManager sysManager = sysManagerService.getSysManagerById(currentManager.getSysManager().getId());
            //对产品表添加修改人数据===最后修改人信息
            if (productId != null && productId.trim().length() > 0) {
                ProductOtherAttachFile productOtherAttachFile1 = productOtherAttachFileService.selectProductOtherAttachFileByProductId(productId);
                if (productOtherAttachFile1 == null) {
                    resultMap.put("flag", "false");
                    resultMap.put("msg", "附件不存在");
                    return resultMap;
                }
                productOtherAttachFile.setId(productOtherAttachFile1.getId());
                int i = productOtherAttachFileService.updateProductOtherAttachFileById(productOtherAttachFile);
                if (i == 0) {
                    resultMap.put("flag", "false");
                    resultMap.put("msg", "修改附件失败");
                    return resultMap;
                }
            } else {
                resultMap.put("flag", "false");
                resultMap.put("msg", "服务器繁忙");
                return resultMap;
            }

            //记录业务日志
            Map<String, Object> params = new HashMap<>();
            params.put("productOtherAttachFile", productOtherAttachFile);
            saveBusinessLog("附件信息报保存修改", "对附件信息进行修改", params);

            resultMap.put("flag", "true");
            resultMap.put("msg", "操作成功");
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("flag", "false");
        resultMap.put("msg", "暂存失败请稍后重试");
        return resultMap;
    }

}