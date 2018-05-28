package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.WechatMenuMapper;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.Slideshow;
import com.hpmont.domain.wechat.WechatMenu;
import com.hpmont.service.wechat.IWechatMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/5/28.
 */
@Service
public class WechatMenuService implements IWechatMenuService{

    @Autowired
    private WechatMenuMapper wechatMenuDao;

    @Override
    public PageInfo<WechatMenu> findWechatMenu(PageSearch search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<WechatMenu> list = wechatMenuDao.findWechatMenu(search);
        PageInfo<WechatMenu> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int insertWechatMenu(WechatMenu menu) {
        return wechatMenuDao.insert(menu);
    }

    @Override
    public int updateWechatMenu(WechatMenu menu) {
        return wechatMenuDao.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int deleteWechatMenu(Integer id) {
        return wechatMenuDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<WechatMenu> findMenuByApp() {
        return wechatMenuDao.findMenuByApp();
    }
}
