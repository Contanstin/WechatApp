package com.hpmont.domain.phone;

import java.util.List;

public class PhoneAuthCodes {
    private Integer id;

    private String versionName;

    private List<PhoneAuth> authCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public List<PhoneAuth> getAuthCodes() {
        return authCodes;
    }

    public void setAuthCodes(List<PhoneAuth> authCodes) {
        this.authCodes = authCodes;
    }
}