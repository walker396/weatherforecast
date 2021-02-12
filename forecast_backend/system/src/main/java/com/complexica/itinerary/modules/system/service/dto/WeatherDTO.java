package com.complexica.itinerary.modules.system.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * WeatherDTO for REST Data transform
 * @author Li He
 * @date 2021-02-10
 */
@Getter
@Setter
@Data
public class WeatherDTO implements Serializable {
    private Integer id;
    private String cityId;
    private String city;
    private String country;
    private Date weatherDate;
    private String weatherTime;

    private String tempture;

    private String weather;
    private String user_id;
    private String orderId;

    public WeatherDTO(String cityId, String city, String country, Date weatherDate, String weatherTime, String tempture, String weather, String user_id, String orderId) {
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
