package com.wanlin.service;

import java.util.List;
import java.util.Map;

import com.wanlin.domain.City;

/**
 * 城市业务逻辑接口类
 *
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);

    /**
     * 保存城市信息
     * @param city
     */
    int saveCity(City city);

    /**
     * 根据城市ID删除城市信息
     * @param map 城市属性信息
     * @return 成功操作的记录条数
     */
    int deleteCity(Map<String, Object> map);

    /**
     * 修改城市信息
     * @param map 城市属性信息
     * @return 成功操作的记录条数
     */
    int updateCity(Map<String, Object> map);

    /**
     * 根据城市ID获取城市信息
     * @param map 城市ID
     * @return 城市信息
     */
    City findCityById(Map<String, Object> map);

    /**
     * 根据名称获取城市列表
     * @param map 城市名称
     * @return 城市信息列表
     */
    List<City> findCityByName(Map<String, Object> map);

    /**
     * 根据参数获取数据
     * @param param
     * @return
     */
	Map<String, Object> findCityMapByParam(Map<String, Object> param);

	List<Map<String, Object>> findCityListMapByParam(Map<String, Object> param);


}
