package com.hpmont.domain.phone;

public class PhoneUserAdmin {
    private Integer id;

    private Integer adminId;

    private Integer phoneUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getPhoneUserId() {
        return phoneUserId;
    }

    public void setPhoneUserId(Integer phoneUserId) {
        this.phoneUserId = phoneUserId;
    }

    public PhoneUserAdmin(Integer adminId, Integer phoneUserId) {
        this.adminId = adminId;
        this.phoneUserId = phoneUserId;
    }

    public PhoneUserAdmin() {

    }
}