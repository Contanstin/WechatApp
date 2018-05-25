package com.hpmont.service.phone;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchPhone;
import com.hpmont.domain.phone.PhoneUser;


/**
 * Created by 徐 on 2018/5/10.
 */
public interface IPhoneService {
    PageInfo<PhoneUser> findPhoneList(SearchPhone search);

    int updatePhoneUser(PhoneUser phone);

    String login(String s, String tempStrPW, String tempStrUUID, String tempLanguage);

    PhoneUser getUserPhone(String tempStrPhone);

    void insertPhoneUser(PhoneUser phone);

    int deletePhoneUser(Integer id);

    int checkAuthoUser(String tempStrPhone);

    String getInfo(String tempStrPhone, int i);

    int updatePW(String s, String tempPassWord);
}
