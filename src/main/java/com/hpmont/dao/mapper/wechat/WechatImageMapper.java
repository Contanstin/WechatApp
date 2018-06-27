package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.WechatImage;

import java.util.List;

public interface WechatImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatImage record);

    int updateByPrimaryKeySelective(WechatImage record);

    Page<WechatImage> findSlideshowList(SearchCommon search);

    List<String> findSlideshowUrlByApp(SearchCommon search);
}