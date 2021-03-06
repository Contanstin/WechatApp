package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchMenu;
import com.hpmont.domain.wechat.WechatMenu;

import java.util.List;

public interface WechatMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatMenu record);

    int updateByPrimaryKeySelective(WechatMenu record);

    Page<WechatMenu> findWechatMenu(SearchMenu search);

    List<WechatMenu> findMenuByApp(SearchMenu search);
}