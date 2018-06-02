package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.FaultDescriptionMapper;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.wechat.FaultDescription;
import com.hpmont.domain.wechat.WechatMenu;
import com.hpmont.service.wechat.IFaultDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/6/1.
 */
@Service
public class FaultDescriptionService implements IFaultDescriptionService{


    @Autowired
    private FaultDescriptionMapper faultDao;

    @Override
    public List<FaultDescription> findFaultListByApp(SearchFault search) {
        return faultDao.findFaultListByApp(search);
    }

    @Override
    public PageInfo<FaultDescription> findFaultList(SearchFault search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<FaultDescription> list = faultDao.findFaultList(search);
        PageInfo<FaultDescription> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void insertFault(FaultDescription fault) {
        faultDao.insert(fault);
    }

    @Override
    public void updateFault(FaultDescription fault) {
        faultDao.updateByPrimaryKeySelective(fault);
    }

    @Override
    public void deleteFault(Integer id) {
        faultDao.deleteByPrimaryKey(id);
    }
}
