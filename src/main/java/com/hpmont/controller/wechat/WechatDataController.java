package com.hpmont.controller.wechat;

import com.hpmont.controller.BaseController;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.search.SearchMenu;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.domain.wechat.WechatManual;
import com.hpmont.domain.wechat.WechatFault;
import com.hpmont.domain.wechat.WechatMenu;
import com.hpmont.service.wechat.*;
import com.hpmont.service.wechat.impl.CustomerJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by 徐 on 2018/6/11.
 */
@Controller
@RequestMapping("/wechat")
public class WechatDataController extends BaseController {

    @Autowired
    private IWechatFaultService faultService;

    @Autowired
    private IWechatMenuService wechatMenuService;

    @Autowired
    private IWechatManualService manualService;

    @Autowired
    private IWechatImageService imageService;

    @Autowired
    private IDictVersionService versionService;

    @Autowired
    private CustomerJsonService customerJsonService;


    @RequestMapping(value = "/findFaultListByApp")
    @ResponseBody
    public List<WechatFault> findFaultListByApp(@RequestBody SearchFault search){
        List<WechatFault> list=null;
        try {
            list= faultService.findFaultListByApp(search);
        } catch (Exception e) {
            logger.error("微信查询故障说明失败",e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/findMenuByApp")
    public List<WechatMenu> findMenuByApp(@RequestBody SearchMenu search){
        List<WechatMenu> list=null;
        try {
            list = wechatMenuService.findMenuByApp(search);
        } catch (Exception e) {
            logger.error("微信查询菜单列表出错", e);
        }
        return list;
    }


    @ResponseBody
    @RequestMapping(value = "/findSlideshowUrlByApp")
    public  List<String> findSlideshowUrlByApp(@RequestBody SearchCommon search){
        List<String> list=null;
        try {
            list = imageService.findSlideshowUrlByApp(search);
        } catch (Exception e) {
            logger.error("微信查询轮播图url列表出错", e);
        }
        return list;
    }

    @RequestMapping(value = "/findVersionTypeByApp")
    @ResponseBody
    public List<DictVersion> findVersionType(@RequestBody SearchCommon search){
        List<DictVersion> list=null;
        try {
            search.setStatus(1);
            list= versionService.findVersionType(search);
        } catch (Exception e) {
            logger.error("查询软件版本失败",e);
        }
        return list;
    }



    @RequestMapping(value = "/findManualListByApp")
    @ResponseBody
    public List<WechatManual> findManualListByApp(@RequestBody SearchManual search){
        List<WechatManual> list=null;
        try {
            list= manualService.findManualListByApp(search);
        } catch (Exception e) {
            logger.error("查询手册种类失败",e);
        }
        return list;
    }

    @RequestMapping(value = "/findDownloadUrlByApp")
    @ResponseBody
    public String findDownloadUrlByApp(@RequestBody Integer versionType){
        String url="";
        try {
            if (versionType==1){
                url="https://www.pgyer.com/USza";
            }else if (versionType==2){
                url="https://www.pgyer.com/T3vS";
            }

        } catch (Exception e) {
            logger.error("查询下载地址失败",e);
        }
        return url;
    }

    @RequestMapping(value = "/findCustomerJsonByApp")
    @ResponseBody
    public List<Map<String, String>> findCustomerJsonByApp(){
        List<Map<String, String>> list=null;
        try {
            list = customerJsonService.getCustomer();

        } catch (Exception e) {
            logger.error("返回服务人员json失败",e);
        }
        return list;
    }
}
