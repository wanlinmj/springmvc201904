package com.wanlin.util;

import com.wanlin.annotations.Wanlin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 
 * @author kangwl_pc
 *
 */
// 不启用 aspectj  注释不加载bean 即可
@Component
@Aspect
public class TestInterceptor {

 
    private Log logger = LogFactory.getLog(TestInterceptor.class);

    
    /**
     *    监测方法执行过程
     */
    @Around("@annotation(wanlin)")
    public Object exec(ProceedingJoinPoint invocation , Wanlin wanlin) throws Throwable {
    	Object result;
    	String dateTime = DateFormatUtil.date2String(new Date(), DateFormatUtil.pattern2);

        try{
        	result = invocation.proceed(); 
           System.out.println("time ========================>" + dateTime + "   isMark ===" + wanlin.isMark());
         }finally{
            logger.info("come in ........");
         }

        return result;  
    } 
    


 
}
