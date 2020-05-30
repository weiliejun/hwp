package com.hwp.admin.components.trustsign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwp.admin.app.service.product.ProductService;
import com.hwp.admin.components.trustsign.service.TrustSignService;
import com.hwp.admin.web.base.AbstractBaseController;
import com.hwp.common.model.cfcaContractnoRecord.bean.CfcaContractnoRecord;
import com.hwp.common.model.product.bean.Product;
import com.hwp.common.util.StringHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2014-4-5
 * @author  zqs
 * ===========================================================================
 *
 */
@Controller
public class TrustSignController extends AbstractBaseController {
    private static final Log logger = LogFactory.getLog(TrustSignController.class);
    @Autowired
    private ProductService productService;

    @Autowired
    private TrustSignService trustSignService;

    /**
     * @param model
     * @param request
     * @param productId
     * @return
     * @description 一键签署合同
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-27 下午4:35:40
     */

    @RequestMapping("/business/product/contract/sign")
    public @ResponseBody
    Map<String, String> contractSign(Model model, HttpServletRequest request, String productId) {
        Product product = productService.getProductById(productId);
        return trustSignService.trustSignRegisterAndSign(product);
    }

    /**
     * @param request
     * @param response
     * @param
     * @description 下载安心签合同
     * @version 1.0
     * @author 张可乐
     * @update 2017-3-27 下午4:31:59
     */
    @RequestMapping(value = {"/business/trustsign/contract/download"})
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String businessId, String businessType) {
        OutputStream outputStream = null;
        try {
            CfcaContractnoRecord cfcaContractnoRecord = trustSignService.findCfcaContractnoRecordBybusinessIdAndBusinessType(businessId, businessType);
            if (cfcaContractnoRecord == null) {
                logger.error("下载安心签合同时，查询[CfcaContractnoRecord]对象为空");
                return;
            }
            String contractNo = cfcaContractnoRecord.getContractNo();
            String contractStr = cfcaContractnoRecord.getRemark();
            JSONObject contractJson = null;
            if (StringHelper.isNotEmpty(contractStr)) {
                contractJson = JSON.parseObject(contractStr);
            }
            byte[] fileBtye = trustSignService.downLoadContract(contractNo);
            outputStream = response.getOutputStream();
            response.reset();
            String contractName = "";
            if (contractJson != null) {
                contractName = contractJson.getString("contractName");
            }
            String pdfName = contractName + ".pdf";
            String fileName = null;
            String userAgent = request.getHeader("User-Agent");
            // 基于IE内核
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(pdfName, "UTF-8");
            } else {
                // 非IE内核
                fileName = new String(pdfName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("Location", fileName);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            outputStream.write(fileBtye);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}