package com.complexica.itinerary.modules.system.mapper;

import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDetailDTO;
import com.complexica.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ItineraryDetailsMapper extends EntityMapper<ItineraryDetailDTO, HashMap> {
//    @Mapping(source = "userId", target = "userId")
//    Itinerary toEntity(ItineraryDTO itineraryDTO, String userId);
}
