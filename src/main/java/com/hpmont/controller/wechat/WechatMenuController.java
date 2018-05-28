package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.phone.PhoneUser;
import com.hpmont.domain.wechat.Slideshow;
import com.hpmont.domain.wechat.WechatMenu;
import com.hpmont.service.wechat.ISlideshowService;
import com.hpmont.service.wechat.IWechatMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.hpmont.constants.Constant.HD5L;
import static com.hpmont.constants.Constant.MONT70;
import static com.hpmont.constants.Constant.MONT71;

/**
 * Created by 徐 on 2018/5/22.
 */
@Controller
@RequestMapping("/wechat_menu")
public class WechatMenuController extends BaseController{

    @Autowired
    private IWechatMenuService wechatMenuService;

    @RequestMapping(value = "/list")
    public String findAll(Model model, PageSearch search){
        try {
            PageInfo<WechatMenu> list = wechatMenuService.findWechatMenu(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询轮播图列表出错", e);
        }
        return "/wechat_menu/list";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(WechatMenu menu){
        try {

            if (null==menu.getId()){
                wechatMenuService.insertWechatMenu(menu);
            }else {
                wechatMenuService.updateWechatMenu(menu);
            }
        } catch (Exception e) {
            logger.error("编辑微信菜单失败",e);
            return errorResult("编辑微信菜单失败");
        }
        return success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Integer id){
        try {
            wechatMenuService.deleteWechatMenu(id);
        } catch (Exception e) {
            logger.error("删除微信菜单失败",e);
            return errorResult("删除微信失败");
        }
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "/findMenuByApp")
    public List<WechatMenu> findMenuByApp(){
        List<WechatMenu> list=null;
        try {
            list = wechatMenuService.findMenuByApp();
        } catch (Exception e) {
            logger.error("微信查询菜单列表出错", e);
        }
        return list;
    }
}
