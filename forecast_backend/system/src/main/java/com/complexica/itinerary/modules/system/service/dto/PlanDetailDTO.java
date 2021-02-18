package com.complexica.itinerary.modules.system.service.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.complexica.itinerary.modules.system.model.Itinerary;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PlanDetailDTO for REST Data transform
 * @author Li He
 * @date 2021-02-10
 */
@Data
@NoArgsConstructor
public class PlanDetailDTO implements Serializable {

    @NotNull
    private Long id;
    @NotBlank
    private String cityId;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotNull
    private Date weatherDate;
    @NotBlank
    private String weatherTime;
    @NotBlank
    private String tempture;
    @NotBlank
    private String weather;
    private Long user_id;
    private Integer orderId;
    private ItineraryDTO itineraryDTO;
    public void copy(PlanDetailDTO source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }


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

    public ItineraryDTO getItineraryDTO() {
        return itineraryDTO;
    }

    public void setItineraryDTO(ItineraryDTO itineraryDTO) {
        this.itineraryDTO = itineraryDTO;
    }
}
