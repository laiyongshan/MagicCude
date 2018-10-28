package com.rflash.basemodule.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yangfan on 2017/11/24.
 */

public class AssetsUtil {


    public static String readAssetsTxt(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "utf-8");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }
}
