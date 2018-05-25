package com.hpmont.service.wechat.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.wechat.SlideshowMapper;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.Slideshow;
import com.hpmont.service.wechat.ISlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 徐 on 2018/5/21.
 */
@Service
public class SlideshowService implements ISlideshowService{

    @Autowired
    private SlideshowMapper slideshowDao;

    @Override
    public PageInfo<Slideshow> findSlideshowList(PageSearch search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<Slideshow> list = slideshowDao.findSlideshowList(search);
        PageInfo<Slideshow> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int insert(Slideshow slideshow) {
        return slideshowDao.insert(slideshow);
    }

    @Override
    public int update(Slideshow slideshow) {
        return slideshowDao.updateByPrimaryKeySelective(slideshow);
    }

    @Override
    public int delete(Integer id) {
        return slideshowDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> findSlideshowUrlByApp() {
        return slideshowDao.findSlideshowUrlByApp();
    }
}
