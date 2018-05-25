package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.Slideshow;

import java.util.List;

/**
 * Created by 徐 on 2018/5/21.
 */
public interface ISlideshowService {
    PageInfo<Slideshow> findSlideshowList(PageSearch search);

    int insert(Slideshow slideshow);

    int update(Slideshow slideshow);

    int delete(Integer id);

    List<String> findSlideshowUrlByApp();
}
