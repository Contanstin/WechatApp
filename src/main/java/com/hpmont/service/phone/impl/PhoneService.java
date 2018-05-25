package com.hpmont.service.phone.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpmont.constants.Constant;
import com.hpmont.dao.mapper.PhoneUUIDMapper;
import com.hpmont.dao.mapper.PhoneUserMapper;
import com.hpmont.domain.search.SearchPhone;
import com.hpmont.domain.phone.PhoneUUID;
import com.hpmont.domain.phone.PhoneUser;
import com.hpmont.domain.phone.PhoneUserAdmin;
import com.hpmont.service.phone.IPhoneService;
import com.hpmont.util.Principal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 徐 on 2018/5/10.
 */
@Service("PhoneService")
public class PhoneService implements IPhoneService {

    @Autowired
    private PhoneUserMapper userPhoneDao;

    @Autowired
    private PhoneUUIDMapper uuidDao;

    @Override
    public PageInfo<PhoneUser> findPhoneList(SearchPhone search) {
        PageHelper.startPage(search.getCurrpage(), Constant.PAGEROWS);
        Page<PhoneUser> list = userPhoneDao.findPhoneList(search);
        PageInfo<PhoneUser> pageInfo=new PageInfo<>(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public int updatePhoneUser(PhoneUser phone) {
        phone.setModifyTime(new Date());
        if (Integer.parseInt(phone.getPassCheck())!=2){
            phone.setPassCheck("11325");
        }else {
            phone.setPassCheck("0");
        }
        return userPhoneDao.updateByPrimaryKeySelective(phone);
    }

    @Override
    public PhoneUser getUserPhone(String tempStrPhone) {
        return userPhoneDao.getUserPhone(tempStrPhone);
    }

    @Override
    public void insertPhoneUser(PhoneUser phone) {
        if (Integer.parseInt(phone.getPassCheck())>0){
            phone.setPassCheck("11325");
        }
        userPhoneDao.insert(phone);
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        Integer adminId= Math.toIntExact(principa.getId());
        userPhoneDao.insertPhoneUserAdmin(new PhoneUserAdmin(adminId,phone.getId()));
        if (adminId!=1){
            userPhoneDao.insertPhoneUserAdmin(new PhoneUserAdmin(1,phone.getId()));
        }
    }

    @Override
    public int deletePhoneUser(Integer id) {
        return userPhoneDao.deleteByPrimaryKey(id);
    }

    @Override
    public int checkAuthoUser(String tempStrPhone) {
        int tempFlag;
        PhoneUser user = userPhoneDao.getUserPhone(tempStrPhone);
        if(user == null)
        {
            tempFlag = 0;
        }
        else
        {
            if(user.getPassCheck().equals("11325"))
            {//授权通过
                tempFlag = 1;
            }
            else
            {
                tempFlag = 0;
            }
        }
        return tempFlag;
    }

    @Override
    public String getInfo(String tempPhone, int tempIndex) {
        String tempMess="";
        PhoneUser tempUser = userPhoneDao.getUserPhone(tempPhone);
        if(tempUser != null)
        {
            switch(tempIndex)
            {
                case 0://用户名
                    tempMess = tempUser.getUsername();
                    break;
                case 1://公司
                    tempMess = tempUser.getCompany();
                    break;
                case 2://邮箱
                    tempMess = tempUser.getEmail();
                    break;
                case 3://地址
                    tempMess = tempUser.getAddress();
                    break;
                case 8://语言
                    tempMess = tempUser.getLanguage();
                default://其他
                    break;
            }
        }
        return tempMess;
    }

    @Override
    public int updatePW(String phone, String password) {
        int tempCnt=userPhoneDao.updataPW(phone,password);
        int tempFlag;
        if(tempCnt<1)
        {
            tempFlag = 0;
        }
        else
        {
            tempFlag = 1;
        }
        return tempFlag;
    }

    @Override
    public String login(String tempPhone, String tempPW,String tempStrUUID,String tempLang) {
        String tempRstStr = "";

        PhoneUUID phoneUUID = new PhoneUUID();
        phoneUUID.setPhoneNumber(tempPhone);
        phoneUUID.setUuid(tempStrUUID);

        PhoneUser   user = userPhoneDao.getUserPhone(tempPhone);

        //1)校验用户是否注册
        if(user == null)
        {
            if(tempLang.equals("zh"))
            {
                tempRstStr = "未注册账户";
            }
            else
            {
                tempRstStr ="Unregistered account";
            }

            return tempRstStr;
        }
        //2)检测授权
        if(!(user.getPassCheck().equals("11325")))
        {
            if(tempLang.equals("zh"))
            {
                tempRstStr = "未授权账户";
            }
            else
            {
                tempRstStr ="Unauthorized account";
            }

            return tempRstStr;
        }
        //3)密码验证
        if(!(tempPW.equals(user.getPassword())))
        {
            if(tempLang.equals("zh"))
            {
                tempRstStr = "密码不正确";
            }
            else
            {
                tempRstStr = "Incorrect password";
            }

            return tempRstStr;
        }
        //4)IMEI验证
        //4.1)判断是否存在

        if(uuidDao.isExistUUID(phoneUUID))
        {//存在当前客户登记信息，则登录成功
            if(tempLang.equals("zh"))
            {
                tempRstStr = "登录成功";
            }
            else
            {
                tempRstStr = "Login success";
            }

            return tempRstStr;
        }
        else
        {
            int tempNum = Integer.parseInt(user.getAuthNum());
            int tempLogCnt = uuidDao.getUUIDCount(tempPhone);
            if(tempLogCnt >= tempNum)
            {//达到设定登录用户数量时
                if(tempLang.equals("zh"))
                {
                    tempRstStr = "到达授权上限";
                }
                else
                {
                    tempRstStr = "Reach the upper limit authorization number";
                }
                return tempRstStr;
            }
            else
            {//插入数据
                int tempCnt = uuidDao.insert(phoneUUID);
                if(tempCnt<1)
                {
                    if(tempLang.equals("zh"))
                    {
                        tempRstStr = "登记失败";
                    }
                    else
                    {
                        tempRstStr = "Registration failure";
                    }
                    return tempRstStr;
                }
                else
                {
                    if(tempLang.equals("zh"))
                    {
                        tempRstStr = "登录成功";
                    }
                    else
                    {
                        tempRstStr = "Login success";
                    }
                    return tempRstStr;
                }
            }
        }
    }


}
