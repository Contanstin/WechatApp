package com.hpmont.service.adminuser.Impl;

import com.hpmont.dao.mapper.AdminRoleMapper;
import com.hpmont.dao.mapper.AdminUserMapper;
import com.hpmont.dao.mapper.AdminUserRoleMapper;
import com.hpmont.domain.manual.admin.AdminUser;
import com.hpmont.domain.manual.admin.AdminUserRoleKey;
import com.hpmont.domain.manual.authority.AdminRoleList;
import com.hpmont.domain.manual.authority.AllUser;
import com.hpmont.service.adminuser.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    private AdminUserMapper adminUserDao;
    @Autowired
    private AdminUserRoleMapper adminUserRoleDao;
    @Autowired
    private AdminRoleMapper roleDao;

    @Override
    public AdminUser getUserByName(String userName) {
        return adminUserDao.selectByName(userName);
    }

    @Override
    public List<String> tgetUserAuthority(Long userId) {
        List<Long> roles= adminUserDao.selectRoleByUserId(userId);
        if(roles!=null && roles.size()>0) {
            return adminUserRoleDao.selectAuthorityByRoleId(roles);
        }else {
            return null;
        }
    }

    @Override
    public List<AllUser> getUserList() {
        return adminUserDao.selectAllUser();
    }

    @Override
    public int addUser(AdminUser record,Long roleid,String [] authCodeIds) {
        adminUserDao.insert(record);
        AdminUserRoleKey aurk=new AdminUserRoleKey();
        aurk.setAdminId(record.getId());
        aurk.setRoleId(roleid);
        return adminUserRoleDao.insert(aurk);
    }

    @Override
    public int updateUser(AdminUser record,Long roleid,String [] authCodeIds) {
        if (record.getId()==1){
            return 0;
        }
        adminUserRoleDao.deleteByUserId(record.getId());
        AdminUserRoleKey aurk=new AdminUserRoleKey();
        aurk.setAdminId(record.getId());
        aurk.setRoleId(roleid);
        adminUserRoleDao.insert(aurk);
        return adminUserDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteUser(Long id) {
        if (id==1){
            return 0;
        }
        adminUserRoleDao.deleteByUserId(id);
        return adminUserDao.deleteByPrimaryKey(id);
    }

    /**
     * 查询角色列表
     *
     * @return
     */
    @Override
    public List<AdminRoleList> selectAdminRoleList() {
        return roleDao.selectAdminRoleList();
    }

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public AdminUser getAdminUser(Long userId) {
        return adminUserDao.selectByPrimaryKey(userId);
    }

    /**
     * 获取用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public AdminUserRoleKey getUserRole(Long userId) {
        return adminUserRoleDao.selectByuserId(userId);
    }

    /**
     * 更新用户密码
     *
     * @param userinfo
     * @return
     */
    @Override
    public int changePassword(AdminUser userinfo) {
        return adminUserDao.updateByPrimaryKeySelective(userinfo);
    }
}
