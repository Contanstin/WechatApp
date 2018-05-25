package com.hpmont.dao.mapper;

import com.hpmont.domain.manual.admin.AdminRoleAuthority;

public interface AdminRoleAuthorityMapper {
	
	int deleteByPrimaryKey(Long id);

	int insert(AdminRoleAuthority record);

	/**
	 * 通过角色id删除权限
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Integer roleId);
}