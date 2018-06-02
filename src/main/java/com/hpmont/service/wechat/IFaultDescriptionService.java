package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.FaultDescription;

import java.util.List;

/**
 * Created by 徐 on 2018/6/1.
 */
public interface IFaultDescriptionService {
    List<FaultDescription> findFaultListByApp(SearchFault search);

    PageInfo<FaultDescription> findFaultList(SearchFault search);

    void insertFault(FaultDescription fault);

    void updateFault(FaultDescription fault);

    void deleteFault(Integer id);
}
