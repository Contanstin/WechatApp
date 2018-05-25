package com.hpmont.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.hpmont.shiro.AuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hpmont.domain.manual.LoginData;
import com.google.common.base.Strings;

/**
 * Created by Administrator on 2016/6/8.
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * 登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginhome(HttpServletRequest request, ModelMap model, @ModelAttribute("errormsg") String excepstr) {
		String message = "";
		if (!Strings.isNullOrEmpty(excepstr)) {
			if (excepstr.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
				message = "admin.captcha.invalid";
			} else if (excepstr.equals("org.apache.shiro.authc.UnknownAccountException")) {
				message = "admin.login.unknownAccount";
			} else if (excepstr.equals("org.apache.shiro.authc.DisabledAccountException")) {
				message = "admin.login.disabledAccount";
			} else if (excepstr.equals("org.apache.shiro.authc.LockedAccountException")) {
				message = "admin.login.lockedAccount";
			} else if (excepstr.equals("org.apache.shiro.authc.AuthenticationException")) {
				message = "admin.login.authentication";
			} else if (excepstr.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
				message = "admin.login.incorrectCredentials";
			} else {
				message = "admin.login.loginerror";
			}
		}
		if (!Strings.isNullOrEmpty(message)) {
			model.addAttribute("errmsg", message);
		}
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/login";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		return "redirect:/login.jhtml";
	}

	@RequestMapping(value = "404.html", method = RequestMethod.GET)
	public String notfind() {
		return "/404";
	}

	/**
	 * 登录提交控制
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(LoginData user, RedirectAttributes redirectAttributes) {
		try {
			SecurityUtils.getSubject().login(new AuthenticationToken(user.getUserName(), user.getPassword(),
					user.getCaptchaId(), user.getCaptcha()));
		} catch (AuthenticationException ex) {
			redirectAttributes.addFlashAttribute("errormsg", ex.toString());
			return "redirect:/login.jhtml";
		}
		return "redirect:/common/main.jhtml";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				subject.logout();
			}
		} catch (Exception ex) {
			logger.error("退出登录失败", ex);
		}
		return "redirect:/login.jhtml";
	}
}
