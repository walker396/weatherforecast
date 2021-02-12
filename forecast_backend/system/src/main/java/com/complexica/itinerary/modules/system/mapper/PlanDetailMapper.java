package com.complexica.itinerary.modules.system.mapper;

import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.itinerary.modules.system.service.dto.PlanDetailDTO;
import com.complexica.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanDetailMapper extends EntityMapper<PlanDetailDTO, PlanDetail> {
//    @Mapping(source = "itinerary.id", target = "itinerary.id")
//    PlanDetail toEntity(List<PlanDetailDTO> itineraryDTO);
}
