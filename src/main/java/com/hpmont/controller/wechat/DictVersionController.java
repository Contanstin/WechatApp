package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.search.SearchCommon;
import com.hpmont.domain.wechat.DictVersion;
import com.hpmont.service.wechat.IDictVersionService;
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
@RequestMapping("/version_type")
public class DictVersionController extends BaseController{

    @Autowired
    private IDictVersionService dictVersionService;


    @RequestMapping(value = "/list")
    public String pageVersionType(Model model, SearchCommon search){
        try {
            PageInfo<DictVersion> list = dictVersionService.pageVersionType(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询软件版本失败", e);
        }
        return "/version_type/list";
    }


    @RequestMapping(value = "/findVersionType")
    @ResponseBody
    public List<DictVersion> findVersionType(SearchCommon search){
        List<DictVersion> list=null;
        try {
            list= dictVersionService.findVersionType(search);
        } catch (Exception e) {
            logger.error("查询软件版本失败",e);
        }
        return list;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(DictVersion dictVersion){
        try {

            if (null==dictVersion.getId()){
                dictVersionService.insert(dictVersion);
            }else {
                dictVersionService.update(dictVersion);
            }
        } catch (Exception e) {
            logger.error("编辑软件版本失败",e);
            return errorResult("编辑软件版本失败");
        }
        return success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Integer id){
        try {
            dictVersionService.delete(id);
        } catch (Exception e) {
            logger.error("删除软件版本失败",e);
            return errorResult("删除软件版本失败");
        }
        return success();
    }
}
