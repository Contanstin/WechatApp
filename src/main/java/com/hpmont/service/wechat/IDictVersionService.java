package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.DictVersion;

import java.util.List;

/**
 * Created by 徐 on 2018/6/11.
 */
public interface IDictVersionService {
    PageInfo<DictVersion> pageVersionType(SearchCommon search);

    void insert(DictVersion dictVersion);

    void update(DictVersion dictVersion);

    void delete(Integer id);

    List<DictVersion> findVersionType(SearchCommon search);
}
