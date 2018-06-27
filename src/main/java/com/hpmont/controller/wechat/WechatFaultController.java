package com.hpmont.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.hpmont.controller.BaseController;
import com.hpmont.domain.search.SearchFault;
import com.hpmont.domain.wechat.WechatFault;
import com.hpmont.service.wechat.IWechatFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 徐 on 2018/6/1.
 */
@Controller
@RequestMapping("/fault")
public class WechatFaultController extends BaseController{

    @Autowired
    private IWechatFaultService faultService;

    @RequestMapping(value = "/list")
    public String findAll(Model model, SearchFault search){
        try {
            PageInfo<WechatFault> list = faultService.findFaultList(search);
            model.addAttribute("page",list);
            model.addAttribute("search",search);
        } catch (Exception e) {
            logger.error("查询故障说明失败", e);
        }
        return "/fault/list";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(WechatFault fault){
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
