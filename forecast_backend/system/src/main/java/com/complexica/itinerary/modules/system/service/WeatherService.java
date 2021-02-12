package com.complexica.itinerary.modules.system.service;


import com.complexica.itinerary.modules.system.model.Weather;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface WeatherService {

    public List<Weather> getWeather(String cityName, String Country);


    public List<Weather> getApiWeather(String cityName, String Country);

}
