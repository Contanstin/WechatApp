package com.hpmont.controller;

import com.hpmont.dao.mapper.PhoneUserMapper;
import com.hpmont.domain.search.SearchPhone;
import com.hpmont.domain.phone.PhoneUser;
import com.hpmont.service.phone.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 徐 on 2018/5/17.
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController{

    @Resource(name = "PhoneService")
    private IPhoneService phoneService;

    @Autowired
    private PhoneUserMapper phoneUserDao;

    @ResponseBody
    @RequestMapping(value = "/phoneUserList")
    public List<PhoneUser> findAll(){
        List<PhoneUser> phoneList=null;
        try {
            SearchPhone search = new SearchPhone();
            search.setAdminId(1);
             phoneList = phoneUserDao.findPhoneList(search);
        } catch (Exception e) {
            logger.error("查询手机用户列表出错", e);
        }
        return phoneList;
    }
}
