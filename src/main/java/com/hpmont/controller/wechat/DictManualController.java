package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.DictManual;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.service.wechat.IDictManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 徐 on 2018/6/11.
 */
@Controller
@RequestMapping("/manual_type")
public class DictManualController extends BaseController{

    @Autowired
    private IDictManualService dictManualService;

    @RequestMapping(value = "/list")
    public String pageManualType(Model model, SearchCommon search){
        try {
            PageInfo<DictManual> list = dictManualService.pageManualType(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询手册类型失败", e);
        }
        return "/manual_type/list";
    }


    @RequestMapping(value = "/findManualType")
    @ResponseBody
    public List<DictManual> findManualType(SearchCommon search){
        List<DictManual> list=null;
        try {
            list= dictManualService.findManualType(search);
        } catch (Exception e) {
            logger.error("查询手册种类失败",e);
        }
        return list;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(DictManual dictManual){
        try {

            if (null==dictManual.getId()){
                dictManualService.insert(dictManual);
            }else {
                dictManualService.update(dictManual);
            }
        } catch (Exception e) {
            logger.error("编辑手册类型失败",e);
            return errorResult("编辑手册类型失败");
        }
        return success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Integer id){
        try {
            dictManualService.delete(id);
        } catch (Exception e) {
            logger.error("删除手册类型失败",e);
            return errorResult("删除手册类型失败");
        }
        return success();
    }

}
