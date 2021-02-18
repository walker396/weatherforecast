package com.complexica.itinerary.modules.system.service.dto;

import com.complexica.annotation.Query;
import lombok.Data;

@Data
public class ItineraryCriteria {
    @Query
    private String userId;

    // right blur
    @Query(type = Query.Type.RIGHT_LIKE)
    private String name;
}
