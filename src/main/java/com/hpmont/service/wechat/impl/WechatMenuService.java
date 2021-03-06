package com.hpmont.service.wechat.impl;

import com.hpmont.dao.mapper.wechat.WechatMenuMapper;
import com.hpmont.domain.search.SearchMenu;
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
    public List<WechatMenu> findWechatMenu(SearchMenu search) {
        return wechatMenuDao.findWechatMenu(search);
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
    public List<WechatMenu> findMenuByApp(SearchMenu search) {
        return wechatMenuDao.findMenuByApp(search);
    }
}
