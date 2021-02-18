package com.complexica.itinerary.modules.system.rest;

import cn.hutool.core.date.DateUtil;
import com.complexica.itinerary.modules.system.model.Weather;
import com.complexica.itinerary.modules.system.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import java.io.Serializable;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Controller for weather
 * accept Rest request
 * @author Li He
 * @date 2021-02-10
 */
@Api(tags = "Weather broadcast")
@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;



    @Autowired
    private RedisTemplate<Object, Object> redisCacheTemplate;

    /**
     * get 5 days weather forecast of the specific city
     * @param city city name
     * @param country  country abbreviation ,eg. AU Australia
     * @return
     */
    @ApiOperation(value = "query 5 days weather forecast of the specific city")
    @GetMapping(value = "/5daysWeather")
    public ResponseEntity getWeather(@RequestParam String city, @RequestParam String country){
        Date currentDate = new Date();
        //Generate the key for weather forecast cache
        String key = "weather:"+city+"-"+country+":"+DateUtil.format(currentDate,"yyyy-MM-dd");

        //query the weather forecast from the redis
        List<Weather> weathers = (List<Weather>) redisCacheTemplate.opsForValue().get(key);
        //when forecast do not exist in redis,call weather api.
        if(weathers==null) {
            List<Weather> result = weatherService.getApiWeather(city, country);
            //filter the record out of period: 12:00-18:00
            List<String> ftimes = Arrays.asList("12","13","14","15","16","17","18");
            System.out.println(ftimes);
            List<Weather> filteredResult = result.stream().filter(
                    r-> (ftimes.contains(r.getWeatherTime())) ).collect(Collectors.toList()
            );
            //Cache the city weather forecast in redis
            redisCacheTemplate.opsForValue().set(key, filteredResult, Duration.ofHours(1));
            return new ResponseEntity(filteredResult, HttpStatus.OK);
        }else{  //have weather from cache ,return weather forecast
            System.out.println(weathers.toString());
            return new ResponseEntity(weathers,HttpStatus.OK);
        }
    }
}
