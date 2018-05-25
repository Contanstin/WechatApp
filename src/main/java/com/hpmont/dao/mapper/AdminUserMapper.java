package com.hpmont.dao.mapper;

import java.util.List;

import com.hpmont.domain.manual.admin.AdminUser;
import com.hpmont.domain.manual.authority.AllUser;

public interface AdminUserMapper {

	int deleteByPrimaryKey(Long id);

	int insert(AdminUser record);

	AdminUser selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AdminUser record);

	int updateByPrimaryKey(AdminUser record);

	AdminUser selectByName(String userName);

	List<Long> selectRoleByUserId(Long userId);

	List<AllUser> selectAllUser();
}