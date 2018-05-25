package com.hpmont.domain.manual;

import com.hpmont.domain.manual.admin.AdminMenu;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public class TreeAdminMenu {
    private Long id;

    private String menuName;

    private String menuUrl;

    private Long parentId;

    private Integer orderNum;

    private Integer menuType;

    private String authorities;

    private String description;

    private List<AdminMenu> submenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AdminMenu> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<AdminMenu> submenu) {
        this.submenu = submenu;
    }
}
