package com.rflash.basemodule;

import android.content.Context;

/**
 * Created by yangfan on 2017/11/27.
 */

public interface DefaultView {
    void showProgress();
    void dismissProgress();
    boolean isDismiss();

    Context getContext();
}
