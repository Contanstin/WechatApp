package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.WechatImage;

import java.util.List;

/**
 * Created by 徐 on 2018/5/21.
 */
public interface IWechatImageService {
    PageInfo<WechatImage> findSlideshowList(SearchCommon search);

    int insert(WechatImage slideshow);

    int update(WechatImage slideshow);

    int delete(Integer id);

    List<String> findSlideshowUrlByApp(SearchCommon search);

    List<WechatImage> findImages(String departmentType);
}
