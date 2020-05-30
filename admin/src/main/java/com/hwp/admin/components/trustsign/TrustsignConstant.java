package com.hwp.admin.components.trustsign;

public class TrustsignConstant {
    // 安心签请求码
    public static final String TXCODE_3001 = "3001";
    public static final String TXCODE_3002 = "3002";
    public static final String TXCODE_3203 = "3203";

    // 接口成功标识
    public static final String RETCODE_SUCCESS = "60000000";

    // 合同中角色表示
    public static final String ROLE_INVESTOR = "investor";
    public static final String ROLE_BORROWER = "borrower";

    // 合同url
    // public static final String PRODUCT_AGREEMENT_ELITEPLAN_URL =
    // "/user/agreement/personcreditauthorization/download";
    public static final String PERSON_CREDIT_URL = "/user/agreement/personcreditauthorization/download";
    public static final String CREDIT_UNDERTAKING_URL = "/product/agreement/creditUndertakingAgreement/download"; // 信用承诺书URL
    public static final String INFO_CONSULTATION_URL = "/product/agreement/infoConsultationAgreement/download"; // 信息咨询服务协议URL

    // 安心签合同编号，业务类型
    public static final String BUSINESS_TYPE_PERSONCREDIT = "userCreditQuery";// 个人信用
    public static final String BUSINESS_TYPE_PRODUCT = "product";// 产品借款合同
    public static final String BUSINESS_TYPE_CREDITASSIGNMENT = "creditAssignment";// 债权转让
    public static final String BUSINESS_TYPE_CREDIT_UNDERTAKING = "creditUndertakingAgreement";// 信用承诺书
    public static final String BUSINESS_TYPE_INFO_CONSULTATION = "infoConsultationAgreement";// 信息咨询服务协议
}