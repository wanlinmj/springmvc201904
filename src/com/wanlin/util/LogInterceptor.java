package com.wanlin.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
 
/**
 * 
 * @author kangwl_pc
 *
 */
// 不启用 aspectj  注释不加载bean 即可
@Component
@Aspect
public class LogInterceptor {
    @Resource
    private HttpServletRequest request;
 
    public Log log = LogFactory.getLog(LogInterceptor.class);
    
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    StringBuilder requestInfo = new StringBuilder("");
 
    @Pointcut("execution(* com.wanlin.controller*..*(..))")
    public void pointCut() {
        log.info(request.getParameterMap());
    }
    
    /**
                *    监测方法执行过程
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Around("pointCut()") 
    public Object exec(ProceedingJoinPoint invocation) throws Throwable { 
    	Object result = null;
    	String dateTime = DateFormatUtil.date2String(new Date(), DateFormatUtil.pattern2);
    	requestInfo.append("\nSpringMVC action report ------------------- " + dateTime + " -----------------\n");
    	long begin = System.currentTimeMillis();
        requestInfo.append(getController(invocation));
        requestInfo.append(getMethod(invocation));
        requestInfo.append(getUri());
        requestInfo.append(getParameter(invocation));
        requestInfo.append("RemoteAddr  : " + LogInterceptor.getIpAddr(request) + "\n");
        try{
        	result = invocation.proceed(); 
            long end = System.currentTimeMillis();
            long exeTime = end - begin;
            requestInfo.append("exeTime     : " + exeTime + " 毫秒 \n");
      	    requestInfo.append(getReturn(result));
         }finally{
        	 requestInfo.append("--------------------------------------------------------------------------------\n");
      	   	 log.info("	=================== 请求信息  ================= ");
             log.info(requestInfo.toString());
             requestInfo.setLength(0); // 清除stringBuffer内容
         }
        /**
         * 不需要异常处理 ，异常处理由全局捕获处理
         * catch(Exception e){
      	   log.error("===============请求信息记录出错==========",e);
         }
         */
        
        return result;  
    } 
    
    
 
  /*  @AfterReturning(pointcut = "pointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
       try{
    	  // devEnvironmentLog(joinPoint, returnValue);
    	   requestInfo.append(getReturn(returnValue));
    	   requestInfo.append("--------------------------------------------------------------------------------\n");
       }catch(Exception e){
    	   log.error("===============请求信息记录出错==========",e);
       }finally{
    	   log.info("	=================== 请求信息 ================ ：");
           log.info(requestInfo.toString());
           requestInfo.setLength(0); // 清除stringBuffer内容
       }
    	
    }*/
 
  /*  @Around("pointCut()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around start..");
		try {
			pjp.proceed();
		} catch (Throwable ex) {
			System.out.println("error in around");
			throw ex;
		}
		System.out.println("around end");
	}*/
    
  /*  @AfterThrowing(pointcut = "pointCut()", throwing = "error")
	public String afterThrowing(JoinPoint jp, Throwable error) {
		System.out.println("error:" + error);
		return "dddd";
	}*/
    
   /**
    * 
    * @param joinPoint
    * @param returnValue
    */
    public void devEnvironmentLog(JoinPoint joinPoint, Object returnValue) {
        StringBuilder sb = new StringBuilder("\nSpringMVC action report -------- ")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .append(" ------------------------------\n");
        sb.append(getController(joinPoint));
        sb.append(getMethod(joinPoint));
        sb.append(getUri());
        sb.append(getParameter(joinPoint));
        sb.append("RemoteAddr  : " + LogInterceptor.getIpAddr(request) + "\n");
        sb.append(getReturn(returnValue));
        sb.append("--------------------------------------------------------------------------------\n");
        log.info("	=================== 请求信息 ================ ：");
        log.info(sb.toString());
    }
 
    
  /*  private void productEnvironmentLog(JoinPoint joinPoint, Object returnValue) {
        StringBuilder sb = new StringBuilder();
 
        sb.append(request.getRequestURI()).append(", ");
        sb.append("IP: " + LogInterceptor.getIpAddr(request)).append(", [");
 
        Map<String, String[]> parameters = request.getParameterMap();
 
        for (Map.Entry<String, String[]> entity : parameters.entrySet()) {
 
            sb.append(String.format("%s = %s, ", entity.getKey(), StringUtils.join(entity.getValue(), ',')));
        }
 
        sb.delete(sb.length() - 2, sb.length()).append("]");
 
        log.info(sb.toString());
    }*/
 
    private Map<String, MultipartFile> getRequestFileMap(JoinPoint joinPoint) {
        Map<String, MultipartFile> fileMap = null;
        if (true) {
            Object[] args = joinPoint.getArgs();
            for (Object object : args) {
                if (object instanceof MultipartHttpServletRequest) {
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) object;
                    fileMap = multipartHttpServletRequest.getFileMap();
                    break;
                }
            }
        }
        return fileMap;
    }
 
 
    private String getController(JoinPoint joinPoint) {
        return new StringBuffer().append("Controller  : ").append(joinPoint.getTarget().getClass().getName()).append(".(")
                .append(joinPoint.getTarget().getClass().getSimpleName()).append(".java:1)").toString();
    }
 
    private String getMethod(JoinPoint joinPoint) {
        return new StringBuffer().append("\nMethod      : ").append(joinPoint.getSignature().getName()).append("\n").toString();
    }
 
    private String getUri() {
        String uri = request.getRequestURI();
        if (uri != null) {
            return new StringBuffer().append("url         : ").append(uri)
                    .append(" user=" + request.getRemoteUser()).append("\n").toString();
        }
        return "";
    }
 
    @SuppressWarnings("unchecked")
	private String getParameter(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer();
        Map<String, MultipartFile> fileMap = getRequestFileMap(joinPoint);
        Enumeration<String> e = request.getParameterNames();
        if (e.hasMoreElements() || (fileMap != null && fileMap.size() > 0)) {
            sb.append("Parameter   : ");
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    sb.append(name).append("=").append(values[0]);
                } else {
                    sb.append(name).append("[]={");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0)
                            sb.append(",");
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
                sb.append("  ");
            }
            if (fileMap != null && fileMap.size() > 0) {
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                    MultipartFile file = entry.getValue();
                    sb.append(entry.getKey()).append("=").append(file.getOriginalFilename());
                    sb.append(" (contentType=" + file.getContentType() + ",size=" + file.getSize() + ")");
                    sb.append("  ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
 
    private String getReturn(Object returnValue) {
        StringBuffer sb = new StringBuffer();
        String returnJSON = "";
        returnJSON = JSONObject.toJSONString(returnValue);
        sb.append("return      : " + returnJSON);
        sb.append("\n");
        return sb.toString();
    }
 
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader( "x-forwarded-for" );
        if (ip == null || ip.length() == 0 || " unknown " .equalsIgnoreCase(ip)) {
            ip = request.getHeader( "Proxy-Client-IP" );
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader( "WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")){
            ip = "127.0.0.1";
        }
        return ip;
    }
 
}
