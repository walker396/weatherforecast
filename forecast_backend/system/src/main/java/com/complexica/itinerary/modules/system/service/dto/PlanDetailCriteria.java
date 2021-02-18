package com.complexica.itinerary.modules.system.service.dto;

import com.complexica.annotation.Query;
import com.complexica.itinerary.modules.system.model.Itinerary;
import lombok.Data;

@Data
public class PlanDetailCriteria {


    // right blur
    @Query(type = Query.Type.EQUAL)
    private Itinerary itinerary;
}
