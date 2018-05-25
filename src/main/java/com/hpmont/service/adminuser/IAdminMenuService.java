package com.hpmont.service.adminuser;

import java.util.List;

import com.hpmont.domain.manual.admin.AdminMenu;
import com.hpmont.domain.manual.ShowAdminMenu;
import com.hpmont.domain.manual.TreeAdminMenu;
import com.hpmont.domain.manual.authority.AdminRoleList;
import com.hpmont.domain.manual.authority.UserAuthority;
import com.hpmont.util.page.Page;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface IAdminMenuService {

    List<TreeAdminMenu> searchMenuByRoleId(Long roleId);

    //菜单管理
    List<ShowAdminMenu> showMenu(Long userId);
    int updateMenu(AdminMenu record);
    int deleteMenu(Long id);
    int addMenu(AdminMenu record);

    //分页查询用户菜单权限
    Page<UserAuthority> selectUserAuthority(UserAuthority userAuthority, int prePageRows);

    //删除用户对某菜单的权限
    int deleteAuthority(Long id);

    //添加用户对某菜单的权限
    int addAuthority(List<Long> menuIds,Long roleId);

    //显示权限列表
    List<AdminRoleList> getAdminRoleList();

    /**
     * 树形菜单查询列表
     * @return
     */
    List<TreeAdminMenu> searchTreeMenu();
}
