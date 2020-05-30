package com.hwp.common.constant;


import java.io.File;

/**
 * 系统全局常量
 */
public class GlobalConstant {
    //状态有效
    public static final String STATUS_VALID = "1";
    //状态无效
    public static final String STATUS_INVALID = "0";
    //数据有效
    public static final String DATA_VALID = "1";
    //数据无效
    public static final String DATA_INVALID = "0";
    //理财产品数据无效
    public static final String KS_DATA_VALID = "1";
    //理财产品数据有效
    public static final String KS_DATA_INVALID = "0";
    //default字面量
    public static final String DEFAULT = "default";
    // 用户免费使用服务次数
    public static final Integer SERVICE_FREE_COUNT = 5;
    // 用户上传转账凭证路径
    public static final String TRANSFER_FILES_TEMP_PATH = "/upload/transferFile/";

    // 用户上传身份证路径
    public static final String USER_AUTH_TEMP_PATH = "/upload/user/sfz/";

    // 用户上传工作凭证路径
    public static final String USER_VOUCHER_TEMP_PATH = "/upload/user/gzpz/";
    // 用户上传本地路径
    public static final String APP_QIYEBAO_TEMP_USER_PHOTO_PATH = File.separator+"upload"+File.separator+"xmxxgl"+File.separator+"jsxm";

    // 用户数据库上传路径
    public static final String APP_QIYEBAO_USER_PHOTO_PATH = File.separator+"upload"+File.separator+"xmxxgl"+File.separator+"jsxm";

    public static final String EEC_SERVICE_DOC = "/upload/eecservice/doc/";
    //管理后台初始密码
    public static final String CONSOLE_PASSWORD_INIT = "123456";

    //管理后台系统超级管理角色名称
    public static final String CONSOLE_SUPER_ADMIN_ROLE = "系统超级管理员";
    // 用户申请API最大次数
    public static final Integer APPLY_API_MAX = 5;
    public static final String SUCCESS = "0000";
    public static final String FAIL = "0001";
    public static final String ERROR = "0002";
    //public static final String OFF_LINE = "9999";
    public static final String FORCE_OFF_LINE = "9999";
    //redis存储app验证码相关跟路径key
    public static final String APP_VALIDATE_CODE_KEY = "app:validate_code:";
    //redis存储晋商验证码返回的messageToken根路径key
    public static final String APP_MESSAGE_TOKEN_KEY = "app:message_token_code:";
    //App验证码相关 注册
    public static final String APP_REGISTER = "register";
    //App验证码相关 登录
    public static final String APP_LOGIN = "login";
    //App验证码相关 找回密码
    public static final String APP_RET_PWD = "retpwd";
    //App验证码相关 重置交易密码
    public static final String APP_RESET_PWD = "resetpwd";
    // 路径分割符
    private static final String FILE_SEPARATOR = File.separator;
    // 用户上传投资观点图片本地路径
    public static final String APP_JIAOKAN_TEMP_USER_PHOTO_PATH = FILE_SEPARATOR + "upload" + FILE_SEPARATOR + "temp" + FILE_SEPARATOR + "investmentArticle";

    // 消息类型
    public static class MessageType {
        // 短信
        public static final String SMS = "sms";
        // 站内信
        public static final String WEBSITE = "website";
        // email
        public static final String EMAIL = "email";
    }

}
