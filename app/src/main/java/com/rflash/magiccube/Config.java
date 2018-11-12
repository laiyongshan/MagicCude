package com.rflash.magiccube;

/**
 * Created by yangfan on 2017/11/15.
 */

public class Config {


    public static final String USER_PWD = "user_pwd";
    public static final String USER_STORE = "user_store";

    public static final String ANDROID = "ANDROID";
    public static final String APP_NAME = "magiccube";

    public static final String REDUCTION = "reduction";

    //验签
    public static final String USER_PUBKEY = "pubKey";
    //签名
    public static final String USER_PRVKEY = "prvKey";

    public static final String POINT_ID = "pointId";

    //*********************http请求公共参数**************************//
    //请求流水号
    public static final String REQUEST_NO = "requestNo";
    //版本号
    public static final String VERSION = "version";
    public static final String VERSION_CODE = "V1.0";
    //机身序列号
    public static final String MACHINECODE = "machineCode";
    //终端号
    public static final String TERMCODE="termCode";
    //商户号
    public static final String MERCHANTCODE="merchantCode";
    //养卡点账号
    public static final String ACCOUNT = "account";
    //验签字段
    public static final String SIGNATURE = "signature";
    //*********************http请求公共参数**************************//


    public static final String HTTP_SUCCESS = "0000";


    public static final String DEFAULT_PRIVATE_PATH = "prv.pem";
    public static final String DEFAULT_PUBLIC_PATH = "pub.pem";
    public static final String AES = "75e18445ec43e46583462d695e839ebf";

    public static final String ORDER_SQL = "CASE WHEN p.state = 'NOT_OPERATOR' THEN p.plan_operator_time END ASC , CASE WHEN p.state != 'NOT_OPERATOR' THEN p.plan_operator_time END DESC";

    //操作
    public static final String DEAL = "DEAL";

    //未操作
    public static final String NOT_OPERATOR = "NOT_OPERATOR";

    //消费
    public static final String SALE = "SALE";

    //还款
    public static final String REPAY = "REPAY";

    //账单查询未确认状态
    public static final String ISCONFIRM_N = "N";

    //账单状态 VALID 正常
    public static final String VALID = "VALID";

    //账单查询显示忽略数据 Y 显示 N 不显示
    public static final String SHOWIGNORE = "N";

    //修改账单金额  操作标志：UPDATE
    public static final String CONFIRM = "CONFIRM";


//    WAIT_CONFIRM: 正在受理
//    ACCEPTED_FAILURE：受理失败

    public static final String WAIT_CONFIRM = "WAIT_CONFIRM";
    public static final String ACCEPTED_FAILURE = "FAIL";

    public static final String CHANNELSTATE_VALID = "valid";
    public static final String BIND_Y = "Y";
    public static final String AVAILABLE = "Y";

    //数据库名字
    public static final String DB_NAME = "rflash.db";
    //数据库版本
    public static final int DB_VERSION = 1;
    //数据库table
    public static final String DB_TABLE = "CardRecord";

    public static final String OPERATIONTIME_SUCCESS = "SUCCESS";
    public static final String OPERATIONTIME_FAIL = "FAIL";

    //*********************pos相关**************************//
    public static final String APPLICATIONID = "cn.bw.unionpay.gzeh";
    public static final String BASE_DIR = "cn.basewin.unionpay.external.";
    public static final String SIGN_DIR = BASE_DIR + "SignExternalAty";
    public static final String SALE_DIR = BASE_DIR + "SaleExternalAty";
    public static final String VOID_DIR = BASE_DIR + "VoidExternalAty";
    public static final String REFUND_DIR = BASE_DIR + "RefundExternalAty";
    public static final String QUERY_DIR = BASE_DIR + "QueryBalanceExternalAty";
    public static final String SETTLE_DIR = BASE_DIR + "SettlementExternalAty";
    public static final String PRINT_DIR = BASE_DIR + "PrintExternalAty";
    public static final String SYSMANAGEMENT_DIR = BASE_DIR + "SysManageExternalAty";
    public static final String GETSN_DIR = BASE_DIR + "GetSNExternalAty";
    //*********************pos相关**************************//

    /**
     * 征信产品
     */
    //银联用户画像
    public static  final  String USER_PORTRAIT = "0032";
    //银联用户交易报告
    public static  final  String TRANS_REPORT = "0071";
    //用户疑似套现
    public static  final  String SUSPECT_CASH = "0046";
    //用户账单真伪核验
    public static  final  String BILL_CHECK = "0058";
    //云信贷申请反欺诈
    public static  final  String CLOUD_CREDIT_APPLICATION = "0116";
    //云信贷互联反欺诈
    public static  final  String CLOUD_CREDIT_INTERNET = "0147";
    //四要素验证
    public static final String FOUR_ELEMNETS_VERICATION="0070";

    // startActivityForResult中requestCode标识
    public static  final  int INTENTOK = 101;

    public static  final  String STRINGALIPAY = "支付宝";
    public static  final  String STRINGWXPAY = "微信";

    public static  final  String ALIPAY = "ALIPAY";
    public static  final  String WXPAY = "WXPAY";

    // 申请链接接口类型
    public static  final  String CREDITCARD = "CREDIT_CARD";// 信用卡申请链接
    public static  final  String IOAN  = "IOAN ";// 贷款申请链接
}
