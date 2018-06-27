package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.WechatFaultMapper;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.wechat.WechatFault;
import com.hpmont.service.wechat.IWechatFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/6/1.
 */
@Service
public class WechatFaultService implements IWechatFaultService {


    @Autowired
    private WechatFaultMapper faultDao;

    @Override
    public List<WechatFault> findFaultListByApp(SearchFault search) {
        if (search.getFaultName()!=null){
            if (search.getFaultName().length()>2){
                search.setFaultCode(search.getFaultName());
                search.setFaultName("");
            }
        }
        return faultDao.findFaultListByApp(search);
    }

    @Override
    public PageInfo<WechatFault> findFaultList(SearchFault search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<WechatFault> list = faultDao.findFaultList(search);
        PageInfo<WechatFault> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void insertFault(WechatFault fault) {
        faultDao.insert(fault);
    }

    @Override
    public void updateFault(WechatFault fault) {
        faultDao.updateByPrimaryKeySelective(fault);
    }

    @Override
    public void deleteFault(Integer id) {
        faultDao.deleteByPrimaryKey(id);
    }
}
