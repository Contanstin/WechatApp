package com.hpmont.dao.mapper;

import java.util.List;

import com.hpmont.domain.manual.admin.AdminUserRoleKey;

public interface AdminUserRoleMapper {

	int insert(AdminUserRoleKey record);

	List<String> selectAuthorityByRoleId(List<Long> roleIds);

	AdminUserRoleKey selectByuserId(Long userId);

	int deleteByUserId(Long userId);

	int countByRoleId(Integer id);
}