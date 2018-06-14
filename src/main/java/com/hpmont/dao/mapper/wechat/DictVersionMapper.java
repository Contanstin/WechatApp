package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.DictVersion;

public interface DictVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictVersion record);

    int updateByPrimaryKeySelective(DictVersion record);

    Page<DictVersion> findVersionType();

}