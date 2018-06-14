package com.hpmont.interceptor;

import com.hpmont.domain.manual.admin.AdminLog;
import com.hpmont.service.log.IAdminLogService;
import com.hpmont.util.Principal;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/22.
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
	@Resource(name = "adminLogServiceImpl")
	private IAdminLogService logService;
	/** 默认忽略参数 */
	private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[] { "password", "rePassword",
			"currentPassword" };
	/** 默认忽略地址 */
	private static final String[] DEFAULT_IGNORE_URL = new String[] { "/common/main.jhtml", "/common/captcha.jhtml",
			"/login.jhtml","/slideshow/showPic","/slideshow/showPic","/slideshow/download","/manual/download" };
	/** antPathMatcher */
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();
	/** 忽略参数 */
	private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;
	/** 忽略参数 */
	private String[] ignoreUrls = DEFAULT_IGNORE_URL;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String username = this.getCurrentUsername();
		String path = request.getServletPath();
		if (this.ignoreUrls != null && this.ignoreUrls.length > 0) {
			for (String parurl : ignoreUrls) {
				if (antPathMatcher.match(parurl, path)) {
					return;
				}
			}
		}
		String operation = path;
		String operator = username;
		String ip = request.getRemoteAddr();
		StringBuffer parameter = new StringBuffer();
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap != null) {
			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				String parameterName = entry.getKey();
				if (!ArrayUtils.contains(ignoreParameters, parameterName)) {
					String[] parameterValues = entry.getValue();
					if (parameterValues != null) {
						for (String parameterValue : parameterValues) {
							parameter.append(parameterName + " = " + parameterValue + "\n");
						}
					}
				}
			}
		}
		if (operation!=null&&operation.contains("list"))
			return;
		AdminLog log = new AdminLog();
		log.setCreateTime(new Date());
		log.setOperation(operation);
		log.setOperator(operator);
		log.setParameter(parameter.toString());
		log.setIp(ip);
		if (StringUtils.isNotEmpty(log.getParameter())&&null!=log.getOperation())
		 logService.addLog(log);
	}

	/**
	 * 设置忽略参数
	 *
	 * @return 忽略参数
	 */
	public String[] getIgnoreParameters() {
		return ignoreParameters;
	}

	/**
	 * 设置忽略参数
	 *
	 * @param ignoreParameters
	 *            忽略参数
	 */
	public void setIgnoreParameters(String[] ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}

	private String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}
}
