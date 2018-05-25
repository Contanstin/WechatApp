package com.hpmont.shiro;

import com.hpmont.constants.Setting;
import com.hpmont.domain.manual.admin.AdminUser;
import com.hpmont.service.adminuser.IAdminUserService;
import com.hpmont.service.adminuser.ICaptchaService;
import com.hpmont.util.Principal;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class AuthenticationRealm extends AuthorizingRealm {
	
	@Resource(name = "adminUserServiceImpl")
	private IAdminUserService adminService;
	
	@Resource(name = "captchaServiceImpl")
	private ICaptchaService captchaService;

	/**
	 * 获取认证信息
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token)
			throws AuthenticationException {
		com.hpmont.shiro.AuthenticationToken authenticationToken = (com.hpmont.shiro.AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
//		String ip = authenticationToken.getHost();
		if (!captchaService.isValid(Setting.CaptchaType.adminLogin, captchaId, captcha)) {
			throw new UnsupportedTokenException();
		}
		if (username != null && password != null) {
			AdminUser admin = null;
			try {
				admin = adminService.getUserByName(username);
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
			}
			if (admin == null) {
				throw new UnknownAccountException();
			}
			if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
				throw new IncorrectCredentialsException();
			}
			// admin.setLoginIp(ip);
			// admin.setLoginDate(new Date());
			// admin.setLoginFailureCount(0);
			// adminService.update(admin);
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());

		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 *
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = adminService.tgetUserAuthority(principal.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}
}
