package com.complexica.itinerary.modules.system.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Plan Detail Bean
 * @author Li He
 * @date 2021-02-10
 */

@Entity
@Data
@Table(name="plan_detail")
public class PlanDetail  implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    @NotNull(groups = Update.class)
    private Long id;

    @Column(name="city_id")
    private String cityId;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @Column(name="weather_date")
    private Date weatherDate;
    @Column(name="weather_time")
    private String weatherTime;

    @Column(name="tempture")
    private String tempture;

    @Column(name="weather")
    private String weather;
    @Column(name="user_id")
    private Long user_id;
    @Column(name="order_id")
    private Integer orderId;


    @Column(name="itinerary_id")
    private Long itineraryId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(String weatherTime) {
        this.weatherTime = weatherTime;
    }

    public String getTempture() {
        return tempture;
    }

    public void setTempture(String tempture) {
        this.tempture = tempture;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public @interface Update {}

    public void copy(PlanDetail source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }



}
