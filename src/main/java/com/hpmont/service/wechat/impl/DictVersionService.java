package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.DictVersionMapper;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.domain.wechat.FaultDescription;
import com.hpmont.service.wechat.IDictVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 徐 on 2018/6/11.
 */
@Service
public class DictVersionService implements IDictVersionService{

    @Autowired
    private DictVersionMapper dictVersionDao;

    @Override
    public PageInfo<DictVersion> pageVersionType(PageSearch search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<DictVersion> list = dictVersionDao.findVersionType(search);
        PageInfo<DictVersion> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void insert(DictVersion dictVersion) {
        dictVersionDao.insert(dictVersion);
    }

    @Override
    public void update(DictVersion dictVersion) {
        dictVersionDao.updateByPrimaryKeySelective(dictVersion);
    }

    @Override
    public void delete(Integer id) {
        dictVersionDao.deleteByPrimaryKey(id);
    }
}
