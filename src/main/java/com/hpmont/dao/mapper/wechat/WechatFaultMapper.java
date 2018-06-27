package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.wechat.WechatFault;

import java.util.List;

public interface WechatFaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatFault record);

    int updateByPrimaryKeySelective(WechatFault record);

    List<WechatFault> findFaultListByApp(SearchFault search);

    Page<WechatFault> findFaultList(SearchFault search);
}