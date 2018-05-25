package com.hpmont.dao.mapper;

import com.hpmont.domain.manual.admin.AdminRole;
import com.hpmont.domain.manual.role.RoleAuth;

import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */
public interface AdminRoleManageMapper {
    /**
     * 查询角色和权限
     * @param id
     * @return
     */
    RoleAuth selectRole(Integer id);

    /**
     * 查询所有角色
     * @return
     */
    List<AdminRole> selectAllRole();
}
