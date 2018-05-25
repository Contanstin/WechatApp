package com.hpmont.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpmont.domain.manual.TreeAdminMenu;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hpmont.service.adminuser.IAdminMenuService;
import com.hpmont.service.adminuser.ICaptchaService;
import com.google.common.base.Strings;

/**
 * Created by Administrator on 2016/6/14.
 */
@Controller("commonController")
@RequestMapping("/common")
public class CommonController extends BaseController{
    @Resource(name = "adminMenuServiceImpl")
    private IAdminMenuService adminMenuService;

    @Resource(name = "captchaServiceImpl")
    private ICaptchaService captchaService;
    /**
     * 主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, ModelMap model) {
        List<TreeAdminMenu> lsmenu=adminMenuService.searchMenuByRoleId(this.getCurrUserId().longValue());
        if(lsmenu!=null && lsmenu.size()>0) {
            model.addAttribute("username",this.getCurrUserName());
            model.addAttribute("menus", lsmenu);
            if(lsmenu.size()>0 || lsmenu.get(0).getSubmenu().size()>1) {
                model.addAttribute("firsturl",lsmenu.get(0).getSubmenu().get(0).getMenuUrl());
            }
        }
        return "/common/main";
    }

    /**
     * 权限错误
     */
    @RequestMapping("/unauthorized")
    public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            response.addHeader("loginStatus", "unauthorized");
            try {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (IOException e) {
                logger.error("显示无效权限页面出错",e);
            }
            return null;
        }
        return "common/unauthorized";
    }
    /**
     * 验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (Strings.isNullOrEmpty(captchaId)) {
            captchaId = request.getSession().getId();
        }
        String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
        String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
        response.addHeader(pragma, value);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            BufferedImage bufferedImage = captchaService.buildImage(captchaId);
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }

}
