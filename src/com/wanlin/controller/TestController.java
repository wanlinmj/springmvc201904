package com.wanlin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangwl_pc
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String getHello(String name ){
        String res = "reeee";
        System.out.println("name +++ " + name);	
        return res + name;
    }

    @RequestMapping("/getdata/{id}")
    public String getdata(@PathVariable("id")String id ){
        String res = "reeee";
        System.out.println("id +++ " + id);	
        return res + id;
    }
    // http://localhost/springmvc/test/getdataJSON/xxx123
    @RequestMapping("/getdataJSON/{id}")
    public Map<String,Object> getdataJSON(@PathVariable("id")String id ){
        String res = "reeee";
        System.out.println("id  +++ " + id);	
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("keyy", res + id);
        return map;
    }
}
