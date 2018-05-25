package com.hpmont.controller.role;

import com.hpmont.controller.BaseController;
import com.hpmont.domain.manual.role.Authority;
import com.hpmont.domain.manual.role.RoleAuth;
import com.hpmont.service.adminuser.IAdminMenuService;
import com.hpmont.service.role.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Resource(name = "adminMenuServiceImpl")
    private IAdminMenuService menuService;

    @Resource(name = "roleService")
    private IRoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        try {
            model.addAttribute("list",roleService.getAllRole());
        }catch (Exception ex){
            logger.error("获取角色列表出错",ex);
        }
        return "/role/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        try {
            model.addAttribute("menu",menuService.searchTreeMenu());
        }catch (Exception ex){
            logger.error("新增角色获取菜单列表出错",ex);
        }
        return "/role/add";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id, ModelMap model) {
        try {
            model.addAttribute("menu",menuService.searchTreeMenu());
            RoleAuth ra=roleService.getRole(id);
            model.addAttribute("rolemenus",this.getRoleAuths(ra.getAuthority()));
            model.addAttribute("role", ra);
        }catch (Exception ex){
            logger.error("获取角色编辑信息出错",ex);
        }
        return "/role/edit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Integer id, ModelMap model) {
        try {
             roleService.delete(id);
            model.addAttribute("list",roleService.getAllRole());
        }catch (Exception ex){
            logger.error("删除角色信息出错",ex);
        }
        return "/role/list";
    }
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(RoleAuth role, HttpServletRequest request, ModelMap model) {
        role.setIsSystem(false);
        roleService.saveRole(role,request.getParameterValues("authorities"));
        return "redirect:list.jhtml";
    }
    private String getRoleAuths(List<Authority> authorities){
        StringBuilder sb=new StringBuilder();
        if(authorities!=null){
            for (Authority at:authorities) {
                if(at.getMenuId()!=null) {
                    sb.append("," + at.getMenuId().toString());
                }
            }
            if(sb.length()>0){
                sb.append(",");
            }
        }
        return sb.toString();
    }

}