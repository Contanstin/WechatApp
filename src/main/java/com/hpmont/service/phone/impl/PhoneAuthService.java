package com.hpmont.service.phone.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.PhoneAuthMapper;
import com.hpmont.domain.search.SearchPhoneAuth;
import com.hpmont.domain.phone.PhoneAuth;
import com.hpmont.domain.phone.PhoneAuthAdmin;
import com.hpmont.domain.phone.PhoneAuthCodes;
import com.hpmont.service.phone.IPhoneAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/5/17.
 */
@Service("PhoneAuthService")
public class PhoneAuthService implements IPhoneAuthService{

    @Autowired
    private PhoneAuthMapper phoneAuthDao;


    @Override
    public PageInfo<PhoneAuth> findPhoneAuthList(SearchPhoneAuth search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<PhoneAuth> list = phoneAuthDao.findPhoneAuthList(search);
        PageInfo<PhoneAuth> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void insertPhoneAuth(PhoneAuth phoneAuth) {
        phoneAuthDao.insert(phoneAuth);
        phoneAuthDao.insertPhoneAuthAdmin(new PhoneAuthAdmin(phoneAuth.getId(),phoneAuth.getAdminId()));
    }

    @Override
    public void updatePhoneAuth(PhoneAuth phoneAuth) {
        phoneAuthDao.updateByPrimaryKeySelective(phoneAuth);
    }

    @Override
    public void deletePhoneAuth(Integer id) {
        phoneAuthDao.deleteByPrimaryKey(id);
        phoneAuthDao.deleteByAuthId(id);
    }

    @Override
    public List<PhoneAuthCodes> findAuthCodes(Integer adminId) {
        return phoneAuthDao.findAuthCodes(adminId);
    }

    @Override
    public List<PhoneAuth> findByVersionType(Integer versionType, Integer adminId) {

        return phoneAuthDao.findByVersionType(versionType,adminId);
    }
}
