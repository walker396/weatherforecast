package com.complexica.itinerary.modules.system.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * Weather Bean
 * @author Li He
 * @date 2021-02-10
 */
@Getter
@Setter
@Data
public class Weather implements Serializable {
    private Integer id;
    private String cityId;
    private String city;
    private String country;
    private String weatherDate;
    private String weatherTime;

    private String tempture;

    private String weather;
    private String user_id;
    private String orderId;

    public Weather(Integer id,String cityId, String city, String country, String weatherDate, String weatherTime, String tempture, String weather, String user_id, String orderId) {
        this.id = id;
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.weatherDate = weatherDate;
        this.weatherTime = weatherTime;
        this.tempture = tempture;
        this.weather = weather;
        this.user_id = user_id;
        this.orderId = orderId;
    }
}

