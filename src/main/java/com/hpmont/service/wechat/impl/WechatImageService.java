package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.WechatImageMapper;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.WechatImage;
import com.hpmont.service.wechat.IWechatImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/5/21.
 */
@Service
public class WechatImageService implements IWechatImageService {

    @Autowired
    private WechatImageMapper imageDao;

    @Override
    public PageInfo<WechatImage> findSlideshowList(SearchCommon search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<WechatImage> list = imageDao.findSlideshowList(search);
        PageInfo<WechatImage> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int insert(WechatImage slideshow) {
        return imageDao.insert(slideshow);
    }

    @Override
    public int update(WechatImage slideshow) {
        return imageDao.updateByPrimaryKeySelective(slideshow);
    }

    @Override
    public int delete(Integer id) {
        return imageDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> findSlideshowUrlByApp(SearchCommon search) {
        return imageDao.findSlideshowUrlByApp(search);
    }
}
