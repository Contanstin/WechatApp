package com.hpmont.dao.mapper.wechat;

import com.github.pagehelper.Page;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.FaultDescription;

import java.util.List;

public interface FaultDescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FaultDescription record);

    int updateByPrimaryKeySelective(FaultDescription record);

    List<FaultDescription> findFaultListByApp(SearchFault search);

    Page<FaultDescription> findFaultList(SearchFault search);
}