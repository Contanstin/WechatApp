package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.DictVersion;

import java.util.List;

/**
 * Created by 徐 on 2018/6/11.
 */
public interface IDictManualService {
    PageInfo<DictManual> pageManualType(SearchCommon search);

    void insert(DictManual dictManual);

    void update(DictManual dictManual);

    void delete(Integer id);

    List<DictManual> findManualType(SearchCommon search);
}
