package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.Slideshow;

import java.util.List;

public interface SlideshowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slideshow record);

    int updateByPrimaryKeySelective(Slideshow record);

    Page<Slideshow> findSlideshowList(PageSearch search);

    List<String> findSlideshowUrlByApp();
}