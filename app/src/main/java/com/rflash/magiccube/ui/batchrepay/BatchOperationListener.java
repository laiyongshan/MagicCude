package com.rflash.magiccube.ui.batchrepay;

/**
 * Created by yangfan on 2017/11/21.
 */

public interface BatchOperationListener {

    /**
     * 还款
     * @param position
     */
    void onRepay(int position);

    /**
     * 代付
     * @param position
     */
    void onHelpPay(int position);

    /**
     * checkbox 改变监听
     */
    void onCheckListener(int position, boolean checked);
}

