package com.rflash.magiccube.ui.presentoperation;

/**
 * Created by yangfan on 2018/1/11.
 */

public interface AdapterItemOperation {

    //消费或者还款
    void operation(OperationItem operationItem);

    //只选择这张卡
    void selectTheCard(OperationItem operationItem);

    //长按
    void longClickItem(OperationItem operationItem);
}
