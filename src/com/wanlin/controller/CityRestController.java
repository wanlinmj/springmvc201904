package com.wanlin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanlin.domain.City;
import com.wanlin.service.CityService;
import com.wanlin.util.CustomerException;

/**
 * @author kangwl_pc
 *
 */
@RestController
public class CityRestController {
	
	private Log logger = LogFactory.getLog(CityRestController.class);

    @Autowired
    private CityService cityService;
    // http://localhost/springmvc/api/city?cityName=xx
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {

    	logger.info("==================== startCity ========================" + cityName);

        return cityService.findCityByName(cityName);
    }
    //http://localhost/springmvc/api/sayHello?name=hong
    @RequestMapping(value = "/api/sayHello")
    public String sayHello(String name) {

    	name = " 你 好 呀  , " + name; 
        //return cityService.findCityByName(cityName);
        return name;
    }

    @RequestMapping(value = "/api/savecity" , method = RequestMethod.POST)
    public int saveCity(long provinceId,String cityName,String description) {
        int resultNum = 0;
        System.out.println("==================== provinceId ========================" + provinceId);
        City city = new City();
        city.setProvinceId(provinceId);
        city.setCityName(cityName);
        city.setDescription(description);
        resultNum = cityService.saveCity(city);
        return resultNum;
    }

    @RequestMapping(value = "/api/deletecity" , method = RequestMethod.GET)
    public int deleteCity(@RequestParam(value = "cityId", required = true) String cityId){
        int resultNum = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityId",cityId);
        if (!map.isEmpty()) resultNum = cityService.deleteCity(map);
        return resultNum;
    }

    @RequestMapping(value = "/api/updatecity" , method = RequestMethod.POST)
    public int updateCity(@RequestParam(value = "cityId", required = true) String cityId,
                                String cityName, String description){
        int resultNum = 0;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityId",cityId);
        map.put("cityName",cityName);
        map.put("description",description);
        if(!map.isEmpty() && !StringUtils.isEmpty(map.get("cityId")))
            resultNum = cityService.updateCity(map);
        return resultNum;
    }

    @RequestMapping(value="/api/findcitybycityid" , method = RequestMethod.GET)
    public City findCityByCityId(@RequestParam(value = "cityId", required = true) String cityId){
        City city = null;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityId",cityId);
        if (!map.isEmpty()) city = cityService.findCityById(map);
        return city;
    }

    @RequestMapping(value="/api/findcitybyname" , method = RequestMethod.GET)
    public List<City> findCityByName(@RequestParam(value = "cityName", required = true) String cityName){
        List<City> list = null;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityName",cityName);
        if(!map.isEmpty()) list = cityService.findCityByName(map);
        return list;
    }
    
    /**
     * http://localhost/springmvc/api/citymap?cityName=兰溪市
     * @param param 参数     任意数参数绑定到map 使用  @RequestParam
     * @return
     */
    @RequestMapping(value = "/api/citymap", method = RequestMethod.GET)
    public Map<String,Object> findOneCityMap(@RequestParam Map<String,Object> param) {
    	Map<String,Object> res = null;
        res = cityService.findCityMapByParam(param);
        return res;
    }
    
    /**
     * http://localhost/springmvc/api/citylistmap?cityName=wanlin
     * @param param 参数     任意数参数绑定到map 使用  @RequestParam
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/api/citylistmap", method = RequestMethod.GET)
    public List<Object> findCityListMap(@RequestParam Map<String,Object> param) throws Exception {
    	List<Object> res = new ArrayList<Object>();
        String cityName = param.get("cityName") + "";
        if("wanlin1".equals(cityName)){
        	int b = 0;
        	int a = 0;
        	try{
        		a = 5/b;
        	}catch(Exception e){
        		throw new CustomerException(e);
        	}
        	System.out.println(a);
        }
        
        if("wanlin2".equals(cityName)){
        	throw new CustomerException("wanlin2");
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityName","兰溪市");
        res.addAll(cityService.findCityByName(map));
        res.addAll(cityService.findCityListMapByParam(param));
        return res;
    }
}
