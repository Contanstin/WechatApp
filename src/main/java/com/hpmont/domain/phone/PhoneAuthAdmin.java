package com.hpmont.domain.phone;

public class PhoneAuthAdmin {
    private Integer id;

    private Integer authId;

    private Integer adminId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public PhoneAuthAdmin() {
    }

    public PhoneAuthAdmin(Integer authId, Integer adminId) {
        this.authId = authId;
        this.adminId = adminId;
    }
}