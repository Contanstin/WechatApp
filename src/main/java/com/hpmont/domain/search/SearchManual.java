package com.hpmont.domain.search;

import com.hpmont.domain.page.PageSearch;

/**
 * Created by 徐 on 2018/5/30.
 */
public class SearchManual extends PageSearch{

    private String manualName;

    private Integer manualType;

    private Integer versionType;

    public String getManualName() {
        return manualName;
    }

    public void setManualName(String manualName) {
        this.manualName = manualName;
    }

    public Integer getManualType() {
        return manualType;
    }

    public void setManualType(Integer manualType) {
        this.manualType = manualType;
    }

    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
    }
}
