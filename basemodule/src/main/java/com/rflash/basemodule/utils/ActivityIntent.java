package com.rflash.basemodule.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by yangfan on 2017/10/30.
 *  Activity跳转工具类。
 *
 */

public class ActivityIntent  {



    public static void readyGo(Context context,Class cls, Bundle bundle){
        Intent intent = new Intent(context,cls);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * Activity跳转。
     * @param context
     * @param cls
     */
    public static void readyGo(Context context,Class cls){
        readyGo(context,cls,null);
    }




}
