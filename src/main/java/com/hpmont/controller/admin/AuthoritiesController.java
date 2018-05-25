package com.hpmont.controller.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hpmont.controller.BaseController;
import com.hpmont.domain.manual.admin.AdminMenu;
import com.hpmont.domain.manual.admin.AdminUser;
import com.hpmont.domain.manual.TreeAdminMenu;
import com.hpmont.domain.manual.authority.AdminRoleList;
import com.hpmont.domain.manual.authority.UserAuthority;
import com.hpmont.service.adminuser.IAdminUserService;
import com.hpmont.util.page.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpmont.constants.Constant;
import com.hpmont.domain.manual.authority.AllUser;
import com.hpmont.service.adminuser.IAdminMenuService;

/**
 * Created by fanlinlin on 2016/9/9.
 */
@Controller("authoritiesController")
@RequestMapping("/authority")
public class AuthoritiesController extends BaseController {
	
	@Resource(name = "adminMenuServiceImpl")
	private IAdminMenuService adminMenuService;
	
	@Resource(name = "adminUserServiceImpl")
	private IAdminUserService adminUserService;

	@RequestMapping(value = "/authoritiesMenuList", method = RequestMethod.GET)
	public String showMenu(HttpServletRequest request, ModelMap model) {
		try {
			List<TreeAdminMenu> MenuLs = adminMenuService.searchTreeMenu();
			if (MenuLs != null && MenuLs.size() > 0) {
				model.addAttribute("MenuLs", MenuLs);
			}
		} catch (Exception ex) {
			logger.error("查询菜单异常", ex);
		}
		return "authority/menu";
	}

	@RequestMapping(value = "/updateMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String getMenu(HttpServletRequest request, AdminMenu adminMenu, ModelMap model) {
		try {
			model.addAttribute("id", adminMenu.getId());
			model.addAttribute("menuName", adminMenu.getMenuName());
			model.addAttribute("parentId", adminMenu.getParentId());
			model.addAttribute("description", adminMenu.getDescription());
			model.addAttribute("menuType", adminMenu.getMenuType());
			model.addAttribute("orderNum", adminMenu.getOrderNum());
			model.addAttribute("authorities", adminMenu.getAuthorities());
			model.addAttribute("menuUrl", adminMenu.getMenuUrl());
			adminMenuService.updateMenu(adminMenu);
		} catch (Exception ex) {
			logger.error("更新菜单异常", ex);
		}
		return "authority/menu";
	}

	@RequestMapping(value = "/deleteMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteMenu(HttpServletRequest request, Long id, ModelMap model) {
		try {
			model.addAttribute("id", id);
			adminMenuService.deleteMenu(id);
		} catch (Exception ex) {
			logger.error("删除菜单异常", ex);
		}
		return "authority/menu";
	}

	@RequestMapping(value = "/addMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String addMenu(HttpServletRequest request, AdminMenu adminMenu, ModelMap model) {
		try {
			model.addAttribute("menuName", adminMenu.getMenuName());
			model.addAttribute("parentId", adminMenu.getParentId());
			model.addAttribute("description", adminMenu.getDescription());
			model.addAttribute("menuType", adminMenu.getMenuType());
			model.addAttribute("authorities", adminMenu.getAuthorities());
			model.addAttribute("menuUrl", adminMenu.getMenuUrl());
			adminMenuService.addMenu(adminMenu);
		} catch (Exception ex) {
			logger.error("新增菜单异常", ex);
		}
		return "authority/menu";
	}

	@RequestMapping(value = "/showUserAuthority", method = { RequestMethod.GET, RequestMethod.POST })
	public String showUserAuthority(UserAuthority userAuthority, ModelMap model) {
		try {
			model.addAttribute("menuName", userAuthority.getMenuName());
			model.addAttribute("name", userAuthority.getName());
			Page<UserAuthority> ls = adminMenuService.selectUserAuthority(userAuthority, Constant.PAGEROWS);
			model.addAttribute("page", ls);
		} catch (Exception ex) {
			logger.error("查询用户菜单权限异常", ex);
		}
		return "authority/usermenu";
	}

	@RequestMapping(value = "/deleteUserAuthority", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteUserAuthority(Long id, ModelMap model) {
		try {
			model.addAttribute("id", id);
			adminMenuService.deleteAuthority(id);
		} catch (Exception ex) {
			logger.error("删除用户某菜单权限出错", ex);
		}
		return "authority/usermenu";
	}

	@RequestMapping(value = "/addUserAuthority", method = { RequestMethod.GET, RequestMethod.POST })
	public String addUserAuthority(Long roleId, Long[] ids, ModelMap model) {
		try {
			List<Long> menuIds = Arrays.asList(ids);
			adminMenuService.addAuthority(menuIds, roleId);
		} catch (Exception ex) {
			logger.error("添加用户某菜单权限出错", ex);
		}
		return "authority/menu";
	}

	@RequestMapping(value = "/userList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getUserList(HttpServletRequest request, ModelMap model) {
		try {
			List<AllUser> ls = adminUserService.getUserList();
			if (ls != null && ls.size() > 0) {
				model.addAttribute("ls", ls);
			}
		} catch (Exception ex) {
			logger.error("获取用户列表出错", ex);
		}
		return "authority/user";
	}

	@RequestMapping(value = "/adduser", method = { RequestMethod.GET, RequestMethod.POST })
	public String editUser(Long userId, ModelMap model) {
		model.addAttribute("roles", adminUserService.selectAdminRoleList());
		if (userId != null) {
			model.addAttribute("usermodel", adminUserService.getAdminUser(userId));
			model.addAttribute("userrole", adminUserService.getUserRole(userId));
			return "authority/useredit";
		}
		return "authority/useradd";
	}

	@RequestMapping(value = "/userAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public String addUser(AllUser allUser, HttpServletRequest request) {
		try {
			AdminUser adminUser = new AdminUser();
			adminUser.setCreateTime(new Date());
			adminUser.setUserName(allUser.getUserName());
			adminUser.setRealName(allUser.getRealName());
			adminUser.setDepartment(allUser.getDepartment());
			adminUser.setIsEnable(true);
			if (allUser.getPassword() != null) {
				adminUser.setPassword(DigestUtils.md5Hex(allUser.getPassword()));
			}
			adminUserService.addUser(adminUser, allUser.getRoleId(),request.getParameterValues("authCodeIds"));
		} catch (Exception ex) {
			logger.error("新增用户出错", ex);
		}
		return "redirect:userList.jhtml";
	}

	@RequestMapping(value = "/userUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateUser(AllUser allUser, HttpServletRequest request) {
		try {
			AdminUser adminUser = new AdminUser();
			adminUser.setModifyTime(new Date());
			adminUser.setId(allUser.getId());
			adminUser.setUserName(allUser.getUserName());
			adminUser.setRealName(allUser.getRealName());
			adminUser.setDepartment(allUser.getDepartment());
			if (allUser.getIsEnable() == null) {
				adminUser.setIsEnable(false);
			} else {
				adminUser.setIsEnable(allUser.getIsEnable());
			}
			if (allUser.getPassword() != null) {
				adminUser.setPassword(DigestUtils.md5Hex(allUser.getPassword()));
			}
			adminUserService.updateUser(adminUser, allUser.getRoleId(),request.getParameterValues("authCodeIds"));
		} catch (Exception ex) {
			logger.error("更新用户信息出错", ex);
		}
		return "redirect:userList.jhtml";
	}

	@RequestMapping(value = "/userDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteUser(Long id, ModelMap model) {
		try {
			model.addAttribute("id", id);
			adminUserService.deleteUser(id);
		} catch (Exception ex) {
			logger.error("删除用户信息", ex);
		}
		return "authority/user";
	}

	// 获取权限名称、Id，列表
	@RequestMapping(value = "/roleList", method = { RequestMethod.GET, RequestMethod.POST })
	public String getRoleList(HttpServletRequest request, ModelMap model) {
		try {
			List<AdminRoleList> ls = adminMenuService.getAdminRoleList();
			if (ls != null && ls.size() > 0) {
				model.addAttribute("ls", ls);
			}
		} catch (Exception ex) {
			logger.error("获取用户列表出错", ex);
		}
		return "authority/user";
	}

	@ResponseBody
	@RequestMapping(value = "/changPassword", method = { RequestMethod.POST })
	public String changePassword(HttpServletRequest request, ModelMap mode) {
		try {
			String password = request.getParameter("newpassword");
			String confirmPassword = request.getParameter("confirmpassword");
			if (password == null || password.trim().equals("")) {
				return errorResult("密码不能为空");
			}
			if (!password.equals(confirmPassword)) {
				return errorResult("两次输入的密码不一致");
			}
			AdminUser adminUser = new AdminUser();
			adminUser.setId(this.getCurrUserId().longValue());
			adminUser.setPassword(DigestUtils.md5Hex(password));
			adminUserService.changePassword(adminUser);
			return success();
		} catch (Exception ex) {
			logger.error("更新密码出错", ex);
			return errorResult("更新失败");
		}
	}

}
