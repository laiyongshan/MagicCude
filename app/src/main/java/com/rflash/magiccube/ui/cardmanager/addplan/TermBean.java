package com.rflash.magiccube.ui.cardmanager.addplan;

import com.rflash.magiccube.http.BaseBean;

/**
 * @author lys
 * @time 2018/11/22 16:25
 * @desc:
 */

public class TermBean extends BaseBean {
        String termCode;

        public void setTermCode(String termCode) {
                this.termCode = termCode;
        }

        public String getTermCode() {
                return termCode;
        }
}
