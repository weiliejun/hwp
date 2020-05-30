/*
package com.hwp.res.controller;

import com.hwp.common.util.Amount2RMB;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
import com.hwp.common.util.ThousandsHelper;
import com.itech.ups.app.product.action.RepayModel;
import com.itech.ups.app.product.action.RepayModelCalculator;
import com.itech.ups.app.product.application.domain.ProductUtil;
import com.itech.ups.app.product.application.service.ProductService;
import com.itech.ups.app.user.application.domain.User;
import com.itech.ups.app.user.application.service.UserService;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

*
 * @author 徐赛平
 * @version 1.0
 * @description 优信贷--借款协议
 * @update 2018-9-20 上午10:13:58


@Controller
public class InvestAgreementCreditLoan {

 @Autowired
    private ProductService service;

    @Autowired
    private UserService userService;

    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    private String logoPath = "assets/ui/themes/base/images/logo_xs.png";
    private String shuiyinPath = "assets/ui/themes/base/images/shuiyin.png";

    @Value("${customerWmsURL}")
    private String customerWmsURL;


*
     * @param request
     * @param response
     * @param userId
     * @param roleType   borGuaLoan:借担保小贷 investor:出借人；manager 管理员
     * @param productId
     * @param isTemplate 是否范本 true：是；false 不是
     * @return String
     * @description 优信贷--借款协议 页面
     * @version 1.0
     * @author 徐赛平
     * @update 2018-9-20 上午10:15:58

    @RequestMapping(value = {"/product/agreement/investagreementcreditloan/view"})
    public String view(HttpServletRequest request, HttpServletResponse response, String userId, @RequestParam(required = true) String roleType, String appType, String productId, boolean isTemplate) {
        Map<String, Object> map = null;
        try {
            map = getAgreementMap(productId, userId, roleType, request, isTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("map", map);
        request.setAttribute("appType", appType);
        return "product/agreement/investAgreementCreditLoan";
    }


*
     * @param request
     * @param response
     * @param userId     出借人ID
     * @param roleType
     * @param productId
     * @param isTemplate
     * @description 优信贷--借款协议 下载
     * @version 1.0
     * @author 徐赛平
     * @update 2018-9-20 上午10:15:58

    @RequestMapping(value = {"/product/agreement/investagreementcreditloan/download"})
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String userId, String roleType, String productId, boolean isTemplate) {
        ITextRenderer iTextRenderer = null;
        OutputStream outputStream = null;
        try {
            User currentUser = userService.findUser(userId);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(getHtml(productId, userId, roleType, request, isTemplate).getBytes("UTF-8")));

            iTextRenderer = new ITextRenderer();
            ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
            fontResolver.addFont(this.getClass().getResource(ProductUtil.FONTPATH).getPath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            iTextRenderer.setDocument(doc, null);
            iTextRenderer.layout();

            outputStream = response.getOutputStream();
            response.reset();
            String pdfName = "优信贷借款协议(范本).pdf";
            if (!isTemplate) {
                pdfName = "优信贷借款协议.pdf";
                if (null != currentUser && !"borGuaLoan ".equals(roleType) && !"manager".equals(roleType)) {
                    pdfName = "优信贷借款协议(" + currentUser.getNickName() + ").pdf";
                }
            }
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
            // 不加水印 只是生成 pdf 文件
            // iTextRenderer.createPDF(outputStream);
            // 加水印 的 iTextRenderer
            iTextRenderer.createPDF(outputStream, request.getSession().getServletContext().getRealPath("/") + shuiyinPath);
            iTextRenderer.finishPDF();
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

    private Map<String, Object> getAgreementMap(String productId, String userInfoId, String roleType, HttpServletRequest request, boolean isTemplate) throws Exception {
        boolean agreementStatus = false;// 还款中及以后状态协议生效
        Map<String, Object> map = service.findLoanAgreementByProductId(productId, "investAgreementCreditLoan");

        // 借款金额 （大写）
        BigDecimal amount = (BigDecimal) map.get("AMOUNT");
        map.put("AMOUNTRMB", Amount2RMB.convert(amount.setScale(2).toString()));

        String status = map.get("STATUS").toString();

        Calendar calendar = null;
        if (status.equals("init") || status.equals("unConfirmed") || status.equals("tender") || status.equals("fail") || isTemplate) {
            calendar = DateHelper.getYMDFormatCalendar(map.get("REPAY_MODEL_START_DATE").toString());
            agreementStatus = false;
        } else {
            calendar = DateHelper.getYMDFormatCalendar(map.get("REPAY_START_DATE").toString());
            agreementStatus = true;
        }
        // 借款人还款计划
        map.put("AMOUNT", amount.setScale(2).toString());
        RepayModelCalculator repayModelForBorrower = new RepayModelCalculator(map.get("TYPE").toString(), map.get("REPAY_TYPE").toString(), amount, (BigDecimal) map.get("ANNUAL_RATE"), calendar, Integer.parseInt(map.get("TIME_LIMIT").toString()), map.get("TIME_LIMIT_UNIT").toString(), Integer.parseInt(map.get("REPAY_INTEREST_DAY").toString()));
        List<RepayModel> listRepayModel = repayModelForBorrower.getRepayModel();
        for (RepayModel r : listRepayModel) {
            r.setPrinciple(ThousandsHelper.formateThousands(r.getPrinciple(), true));
            r.setInterest(ThousandsHelper.formateThousands(r.getInterest(), true));
            r.setIncome(ThousandsHelper.formateThousands(r.getIncome(), true));
        }
        map.put("listRepayModel", listRepayModel);

        // 出借人信息
        String investorName = "";
        String investorIdNo = "";
        String investorEmail = "";
        if (agreementStatus) {
            User investorUser = userService.findUser(userInfoId);
            investorName = investorUser.getName().substring(0, 1) + "**"; // 出借人姓名
            investorIdNo = StringHelper.replaceWithStr(investorUser.getIdNo(), "*", 6, 9); // 出借人证件号码
            investorEmail = investorUser.getEmail() != null ? investorUser.getEmail() : ""; // 出借人邮箱
        }
        map.put("investorName", investorName); // 出借人姓名
        map.put("investorIdNo", investorIdNo); // 出借人证件号码
        map.put("investorEmail", investorEmail); // 出借人邮箱
        map.put("investorAddress", ""); //
        map.put("isTemplate", isTemplate);
        map.put("agreementStatus", agreementStatus);

        Object repay_start_date = map.get("REPAY_START_DATE");
        SimpleDateFormat sdfRepay = new SimpleDateFormat("yyyy年MM月dd日");
        if (!isTemplate && agreementStatus) {
            String repay_start_dateFormat = sdfRepay.format(DateHelper.stringToDate(repay_start_date.toString(), "yyyy-MM-dd"));
            String repay_end_dateFormat = sdfRepay.format(DateHelper.stringToDate(map.get("REPAY_END_DATE").toString(), "yyyy-MM-dd"));
            map.put("REPAY_START_DATE", repay_start_dateFormat);
            map.put("REPAY_END_DATE", DateHelper.getYMDFormatDate());
            map.put("updateTime", repay_start_dateFormat);
        } else {
            map.put("REPAY_START_DATE", DateHelper.getYMDFormatDate());
            map.put("REPAY_END_DATE", "");
            map.put("updateTime", DateHelper.getYMDFormatDate());
        }
        // 格式化证件号码
        if ("corp".equals(map.get("BORROWER_TYPE"))) {
            map.put("BUSI_CODE", StringHelper.replaceWithStr((String) map.get("BUSI_CODE"), "*", 6, 9));
        }
        // 查询是否进行过安心签签署
        StringBuffer businessId = new StringBuffer(productId);
        businessId.append(userInfoId);
        int signCnt = service.findCfcaContractnoRecordsTotalCount(businessId.toString(), "product");
        if (signCnt == 0) {
            map.put("trustContractFlag", "false");
        } else {
            map.put("trustContractFlag", "true");
        }
        // 通知及送达地址
        String noticeAddress = "";
        if ("corp".equals(map.get("BORROWER_TYPE"))) {
            noticeAddress = StringHelper.nvl(map.get("CORP_ADDRESS"));
        } else {
            noticeAddress = StringHelper.nvl(map.get("PROVINCE")) + StringHelper.nvl(map.get("CITY")) + StringHelper.nvl(map.get("HOME_ADDRESS"));
        }
        map.put("NAME", ((String) map.get("NAME")).substring(0, 1) + "**");
        map.put("HOME_ADDRESS", map.get("HOME_ADDRESS") == null ? "" : map.get("HOME_ADDRESS"));
        map.put("ID_NO", StringHelper.replaceWithStr((String) map.get("ID_NO"), "*", 6, 9));
        map.put("logoPath", request.getSession().getServletContext().getRealPath("/") + logoPath);
        map.put("noticeAddress", noticeAddress);
        map.put("businessId", businessId);
        map.put("userId", userInfoId);
        map.put("roleType", roleType);
        map.put("customerWmsURL", customerWmsURL);

        return map;
    }

    public String getHtml(String productId, String userInfoId, String roleType, HttpServletRequest request, boolean isTemplate) throws Exception {
        Map<String, Object> map = getAgreementMap(productId, userInfoId, roleType, request, isTemplate);
        BufferedWriter writer = null;
        StringWriter stringWriter = new StringWriter();
        writer = new BufferedWriter(stringWriter);
        Template template = freemarkerConfig.getConfiguration().getTemplate("investAgreementCreditLoan.html");
        template.process(map, writer);
        String htmlContent = stringWriter.toString();
        writer.flush();
        writer.close();
        return htmlContent;
    }

}
*/
