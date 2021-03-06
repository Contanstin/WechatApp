package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchMenu;
import com.hpmont.domain.wechat.WechatMenu;

import java.util.List;

/**
 * Created by 徐 on 2018/5/28.
 */
public interface IWechatMenuService {
    List<WechatMenu> findWechatMenu(SearchMenu search);

    int insertWechatMenu(WechatMenu menu);

    int updateWechatMenu(WechatMenu menu);

    int deleteWechatMenu(Integer id);

    List<WechatMenu> findMenuByApp(SearchMenu search);
}
