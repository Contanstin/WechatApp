package com.hpmont.domain.wechat;

import java.util.Date;

public class ServiceManual {
    private Integer id;

    private String manualName;

    private String realName;

    private String name;

    private Byte manualType;

    private String manualFormat;

    private String manualUrl;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManualName() {
        return manualName;
    }

    public void setManualName(String manualName) {
        this.manualName = manualName == null ? null : manualName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Byte getManualType() {
        return manualType;
    }

    public void setManualType(Byte manualType) {
        this.manualType = manualType;
    }

    public String getManualFormat() {
        return manualFormat;
    }

    public void setManualFormat(String manualFormat) {
        this.manualFormat = manualFormat == null ? null : manualFormat.trim();
    }

    public String getManualUrl() {
        return manualUrl;
    }

    public void setManualUrl(String manualUrl) {
        this.manualUrl = manualUrl == null ? null : manualUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}