package com.hpmont.controller;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.hpmont.constants.Constant;
import com.hpmont.util.DateEditor;
import com.hpmont.util.Message;
import com.hpmont.util.Principal;
import com.hpmont.util.SpringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 错误视图 */
	protected static final String ERROR_VIEW = "/admin/common/error";

	/** 错误消息 */
	protected static final Message ERROR_MESSAGE = Message.error("admin.message.error");

	/** 成功消息 */
	protected static final Message SUCCESS_MESSAGE = Message.success("admin.message.success");

	/** "验证结果"参数名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	@Resource(name = "validator")
	private Validator validator;
	
	/**将返回结果转换成json并添加默认成功的状态*/
	protected String jsonResult(Object object){
		return jsonResult(object, Constant.SUCCESS, null);
	}
	
	/**返回成功信息*/
	protected String success(){
		return jsonResult(null, Constant.SUCCESS, null);
	}
	
	/**返回错误信息*/
	protected String errorResult(String errorMsg){
		return jsonResult(null, Constant.FAIL, errorMsg);
	}
	
	/**返回错误信息*/
	protected String errorResult(String errorMsg, Object data){
		return jsonResult(data, Constant.FAIL, errorMsg);
	}
	
	/**将返回结果转换成json并添加状态及提示消息*/
	protected String jsonResult(Object object, 
			String status, String errorMsg){
		JSONObject result = new JSONObject();
		
		if(object != null)
			result.put("data", JSON.toJSONString(object));
		if(errorMsg != null)
			result.put(Constant.MESSAGE, errorMsg);
		
		result.put(Constant.CODE, status);
		
		return result.toString();
	}

	/**
	 * 数据绑定
	 *
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	protected Integer getCurrUserId() {
		return ((Principal) SecurityUtils.getSubject().getPrincipal()).getId().intValue();
	}

	protected String getCurrUserName() {
		return ((Principal) SecurityUtils.getSubject().getPrincipal()).getUsername();
	}

	/**
	 * 数据验证
	 *
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
					RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 数据验证
	 *
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
					RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 货币格式化
	 *
	 * @param amount
	 *            金额
	 * @param showSign
	 *            显示标志
	 * @param showUnit
	 *            显示单位
	 * @return 货币格式化
	 */
	// protected String currency(BigDecimal amount, boolean showSign, boolean
	// showUnit) {
	// Setting setting = SettingUtils.get();
	// String price = setting.setScale(amount).toString();
	// if (showSign) {
	// price = setting.getCurrencySign() + price;
	// }
	// if (showUnit) {
	// price += setting.getCurrencyUnit();
	// }
	// return price;
	// }

	/**
	 * 获取国际化消息
	 *
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String message(String code, Object... args) {
		return SpringUtils.getMessage(code, args);
	}

	/**
	 * 添加瞬时消息
	 *
	 * @param redirectAttributes
	 *            RedirectAttributes
	 * @param message
	 *            消息
	 */
	// protected void addFlashMessage(RedirectAttributes redirectAttributes,
	// Message message) {
	// if (redirectAttributes != null && message != null) {
	// redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME,
	// message);
	// }
	// }

	/**
	 * 添加日志
	 *
	 * @param content
	 *            内容
	 */
	// protected void addLog(String content) {
	// if (content != null) {
	// RequestAttributes requestAttributes =
	// RequestContextHolder.currentRequestAttributes();
	// requestAttributes.setAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME, content,
	// RequestAttributes.SCOPE_REQUEST);
	// }
	// }

}
