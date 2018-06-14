package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.DictManualMapper;
import com.hpmont.dao.mapper.wechat.DictVersionMapper;
import com.hpmont.dao.mapper.wechat.ServiceManualMapper;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.DictVersion;
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

    @Autowired
    private DictVersionMapper dictVersionDao;

    @Autowired
    private DictManualMapper dictManualDao;

    @Override
    public PageInfo<ServiceManual> findManualList(SearchManual search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<ServiceManual> list = manualDao.findManualList(search);
        PageInfo<ServiceManual> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public List<DictManual> findManualType() {
        return dictManualDao.findManualType();
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
    public List<ServiceManual> findManualListByApp(SearchManual search) {
        return manualDao.findManualListByApp(search);
    }

    @Override
    public List<DictVersion> findVersionType() {
        return dictVersionDao.findVersionType();
    }
}
