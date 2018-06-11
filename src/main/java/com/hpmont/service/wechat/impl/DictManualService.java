package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.DictManualMapper;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.service.wechat.IDictManualService;
import com.hpmont.service.wechat.IDictVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 徐 on 2018/6/11.
 */

@Service
public class DictManualService implements IDictManualService{

    @Autowired
    private DictManualMapper dictManualDao;

    @Override
    public PageInfo<DictManual> pageManualType(PageSearch search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<DictManual> list = dictManualDao.findManualType(search);
        PageInfo<DictManual> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void insert(DictManual dictManual) {
        dictManualDao.insert(dictManual);
    }

    @Override
    public void update(DictManual dictManual) {
        dictManualDao.updateByPrimaryKeySelective(dictManual);
    }

    @Override
    public void delete(Integer id) {
        dictManualDao.deleteByPrimaryKey(id);
    }
}
