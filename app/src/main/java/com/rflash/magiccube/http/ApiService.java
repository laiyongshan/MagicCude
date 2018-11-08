package com.rflash.magiccube.http;

import com.rflash.magiccube.Config;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yangfan on 2017/10/31.
 */

public interface ApiService {

    int DEFAULT_TIMEOUT = 60;
    //内网地址
//    String URL = "http://10.252.1.170:8083/";
    //外网地址
//    String URL = "http://113.98.101.186:48083/";
    //生产地址
    String URL = "http://gateweb.ehwlsj.com/";


    /**
     * 登录
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param password    密码
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/user/login")
    Observable<BaseBean> login(@Field(Config.VERSION) String version,
                               @Field(Config.REQUEST_NO) String requestNo,
                               @Field(Config.MACHINECODE) String machineCode,
                               @Field(Config.ACCOUNT) String account,
                               @Field(Config.SIGNATURE) String signature,
                               @Field("password") String password);

    /**
     * 账单查询
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param isConfirm   确认状态 N: 未确认   Y: 已确认
     * @param state       账单状态 VALID: 正常  ABANDONED: 废弃
     * @param pageNum     查询的页数 分页的时候的页数，不传值的话会默认1，就是返回分页第一页内容
     * @param available   Y 只查可用
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/cardbill/queryCardBill")
    Observable<BaseBean> queryCardBill(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("isConfirm") String isConfirm,
                                       @Field("state") String state,
                                       @Field("pageNum") String pageNum,
                                       @Field("available") String available,
                                       @Field("showIgnore") String showIgnore);


    /**
     * 规划查询
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param pointId     养卡点id
     * @param startDate   查询时间段里的开始日期，YYYYMMDD
     * @param state       状态 NOT_OPERATOR：未操作    DEAL：已操作
     * @param endDate     查询时间段里的结束日期，YYYYMMDD
     * @param cardSeqno   卡位
     * @param cardNo      卡号
     * @param tranType    操作类型 SALE：消费  REPAY：还款
     * @param today       0或不传，不限定   1限定   当为1时，开始结束日期会被忽略
     * @param pageNum     查询的页数 分页的时候的页数，不传值的话会默认1，就是返回分页第一页内容
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/query")
    Observable<BaseBean> planQuery(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                   @Field(Config.POINT_ID) String pointId,
                                   @Field("startDate") String startDate,
                                   @Field("endDate") String endDate,
                                   @Field("cardSeqno") String cardSeqno,
                                   @Field("cardNo") String cardNo,
                                   @Field("state") String state,
                                   @Field("tranType") String tranType,
                                   @Field("today") String today,
                                   @Field("pageNum") String pageNum,
                                   @Field("order") String order);


    /**
     * 查询代付账户可用金额
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param pointId     养卡点id
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/pay/balance")
    Observable<BaseBean> balance(@Field(Config.VERSION) String version,
                                 @Field(Config.REQUEST_NO) String requestNo,
                                 @Field(Config.MACHINECODE) String machineCode,
                                 @Field(Config.ACCOUNT) String account,
                                 @Field(Config.SIGNATURE) String signature,
                                 @Field(Config.POINT_ID) String pointId);


    /**
     * 确认还款
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param planId      规划单号
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/confirm")
    Observable<BaseBean> confirm(@Field(Config.VERSION) String version,
                                 @Field(Config.REQUEST_NO) String requestNo,
                                 @Field(Config.MACHINECODE) String machineCode,
                                 @Field(Config.ACCOUNT) String account,
                                 @Field(Config.SIGNATURE) String signature,
                                 @Field("planId") String planId);

    /**
     * 发起代付
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param planId      规划单号
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/pay/pay")
    Observable<BaseBean> helpPay(@Field(Config.VERSION) String version,
                                 @Field(Config.REQUEST_NO) String requestNo,
                                 @Field(Config.MACHINECODE) String machineCode,
                                 @Field(Config.ACCOUNT) String account,
                                 @Field(Config.SIGNATURE) String signature,
                                 @Field("planId") String planId);


    /**
     * 修改账单金额
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param billId      账单id
     * @param cardNo      卡号
     * @param billMonth   账单月份 格式：YYYYMM
     * @param billAmt     实际金额
     * @param operate     操作标志 修改操作:UPDATE
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/cardbill/updateCardBill")
    Observable<BaseBean> updateCardBill(@Field(Config.VERSION) String version,
                                        @Field(Config.REQUEST_NO) String requestNo,
                                        @Field(Config.MACHINECODE) String machineCode,
                                        @Field(Config.ACCOUNT) String account,
                                        @Field(Config.SIGNATURE) String signature,
                                        @Field("billId") String billId,
                                        @Field("cardNo") String cardNo,
                                        @Field("billMonth") String billMonth,
                                        @Field("billAmt") String billAmt,
                                        @Field("operate") String operate);

    /**
     * 查询账单通知数量
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param pointId     养卡点id
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/remindCount")
    Observable<BaseBean> billCount(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                   @Field(Config.POINT_ID) String pointId);


    /**
     * APP更新检测
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param type        ANDROID:表示android
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/update/query")
    Observable<BaseBean> updateApp(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                   @Field("type") String type);

    /**
     * 统计报表
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/getCount")
    Observable<BaseBean> threeDayCount(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature);

    /**
     * 校验最后交易时间
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param planId      规划号
     * @param cardNo      卡号
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/lastoperator")
    Observable<BaseBean> lastOperator(@Field(Config.VERSION) String version,
                                      @Field(Config.REQUEST_NO) String requestNo,
                                      @Field(Config.MACHINECODE) String machineCode,
                                      @Field(Config.ACCOUNT) String account,
                                      @Field(Config.SIGNATURE) String signature,
                                      @Field("planId") String planId,
                                      @Field("cardNo") String cardNo);


    /**
     * 查询渠道
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/channel/query")
    Observable<BaseBean> queryChannel(@Field(Config.VERSION) String version,
                                      @Field(Config.REQUEST_NO) String requestNo,
                                      @Field(Config.MACHINECODE) String machineCode,
                                      @Field(Config.ACCOUNT) String account,
                                      @Field(Config.SIGNATURE) String signature);


    /**
     * 商户渠道
     *
     * @param version      版本
     * @param requestNo    请求流水号  （当天时间）
     * @param machineCode  机身序列号
     * @param account      养卡点账号
     * @param signature    签名字段
     * @param channel      渠道号
     * @param channelState 渠道状态 valid正常
     *                     freeze冻结
     * @param state        商户状态 valid正常
     *                     freeze冻结
     * @param bind         是否只查询已与该养卡点绑定的商户
     *                     N：否
     *                     Y：是
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/merchant/query")
    Observable<BaseBean> queryMerchant(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("channel") String channel,
                                       @Field("channelState") String channelState,
                                       @Field("state") String state,
                                       @Field("bind") String bind,
                                       @Field("merchantName") String merchantName,
                                       @Field("pageNum") String pageNum);

    /**
     * 消费规划修改
     *
     * @param version      版本
     * @param requestNo    请求流水号  （当天时间）
     * @param machineCode  机身序列号
     * @param account      养卡点账号
     * @param signature    签名字段
     * @param channel      渠道号
     * @param planId       规划单号
     * @param amt          规划金额
     * @param merchantCode 商户号
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/modify")
    Observable<BaseBean> consumeModify(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("planId") String planId,
                                       @Field("amt") String amt,
                                       @Field("merchantCode") String merchantCode,
                                       @Field("channel") String channel);


    /**
     * 消费规划修改
     *
     * @param version     版本
     * @param requestNo   请求流水号  （当天时间）
     * @param machineCode 机身序列号
     * @param account     养卡点账号
     * @param signature   签名字段
     * @param planId      规划单号
     * @param amt         规划金额
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/modify")
    Observable<BaseBean> repayModify(@Field(Config.VERSION) String version,
                                     @Field(Config.REQUEST_NO) String requestNo,
                                     @Field(Config.MACHINECODE) String machineCode,
                                     @Field(Config.ACCOUNT) String account,
                                     @Field(Config.SIGNATURE) String signature,
                                     @Field("planId") String planId,
                                     @Field("amt") String amt);


    /**
     * 征信产品列表
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/product/query")
    Observable<BaseBean> queryCredit(@Field(Config.VERSION) String version,
                                     @Field(Config.REQUEST_NO) String requestNo,
                                     @Field(Config.MACHINECODE) String machineCode,
                                     @Field(Config.ACCOUNT) String account,
                                     @Field(Config.SIGNATURE) String signature);


    /**
     * 单一征信产品详细列表
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param creditType
     * @param pageNum
     * @param signature
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/product/detail")
    Observable<BaseBean> queryCreditProduct(@Field(Config.VERSION) String version,
                                            @Field(Config.REQUEST_NO) String requestNo,
                                            @Field(Config.MACHINECODE) String machineCode,
                                            @Field(Config.ACCOUNT) String account,
                                            @Field("creditType") String creditType,
                                            @Field("pageNum") String pageNum,
                                            @Field(Config.SIGNATURE) String signature);

    /**
     * 删除征信订单
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param orderNo
     * @param signature
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/product/delete")
    Observable<BaseBean> deletePersonalMessage(@Field(Config.VERSION) String version,
                                               @Field(Config.REQUEST_NO) String requestNo,
                                               @Field(Config.MACHINECODE) String machineCode,
                                               @Field(Config.ACCOUNT) String account,
                                               @Field("orderNo") String orderNo,
                                               @Field(Config.SIGNATURE) String signature);

    /**
     * 提交征信
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param creditType
     * @param cardNo
     * @param name
     * @param identityCard
     * @param mobile
     * @param beginDate
     * @param endDate
     * @param signature
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/product/launch")
    Observable<BaseBean> queryProduct(@Field(Config.VERSION) String version,
                                      @Field(Config.REQUEST_NO) String requestNo,
                                      @Field(Config.MACHINECODE) String machineCode,
                                      @Field(Config.ACCOUNT) String account,
                                      @Field("creditType") String creditType,
                                      @Field("cardNo") String cardNo,
                                      @Field("name") String name,
                                      @Field("identityCard") String identityCard,
                                      @Field("mobile") String mobile,
                                      @Field("beginDate") String beginDate,
                                      @Field("endDate") String endDate,
                                      @Field(Config.SIGNATURE) String signature);

    /**
     * 购买征信产品
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param creditType
     * @param num
     * @param payType
     * @param signature
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/product/buy")
    Observable<BaseBean> rechargeTimes(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field("creditType") String creditType,
                                       @Field("num") String num,
                                       @Field("payType") String payType,
                                       @Field(Config.SIGNATURE) String signature);

    /**
     * 申请链接列表
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param type
     * @param signature
     * @return
     */
    @FormUrlEncoded
    @POST("card-gate-web/link/query")
    Observable<BaseBean> queryLink(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field("type") String type,
                                   @Field(Config.SIGNATURE) String signature);

    /**
     * 首页统计
     * * @param version
     *
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/getCount")
    Observable<BaseBean> getCount(@Field(Config.VERSION) String version,
                                  @Field(Config.REQUEST_NO) String requestNo,
                                  @Field(Config.MACHINECODE) String machineCode,
                                  @Field(Config.ACCOUNT) String account,
                                  @Field(Config.SIGNATURE) String signature
    );

    /**
     * 首页通知数量
     * * @param version
     *
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/remindCount")
    Observable<BaseBean> getremindCount(@Field(Config.VERSION) String version,
                                        @Field(Config.REQUEST_NO) String requestNo,
                                        @Field(Config.MACHINECODE) String machineCode,
                                        @Field(Config.ACCOUNT) String account,
                                        @Field(Config.SIGNATURE) String signature
    );


    /**
     * 业务员查询
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param name
     * @param profitRatio
     */
    @FormUrlEncoded
    @POST("card-gate-web/sales/querySalesMan")
    Observable<BaseBean> querySalesMan(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("name") String name,
                                       @Field("profitRatio") String profitRatio
    );

    /**
     * 业务员新增/修改
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param id          修改时必填
     * @param flag        新增：ADD,修改：UPDATE
     */
    @FormUrlEncoded
    @POST("card-gate-web/sales/operaSalesMan")
    Observable<BaseBean> operaSalesMan(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("id") String id,
                                       @Field("flag") String flag,
                                       @Field("name") String name,
                                       @Field("profitRatio") String profitRatio
    );

    /**
     * 业务员新增/修改
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param id          业务员id
     * @param name        业务员名称
     */
    @FormUrlEncoded
    @POST("card-gate-web/sales/deleteSalesMan")
    Observable<BaseBean> deleteSalesMan(@Field(Config.VERSION) String version,
                                        @Field(Config.REQUEST_NO) String requestNo,
                                        @Field(Config.MACHINECODE) String machineCode,
                                        @Field(Config.ACCOUNT) String account,
                                        @Field(Config.SIGNATURE) String signature,
                                        @Field("id") String id,
                                        @Field("name") String name
    );


    /**
     * 账单确认
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/bill")
    Observable<BaseBean> getBillConfirmList(@Field(Config.VERSION) String version,
                                            @Field(Config.REQUEST_NO) String requestNo,
                                            @Field(Config.MACHINECODE) String machineCode,
                                            @Field(Config.ACCOUNT) String account,
                                            @Field(Config.SIGNATURE) String signature,
                                            @Field("pageNum") String pageNum);

    /**
     * 还款账单
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/repaymsg")
    Observable<BaseBean> getRefundList(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("pageNum") String pageNum);

    /**
     * 商户管理
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/merchant/query")
    Observable<BaseBean> queryShanghu(@Field(Config.VERSION) String version,
                                      @Field(Config.REQUEST_NO) String requestNo,
                                      @Field(Config.MACHINECODE) String machineCode,
                                      @Field(Config.ACCOUNT) String account,
                                      @Field(Config.SIGNATURE) String signature,
                                      @Field("pageNum") String pageNum,
                                      @Field("channelName") String channelName,
                                      @Field("merchantName") String merchantName,
                                      @Field("merchantCode") String merchantCode,
                                      @Field("state") String state,
                                      @Field("merchantType") String merchantType,
                                      @Field("startDate") String startDate,
                                      @Field("endDate") String endDate,
                                      @Field("bind") String bind
    );

    /**
     * 商户删除
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature   /**
     *                    格式：
     *                    渠道号|商户号
     *                    多个的时候逗号隔开
     *                    例：
     *                    1|123,1|456,2|789
     */
    @FormUrlEncoded
    @POST("card-gate-web/merchant/unbind")
    Observable<BaseBean> unbindShanghu(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("merchant") String merchant);


    /**
     * 商户下載
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature   /**
     *                    格式：
     *                    渠道号|商户号
     *                    多个的时候逗号隔开
     *                    例：
     *                    1|123,1|456,2|789
     */
    @FormUrlEncoded
    @POST("card-gate-web/merchant/bind")
    Observable<BaseBean> bindShanghu(@Field(Config.VERSION) String version,
                                     @Field(Config.REQUEST_NO) String requestNo,
                                     @Field(Config.MACHINECODE) String machineCode,
                                     @Field(Config.ACCOUNT) String account,
                                     @Field(Config.SIGNATURE) String signature,
                                     @Field("merchant") String merchant);


    /**
     * 财务管理
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/report/query")
    Observable<BaseBean> queryReport(@Field(Config.VERSION) String version,
                                     @Field(Config.REQUEST_NO) String requestNo,
                                     @Field(Config.MACHINECODE) String machineCode,
                                     @Field(Config.ACCOUNT) String account,
                                     @Field(Config.SIGNATURE) String signature,
                                     @Field("pageNum") String pageNum,
                                     @Field("cardSeqno") String cardSeqno,
                                     @Field("cardNo") String cardNo,
                                     @Field("customerNmae") String customerNmae,
                                     @Field("salesMan") String salesMan,
                                     @Field("startDate") String startDate,
                                     @Field("endDate") String endDate
    );


    /**
     * 财务详情（规划查询）
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/query")
    Observable<BaseBean> queryPlan(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                   @Field("pageNum") String pageNum,
                                   @Field("cardNo") String cardNo,
                                   @Field("tranType") String tranType,
                                   @Field("channelId") String channelId);

    /**
     * （规划查询）
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/query")
    Observable<BaseBean> queryPlanning(@Field(Config.VERSION) String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature,
                                       @Field("pageNum") String pageNum,
                                       @Field("cardNo") String cardNo,
                                       @Field("tranType") String tranType,
                                       @Field("cardSeqno") String cardSeqno,
                                       @Field("customerName") String customerName,
                                       @Field("state") String state,
                                       @Field("accountType") String accountType,
                                       @Field("startDate") String startDate,
                                       @Field("endDate") String endDate,
                                       @Field("syncState") String syncState
    );

    /**
     * （规划查询）
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     续费提醒
     */
    @FormUrlEncoded
    @POST("card-gate-web/msg/expires")
    Observable<BaseBean> expiresMsg(@Field(Config.VERSION) String version,
                                    @Field(Config.REQUEST_NO) String requestNo,
                                    @Field(Config.MACHINECODE) String machineCode,
                                    @Field(Config.ACCOUNT) String account,
                                    @Field(Config.SIGNATURE) String signature,
                                    @Field("pageNum") String pageNum);


    /**
     * 查询卡片
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/card/queryCard")
    Observable<BaseBean> queryCard(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                   @Field("pageNum") String pageNum,
                                   @Field("cardNo") String cardNo,
                                   @Field("cardSeqno") String cardSeqno,
                                   @Field("salesMan") String salesMan,
                                   @Field("billDate") String billDate,
                                   @Field("repayDate") String repayDate,
                                   @Field("state") String state,
                                   @Field("count") String count
    );

    /**
     * 查询卡片
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/cardfee/query")
    Observable<BaseBean> queryCardfee(@Field(Config.VERSION) String version,
                                      @Field(Config.REQUEST_NO) String requestNo,
                                      @Field(Config.MACHINECODE) String machineCode,
                                      @Field(Config.ACCOUNT) String account,
                                      @Field(Config.SIGNATURE) String signature,
                                      @Field("pageNum") String pageNum,
                                      @Field("cardNo") String cardNo,
                                      @Field("startTime") String startTime
    );

    /**
     * 提额记录信息查询
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * @param pageNum     查询页数
     */
    @FormUrlEncoded
    @POST("card-gate-web/cardfee/queryLimitChange")
    Observable<BaseBean> queryLimitChange(@Field(Config.VERSION) String version,
                                          @Field(Config.REQUEST_NO) String requestNo,
                                          @Field(Config.MACHINECODE) String machineCode,
                                          @Field(Config.ACCOUNT) String account,
                                          @Field(Config.SIGNATURE) String signature,
                                          @Field("pageNum") String pageNum,
                                          @Field("cardNo") String cardNo,
                                          @Field("changeType") String changeType
    );

    /**
     * 添加提额记录信息
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/cardfee/insertLimitChange")
    Observable<BaseBean> insertLimitChange(@Field(Config.VERSION) String version,
                                           @Field(Config.REQUEST_NO) String requestNo,
                                           @Field(Config.MACHINECODE) String machineCode,
                                           @Field(Config.ACCOUNT) String account,
                                           @Field(Config.SIGNATURE) String signature,
                                           @Field("cardNo") String cardNo,
                                           @Field("time") String time,
                                           @Field("amt") String amt,
                                           @Field("createUser") String createUser,
                                           @Field("changeType") String changeType
    );

    /**
     * 查询账单
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/cardbill/queryCardBill")
    Observable<BaseBean> queryBill(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                   @Field("pageNum") String pageNum,
                                   @Field("cardNo") String cardNo,
                                   @Field("billMonth") String billMonth,
                                   @Field("available") String available
    );


    /**
<<<<<<< HEAD
     * 四要素验证
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     *
     * */
    @FormUrlEncoded
    @POST("card-gate-web/product/relCard")
    Observable<BaseBean> relCard(@Field(Config.VERSION)String version,
                                         @Field(Config.REQUEST_NO) String requestNo,
                                         @Field(Config.MACHINECODE) String machineCode,
                                         @Field(Config.ACCOUNT) String account,
                                         @Field(Config.SIGNATURE) String signature,
                                        @Field("cardNo") String cardNo,
                                        @Field("name") String name,
                                        @Field("identityCard") String identityCard,
                                        @Field("mobile") String mobile,
                                        @Field("cardMedia") String cardMedia);


    /**
     * 添加卡片
=======
     * 卡片详情
     *
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/card/queryCardDetail")
    Observable<BaseBean> queryCardDetail(@Field(Config.VERSION) String version,
                                         @Field(Config.REQUEST_NO) String requestNo,
                                         @Field(Config.MACHINECODE) String machineCode,
                                         @Field(Config.ACCOUNT) String account,
                                         @Field(Config.SIGNATURE) String signature,
                                         @Field("cardNo") String cardNo);

    /**
     * 添加卡片
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/card/insertCard")
    Observable<BaseBean> insertCard(@Field(Config.VERSION) String version,
                                    @Field(Config.REQUEST_NO) String requestNo,
                                    @Field(Config.MACHINECODE) String machineCode,
                                    @Field(Config.ACCOUNT) String account,
                                    @Field(Config.SIGNATURE) String signature,
                                    @Field("cardNo") String cardNo,
<<<<<<< HEAD
                                    @Field("cardSeqno")String cardSeqno,
                                    @Field("bankCode")String bankCode,
                                    @Field("customerName")String customerName,
                                    @Field("customerID")String customerID,
                                    @Field("phone")String phone,
                                    @Field("billDate")String billDate,
                                    @Field("repayDateType")String repayDateType,
                                    @Field("repayDate")String repayDate,
                                    @Field("salesMan")String salesMan,
                                    @Field("fixedLimit")String fixedLimit,
                                    @Field("availableAmt")String availableAmt,
                                    @Field("currentRepayAmt")String currentRepayAmt,
                                    @Field("initAmt")String initAmt,
=======
                                    @Field("cardSeqno") String cardSeqno,
                                    @Field("bankCode") String bankCode,
                                    @Field("customerName") String customerName,
                                    @Field("customerID") String customerID,
                                    @Field("phone") String phone,
                                    @Field("billDate") String billDate,
                                    @Field("repayDateType") String repayDateType,
                                    @Field("repayDate") String repayDate,
                                    @Field("salesMan") String salesMan,
                                    @Field("fixedLimit") String fixedLimit,
                                    @Field("availableAmt") String availableAmt,
                                    @Field("initAmt") String initAmt,
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
                                    @Field("tempLimit") String tempLimit,
                                    @Field("tempLimitDate") String tempLimitDate,
                                    @Field("isHolidayPlan") String isHolidayPlan,
                                    @Field("isFreePlan") String isFreePlan,
                                    @Field("freePlanRate") String freePlanRate,
                                    @Field("serviceType") String serviceType,
                                    @Field("serviceAmt") String serviceAmt,
                                    @Field("serviceRate") String serviceRate,
                                    @Field("serviceStartDate") String serviceStartDate,
                                    @Field("serviceEndDate") String serviceEndDate,
                                    @Field("paidAmt") String paidAmt,
                                    @Field("ebankinfo") String ebankinfo,
                                    @Field("stagesList") String stagesList,
                                    @Field("cardMedia") String cardMedia);

    /**
     * 添加交易记录
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/plan/add")
    Observable<BaseBean> addPlan(@Field(Config.VERSION) String version,
                                 @Field(Config.REQUEST_NO) String requestNo,
                                 @Field(Config.MACHINECODE) String machineCode,
                                 @Field(Config.ACCOUNT) String account,
                                 @Field(Config.SIGNATURE) String signature,
                                 @Field("tranType") String tranType,
                                 @Field("cardNo") String cardNo,
                                 @Field("channel") String channel,
                                 @Field("merchantCode") String merchantCode,
                                 @Field("termCode") String termCode,
                                 @Field("amt") String amt,
                                 @Field("isNeedT0") String isNeedT0,
                                 @Field("date") String date,
                                 @Field("state") String state);

    /**
     * 修改卡片状态
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/card/updateCardState")
    Observable<BaseBean> updateCardState(@Field(Config.VERSION) String version,
                                         @Field(Config.REQUEST_NO) String requestNo,
                                         @Field(Config.MACHINECODE) String machineCode,
                                         @Field(Config.ACCOUNT) String account,
                                         @Field(Config.SIGNATURE) String signature,
                                         @Field("cardNo") String cardNo,
                                         @Field("state") String state);

    /**
     * 字典同步
     *
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     */
    @FormUrlEncoded
    @POST("card-gate-web/dict/query")
<<<<<<< HEAD
    Observable<BaseBean> queryDict(@Field(Config.VERSION)String version,
                                         @Field(Config.REQUEST_NO) String requestNo,
                                         @Field(Config.MACHINECODE) String machineCode,
                                         @Field(Config.ACCOUNT) String account,
                                         @Field(Config.SIGNATURE) String signature);

    /**
     * 卡片續期
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     *
     * */
    @FormUrlEncoded
    @POST("card-gate-web/card/renewalCard")
    Observable<BaseBean> renewalCard(@Field(Config.VERSION)String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature,
                                    @Field("cardNo") String cardNo,
                                     @Field("serviceEndDate") String serviceEndDate,
                                     @Field("serviceType") String serviceType,
                                     @Field("serviceAmt") String serviceAmt,
                                     @Field("serviceRate") String serviceRate,
                                     @Field("paidAmt") String paidAmt,
                                     @Field("fixedLimit") String fixedLimit,
                                     @Field("currentRepayAmt") String currentRepayAmt,
                                     @Field("initAmt") String initAmt,
                                     @Field("serviceStartDate") String serviceStartDate,
                                     @Field("availableAmt") String availableAmt,
                                     @Field("state") String state);

    /**
     * 續期提醒
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     *
     * */
    @FormUrlEncoded
    @POST("card-gate-web/msg/expires")
    Observable<BaseBean> expiresMsg(@Field(Config.VERSION)String version,
                                     @Field(Config.REQUEST_NO) String requestNo,
                                     @Field(Config.MACHINECODE) String machineCode,
                                     @Field(Config.ACCOUNT) String account,
                                     @Field(Config.SIGNATURE) String signature,
                                     @Field("pageNum") String pageNum);

    /**
     *规划查询
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     *version, requestNo, machineCode, account, signature, pageNum,cardNo,tranType,
    cardSeqno,customerName,state,accountType,startDate,endDate,syncState
     * */
    @FormUrlEncoded
    @POST("card-gate-web/plan/query")
    Observable<BaseBean> queryPlanning(@Field(Config.VERSION)String version,
                                    @Field(Config.REQUEST_NO) String requestNo,
                                    @Field(Config.MACHINECODE) String machineCode,
                                    @Field(Config.ACCOUNT) String account,
                                    @Field(Config.SIGNATURE) String signature,
                                    @Field("pageNum") String pageNum,
                                       @Field("cardNo") String cardNo,
                                       @Field("tranType") String tranType,
                                       @Field("cardSeqno") String cardSeqno,
                                       @Field("customerName") String customerName,
                                       @Field("state") String state,
                                       @Field("accountType") String accountType,
                                       @Field("startDate") String startDate,
                                       @Field("endDate") String endDate,
                                       @Field("syncState") String syncState,
                                       @Field("count") String count);

    /**
     *同步规划
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature
     * */
    @FormUrlEncoded
    @POST("card-gate-web/plan/sync")
    Observable<BaseBean> syncPlan(@Field(Config.VERSION)String version,
                                       @Field(Config.REQUEST_NO) String requestNo,
                                       @Field(Config.MACHINECODE) String machineCode,
                                       @Field(Config.ACCOUNT) String account,
                                       @Field(Config.SIGNATURE) String signature);

=======
    Observable<BaseBean> queryDict(@Field(Config.VERSION) String version,
                                   @Field(Config.REQUEST_NO) String requestNo,
                                   @Field(Config.MACHINECODE) String machineCode,
                                   @Field(Config.ACCOUNT) String account,
                                   @Field(Config.SIGNATURE) String signature);
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec

    /**
     * @param version
     * @param requestNo
     * @param machineCode
     * @param account
     * @param signature   //修改密码
     */
    @FormUrlEncoded
    @POST("card-gate-web/password/modify")
    Observable<BaseBean> modifyPassword(@Field(Config.VERSION) String version,
                                        @Field(Config.REQUEST_NO) String requestNo,
                                        @Field(Config.MACHINECODE) String machineCode,
                                        @Field(Config.ACCOUNT) String account,
                                        @Field(Config.SIGNATURE) String signature,
                                        @Field("password") String password,
                                        @Field("newPassword") String newPassword);
}