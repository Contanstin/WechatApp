package com.hpmont.controller.phone;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hpmont.bean.CodeMSG;
import com.hpmont.bean.SendEmail;
import com.hpmont.bean.SmsModule;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.search.SearchPhone;
import com.hpmont.domain.phone.PhoneUser;
import com.hpmont.service.phone.IPhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hpmont.constants.Constant.HD5L;
import static com.hpmont.constants.Constant.MONT70;
import static com.hpmont.constants.Constant.MONT71;

/**
 * Created by xu2048@yeah.net on 2017/9/5.
 */
@Controller
public class PhoneController extends BaseController {

    @Resource(name = "PhoneService")
    private IPhoneService phoneService;

    @RequestMapping(value = "/phone/list")
    public String findAll(Model model,SearchPhone search){
        try {
            search.setAdminId(getCurrUserId());
            PageInfo<PhoneUser> list = phoneService.findPhoneList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询手机用户列表出错", e);
        }
        return "/phone/list";
    }

    @RequestMapping(value = "/phone/manage")
    public String manage(Model model,SearchPhone search){
        try {
            search.setAdminId(getCurrUserId());
            PageInfo<PhoneUser> list = phoneService.findPhoneList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询手机用户列表出错", e);
        }
        return "/phone/manage";
    }

    @RequestMapping(value = "/phone/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit( PhoneUser phone, HttpServletRequest request){
        String[] authCodes = request.getParameterValues("authCodes");
        try {
            if (null==phone.getCompany()){
                phone.setCompany("");
            }if (null==phone.getAddress()){
                phone.setAddress("");
            }if (null==phone.getLanguage()){
                phone.setLanguage("");
            }if (null==phone.getVersion()){
                phone.setVersion("");
            }if (null!=phone.getVersionType()){
                if (phone.getVersionType()==1){
                    phone.setVersionName(MONT70);
                }else if (phone.getVersionType()==2){
                    phone.setVersionName(MONT71);
                }else {
                    phone.setVersionName(HD5L);
                }
            }
            if (null!=authCodes){
                if (authCodes.length>0){
                    String mcuCode="";
                    for (String authCode : authCodes) {
                        mcuCode+=authCode+",";
                    }
                    mcuCode=mcuCode.substring(0,mcuCode.length()-1);
                    phone.setMcuCode(mcuCode);
                }
            }

            if (null==phone.getId()){
                phoneService.insertPhoneUser(phone);
            }else {
                phoneService.updatePhoneUser(phone);
            }
        } catch (Exception e) {
            logger.error("编辑手机信息失败",e);
        return errorResult("编辑手机信息失败");
             }
        return success();
    }

    @RequestMapping(value = "/phone/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Integer id){
        try {
            phoneService.deletePhoneUser(id);
        } catch (Exception e) {
            logger.error("删除手机信息失败",e);
        return errorResult("删除手机信息失败");
             }
        return success();
    }

    @RequestMapping(value = "/LoginServlet", method = RequestMethod.POST)
    @ResponseBody
    public Map login(HttpServletRequest request) {
        Map tempData = null;

        try {
            //获取客户端发送来的数据
            String tempStrPhone = request.getParameter("keyPhone");//电话号码或者邮箱
            String tempStrPW = request.getParameter("keyPassword");
            String tempStrUUID = request.getParameter("keyUUID");
            String tempLanguage = request.getParameter("language");	//语言
            if ((tempLanguage == null) || (tempLanguage.equals(""))) {
                tempLanguage = "zh";
            }

            //校验手机号码和密码
            boolean tempLoginRst = false;
            String tempStrMess = "";
            if ((tempStrPhone != null) && (!tempStrPhone.equals(""))
                    && (tempStrPW != null) && (!tempStrPW.equals(""))
                    && (tempStrUUID != null) && (!tempStrUUID.equals(""))) {
                tempStrMess = phoneService.login(tempStrPhone.toLowerCase(), tempStrPW, tempStrUUID, tempLanguage);
            } else {
                if (tempLanguage.equals("zh")) {
                    tempStrMess = "手机或密码输入不能为空";
                } else {
                    tempStrMess = "Cell phone or password input can not be empty";
                }
            }

            //返回数据
            //1)状态信息
            if ((tempStrMess.equals("登录成功"))
                    || (tempStrMess.equals("Login success"))) {
                tempLoginRst = true;
            }
            //2）操作状态
            String tempStrRst = "0";
            if (tempLoginRst) {
                tempStrRst = "1";
            }
            //3)返回所有的客户信息

            String tempGetUserName = "";//用户名
            String tempGetCompName = "";//公司名
            String tempGetEMail = "";//电邮
            String tempGetPhone = "";//电话
            String tempGetAdd = "";//地址
            String tempMcuCode = "";//访问权限

            if (tempLoginRst) {
                PhoneUser tempUser = phoneService.getUserPhone(tempStrPhone);
                tempGetUserName = tempUser.getUsername();
                tempGetCompName = tempUser.getCompany();
                tempGetEMail = tempUser.getEmail();
                tempGetPhone = tempUser.getPhoneNumber();
                tempGetAdd = tempUser.getAddress();
                tempMcuCode = tempUser.getMcuCode();
            }
            //4)生成session
            HttpSession session;
            session = request.getSession(false); //获取session
            if ((session == null) && (tempLoginRst)) {
                session = request.getSession(true);
                session.setMaxInactiveInterval(1800);//30分钟=1800s
                session.setAttribute("UserIDPhone", tempStrPhone);
            }

            //组织数据
            tempData = new HashMap<>();
            tempData.put("msg", tempStrMess);
            tempData.put("code", tempStrRst);

            Map<String, Object> tempUser = new HashMap<>();
            tempUser.put("userName", tempGetUserName);
            tempUser.put("company", tempGetCompName);
            tempUser.put("eMail", tempGetEMail);
            tempUser.put("phone", tempGetPhone);
            tempUser.put("address", tempGetAdd);
            //权限
            String[] tempArray = tempMcuCode.split("\\,");
            List tempList = new ArrayList();
            for (int i = 0; i < tempArray.length; i++) {
                tempList.add(tempArray[i]);
            }
            tempUser.put("mcuCode", tempList);

            tempData.put("content", tempUser);
        } catch (Exception e) {
            logger.error("手机用户登录失败",e);
        }

        return tempData;
    }


    @RequestMapping(value = "/GetVeritifCodeServlet", method = RequestMethod.POST)
    @ResponseBody
    public Map GetVeritifCode(HttpServletRequest request) {
        Map tempData = null;

        try {
            //获取客户端发送来的数据
            String tempStrPhone = request.getParameter("keyPhone");//电话号码或者邮箱
            String tempLanguage = request.getParameter("language");	//语言
            String tempEMail="";									//通过账户获取到的邮箱
            if((tempLanguage==null)||(tempLanguage.equals("")))
            {
                tempLanguage="zh";
            }

            //获取随机码
            int tempCode = (int)(Math.random()*(999999-100000) + 100000);
            String tempStrCode = String.valueOf(tempCode);

            //校验账户和是否授权
            int TempRst = -1;
            if((tempStrPhone != null)&&(!tempStrPhone.equals("")))
            {
                TempRst = phoneService.checkAuthoUser(tempStrPhone);
                tempEMail = phoneService.getInfo(tempStrPhone,2);
            }

            //返回数据
            //1)状态信息
            String tempStrMess = "";
            String tempStrRst = "0";
            switch(TempRst)
            {
                case -1:
                    if(tempLanguage.equals("zh"))
                    {
                        tempStrMess="手机号码输入无效";
                    }
                    else
                    {
                        tempStrMess="Invalid phone number";
                    }
                    break;
                case  0:
                    if(tempLanguage.equals("zh"))
                    {
                        tempStrMess="未注册或未授权";
                    }
                    else
                    {
                        tempStrMess="Unregistered or unauthorized";
                    }
                    break;
                case  1:
                    if(tempLanguage.equals("zh"))
                    {//中文国家,发送短信
                        String tempSendMsg = "【海浦蒙特】您的验证码为："+ tempStrCode +"，有效期为2分钟请立即验证。请不要将其透露给任何个人和单位。";
                        String tempStr = SmsModule.sendMessage(tempStrPhone,tempSendMsg);//短信发送
                        //测试
    //                    String tempStr = "{\"code\": 0,\"msg\": \"发送成功\",\"count\": 1,\"fee\": 0.05,\"unit\": \"RMB\",\"mobile\": \"13200000000\",\"sid\": 3310228982	}";
                        try
                        {
                            JSONObject tempJS = JSONObject.parseObject(tempStr);
                            String tempStrMsg = "发送失败";
                            if(tempJS.containsKey("msg"))
                            {
                                tempStrMsg = tempJS.get("msg").toString();
                            }

                            if(tempJS.containsKey("count"))
                            {
                                String tempStrCnt = tempJS.get("count").toString();
                                int tempCnt = Integer.parseInt(tempStrCnt);
                                if(tempCnt >= 1)
                                {
                                    tempStrRst = "1";
                                    tempStrMess = tempStrMsg;
                                }
                                else
                                {
                                    tempStrRst = "0";
                                    tempStrMess = tempStrMsg;
                                }
                            }
                            else
                            {
                                //错误返回码时
                                //内容：{"http_status_code":400,"code":-3,"msg":"IP没有权限","detail":"IP 183.13.120.232未加入白名单,可在后台‘系统设置->IP白名单设置’里添加"}
                                tempStrRst = "0";
                                tempStrMess = tempStrMsg;
                            }
                        }
                        catch(JSONException ex)
                        {
                            logger.error("转json失败", ex);
                            ex.printStackTrace();
                        }
                    }
                    else
                    {//非中文国家,发送邮件
                        String tempSendMsg = "Your verification code is:"+ tempStrCode +", is valid for two minutes immediately verified. Do not be disclosed to any individuals and units.";
                        if(tempEMail != null)
                        {
                            if(SendEmail.sendMessageMail(tempEMail,tempSendMsg))
                            {
                                tempStrRst = "1";
                                tempStrMess = "A verification code has been sent to your E-mail!";
                            }
                            else
                            {
                                tempStrRst = "0";
                                tempStrMess = "Invalid E-mail!";
                            }
                        }
                        else
                        {
                            tempStrRst = "0";
                            tempStrMess = "Invalid E-mail!";
                        }
                    }
                    break;
            }

            //2）生成Session记录
            HttpSession session;
            session = request.getSession(false); //获取session
            if(session == null)
            {
                session = request.getSession(true);
                session.setMaxInactiveInterval(1800);//30分钟=1800s
            }
            if(tempStrRst.equals("1"))
            {
                CodeMSG tempMsg = new CodeMSG();
                tempMsg.setPhoneNumberLog(tempStrPhone);//记录电话号码
                tempMsg.setCodeLog(tempStrCode);		//记录随机验证码
                tempMsg.setTimeStamp();					//记录时间戳
                session.setAttribute("CODEMSG",tempMsg);
            }
            //3）组装为JSON数据格式

            //组织数据
            tempData = new HashMap<>();
            tempData.put("msg",tempStrMess);
            tempData.put("code",tempStrRst);
            tempData.put("content","");
        } catch (NumberFormatException e) {
            logger.error("获取手机验证码失败",e);
        }

        return tempData;
    }

    @RequestMapping(value = "/ChangePassWordServlet", method = RequestMethod.POST)
    @ResponseBody
    public Map ChangePassWord(HttpServletRequest request) {
        Map tempData = null;
        try {
            //获取客户端发送来的数据
            String tempStrPhone = request.getParameter("keyPhone");		//电话号码或者邮箱
            String tempStrCode = request.getParameter("keyCode");		//验证码
            String tempPassWord = request.getParameter("keyPassword");	//新密码
            String tempLanguage = request.getParameter("language");		//语言
            if((tempLanguage==null)||(tempLanguage.equals("")))
            {
                tempLanguage="zh";
            }

            //数据处理
            int TempRst = -1;
            String tempStrMess = "";
            boolean tempBool=false;

            //1）验证
            if((tempStrPhone != null)&&(!tempStrPhone.equals(""))
                    &&(tempStrCode != null)&&(!tempStrCode.equals(""))
                    &&(tempPassWord != null)&&(!tempPassWord.equals("")))
            {
                HttpSession session;
                session = request.getSession(false); //获取session
                if(session != null)
                {
                    Object tempObj = session.getAttribute("CODEMSG");
                    if(tempObj != null)
                    {
                        CodeMSG tempMsg= (CodeMSG)tempObj;

                        //验证验证码、手机、验证码有效时间等
                        tempBool = tempMsg.getValid(tempStrPhone.toLowerCase(),tempStrCode);

                        if(tempBool)
                        {//验证成功时更改密码
                            TempRst = phoneService.updatePW(tempStrPhone.toLowerCase(),tempPassWord);
                            switch(TempRst)
                            {
                                case  0:
                                    if(tempLanguage.equals("zh"))
                                    {
                                        tempStrMess="更改失败";
                                    }
                                    else
                                    {
                                        tempStrMess="Fail modified!";
                                    }
                                    break;
                                case  1:
                                    if(tempLanguage.equals("zh"))
                                    {
                                        tempStrMess="更改成功";
                                    }
                                    else
                                    {
                                        tempStrMess="Successfully modified!";
                                    }
                                    break;
                            }
                        }
                        else
                        {
                            if(tempLanguage.equals("zh"))
                            {
                                tempStrMess="验证码过期或错误";
                            }
                            else
                            {
                                tempStrMess="Verification code expired!";
                            }

                        }
                    }
                    else
                    {
                        if(tempLanguage.equals("zh"))
                        {
                            tempStrMess="未获取验证码";
                        }
                        else
                        {
                            tempStrMess="Invalid verification code";
                        }
                    }
                }
                else
                {
                    if(tempLanguage.equals("zh"))
                    {
                        tempStrMess="操作超时或未获取验证码";
                    }
                    else
                    {
                        tempStrMess="Verification code expired!";
                    }
                }
            }
            else
            {
                if(tempLanguage.equals("zh"))
                {
                    tempStrMess="电话或密码或验证码空";
                }
                else
                {
                    tempStrMess="Account number or password is invalid!";
                }
            }

            //2）设置本次操作结果
            String tempStrRst = "0";
            if((TempRst == 1)&&(tempBool))
            {
                tempStrRst="1";
            }
            //3）组装为JSON数据格式

            //组织数据
            tempData = new HashMap<>();
            tempData.put("msg",tempStrMess);
            tempData.put("code",tempStrRst);
            tempData.put("content","");
        } catch (Exception e) {
            logger.error("手机用户修改密码失败",e);
        }

        return tempData;
    }


}
