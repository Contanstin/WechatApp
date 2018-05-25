package com.hpmont.dao.mapper;

import com.github.pagehelper.Page;
import com.hpmont.domain.search.SearchPhoneAuth;
import com.hpmont.domain.phone.PhoneAuth;
import com.hpmont.domain.phone.PhoneAuthAdmin;
import com.hpmont.domain.phone.PhoneAuthCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhoneAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneAuth record);

    int updateByPrimaryKeySelective(PhoneAuth record);

    Page<PhoneAuth> findPhoneAuthList(SearchPhoneAuth search);

    int insertPhoneAuthAdmin(PhoneAuthAdmin phoneAuthAdmin);

    int deleteByAuthId(Integer id);

    List<PhoneAuthCodes> findAuthCodes(Integer adminId);

    int deleteByAdminId(Integer id);

    List<PhoneAuth> findByVersionType(@Param("versionType") Integer versionType, @Param("adminId") Integer adminId);
}