package com.hpmont.domain.wechat;

import java.util.Date;

public class WechatManual {
    private Integer id;

    private String manualName;

    private String realName;

    private Integer manualType;

    private String name;

    private String manualFormat;

    private String manualUrl;

    private Integer versionType;

    private String versionName;

    private Integer languageType;

    private Integer departmentType;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer IsRecommend;

    private Integer downloadCount;

    private String imageUrl;
    private String imageName;
    private String videoUrl;
    private Integer orderNum;

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
        this.manualName = manualName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getManualType() {
        return manualType;
    }

    public void setManualType(Integer manualType) {
        this.manualType = manualType;
    }

    public String getManualFormat() {
        return manualFormat;
    }

    public void setManualFormat(String manualFormat) {
        this.manualFormat = manualFormat;
    }

    public String getManualUrl() {
        return manualUrl;
    }

    public void setManualUrl(String manualUrl) {
        this.manualUrl = manualUrl;
    }

    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsRecommend() {
        return IsRecommend;
    }
    public void setIsRecommend(Integer IsRecommend) {
        this.IsRecommend = IsRecommend;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public Integer getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

}