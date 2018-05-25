package com.hpmont.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/12.
 */
public class DateUtil {
	
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmssSSS";
	
	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";
	
	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	public static Date getStr2Date(String dateStr, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(dateStr);
		} catch (java.text.ParseException ex) {
			return null;
		}
	}

	public static String getAddCurrDate2Str(int days, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return sdf.format(cal.getTime());
	}

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmssfff为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 日期转换为string型
	 * 
	 * @param dt
	 * @param format
	 * @return
	 */
	public static String getDateStr(Date dt, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(dt);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrDateTime() {
		return Calendar.getInstance().getTime();
	}
}
