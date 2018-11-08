package com.rflash.magiccube.ui.operationtoday;

import com.rflash.magiccube.ui.presentoperation.OperationItem;

/**
 * Created by yangfan on 2017/11/17.
 */

public interface ConsumeClick extends AdapterItemClick{

    void onConsumeClick(OperationItem operationItem, int position);
    void onRepay(OperationItem operationItem, int position);
}
