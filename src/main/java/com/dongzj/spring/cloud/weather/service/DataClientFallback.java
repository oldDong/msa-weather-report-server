package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.vo.City;
import com.dongzj.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/9/4
 * Time: 17:22
 */
@Component
public class DataClientFallback implements DataClient{

    @Override
    public List<City> listCity() throws Exception {
        List<City> cityList = new ArrayList<>();
        City city = new City();
        city.setCityId("101210107");
        city.setCityName("临安");
        city.setCityCode("linan");
        city.setProvince("浙江");
        cityList.add(city);
        return cityList;
    }

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        return new WeatherResponse();
    }
}
