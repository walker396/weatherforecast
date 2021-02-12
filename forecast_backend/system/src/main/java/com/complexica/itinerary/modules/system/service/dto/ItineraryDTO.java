package com.complexica.itinerary.modules.system.service.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ItineraryDTO for REST Data transform
 * @author Li He
 * @date 2021-02-10
 */
@Data
@Getter
@Setter
public class ItineraryDTO implements Serializable {


    private Long id;

    private String name;
    private Long userId;
    private String status;
    private Date createTime;

    private List<PlanDetailDTO> planDetails;



    public void copy(ItineraryDTO source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
