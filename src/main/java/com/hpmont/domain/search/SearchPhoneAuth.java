package com.hpmont.domain.search;

import com.hpmont.domain.page.PageSearch;

/**
 * Created by 徐 on 2018/5/17.
 */
public class SearchPhoneAuth extends PageSearch{

    private Integer adminId;

    private String versionName;

    private String versionType;

    private String authCode;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }
}
