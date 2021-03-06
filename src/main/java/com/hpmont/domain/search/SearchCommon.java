package com.hpmont.domain.search;

import com.hpmont.domain.page.PageSearch;

/**
 * Created by 徐 on 2018/6/25.
 */
public class SearchCommon  extends PageSearch {


    private Integer languageType;

    private Integer departmentType;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Integer languageType) {
        this.languageType = languageType;
    }

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
    }
}
