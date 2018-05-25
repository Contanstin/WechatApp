package com.hpmont.dao.mapper;


import com.hpmont.domain.phone.PhoneUUID;

public interface PhoneUUIDMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneUUID record);

    int updateByPrimaryKeySelective(PhoneUUID record);

    boolean isExistUUID(PhoneUUID record);

    int getUUIDCount(String tempPhone);
}