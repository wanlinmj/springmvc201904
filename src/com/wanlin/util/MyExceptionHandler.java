package com.wanlin.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

	private static final Log logger = LogFactory.getLog(MyExceptionHandler.class);
	  /**
	            通过@ControllerAdvice注解可以将对于控制器的全局配置放在同一个位置。
		注解了@Controller的类的方法可以使用@ExceptionHandler、@InitBinder、@ModelAttribute注解到方法上。
		@ControllerAdvice注解将作用在所有注解了@RequestMapping的控制器的方法上
		@ExceptionHandler：用于全局处理控制器里的异常。
	  */
	@ExceptionHandler(value=Exception.class)
	public Object exceptionHandler(HttpServletRequest request , Exception ex){
		Map<String,Object> model = new HashMap<String,Object>();
		if(null != ex){
			logger.error("错误信息",ex);
        	// 获取 自定义异常编码
        	String errorCode = ex.getMessage();
        	// 从错误编码中获取对应错误提示信息，用于生成用户提示信息
        	if("wanlin2".equals(errorCode)){
        		String val = PropertiesUtils.getValue("wanlin.xxx01");
        		model.put("meg", errorCode + val);
        	}else{
        		model.put("meg", "  系统发生异常，请稍后 ...  ");
        	}
        }
		return model;
	}
	
	

	/**
	 * @ControllerAdvice是一个@Component，用于定义@ExceptionHandler，
	 * @InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法。
	 * Spring4之前，@ControllerAdvice在同一调度的Servlet中协助所有控制器。
	 * Spring4已经改变：@ControllerAdvice支持配置控制器的子集，而默认的行为仍然可以利用。
	    *   在Spring4中， @ControllerAdvice通过annotations(), basePackageClasses(),
	 * basePackages()方法定制用于选择控制器子集。
	 */
}
