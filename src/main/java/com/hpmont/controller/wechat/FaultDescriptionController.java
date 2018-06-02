package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.page.PageSearch;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.search.SearchManual;
import com.hpmont.domain.wechat.FaultDescription;
import com.hpmont.domain.wechat.ServiceManual;
import com.hpmont.domain.wechat.WechatMenu;
import com.hpmont.service.wechat.IFaultDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 徐 on 2018/6/1.
 */
@Controller
@RequestMapping("/fault")
public class FaultDescriptionController extends BaseController{

    @Autowired
    private IFaultDescriptionService faultService;

    @RequestMapping(value = "/findFaultListByApp")
    @ResponseBody
    public List<FaultDescription> findFaultListByApp(@RequestBody SearchFault search){
        List<FaultDescription> list=null;
        try {
            list= faultService.findFaultListByApp(search);
        } catch (Exception e) {
            logger.error("微信查询故障说明失败",e);
        }
        return list;
    }

    @RequestMapping(value = "/list")
    public String findAll(Model model, SearchFault search){
        try {
            PageInfo<FaultDescription> list = faultService.findFaultList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询故障说明失败", e);
        }
        return "/fault/list";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(FaultDescription fault){
        try {

            if (null==fault.getId()){
                faultService.insertFault(fault);
            }else {
                faultService.updateFault(fault);
            }
        } catch (Exception e) {
            logger.error("编辑故障说明失败",e);
            return errorResult("编辑故障说明失败");
        }
        return success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam Integer id){
        try {
            faultService.deleteFault(id);
        } catch (Exception e) {
            logger.error("删除故障说明失败",e);
            return errorResult("删除故障说明失败");
        }
        return success();
    }

}
