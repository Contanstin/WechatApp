package com.hpmont.service.adminuser.Impl;

import com.hpmont.dao.mapper.AdminMenuMapper;
import com.hpmont.dao.mapper.AdminRoleAuthorityMapper;
import com.hpmont.dao.mapper.AdminRoleMapper;
import com.hpmont.domain.manual.admin.AdminMenu;
import com.hpmont.domain.manual.admin.AdminRoleAuthority;
import com.hpmont.domain.manual.ShowAdminMenu;
import com.hpmont.domain.manual.TreeAdminMenu;
import com.hpmont.domain.manual.authority.AdminRoleList;
import com.hpmont.domain.manual.authority.UserAuthority;
import com.hpmont.service.adminuser.IAdminMenuService;
import com.hpmont.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("adminMenuServiceImpl")
public class AdminMenuServiceImpl implements IAdminMenuService {
    @Autowired
    private AdminMenuMapper menuDao;
    @Autowired
    private AdminRoleAuthorityMapper roleAuthorityDao;
    @Autowired
    private AdminRoleMapper adminRoleDao;

    @Override
    public List<TreeAdminMenu> searchMenuByRoleId(Long roleId) {
        return menuDao.selectByRoleId(roleId);
    }

    @Override
    public List<ShowAdminMenu> showMenu(Long userId) {
        if (userId==1){
            return menuDao.selectMenu();
        }else{
            return null;
        }
    }

    @Override
    public int updateMenu(AdminMenu record) {
        return menuDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteMenu(Long id) {
        return menuDao.deleteByPrimaryKey(id);
    }

    @Override
    public int addMenu(AdminMenu record) {
        if(menuDao.insert(record)==1){
            AdminRoleAuthority adminRoleAuthority = new AdminRoleAuthority();
            adminRoleAuthority.setRoleId(1);
            adminRoleAuthority.setAuthorities(record.getAuthorities());
            adminRoleAuthority.setMenuId(record.getId());
            roleAuthorityDao.insert(adminRoleAuthority);
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Page<UserAuthority> selectUserAuthority(UserAuthority userAuthority, int prePageRows) {
        return menuDao.selectAuthority(userAuthority,userAuthority.getCurrpage(),prePageRows);
    }

    @Override
    public int deleteAuthority(Long id) {
        return roleAuthorityDao.deleteByPrimaryKey(id);
    }

    @Override
    public int addAuthority(List<Long> menuIds,Long roleId) {
        int r=0;
        for(int i=0;i<menuIds.size();i++){
            AdminRoleAuthority adminRoleAuthority = new AdminRoleAuthority();
            AdminMenu adminMenu=menuDao.selectByPrimaryKey(menuIds.get(i));
            adminRoleAuthority.setAuthorities(adminMenu.getAuthorities());
            adminRoleAuthority.setMenuId(menuIds.get(i));
            adminRoleAuthority.setRoleId(roleId.intValue());
            if(roleAuthorityDao.insert(adminRoleAuthority)==1){
                r=1;
            }else{
                r=0;
            }
        }
        return r;
    }

    @Override
    public List<AdminRoleList> getAdminRoleList() {
        return adminRoleDao.selectAdminRoleList();
    }

    @Override
    public List<TreeAdminMenu> searchTreeMenu() {
        return menuDao.selectTreeMenu();
    }

}
