package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.WechatManual;

import java.util.List;

public interface WechatManualMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatManual record);

    int updateByPrimaryKeySelective(WechatManual record);

    Page<WechatManual> findManualList(SearchManual search);

    List<WechatManual> findManualListByApp(SearchManual search);
}