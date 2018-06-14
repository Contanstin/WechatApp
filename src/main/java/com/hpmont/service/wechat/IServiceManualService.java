package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.domain.wechat.ServiceManual;

import java.util.List;

/**
 * Created by 徐 on 2018/5/30.
 */
public interface IServiceManualService {
    PageInfo<ServiceManual> findManualList(SearchManual search);

    List<DictManual> findManualType();

    void insert(ServiceManual manual);

    void update(ServiceManual manual);

    void delete(Integer id);

    List<ServiceManual> findManualListByApp(SearchManual search);

    List<DictVersion> findVersionType();
}
