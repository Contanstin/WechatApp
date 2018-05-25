package com.hpmont.domain.manual.authority;

import com.hpmont.util.DateUtil;

import java.util.Date;

/**
 * Created by fanlinlin on 2016/11/17.
 */
public class AllUser {
    private Long id;

    private String userName;

    private Long roleId;

    private String realName;

    private String department;

    private Boolean isEnable;

    private Date createTime;

    private Date modifyTime;

    private String password;

    private String rolename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getCreateTime() {
        return DateUtil.getDateStr(this.createTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return DateUtil.getDateStr(this.modifyTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
