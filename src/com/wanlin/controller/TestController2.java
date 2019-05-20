package com.wanlin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangwl_pc
 *
 */
@Controller
@RequestMapping("/test")
public class TestController2 {

	// http://localhost/springmvc/test/hellopage?name=xxx1235
    @RequestMapping("/hellopage")
    public String getHello(String name ,Map<String ,Object> map){
        String res = "reeee";
        System.out.println("============ hellopage ===================== " + name);	
        map.put("res", res + name);
        return "/hellopage";
    }

    // http://localhost/springmvc/test/showmeg?name=xxx123
    @ResponseBody
    @RequestMapping("/showmeg")
    public Map<String ,Object> showmeg(String name ,Map<String ,Object> map){
    	Map<String ,Object> result = new HashMap<String,Object>();
        String res = "reeee";
        result.put("res", res + name);
        return result;
    }
}
