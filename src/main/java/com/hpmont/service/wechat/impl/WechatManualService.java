package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.DictManualMapper;
import com.hpmont.dao.mapper.wechat.WechatManualMapper;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.WechatManual;
import com.hpmont.service.wechat.IWechatManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/5/30.
 */
@Service
public class WechatManualService implements IWechatManualService{

    @Autowired
    private WechatManualMapper manualDao;

    @Override
    public PageInfo<WechatManual> findManualList(SearchManual search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<WechatManual> list = manualDao.findManualList(search);
        PageInfo<WechatManual> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void insert(WechatManual manual) {
        manualDao.insert(manual);
    }

    @Override
    public void update(WechatManual manual) {
        manualDao.updateByPrimaryKeySelective(manual);
    }

    @Override
    public int updateDownloadCount(String realName) {
        return  manualDao.updateDownloadCount(realName);
    }

    @Override
    public void delete(Integer id) {
        manualDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<WechatManual> findManualListByApp(SearchManual search) {
        return manualDao.findManualListByApp(search);
    }

    @Override
    public List<WechatManual> findRecommendByApp() {
         return manualDao.findRecommendByApp();
    }

    @Override
    public List<WechatManual> findDownloadRanking() {
        return manualDao.findDownloadRanking();
    }
}
