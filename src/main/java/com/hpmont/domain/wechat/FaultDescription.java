package com.hpmont.domain.wechat;

import java.util.Date;

public class FaultDescription {
    private Integer id;

    private String faultName;

    private String faultCode;

    private String faultImplication;

    private String faultSolution;

    private String versionName;

    private Integer versionType;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName == null ? null : faultName.trim();
    }

    public String getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode == null ? null : faultCode.trim();
    }

    public String getFaultImplication() {
        return faultImplication;
    }

    public void setFaultImplication(String faultImplication) {
        this.faultImplication = faultImplication == null ? null : faultImplication.trim();
    }

    public String getFaultSolution() {
        return faultSolution;
    }

    public void setFaultSolution(String faultSolution) {
        this.faultSolution = faultSolution == null ? null : faultSolution.trim();
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

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
    }
}