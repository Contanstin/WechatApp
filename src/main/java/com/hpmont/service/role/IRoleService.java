package com.hpmont.service.role;

import com.hpmont.domain.manual.admin.AdminRole;
import com.hpmont.domain.manual.role.RoleAuth;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public interface IRoleService {
    /**
     * 查询角色和权限
     * @param roleId
     * @return
     */
    RoleAuth getRole(Integer roleId);

    /**
     * 查询所有角色
     * @return
     */
    List<AdminRole> getAllRole();

    /**
     * 保存用户权限
     * @param role
     * @param auths
     */
    void saveRole(RoleAuth role,String[] auths);

    void delete(Integer id);
}
