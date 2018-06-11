package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.wechat.DictVersion;

/**
 * Created by 徐 on 2018/6/11.
 */
public interface IDictVersionService {
    PageInfo<DictVersion> pageVersionType(PageSearch search);

    void insert(DictVersion dictVersion);

    void update(DictVersion dictVersion);

    void delete(Integer id);
}
