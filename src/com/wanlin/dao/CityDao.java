package com.wanlin.dao;

import java.util.List;
import java.util.Map;

import com.wanlin.domain.City;

public interface CityDao {
	/**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(String cityName);

    /**
     * 保存城市信息
     * @param map 城市属性信息
     * @return 成功操作的记录条数
     */
    int saveCity(Map<String, Object> map);

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
     * 根据名称获取城市数据
     * @param map 城市名称
     * @return 城市信息列表
     */
    Map<String, Object> findCityMapByParam(Map<String, Object> param);
    
    /**
     * 根据名称获取城市数据列表
     * @param map 城市名称
     * @return 城市信息列表
     */
    List<Map<String, Object>> findCityListMapByParam(Map<String, Object> param);
}
