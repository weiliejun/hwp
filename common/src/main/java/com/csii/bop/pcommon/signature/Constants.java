package com.csii.bop.pcommon.signature;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author X
 */
public class Constants {

    //公共配置文件
    public static final String CORE_CONFIGLOCATION = "/config/applicationConfig.xml";
    //核心交易成功
    public static final String AAAAAAA = "000000";
    public static final String ACCEPT_STATUS = "B0000";
    public static final String STATUS = "00007";
    public static final String MIN_STATUS = "RJ00";
    public static final String FAIL = "FAIL00";
    public static final String AAAA = "0000";
    public static final String TIMECODE = "888883";
    //交易码定�?
    public static final String B2CPay = "PP01";
    public static final String B2BPay = "EP01";
    //sql名称定义
    public static final String QUERY_BUSINESS_LIST_SQL = "pp.core.queryBusinessList";//查询业务参数�?
    public static final String QUERY_CLEARING_LIST_SQL = "pp.core.queryClearingList";//查询清算控制�?
    public static final String QUERY_PROCESS_LIST_SQL = "pp.process.queryProcessList";//查询进程控制�?
    public static final String QUERY_PROCESS_ENTITY_SQL = "pp.process.queryProcessEntity";//查询进程控制�?
    public static final String QUERY_PROCESS_LIST_BY_CONDITION_SQL = "pp.process.queryProcessListByConditionOracle";
    public static final String QUERY_SUCCESS_PROCESS_LIST_SQL = "pp.process.querySuccessProcessList";
    public static final String QUERY_DEPARTMENT_LIST_SQL = "pp.core.queryDepartmentList";//查询机构�?
    public static final String QUERY_DEPARTMENT_ENTITY_SQL = "pp.core.queryDepartmentEntity";//查询机构�?
    //默认币种：人民币
    public static final String Default_Currency = "CNY";
    // 帐户类型
    public static final String ACCT_TYPE_COMMPANY = "0"; //公司帐户-TODO
    public static final String ACCT_TYPE_CARD = "2"; //�?
    public static final String ACCT_TYPE_BANKBOOK = "1"; //�?
    public static final String ACCT_TYPE_CREDITCARD = "C"; //信用�?
    public static final String ACCT_TYPE_DITCARD = "D"; //借记�?
    public static final String ACCT_TYPE_SUNING_DITCARD = "0"; //借记�?
    public static final String ACCT_TYPE_INNER = "9"; //内部帐户-TODO
    public static final String PAY_USERCODE = "B00"; //用�?�码
    //交易限额累加标志
    public static final String DayAmtAddFlag = "_DayAmtAddFlag";//当日交易限额累加标志
    public static final String DayAmtAddFlag_Y = "1";//当日交易限额已累�?
    public static final String DayAmtAddFlag_N = "0";//当日交易限额未累�?
    //商户明文中的金额字段名称
    public static final String MerSendAmtName = "TransAmount";//TODO-need change
    //商户明文分隔�?
    public static final String MerPlainSplitor = "[|]";
    public static final String SPACE = "              ";
    //系统追踪�?
    public static final String SystemHost = "1";
    public static final String SystemCred = "2";
    public static final String SystemTown = "3";
    //核心查证交易交易�?
    public static final String CZCodeHost = "806304";//核心
    public static final String CZCodeCred = "1222222222";//信用�?
    public static final String CZCodeTown = "1333333333";//村镇
    public static final String RETURN_CODE = "ReturnCode"; //成都商行交易返回�?
    //通讯服务名称
    public static final String ONLINETRANSPORT_NAME = "onlineTcpTransport";//联机通讯服务名称
    public static final String HOSTTRANSPORT_NAME = "hostTransport";//核心通讯服务名称
    public static final String PEOPLEBANKTRANSPORT_NAME = "peoplebankTransport";//人行通讯服务名称
    public static final String UNIONTRANSPORT_NAME = "unionpayTransport";//银联通讯服务名称
    public static final String NETRANSPORT_NAME = "paygate2NetTcpTransport";//网银渠道通讯服务名称
    public static final String DEHOSTTRANSPORT_NAME = "dehostTransport";
    //超时交易主机查证状�??
    public static final String HOST_TRANS_SUCCESS = "000000";//核心主机交易成功
    public static final String HOST_TRANS_FAIL = "FAIL00"; //核心主机交易失败
    public static final String HOST_TRANS_TIMEFAIL = "FAIL01"; //核心主机交易失败
    public static final String CRED_TRANS_SUCCESS = "00000000001";//信用卡主机交易成�?
    public static final String TOWN_TRANS_SUCCESS = "00000000002";//村镇主机交易成功
    //交易码未找到
    public static final String RESPONSE_CODE_NOTFUND = "O000ZZ";
    //总行机构代码
    public static final String HEAD_DEPT_CODE = "core";

    //前置返回的核心清算日�?
//	public static final String HOST_CLEAR_DATE = "TranDate";
    //日终处理流程说明
    public static final String BATCH_PHASE_CLEAR = "4";//清分
    public static final String BATCH_PHASE_SETT = "5";//结算
    //操作系统
    public static final String WINDOWS_OS = "windows";//Windows操作系统
    public static final String UNIX_OS = "unix";//Unix
    public static final String LINUX_OS = "linux";//Linux
    public static final String AIX_OS = "aix";//Aix
    public static final String MERTRANSLIST = "MerTransList";
    public static final String CHANNEL_WEBCONNECTOR = "04";//web接入
    //SQL
    public static final String QUERY_PROCESS_ENTITY_NAME_SQL = "pp.process.queryProcessEntityName";
    public static final String UPDATE_PROCESS_ENTITY_SQL = "pp.process.updateProcessEntity";
    public static final String UPDATE_DEPARTMENT_ENTITY_SQL = "pp.core.updateDepartmentEntity";
    public static final String UPDATE_DEPARTMENT_ENTITY_DATE_SQL = "pp.core.updateDepartmentEntityDate";
    public static final String QUERY_DEPARTMENT_AND_PROCESS_SQL = "pp.core.queryDepartmentAndProcess";
    public static final String QUERY_DEPARTMENT_AND_PROCESS_LIST_SQL = "pp.core.queryDepartmentAndProcessList";
    public static final String UPDATE_BUSINESS_SQL = "pp.core.updateBusinessEntity";
    public static final String UPDATE_CLEARING_SQL = "pp.core.updateClearingEntity";
    public static final String QUERY_BANK_LIST_SQL = "pp.core.queryBankList";
    public static final String QUERY_BANK_ENTITY_SQL = "pp.core.queryBankEntity";
    public static final String QUERY_BUSINESS_ENTITY_SQL = "pp.core.queryBusinessEntity";
    public static final String QUERY_CLEARING_ENTITY_SQL = "pp.core.queryClearingEntity";
    public static final String QUERY_PROCESS_LIST_SIZE_SQL = "pp.process.queryProcessListSize";
    public static final String DELETE_REDUPLICATE_CHECKING_FILE_SQL = "pp.core.deleteReduplicateCheckingFile";
    public static final String INSERT_CHECKING_FILE_SQL = "pp.core.insertCheckingFile";
    public static final String UPDATE_PROCESS_ENTITY_STATUS_SQL = "pp.process.updateProcessEntityStatus";
    public static final String UPDATE_SAVINGSYSTEM_PROCESS_ENTITY_STATUS_SQL = "pp.process.updateSavingSystemProcessEntityStatus";
    /**
     * 进程ID
     */
    public static final String PROCESS_ID = "PID";
    public static final String TRANSSEQNO = "TransSeqNo"; //平台交易流水�?
    public static final String TRANSDATE = "_TransDate"; //平台交易日期
    public static final String PAYACCTTYPE = "PayAcctType";   //转出帐户类型
    public static final String OPERATOR = "Operator";         //操作方式
    public static final String USERSTATUSCLOSE = "0";    //用户支付签约状�?�关�?
    public static final String OPERATORADD = "1";  //签约
    public static final String OPERATORDELE = "2";  //撤约
    public static final String OPERATORUPDATE = "3";//修改签约
    public static final String ACCNORESULT = "AccNoResult";  //卡号结果�?
    public static final String USERPERDAYLIMIT = "userPerDayLimit";   //每日限额
    public static final String USERPERTRANSLIMIT = "userPerTransLimit";  //每笔限额
    public static final String MESSAGE_TAG = "Message";                        //消息体标�?
    public static final String _BANK = "HRBCC";    //哈尔滨银行标�?
    //通用“是否�?�状态标志位
    public static final String FLAG_YES = "1";    //�?
    public static final String FLAG_NO = "0";    //�?
    //文件传输模式
    public static final String FILE_TRANSFER_MODE_HTTP = "HTTP";    //HTTP方式
    public static final String FILE_TRANSFER_MODE_FTP = "FTP";    //FTP方式
    //采用标准类型
    public static final String STANDARD_TYPE_DEFAULT = "0";    //支付平台默认标准
    public static final String STANDARD_TYPE_CARTOON = "1";    //支付宝卡通标�?
    //摘要算法
    public static final String DIGEST_ALGORITHM_CARTOON = "SHA-1";
    //支付类型
    public static final String PAY_TYPE_COMMON = "01";//网上支付
    public static final String PAY_TYPE_PROTOCOL = "02"; //协议支付
    public static final String PAY_TYPE_BTOB = "03"; //银商宝支�?
    //支付类型
    public static final String SETTING_STATUS_UNUSE = "1";//未启�?
    public static final String SETTING_STATUS_USED = "0"; //启用
    //签名算法
    public static final String SIGNATURE_ALGORITHM_CARTOON = "SHA1withRSA";    //SHA1withRSA
    public static final String SIGNATURE_ALGORITHM_COMMON = "MD5withRSA";    //MD5withRSA
    public static final String MESSAGE_ID = "MessageId";                //消息体标签属性Id
    public static final String TRANS_TYPE_TAG = "TransTypeTag";                //报文类型的标�?
    public static final String Trans_Type_ID = "TransTypeId";                //消息体标签属性Id
    public static final String MESSAGE_TAG_PRO_NAME = "id";                //消息体标签属性Id
    public static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
    public static final String CHARSET = "UTF-8";                            //字符集标�?
    //实时交易查询处理状�??
    public static final String RT_UNSETTLED = "0";//未处�?
    public static final String RT_SETTLED = "1"; //处理�?
    public static final String RT_SUCCEED = "2";//成功
    public static final String RT_FAILED = "3"; //失败
    public static final String NOTIFY_RESULT = "NotifyResponse";                //通知报文返回消息
    public static final String ERROR_TAG = "Error";                            //错误应答的标�?
    public static final String BATCH_WITHDRAW_RESULT_TAG = "BRRNotify";        //批量�?货结果�?�知的标�?
    public static final String CEBBANK_STANDARD_ROOTTAG = "MessageSuit";    //光大标准根标签�??
    public static final String PLAIN_TAG = "Plain";                            //明文的标�?
    public static final String CARTOON_STANDARD_ROOTTAG = "Cartoon";        //卡�?�标准根标签�?
    //商户协议支付证书类型
    public static final String CERT_TYPE_COMMON = "01";        //网上支付证书
    public static final String CERT_TYPE_PROTOCAL = "02";    //协议支付证书
    public static final String HOST_SYSTEM_DEPARTMENTID = "core";//�?要跟数据库表中数据保持一�?
    public static final String COLLECTOR = "collector";
    public static final String PROXYCOLLECTOR = "proxycollector";
    //信用卡系统机构号
    public static final String CREDIT_SYSTEM_DEPARTMENTID = "credit";
    public static final String PPTRANSFER = "806303";  //单笔转账
    public static final String PPCOREDATEQRY = "806300";  //查询核心日期接口
    public static final String PPACTBALQRY = "990002";  //查询账户信息
    public static final String PPACCT4CUSTOMERID = "180022";  //根据客户号查询帐号信�?
    public static final String PPCUSTOMERMESSAGE = "806305";  //查询客户信息
    public static final String PPINTERACCQUERY = "176013";  //内部账户明细查询
    public static final String PPFILECHECK = "806301";  //对账文件
    public static final String PPQUERYERROR = "806304";  //查证交易
    public static final String PPCARDJQ = "806302";//银行卡信息验�?
    public static final String PPCARDPAY = "806303";//日间支付、�??�?
    public static final String PPBranchDeptQUERY = "806307";//查询借记卡分行机构号
    public static final String PPCreditCardBranchDeptQUERY = "806309";//查询贷记卡分行机构号
    public static final String PPBranchDeptCQUERY = "806309";//查询贷记卡分行机构号
    public static final String PPTRANSFERCRIDET = "806308";//贷记卡支付和�?�?
    public static final String PPBATCHTRANSFER = "806302";//批量转账
    public static final String PPINTERACCOUNTBALANCE = "176011";//内部账户余额查询
    public static final String PPCUSTOMERTRANS = "185030";//客户联系信息查询
    public static final String PPCARDSTATUSQUERY = "806001";//卡状态查�?
    public static final String IGNOREOK = "1";  //忽略
    public static final String IGNOREFLAG = "2";  //标志是对账异�?
    public static final String IGNOREERROR = "0"; //不忽�?
    //机构�?
    public static final String DEPARTMENTID_CORE = "core"; //本行核心
    public static final String DEPARTMENTID_UNION = "union"; //银联
    public static final String DEPARTMENTID_PEOPLEBANK = "peoplebank"; //人行
    //商户的默认结算时�?
    public static final String DEFAULT_MERSETTTIME = "00"; //如果商户没有赋�??,则默认为日终结算
    //与对公系统和储蓄系统统一的支付业务代理号-用于结算文件
    public static final String Epay_BusinessNo = "";
    //金额0
    public static final BigDecimal ZERO = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);
    //金额1�?
    public static final BigDecimal RED_CENT = new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_HALF_UP);
    public static final String CORPORATION_SYSTEM_RESPONSE_CODE_ZZ = "270000ZZ";
    public static final String CORPORATION_SYSTEM_RESPONSE_CODE_ZY = "270000ZY";
    //文件名称域分隔符
    public static final String FILENAME_SEPARATOR = "_";
    /**
     * 通知失败记录标志
     */
    public static final String RECORD = "1";//记录失败次数
    public static final String NONRECORD = "0";//不记�?
    /**
     * 交易步骤
     */
    public static final String TRANS_STEP_CHECK_NO = "0"; //0－未对帐
    public static final String TRANS_STEP_CHECK_OK = "1"; //1－对帐成�?
    public static final String TRANS_STEP_CHECK_PPERROR = "2"; //2－对帐失�?
    public static final String TRANS_STEP_CLEARING_OK = "3"; //3－清算成�?
    public static final String TRANS_STEP_CLEARING_ERROR = "4"; //4－清算失�?
    public static final String TRANS_STEP_SETT_OK = "5"; //5－结算成�?
    public static final String TRANS_STEP_SETT_ERROR = "6"; //6－结算失�?
    /**
     * 交易处理状�??
     */
    public static final String TRANS_PROC_STATUS_OK = "0"; //交易正常
    public static final String TRANS_PROC_STATUS_PENDING = "9"; //交易处理�?
    public static final String PROCESS_STATUS_READY = "0"; //可清�?
    public static final String PROCESS_STATUS_PENDING = "1"; //待清�?
    public static final String PROCESS_STATUS_ERROR = "2"; //清算处理异常
    public static final String PROCESS_STATUS_FINISH = "3"; //清算处理完成
    public static final String PROCESS_STATUS_RUNNING = "9"; //清算处理�?
    /**
     * 机构类型
     */
    public static final String DEPARTMENT_TYPE_CORE_SYSTEM = "0"; //核心系统
    public static final String DEPARTMENT_TYPE_CORPORATION_SYSTEM = "1"; //公司业务系统
    public static final String DEPARTMENT_TYPE_CREDIT_SYSTEM = "2"; //信用卡系�?
    public static final String DEPARTMENT_ATTRIBUTE_MANAGEMENT = "1"; //管理性机�?
    public static final String DEPARTMENT_ATTRIBUTE_BUSINESS = "2"; //营业性机�?
    public static final String DEPARTMENT_CLASS_LEVEL_ONE = "1"; //�?类机�?
    public static final String DEPARTMENT_CLASS_LEVEL_TWO = "2"; //二类机构
    public static final String DEPARTMENT_CLASS_AGENT = "3"; //代理机构
    /**
     * 交易状�??
     */
    public static final String TRANS_STATUS_OK = "00"; //交易成功
    public static final String TRANS_STATUS_ERROR = "01"; //交易失败
    public static final String TRANS_STATUS_CANCELED = "02"; //撤消成功
    public static final String TRANS_STATUS_SUB_WITHDRAW = "03"; //部分�?�?
    public static final String TRANS_STATUS_ALL_WITHDRAW = "04"; //全部�?�?
    public static final String TRANS_STATUS_AUTH_OK = "05"; //预授权确认成�?
    public static final String TRANS_STATUS_AUTH_CANCELED = "06"; //预授权撤�?成功
    public static final String TRANS_STATUS_TIMEOUT = "99"; //交易超时
    /**
     * 帐户性质
     */
    public static final String ACCT_KIND_CURRENT = "0001"; //活期结算帐户
    public static final String ACCT_KIND_INNER_CURRENT = "0002";   //内部账户
    public static final String ENT_INTERMEDIATE_ACCOUNT = "0";  //对公中间账户
    public static final String PER_INTERMEDIATE_ACCOUNT = "1";  //对私中间帐户
    public static final String CARD_FEE_ACCOUNT = "2";  //卡手续费账户
    public static final String BANKBOOK_FEE_ACCOUNT = "3";  //折手续费帐户
    public static final String CREDIT_FEE_ACCOUNT = "4";  //信用卡手续费账户
    public static final String ENT_FEE_ACCOUNT = "5";  //对公手续费账�?
    /**
     * 内部账户
     */
    public static final String INNER_ACCOUNT_INTERMEDIATE_ACCOUNT = "0";  //中间账户
    public static final String INNER_ACCOUNT_BANK_INCOME_ACCOUNT = "1";  //银行收入账户
    public static final String INNER_ACCOUNT_POST_INCOME_ACCOUNT = "2";  //邮政收入账户
    public static final String INNER_ACCOUNT_EBANK_INCOME_ACCOUNT = "3";  //个人网银收入账户
    public static final String INNER_ACCOUNT_CREDIT_INCOME_ACCOUNT = "4";  //信用卡收入账�?
    /**
     * 商户结算周期
     */
    public static final String PERIOD_REALTIME = "0"; // 实时
    public static final String PERIOD_DAY = "1"; // �?
    public static final String PERIOD_WEEK = "2"; // �?
    public static final String PERIOD_HALFMONTH = "3"; // 半月
    public static final String PERIOD_MONTH = "4"; // �?
    public static final String PERIOD_SEASON = "5"; // �?
    public static final String PERIOD_HALFYEAR = "6"; // 半年
    public static final String PERIOD_YEAR = "7"; // �?
    /**
     * 机构账户类型
     */
    public static final String DEPT_INNER_ACCT_TYPE_OBJECT = "1";//委托分户(科目)
    public static final String DEPT_INNER_ACCT_TYPE_TRANSITION = "2";//�?来过渡户
    public static final String DEPT_INNER_ACCT_TYPE_SETTLESS = "3";//未结算款账户
    /**
     * 交易�?
     */
    public static final String TRANS_NAME_PAY = "PP01";//支付交易
    public static final String TRANS_NAME_WITHDRAW = "PP02"; //�?货交�?
    /**
     * 交易渠道
     */
    public static final String CHANNEL_NETBANK = "00";// 网银发起
    public static final String CHANNEL_MERCHANT = "01";// 商户网站发起
    public static final String CHANNEL_MERCHANTMGMT = "02";// 商户管理平台发起
    public static final String CHANNEL_MGMT = "03";// 管理平台发起
    public static final String CORP_SYSTEM_BATCH_FILE_TRANS_SUCESS = "00"; //公司业务系统批量文件交易成功
    /**
     * 商户交易状�??
     */
    public static final String MERCHANT_TRANS_STATUS_SUCCESS = "1";//商户交易成功
    /**
     * 批量处理
     */
    public static final String COMMANDARG = "_CommandArg";
    public static final String COMMANDCTX = "_CommandCtx";
    public static final String PREPHASE = "prePhase";
    public static final String CURRENTPHASE = "currentPhase";
    public static final String RETRYTIMES = "retryTimes";
    public static final String DELAYTIME = "delayTime";
    public static final String ACTION = "action";
    /**
     * 文件记录的交易类�?
     */
    public static final String FILERECORD_BATCHWITHDRAW_REQUEST = "01";//批量�?货请�?
    public static final String FILERECORD_BATCHWITHDRAW_REPONSE = "02";//批量�?货结果�?�知
    public static final String FILERECORD_TRANSCHECK = "05";                //清算对账文件通知
    public static final String FILERECORD_SIGNCHECK = "06";                //签约对账文件通知
    /**
     * 文件记录处理状�??
     */
    public static final String FILERECORD_UNPROCESSED = "0";//未处�?
    public static final String FILERECORD_PROCESSING = "1";//处理�?
    public static final String FILERECORD_SUCCESS = "2";//处理成功
    public static final String FILERECORD_FAIL = "3";//处理失败
    public static final String FILERECORD_REQUEST_PROCESSED = "4";//�?货成�?,未生成�??货结果文�?
    public static final String FILERECORD_NOTIFIED = "5";//生成结果文件,已�?�知商户
    public static final String FILERECORD_CANNOT_PROCESS = "9";//错误,无法继续处理
    /**
     * 处理状�??
     */
    public static final String MERCHANT_STATUS_Y = "Y"; //成功
    public static final String MERCHANT_STATUS_N = "N"; //失败
    /**
     * 文件类型的标�?
     */
//	public static final String FILETYPE_SIGNCHECK="SCF";
    public static final String FILETYPE_TRANSCHECK = "CCF";
    //	public static final String FILETYPE_BATCHWITHDRAW_REQUEST="BRF";
    public static final String FILETYPE_BATCHWITHDRAW_RESPONSE = "BRRF";
    public static final String SETT_DEPT_CODE = "000000";
    public static final String FILE_TRANSFER_DIRECTION_INPUT = ".i";
    public static final String BATCH_FILE_TYPE_BATCH_DEPOSIT = "PCK";
    public static final String BATCH_FILE_TYPE_BATCH_WITHDRAW = "PKK";
    public static final String BATCH_FILE_TYPE_BATCH_TRANSFER = "PZZ";
    public static final String BATCH_FILE_TYPE_SETTLEMENT = "PJS";
    public static final String FILE_TYPE_CHECKING_FILE = "WWC";
    public static final String INPUT_FILE_TYPE_FEE_TRANSFER = "0"; //手续费转账文�?
    public static final String INPUT_FILE_TYPE_DEPOSIT = "1"; //商户存款文件
    public static final String INPUT_FILE_TYPE_DEDUCT = "2"; //商户扣款文件
    public static final String DEBIT_CREDIT_MARK_DEBIT = "0"; //0-�?
    public static final String DEBIT_CREDIT_MARK_CREDIT = "1"; //1-�?
    public static final String MERCHANT_ACCT_TYPE_SAVING = "1"; //储蓄
    public static final String MERCHANT_ACCT_TYPE_CREDIT = "U"; //信用�?
    public static final String MERCHANT_DEPARTMENTID = "merchant";
    public static final String ALL_TRANS_ID = "PPPP";
    public static final String ALL_CHANNEL_ID = "99";
    public static final String HEADOFFICE_DEPARTMENTID = "999999902";
    public static final String EBANK_DEPARTMENTID = "999999902";
    public static final String MAIN_BODY_SUFFIX = "000";
    public static final String HEADOFFICE_MAIN_BODY = "999999999";
    public static final String HEADOFFICE = "core";
    //公司业务系统外部系统接入代码
    public static final String CORPORATION_SYSTEM_EXTERNAL_SYSTEM_CODE = "9942000";
    //循环查询的每次查询默认笔�?
    public static final int DEFAULT_QUERY_COUNT = 500;
    public static final String CREDITCARD_DEFAULT_MCC = "5964"; //信用卡支付默认MCC
    //分润序号
    public static final String DEPT_TYPE_ACCOUNTOPEN = "0"; //客户�?户行
    public static final String DEPT_TYPE_MERCHANTOPEN = "1"; //商户�?户行
    public static final String DEPT_TYPE_DEVELOP = "2"; //发展�?
    public static final String DEPT_TYPE_MANAGEMENT = "3"; //商户管理�?
    public static final String DEPT_TYPE_THIRD = "4"; //第三�?
    public static final String DEPT_TYPE_HEAD = "5"; //总行
    public static final String DEPT_TYPE_EBANK = "6"; //个人网银
    //用户类型
    public static final String USER_TYPE_PUBLIC = "0"; //大众版或直接支付
    //渠道类型
    public static final String CHANNEL_TYPE_PUBLIC = "0"; //大众版或直接支付
//	public static final String USER_TYPE_PROFESSIONAL = "1"; //专业�?
//	public static final String USER_TYPE_COMMPANY = "2"; //公司客户
//	public static final String USER_TYPE_SIGN = "3"; //个人签约客户
//	public static final String USER_TYPE_COMMPANY_SIGN = "4";// 公司签约客户
    public static final String CHANNEL_TYPE_PROFESSIONAL = "1"; //专业�?
    public static final String CHANNEL_TYPE_COMMPANY = "2"; //公司客户
    public static final String CHANNEL_TYPE_SIGN = "3"; //个人签约客户
    public static final String CHANNEL_TYPE_PUB_SIGN = "31"; //个人签约客户直接签约
    public static final String CHANNEL_TYPE_PRO_SIGN = "32"; //个人签约客户数字证书签约
    public static final String CHANNEL_TYPE_COMMPANY_SIGN = "4";// 公司签约客户
    public static final String CHANNEL_TYPE_MOBILE = "5";//手机银行
    public static final String CHANNEL_TYPE_MOBILE_PUB = "51";//手机银行直接支付渠道
    public static final String CHANNEL_TYPE_MOBILE_SIGN = "52";//手机银行签约支付渠道
    public static final String CHANNEL_TYPE_SIGNPUB = "6";//动�?�密码版
    //结算状�??
    public static final String SETT_STATUS_NO = "0"; //未结�?
    public static final String SETT_STATUS_SUCCESS = "1"; //结算成功
    public static final String TRANSFER_FLAG_SUCCESS = "0"; //转帐成功
    public static final String TRANSFER_FLAG_NO = "1"; //未转�?
    public static final String TRANSFER_FLAG_FAILD = "2"; //转帐失败
    public static final String TRANSFER_FLAG_NEED_RETRY = "4"; //�?要重新发起转�?
    public static final String TRANSFER_FLAG_PENDING = "9"; //正在发起转帐
    //报表完成状�??
    public static final String REPORT_STATUS_SUCCESS = "0"; //处理成功
    public static final String REPORT_STATUS_NO = "1"; //未处�?
    public static final String REPORT_STATUS_FAILURE = "2"; //处理失败
    public static final String REPORT_STATUS_WAIT = "8"; //等待处理
    public static final String REPORT_STATUS_RUNNING = "9"; //正在处理
    //商户台帐表中的交易类�?
    public static final String MERCHANT_TRANS_PAY = "0"; //支付结算（银�?->商户�?
    public static final String MERCHANT_TRANS_WITHDRAW = "1"; //�?货返还（商户->银行�?
    public static final String MERCHANT_TRANS_FEE_RCV = "2"; //手续费收取（商户->银行�?
    public static final String MERCHANT_TRANS_FEE_RETURN = "3"; //手续费返还（银行->商户�?
    public static final String MERCHANT_TRANS_CASH = "4"; //提现结算
    public static final String MERCHANT_TRANS_PAYINNER = "5"; //支付  分行408到�?�行408
    public static final String MERCHANT_TRANS_WITHDRAWINNER = "6"; //�?�?  总行408到分�?408
    public static final String MERCHANT_TRANS_CWITHDRAWINNER = "7"; //�?�? 贷记卡�?�行511到�?�行408
    public static final String MERCHANT_TRANS_ZHIWITHDRAWINNER = "8"; //�?�?  借记卡支�?511到分�?408
    public static final String MERCHANT_TRANS_A = "A"; //当天超时未结算的交易
    public static final String MERCHANT_TRANS_B = "B"; //当天查实已结算的交易
    public static final String MERCHANT_TRANS_C = "C"; //下一个结算日的交�?
    public static final String MERCHANT_TRANS_D = "D"; //t+1日结算的
    public static final String BRIEFCODE_MERCHANT_FEE_RCV = "4258"; //网上支付向商户扣收手续费
    public static final String BRIEFCODE_MERCHANT_FEE_RTN = "4254"; //网上支付向商户存入支付手续费
    public static final String BRIEFCODE_SAVING_DAYTRANS_RCV = "4248"; //网上支付扣储蓄结算款
    public static final String BRIEFCODE_MERCHANT_PAY = "4252"; //网上支付向商户存入支付本�?
    public static final String BRIEFCODE_CREDIT_DAYTRANS_RCV = "4262"; //网上支付向信用卡结算支付本金
    public static final String BRIEFCODE_MERCHANT_WITHDRAW = "4256"; //网上支付向商户扣收�??款本�?
    public static final String BRIEFCODE_SAVING_DAYTRANS_RTN = "4250"; //网上支付向储蓄存结算�?
    public static final String BRIEFCODE_CREDIT_DAYTRANS_RTN = "4264"; //网上支付向信用卡结算�?款本�?
    public static final String BRIEFCODE_BANK_ASSIGN_PROFIT = "4268"; //网上支付向银行网点分配手续费收入
    public static final String BRIEFCODE_POST_ASSIGN_PROFIT = "4270"; //网上支付向邮政网点分配手续费收入
    public static final String BRIEFCODE_EBANK_ASSIGN_PROFIT = "4272"; //网上支付向网银中心结算手续费收入
    public static final String BRIEFCODE_CREDIT_ASSIGN_PROFIT = "4266"; //网上支付向信用卡结算手续费收�?
    //对帐状�??
    public static final String TRANS_CHECK_STATUS_NO = "0"; //未对�?
    public static final String TRANS_CHECK_STATUS_OK = "1"; //对帐成功
    public static final String TRANS_CHECK_CORESTATUS_OK = "9"; //核心成功，并且原交易是在t-1日， 对帐成功
    public static final String TRANS_CHECK_STATUS_PPERROR = "2"; //支付平台失败，核心成�?
    public static final String TRANS_CHECK_STATUS_PPERROR_0 = "D"; //支付平台失败，核心成�?,未结�?
    public static final String TRANS_CHECK_STATUS_PPERROR_1 = "E"; //支付平台失败，核心成�?,已结�?
    public static final String TRANS_CHECK_STATUS_HOSTERROR = "3"; //支付平台成功，核心不匹配
    public static final String TRANS_CHECK_STATUS_PPSUCCESS = "4"; //支付平台交易失败，核心无对应记录
    public static final String TRANS_CHECK_STATUS_PPHOSTERROR = "5"; //支付平台和核心对账文件金额或核心流水不匹�?
    public static final String TRANS_CHECK_STATUS_PPHOSTERROR_0 = "F"; //支付平台和核心对账文件金额或核心流水不匹�?,未结�?
    public static final String TRANS_CHECK_STATUS_PPHOSTERROR_1 = "G"; //支付平台和核心对账文件金额或核心流水不匹�?,已结�?
    public static final String TRANS_CHECK_STATUS_PPSUCCHOSTNOERROR = "6"; //支付平台交易成功，核心无对应记录
    public static final String TRANS_CHECK_STATUS_PPSUCCHOSTNOERROR_0 = "B"; //支付平台交易成功，核心无对应记录,未结�?
    public static final String TRANS_CHECK_STATUS_PPSUCCHOSTNOERROR_1 = "C"; //支付平台交易成功，核心无对应记录,已结�?
    public static final String TRANS_CHECK_STATUS_PPHOSTEXITSERROR = "7"; //核心成功，支付平台无记录
    public static final String TRANS_CHECK_STATUS_PPHOSTSUCCSERROR = "8"; //核心成功，支付平台匹�?
    public static final String TRANS_CHECK_STATUS_SUCCPPHOSTERROR = "A"; //支付平台和核心对账文件金额或核心流水不匹�?,且支付平台succ
    //企业支付订单状�??
    public static final String ENT_PAY_ORDER_STATUS_NO = "0"; //没有支付
    //核心失败，支付平台成�?
    public static final String ENT_PAY_ORDER_STATUS_OK = "1"; //支付成功
    public static final String ENT_PAY_ORDER_STATUS_ERROR = "2"; //支付失败
    public static final String ENT_PAY_ORDER_STATUS_ENT_CANCELED = "3"; //企业撤销
    public static final String ENT_PAY_ORDER_STATUS_MERCHANT_CANCELED = "4"; //商户撤销
    public static final String ENT_PAY_ORDER_STATUS_PENDING = "9"; //支付处理�?
    //银行级别
    public static final String DEPARTMENT_LEVEL_HEAD = "0"; //总行
    public static final String DEPARTMENT_LEVEL_BRANCH = "1"; //分行
    public static final String DEPARTMENT_LEVEL_SUBBRANCH = "2"; //网点
    public static final String DEPARTMENT_LEVEL_OTHER_SUBBRANCH = "3"; //异地支行网点
    //是否�?要发�?
    public static final String SEND_STATUS_NO = "0"; //不需要发�?
    public static final String SEND_STATUS_YES = "1"; //�?要发�?
    //结算交易发�?�状�?
    public static final String SETT_SEND_STATUS_NO = "0"; //没有发�??
    public static final String SETT_SEND_STATUS_OK = "1"; //发�?�成�?
    public static final String SETT_SEND_STATUS_ERROR = "2"; //发�?�失�?
    public static final String SETT_SEND_STATUS_PENDING = "9"; //发�?�中
    //流水表交易状�?
    public static final String JNL_TRANS_STATUS_FAILD = "00"; //成功
    public static final String JNL_TRANS_STATUS_SUCCESS = "01"; //失败
    public static final String JNL_TRANS_STATUS_TIMEOUT = "09"; //超时
    //交易结果
    public static final String APP_RESULT_SUCCESS = "0";
    public static final String APP_RESULT_FAILD = "1";
    public static final String IN_USE = "0"; //
    public static final String NOT_IN_USE = "1"; //未使�?
    public static final String RESPONSE_CODE_SUCCESS = "000000";
    public static final String RESPONSE_CODE_INVALIADTRAS = "12";
    public static final String CREDIT_CARD_DEPT_ID = "1006"; //信用卡机构号
    public static final String TRANS_TELLER = "FDJIO1GY"; //交易柜员
    //协议支付类型状�??
    public final static String AGREE_SIGN_STATUS_SUCCESS = "0";    //签约成功
    public final static String AGREE_SIGN_STATUS_INVALID = "1";    //签约失效
    public final static String AGREE_SIGN_STATUS_CANCELED = "2"; //签约撤销
    //企业协议支付预签约登记类�?
    public final static String ENT_TYPE_SIGN = "1";//企业协议支付签约
    public final static String ENT_TYPE_SIGN_CANCEL = "0";//企业协议支付撤销
    //企业协议支付签约（撤�?�?
    public final static String ENT_SIGN_PREPARE = "A1";//待处�?
    public final static String ENT_SIGN_AGREE = "A2";//同意
    public final static String ENT_SIGN_REFUSE = "A3";//拒绝
    public final static String ENT_SIGN_INVALID = "A4";//失效
    public static final String QUERY_TYPE_PER_MIDDEL = "1"; //对私中间帐户
    public static final String QUERY_TYPE_CARD_FEE = "2"; // 对私卡手续费帐号
    public static final String QUERY_TYPE_BANKBOOK_FEE = "3"; //对私折手续费帐号
    public static final String QUERY_TYPE_ENT_MIDDEL = "4"; //对公中间帐户
    public static final String QUERY_TYPE_ENT_FEE = "5"; //对公手续费帐�?
    public static final String QUERY_TYPE_FUND_FEE = "6"; //基金直销手续费账�?
    public static final String QUERY_TYPE_FUND_MIDDLE = "7"; //基金赎回款中间账�?
    //交易拒绝类型
    public static final String REJECT_BY_PAYMENT = "A3"; //网关
    public static final String REJECT_BY_COREBANK = "A2"; //银行
    public static final String REJECT_BY_SYSTEM = "A1"; //系统
    //channel�?通状�?
    public static final String CHANNEL_STATE_OPEN = "0";
    public static final String CHANNEL_STATE_CLOSE = "1";
    public static final String HOST_TRANSACTION_CODE = "_HostTransactionCode";
    public static final String DEFAULT_TRANS_CONTENTNAME = "Content";
    //商户结算状�??
    public static final String Merchant_SETT_STATUS_ERROR = "E"; //商户结算异常
    public static final String Merchant_SETT_STATUS_SUCCESS = "S"; //商户结算成功
    public static final String TRANSRESPCODE = "TransRespcode";   //响应�?
    public static final String VALIDATION = "0";    //查证的状态码
    public static final String PPPAYONLINE = "6006";        //借记卡日间支付�?��??�?
    public static final String PPPAYONLINEENQY = "0103";
    public static final String PPCREDITCARDPAYONLINE = "806308";        //贷记卡日间支付�?��??�?
    public static final String PPCREDITCARDWITHDRAW = "806310";        //网上支付核心交易�?
    public static final String NOTALLY = "1";   //未记�?
    public static final String TALLY = "2";    //正常记账
    public static final String ERRORTALLY = "3"; //记账错误
    public static final String CHONGZHENG = "4"; //冲销
    public static final String MEMOCODETRANSID = "MemoCodeTransId";   //摘要�?

//    public static final String FILENAME="FILNAME";   //文件�?
    public static final String HOSTSEQNO = "HostSeqNo";

//    public static final String HOSTSERSEQNO="SerSeqNo";
    public static final String HOSTDATE = "HostDate";
    public static final String DEPARTMENTENTITY = "departmentEntity";
    public static final String AGREEMENTENTITY = "AgreementEntity";
    public static final String MERCHANTCHANNEL = "11"; //商户网站的渠�?
    public static final String ENETBANKCHANNEL = "02"; //企业网银渠道
    public static final String TENPAYCHANNEL = "12"; //财付通渠�?
    public static final String DSTRANSTYPE = "11"; //代收
    public static final String DFTRANSTYPE = "10"; //代付
    public static final String PAYTRANSTYPE = "00"; //支付
    public static final String WITHDRAWTRANSTYPE = "01"; //�?�?
    public static final String TPTXTRANSTYPE = "02"; //提现
    public static final String TRANSMODEALL = "00";//�?有，包括b2c,b2b...
    public static final String TRANSMODEB2C = "01"; //b2c 此�?�和ft_depart_act 表中�? DEPT_ACCTKIND�?致b2c
    public static final String TRANSMODEB2B = "02"; //b2b 此�?�和ft_depart_act 表中�? DEPT_ACCTKIND�?致b2b
    public static final String TRANSMODETENPAY = "03"; //财付�?  此�?�和ft_depart_act 表中�? DEPT_ACCTKIND�?致财付�??
    public static final String TRANSMODEALIPAY = "05"; //支付�?
    public static final String TRANSMODESUNINGPAY = "06"; //苏宁
    public static final String FT_DEPT_CHANAGE_FLAG_0 = "0";//机构操作标识：新�?
    public static final String FT_DEPT_CHANAGE_FLAG_1 = "1";//机构操作标识：变�?
    public static final String FT_DEPT_CHANAGE_FLAG_2 = "2";//机构操作标识：撤�?
    //财付通快捷支付批量�??货结�?
    public static final String BATCH_WITHDRAW_NO_HANDLER = "0"; // 未处�?
    public static final String BATCH_WITHDRAW_HANDLER = "1"; // 处理
    //财付通批量�??货处理状�?
    public static final String FILE_BATCH_FILE_CREATE_ERROR = "2"; //生成结果文件异常
    public static final String FILE_BATCH_FILE_UPLOAD_ERROR = "3"; //上传结果文件异常
    public static final String FILE_BATCH_FILE_NOTIFY_ERROR = "4"; //结果文件异常
    public static final String FILE_BATCH_FILE_SUCC = "1"; //批量�?货结果文件成�?
    public static final String FILE_BATCH_FILE_HANDLE = "9"; //批量�?货结果文件处理中
    public static final String FILE_BATCH_FILE_RESULT = "5"; //可以产生结果文件
    public static final String FILE_BATCH_HANDLER_SINGLE = "6"; //可以产生单笔�?�?
    public static final String FILE_BATCH_FILE_NO_HANDLE = "0"; //未处�?

//	public static final String FILE_BATCH_HANDLER_SINGLE_DOWNFAIL = "7"; //下载批量�?款文件失�?
    public static final String STATUS_U = "U"; //交易处理�?(未知状�??)
    public static final String STATUS_Y = "Y"; //交易成功
    public static final String STATUS_N = "N"; //交易失败
    public static final String CLEARBABANCE_FPCP = "支付";

    //清算账户查询汇�?�分�?
    public static final String CLEARBABANCE_FPTX = "提现";
    public static final String CLEARBABANCE_BRNT = "�?�?";
    public static final String CLEARBABANCE_OTHER = "电子商务转帐";
    public static final String TENPAY_FILETYPE_SIGNCHECK = "TSCF";
    public static final String TENPAY_FILETYPE_TRANSCHECK = "TCCF";
    public static final String YWTYPE = "Ywtype";//业务类型
    public static final String DIRECTOR = "director";//贷记卡中心机构号
    public static final String CDIRECTOR = "feecore";//手续费收入户
    public static final String DEPTACCTKIND_B2C = "01";//支付平台内部账户性质-B2C
    public static final String DEPTACCTKIND_B2B = "02";//支付平台内部账户性质-B2B
    public static final String DEPTACCTKIND_TENPAY = "03";//支付平台内部账户性质-财付�?
    public static final String INPUT_TYPE_FEE_TRANSFER = "7"; //手续费转账文�?
    public static final String INPUT_TYPE_MER_TRANSFER = "1"; //商户转账文件
    public static final String MEMOWITHDRAW = "6"; //�?�?
    //甘肃银行特有�?
    public static final String TWOCLEARSETT = "1";  //二次清分
    public static final String NOTWOCLEARSETT = "0"; //非二次清�?
    public static final String CHECKORDER = "CHECKORDER";
    public static final String MERCERT = "MERCERT";
    public static final String ACCOUNT_PAY_CODE = "PP05";
    public static final String DANBAO_PAY_CODE = "PP06";
    public static final String PHONEPAY = "16";//手机支付
    public static final String CHECKINGBIANHAO = "000001201510";
    public static final Map TRANSCODEMAP = new HashMap();
    public static final String CHECKINGBIANHAOEND = "001";
    public static final String A = "0";
    public static final String EPY = "EPY";
    public static final String RETURN_HOSTDATE = "HostTrsDate"; //核心交易日期（响应）
    public static final String BBBBBBB = "999999"; //核心交易错误
    public static final String HOSTCARDTYPE = "C";  //核心：卡 C:�? 	P:�?(给卡转账时，填写�?
    public static final String HOSTZHETYPE = "P";
    public static final String HOSTACCTYPEHUOQI = "E3";
    public static final String HOSTACCTYPEINNER = "710";
    public static final String BANKINNER = "0"; //行内
    public static final String BANKOTHER = "1"; //行外
    public static final String DEPARTNAME = "DEPARTNAME";
    public static final String TIMEOUTTRANSCODE = "5131";
    public static final String SUCCESS = "S";
    public static final String SIGNMAP = "SignMap";
    /**
     * 晋商易付核心交易�?
     */
    public static final String TRANSACTION_DE00 = "DE00";//大额转账接口
    public static final String TRANSACTION_0192 = "0192";//大额超时查证
    public static final String TRANSACTION_0416 = "0416";//人行对账文件申请
    public static final String TRANSACTION_5110 = "5110";//人行签约
    public static final String TRANSACTION_5112 = "5112";//人行代收
    public static final String TRANSACTION_5113 = "5113";//代收超时,行内查询
    public static final String TRANSACTION_5119 = "5119";//签约信息,人行查询
    public static final String TRANSACTION_5123 = "5123";//代收超时,人行查询
    public static final String TRANSACTION_5130 = "5130";//行内对账文件申请
    public static final String TRANSACTION_5131 = "5131";//超时查证
    public static final String TRANSACTION_5132 = "5132";
    public static final String TRANSACTION_5124 = "5124";
    public static final String TRANSACTION_6006 = "6006";
    public static final String TRANSACTION_6063 = "6063";
    public static final String TRANSACTION_6063B = "6063B";
    /**
     * 操作类型
     */
    public static final String OPTION_FLAG_1 = "1";
    public static final String OPTION_FLAG_2 = "2";
    public static final String OPTION_FLAG_3 = "3";
    /**
     * 单笔总账状�??
     */
    public static final String INNERTRANSFER_STATUS_0 = "0";//未转�?
    public static final String INNERTRANSFER_STATUS_1 = "1";//转账失败
    public static final String INNERTRANSFER_STATUS_9 = "9";//转账处理�?
    /**
     * 结算状�??
     */
    public static final String TRANS_SETT_STATUS_0 = "0";//未结�?
    public static final String TRANS_SETT_STATUS_1 = "1";//已结�?
    /**
     * 业务类型
     */
    public static final String BUSITYPE_1 = "1";//代付业务
    public static final String BUSITYPE_2 = "2";//代收业务
    public static final String SEVENONEZERO = "P"; //710�?
    /**
     * 结算户是否是本行
     */
    public static final String MER_ISBANK_0 = "0"; //本行
    public static final String MER_ISBANK_1 = "1"; //他行
    private Constants() {

    }
}
