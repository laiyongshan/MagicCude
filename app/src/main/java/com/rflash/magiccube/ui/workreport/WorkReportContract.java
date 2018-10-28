package com.rflash.magiccube.ui.workreport;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class WorkReportContract {
    interface View extends BaseView {
        void getThreeDayCount(ThreeDayCount threeDayCount);
    }

    interface  Presenter extends BasePresenter<View> {
        void threeDayCount();
    }
}
