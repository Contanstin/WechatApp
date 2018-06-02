package com.hpmont.domain.search;

import com.hpmont.domain.page.PageSearch;

/**
 * Created by 徐 on 2018/5/30.
 */
public class SearchFault extends PageSearch{

    private String faultName;

    private String faultCode;

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public String getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }
}
