package com.hpmont.service.phone;

import com.github.pagehelper.PageInfo;
import com.hpmont.domain.search.SearchPhoneAuth;
import com.hpmont.domain.phone.PhoneAuth;
import com.hpmont.domain.phone.PhoneAuthCodes;

import java.util.List;

/**
 * Created by 徐 on 2018/5/17.
 */
public interface IPhoneAuthService {
    PageInfo<PhoneAuth> findPhoneAuthList(SearchPhoneAuth search);

    void insertPhoneAuth(PhoneAuth phoneAuth);

    void updatePhoneAuth(PhoneAuth phoneAuth);

    void deletePhoneAuth(Integer id);

    List<PhoneAuthCodes> findAuthCodes(Integer adminId);

    List<PhoneAuth> findByVersionType(Integer versionType, Integer adminId);
}
