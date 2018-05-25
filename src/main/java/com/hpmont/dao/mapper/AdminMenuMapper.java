package com.hpmont.dao.mapper;

import com.hpmont.domain.manual.admin.AdminMenu;
import com.hpmont.domain.manual.ShowAdminMenu;
import com.hpmont.domain.manual.TreeAdminMenu;
import com.hpmont.domain.manual.authority.UserAuthority;
import com.hpmont.util.page.Page;

import java.util.List;

public interface AdminMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminMenu record);

    AdminMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminMenu record);

    //查询菜单
    List<ShowAdminMenu> selectMenu();

    /**
     * 查询一级菜单列表
     * @return
     */
    List<ShowAdminMenu> selectFirstMenus();

    //分页查询用户菜单权限
    Page<UserAuthority> selectAuthority(UserAuthority userAuthority, int currpage, int prePageRows);

    /**
     * 查询树形列表
     * @return
     */
    List<TreeAdminMenu> selectTreeMenu();

    List<TreeAdminMenu> selectByRoleId(Long roleId);
}