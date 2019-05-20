package com.wanlin.util;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 单独配置信息读取工具
 * @author kangwl_pc
 *
 */
public class PropertiesUtils {

	private static Log logger = LogFactory.getLog(PropertiesUtils.class);
	private static String baseName = "config/error-meg";
	private static ResourceBundle res = ResourceBundle.getBundle(baseName);
	
	public static void setBaseName(String baseName) {
		PropertiesUtils.baseName = baseName;
		res = ResourceBundle.getBundle(PropertiesUtils.baseName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String key) {
		T obj = null;
		try {
			obj = (T)res.getObject(key);
		}catch(Exception e) {
			logger.error("配置文件读取出错 ",e);
		}
		return obj;
	}
	
	public static void main(String[] args) {
		
		String val = PropertiesUtils.getValue("wanlin.xxx01");
		System.out.println(val);
	}
}
