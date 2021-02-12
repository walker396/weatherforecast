package com.complexica.itinerary.modules.system.mapper;

import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItineraryMapper extends EntityMapper<ItineraryDTO, Itinerary> {
//    @Mapping(source = "userId", target = "userId")
//    Itinerary toEntity(ItineraryDTO itineraryDTO, String userId);
}
