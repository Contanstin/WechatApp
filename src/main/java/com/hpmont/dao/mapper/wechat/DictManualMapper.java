package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.DictManual;

public interface DictManualMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictManual record);

    int updateByPrimaryKeySelective(DictManual record);

    Page<DictManual> findManualType(PageSearch search);
}