package com.rflash.magiccube.ui.cardmanager.cardbase;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.addcard.PayBean;
import com.rflash.magiccube.ui.addcard.ProductDetailBean;
import com.rflash.magiccube.ui.addcard.VerfyBean;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/27.
 */

public class CardBaseInfoPresenter extends BasePresenterImpl<CardBaseInfoContract.View> implements CardBaseInfoContract.Presenter {

    //卡片详情
    @Override
    public void queryCardDetail(String cardNo) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        String pointId = SpUtil.getString(mView.getContext(), Config.POINT_ID, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("cardNo", cardNo);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> cardbaseinfo = RetrofitFactory.getApiService().queryCardDetail(version, requestNo, machineCode, account, signature, cardNo);
            Observable<BaseBean> compose = cardbaseinfo.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseInfoBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess(BaseInfoBean data) {
                    mView.getDataSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //四要素验证
    @Override
    public void relCard(String cardNo, String name, String identityCard, String mobile, String cardMedia) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        String pointId = SpUtil.getString(mView.getContext(), Config.POINT_ID, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("cardNo", cardNo);
        treeMap.put("name", name);
        treeMap.put("identityCard", identityCard);
        treeMap.put("mobile", mobile);
        treeMap.put("cardMedia", cardMedia);


        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> addCard = RetrofitFactory.getApiService().relCard(version, requestNo, machineCode, account, signature,
                    cardNo, name, identityCard, mobile, cardMedia);
            Observable<BaseBean> compose = addCard.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<VerfyBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess(VerfyBean bean) {
                    mView.verifyResult(bean);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //修改卡片信息
    @Override
    public void updateCard(String cardNo, String customerName, String customerID, String phone, String billDate,
                           String repayDateType, String repayDate, String salesMan, String tempLimit,
                           String tempLimitDate, String isHolidayPlan, String isFreePlan, String freePlanRate,String serviceRate, String ebankinfo, String stagesList, String cardMedia) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        String pointId = SpUtil.getString(mView.getContext(), Config.POINT_ID, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("cardNo", cardNo);
        treeMap.put("customerName", customerName);
        treeMap.put("customerID", customerID);
        treeMap.put("phone", phone);
        treeMap.put("billDate", billDate);
        treeMap.put("repayDateType", repayDateType);
        treeMap.put("repayDate", repayDate);
        treeMap.put("salesMan", salesMan);
        treeMap.put("tempLimit", tempLimit);
        treeMap.put("tempLimitDate", tempLimitDate);
        treeMap.put("isHolidayPlan", isHolidayPlan);
        treeMap.put("isFreePlan", isFreePlan);
        treeMap.put("freePlanRate", freePlanRate);
        treeMap.put("serviceRate",serviceRate);
        treeMap.put("ebankinfo", ebankinfo);
        treeMap.put("stagesList", stagesList);
        treeMap.put("cardMedia", cardMedia);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> addCard = RetrofitFactory.getApiService().updateCard(version, requestNo, machineCode, account, signature,
                    cardNo, customerName,
                    customerID, phone, billDate, repayDateType,
                    repayDate, salesMan, tempLimit, tempLimitDate, isHolidayPlan,
                    isFreePlan, freePlanRate,serviceRate, ebankinfo, stagesList, cardMedia);
            Observable<BaseBean> compose = addCard.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess() {
                    mView.updataSuccess();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //购买征信产品
    @Override
    public void productBuy(String creditType, String num, String payType) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        String pointId = SpUtil.getString(mView.getContext(), Config.POINT_ID, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("creditType", creditType);
        treeMap.put("num", num);
        treeMap.put("payType", payType);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> addCard = RetrofitFactory.getApiService().productBuy(version, requestNo, machineCode, account, signature,
                    creditType, num, payType);
            Observable<BaseBean> compose = addCard.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<PayBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess(PayBean bean) {
                    mView.toPayResult(bean);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询征信产品详细信息
    @Override
    public void productDetail(String creditType) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        String pointId = SpUtil.getString(mView.getContext(), Config.POINT_ID, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("creditType", creditType);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> addCard = RetrofitFactory.getApiService().queryProductDetail(version, requestNo, machineCode, account, signature,
                    creditType);
            Observable<BaseBean> compose = addCard.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<ProductDetailBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }

                @Override
                protected void onSuccess(ProductDetailBean bean) {
                    mView.queryProductDetailResult(bean);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
