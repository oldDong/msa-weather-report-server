package com.dongzj.spring.cloud.weather.controller;

import com.dongzj.spring.cloud.weather.service.DataClient;
import com.dongzj.spring.cloud.weather.service.WeatherReportService;
import com.dongzj.spring.cloud.weather.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/30
 * Time: 17:08
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService weatherReportService;

    @Autowired
    private DataClient dataClient;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        // 由城市数据API微服务来提供数据
        List<City> cityList = null;
        try {
            cityList = dataClient.listCity();
        } catch (Exception e) {
            logger.error("获取城市信息异常！", e);
            throw new RuntimeException("获取城市信息异常！", e);
        }
        model.addAttribute("title", "老董的天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("/weather/report", "reportModel", model);
    }
}
