package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.ServiceManual;

import java.util.List;

public interface ServiceManualMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceManual record);

    int updateByPrimaryKeySelective(ServiceManual record);

    Page<ServiceManual> findManualList(SearchManual search);

    List<ServiceManual> findManualType();

    List<ServiceManual> findManualListByApp(SearchManual search);

    List<ServiceManual> findVersionType();
}