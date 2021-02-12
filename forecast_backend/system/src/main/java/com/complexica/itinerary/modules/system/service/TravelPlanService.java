package com.complexica.itinerary.modules.system.service;



import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author Li He
 * @date 2020-02-09
 */
//@CacheConfig(cacheNames = "travel_plan")
public interface TravelPlanService {


    Map findAll(int page, int pageSize);

    Map findAllItinerarys(int page, int pageSize);

    Map getItineraryDetail(Long itineraryId, int page, int pageSize);

    ItineraryDTO create(Itinerary resources);

    void save(List<PlanDetail> planDetails);

    void delete(Long id);
}
