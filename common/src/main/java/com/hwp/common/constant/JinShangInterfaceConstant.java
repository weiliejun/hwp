package com.hwp.common.constant;

/**
 * 晋商接口返回值常量
 */
public class JinShangInterfaceConstant {
    // 渠道流水号
    public static final String CHANNEL_JNL_NO = "_ChannelJnlNo";
    // 返回码 SUCCESS/FAIL
    public static final String RETURN_CODE = "ReturnCode";
    // 结果信息描述 处理成功 错误原因提示等
    public static final String RETURN_MSG = "ReturnMsg";
    // UUID
    public static final String UUID = "UUID";
    // 手机号码
    public static final String MOBILE_PHONE = "MobilePhone";
    // 短信格式标识
    public static final String TOKEN_MESSAGE = "TokenMessage";
    // 短信验证标识
    public static final String MSG_AUTH_FLAG = "MsgAuthFlag";
    // 交易密码验证标志
    public static final String VERIFY_TRS_PASSWORD_FLAG = "VerifyTrsPasswordFlag";
    // 返回码value
    public static final String RETURN_CODE_VALUE = "000000";
    // 订单号
    public static final String ORDER_ID = "ordId";
    // 交易类型代码 WTHI=资金转出
    public static final String RTXN_TYPE_CODE_WTHI = "WTHI";
    // 交易类型代码 RCGI=资金转入
    public static final String RTXN_TYPE_CODE_RCGI = "RCGI";
    // 交易类型代码 XYSG=兴业基金申购
    public static final String RTXN_TYPE_CODE_XYSG = "XYSG";
    // 交易类型代码 XYSH=兴业基金赎回
    public static final String RTXN_TYPE_CODE_XYSH = "XYSH";
    // 交易类型代码 CPDJ=产品购买-冻结
    public static final String RTXN_TYPE_CODE_CPDJ = "CPDJ";
    // 交易类型代码 CPJD=产品流标-解冻
    public static final String RTXN_TYPE_CODE_CPJD = "CPJD";
    // 交易类型代码 CPKK=产品满标-扣款
    public static final String RTXN_TYPE_CODE_CPKK = "CPKK";
    // 交易类型代码 CPHK=产品回款
    public static final String RTXN_TYPE_CODE_CPHK = "CPHK";
    // 交易状态 0—成功
    public static final String TRADE_STATUS_SUCCESS = "0";
    // 交易状态 1—失败；
    public static final String TRADE_STATUS_FAILURE = "1";
    // 交易状态 2-未找到该流水
    public static final String TRADE_STATUS_NOT_FOUND = "2";
    // 交易状态 4-状态未明
    public static final String TRADE_STATUS_UNKNOUN = "4";
    // 交易状态 其他-未定义，当失败处理
    public static final String TRADE_STATUS_OTHER_UNDEFINED = "5";


}
