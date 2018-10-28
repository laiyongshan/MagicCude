package com.rflash.basemodule.permission;

import java.util.List;

/**
 * Created by yangfan on 2017/10/30.
 */

public interface PermissionListener {

    //授权
    void onGanted();
    //未授权
    void onDenied(List<String> deniedPermission);
}
