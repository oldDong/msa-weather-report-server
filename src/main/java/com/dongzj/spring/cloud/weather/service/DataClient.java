package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.vo.City;
import com.dongzj.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/9/3
 * Time: 13:57
 */
@FeignClient(value = "msa-weather-eureka-client-zuul",fallback = DataClientFallback.class)
public interface DataClient {

    /**
     * 获取城市列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/city/cities")
    List<City> listCity() throws Exception;

    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    @GetMapping("/data/weather/cityId/{cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);
}
