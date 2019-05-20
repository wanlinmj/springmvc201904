package com.wanlin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期格式化工具
 * @author kangwl_pc
 *
 */
public class DateFormatUtil {

	private static final String defaultPattern = "yyyy-MM-dd";
	
	/**
	 * 日期格式 	yyyy-MM-dd HH:mm
	 */
	public static final String pattern1 = "yyyy-MM-dd HH:mm";
	
	/**
	 * 日期格式 	yyyy-MM-dd HH:mm:ss
	 */
	public static final String pattern2 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 字符串转日期
	 * @param datestr   日期字符串
	 * @param pattern	日期格式
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String datestr, String pattern) throws ParseException{
		DateFormat format = DateFormatUtil.gerDateFormat(pattern);
		return format.parse(datestr);
	}
	
	/**
	 * 日期转字符串
	 * @param date 		日期
	 * @param pattern	日期格式
	 * @return
	 */
	public static String date2String(Date date, String pattern){
		DateFormat format = DateFormatUtil.gerDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 
	 * @param pattern
	 * @return
	 */
	private static DateFormat gerDateFormat(String pattern){
		if(StringUtils.isBlank(pattern)){
			pattern = defaultPattern;
		}
		DateFormat format = new SimpleDateFormat(pattern);
		return format;
	}
}
