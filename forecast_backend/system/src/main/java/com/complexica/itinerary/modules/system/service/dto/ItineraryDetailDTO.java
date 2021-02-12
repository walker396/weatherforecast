package com.complexica.itinerary.modules.system.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Data
@Getter
@Setter
public class ItineraryDetailDTO  implements Serializable {
    private Long id;

    private String  name;
    private Long    userId;
    private String  status;
    private Date    createTime;

    private String  cityId;
    private String  city;
    private String  country;
    private Date    weatherDate;
    private String  weatherTime;
    private String  tempture;
    private String  weather;
    private Integer orderId;
}
