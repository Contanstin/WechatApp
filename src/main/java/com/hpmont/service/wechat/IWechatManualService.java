package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.WechatManual;

import java.util.List;

/**
 * Created by 徐 on 2018/5/30.
 */
public interface IWechatManualService {
    PageInfo<WechatManual> findManualList(SearchManual search);

    void insert(WechatManual manual);

    void update(WechatManual manual);

    void delete(Integer id);

    List<WechatManual> findManualListByApp(SearchManual search);
}
