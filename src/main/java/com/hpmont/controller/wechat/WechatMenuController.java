package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchMenu;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.domain.wechat.WechatMenu;
import com.hpmont.service.wechat.IDictVersionService;
import com.hpmont.service.wechat.IWechatMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 徐 on 2018/5/22.
 */
@Controller
@RequestMapping("/wechat_menu")
public class WechatMenuController extends BaseController{

    @Autowired
    private IWechatMenuService wechatMenuService;

    @Autowired
    private IDictVersionService dictVersionService;

    @RequestMapping(value = "/list")
    public String findAll(Model model, SearchMenu search){
        try {
            if (null==search.getVersionType()){
                if (null!=search.getDepartmentType()){
                    List<DictVersion>  versionList= dictVersionService.findVersionType(search);
                    if (null!=versionList&&versionList.size()>0){
                        search.setVersionType(versionList.get(0).getId());
                    }
                }
            }
            List<WechatMenu> list = wechatMenuService.findWechatMenu(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询后台微信菜单出错", e);
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
}
