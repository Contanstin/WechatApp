package com.hpmont.dao.mapper;

import com.hpmont.domain.manual.admin.AdminRole;
import com.hpmont.domain.manual.authority.AdminRoleList;

import java.util.List;

public interface AdminRoleMapper {

    int insert(AdminRole record);
    
    int updateByPrimaryKeySelective(AdminRole record);

    List<AdminRoleList> selectAdminRoleList();

    int deleteByPrimaryKey(Integer id);
}