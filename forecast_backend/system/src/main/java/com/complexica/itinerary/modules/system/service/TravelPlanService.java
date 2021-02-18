package com.complexica.itinerary.modules.system.service;



import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import com.complexica.itinerary.modules.system.service.dto.ItineraryCriteria;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.itinerary.modules.system.service.dto.PlanDetailCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author Li He
 * @date 2020-02-09
 */
@CacheConfig(cacheNames = "itinerary")
public interface TravelPlanService {


//    Map findAll(int page, int pageSize);
    @Cacheable(keyGenerator = "keyGenerator")
    Map findAllItinerarys(int page, int pageSize);

    @Cacheable(keyGenerator = "keyGenerator")
    List<Itinerary>  findItinerarysByName(ItineraryCriteria criteria);

    Map getItineraryDetail(Long itineraryId, int page, int pageSize);

    @Cacheable(keyGenerator = "keyGenerator")
    Map getItineraryDetailByCriteria(PlanDetailCriteria criteria, int page, int pageSize);

    @CacheEvict(allEntries = true)
    ItineraryDTO create(Itinerary resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Itinerary resources);


    void save(List<PlanDetail> planDetails);





    @CacheEvict(allEntries = true)
    void delete(Long id);
}
