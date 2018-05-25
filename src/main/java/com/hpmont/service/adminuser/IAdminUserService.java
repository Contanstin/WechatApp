package com.hpmont.service.adminuser;

import com.hpmont.domain.manual.admin.AdminUser;
import com.hpmont.domain.manual.admin.AdminUserRoleKey;
import com.hpmont.domain.manual.authority.AdminRoleList;
import com.hpmont.domain.manual.authority.AllUser;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
public interface IAdminUserService {
    AdminUser getUserByName(String userName);

    List<String> tgetUserAuthority(Long userId);

    //查询所有用户信息
    List<AllUser> getUserList();

    //新增用户
    int addUser(AdminUser record,Long roleid,String [] authCodeIds);

    //更新用户信息
    int updateUser(AdminUser record,Long roleid,String [] authCodeIds);

    //删除用户
    int deleteUser(Long id);

    /**
     * 查询角色列表
     * @return
     */
    List<AdminRoleList> selectAdminRoleList();

    /**
     * 查询用户
     * @param userId
     * @return
     */
    AdminUser getAdminUser(Long userId);

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    AdminUserRoleKey getUserRole(Long userId);

    /**
     * 更新用户密码
     * @param userinfo
     * @return
     */
    int changePassword(AdminUser userinfo);


}
