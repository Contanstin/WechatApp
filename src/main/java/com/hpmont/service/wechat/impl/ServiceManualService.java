package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.ServiceManualMapper;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.ServiceManual;
import com.hpmont.domain.wechat.Slideshow;
import com.hpmont.service.wechat.IServiceManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/5/30.
 */
@Service
public class ServiceManualService implements IServiceManualService{

    @Autowired
    private ServiceManualMapper manualDao;

    @Override
    public PageInfo<ServiceManual> findManualList(SearchManual search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<ServiceManual> list = manualDao.findManualList(search);
        PageInfo<ServiceManual> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public List<ServiceManual> findManualType() {
        return manualDao.findManualType();
    }

    @Override
    public void insert(ServiceManual manual) {
        manualDao.insert(manual);
    }

    @Override
    public void update(ServiceManual manual) {
        manualDao.updateByPrimaryKeySelective(manual);
    }

    @Override
    public void delete(Integer id) {
        manualDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<ServiceManual> findManualListByType(SearchManual search) {
        return manualDao.findManualListByType(search);
    }
}
