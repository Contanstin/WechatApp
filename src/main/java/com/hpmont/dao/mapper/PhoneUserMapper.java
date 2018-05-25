package com.hpmont.dao.mapper;

import com.github.pagehelper.Page;
import com.hpmont.domain.phone.PhoneUser;
import com.hpmont.domain.phone.PhoneUserAdmin;
import com.hpmont.domain.page.PageSearch;
import org.apache.ibatis.annotations.Param;

public interface PhoneUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneUser record);

    int updateByPrimaryKeySelective(PhoneUser record);

    Page<PhoneUser> findPhoneList(PageSearch search);

    PhoneUser getUserPhone(@Param(value = "tempPhone") String tempPhone);

    int insertPhoneUserAdmin(PhoneUserAdmin phoneUserAdmin);

    int updataPW(@Param(value = "phone")String phone, @Param(value = "password")String password);
}