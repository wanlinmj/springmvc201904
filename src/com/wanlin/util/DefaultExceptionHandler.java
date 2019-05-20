package com.wanlin.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局处理异常
 * @author kangwl_pc
 *
 */
/* @Component */
public class DefaultExceptionHandler  implements HandlerExceptionResolver {

	private static Log logger = LogFactory.getLog(DefaultExceptionHandler.class);
	
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        logger.error(" ============  全局异常  =========== ",ex);
        // TODO
        // 自定义异常逻辑   捕获异常编码 ，转换成异常信息  返回到前端展示界面友好的错误信息  （花哨一点，皮一点）
        // 非自定义异常   错误日志记录 ，请求信息入库   触发提醒，修改对应bug  含糊提示用户 ，系统异常 
        Map<String,Object> model = new HashMap<String,Object>();
        if(null != ex){
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
        
        
        ModelAndView modelAndView = new ModelAndView("errorpage",model);
        return modelAndView;
    }
    
    /**
     * 如果我们要自定义异常解析器，如统一返回json格式的异常信息给客户端，那我们就需要自定义HandlerExceptionResolver。

		之前是实现的接口HandlerExceptionResolver，来处理ServiceException和内部错误，后来想处理参数绑定异常BindException，但死活不成功。
		
		原因在于，SpringMVC默认有多个异常解析器，DefaultHandlerExceptionResolver就是其中一个，而且它处理了BindException；
		同时，更为重要的是，（通过断点调试发现）它的顺序在自定义异常解析器之前，而org.springframework.web.servlet.DispatcherServlet
		#processHandlerException中是按照这些解析器的顺序来处理异常的，一旦前面的解析器处理产生结果，后面的将不再执行
		// 解析器顺序，越小发挥作用越早 
    public int getOrder() {
       return 0;
     }
     * @return
     */
 
	
}
