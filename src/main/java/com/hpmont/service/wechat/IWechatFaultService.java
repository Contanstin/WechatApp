package com.hpmont.service.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.wechat.WechatFault;

import java.util.List;

/**
 * Created by 徐 on 2018/6/1.
 */
public interface IWechatFaultService {
    List<WechatFault> findFaultListByApp(SearchFault search);

    PageInfo<WechatFault> findFaultList(SearchFault search);

    void insertFault(WechatFault fault);

    void updateFault(WechatFault fault);

    void deleteFault(Integer id);
}
