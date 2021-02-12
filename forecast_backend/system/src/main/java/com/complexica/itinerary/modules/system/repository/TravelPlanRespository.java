package com.complexica.itinerary.modules.system.repository;

import com.complexica.itinerary.modules.system.model.Itinerary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Respository for Travel plan
* @author Li He
* @date 2021-02-10
*/
public interface TravelPlanRespository extends JpaRepository<Itinerary, Long>, JpaSpecificationExecutor {

//    @Transactional
//    public void saveBatchJpa(List<PlanDetail> list) {
//        repository.save(list);
//    }

    @Query(value = "select it.id,it.name, it.user_id,it.create_time, pd.city,pd.country,pd.weather_date,pd.weather_time,"
            +"pd.tempture,pd.weather,pd.order_id " +
            "from itinerary it,plan_detail pd where it.id = pd.itinerary_id group by it.id",
            countQuery = "select count(*)" +
                    "from itinerary it " +
                    "  left join plan_detail pd on pd.itinerary_id = it.id"
            , nativeQuery = true) //
    Page<Map> selectAllItineraryAndDetails(Pageable pageable);

    @Query(value = "select it.id,it.name, it.user_id,it.create_time, pd.city,pd.country,pd.weather_date,pd.weather_time,"
            +"pd.tempture,pd.weather,pd.order_id " +
            "from itinerary it,plan_detail pd where it.id = pd.itinerary_id group by it.id",
            countQuery = "select count(*)" +
                    "from itinerary it " +
                    "  left join plan_detail pd on pd.itinerary_id = it.id" +
                    "where pd.city = :city and pd.country= :country"
            , nativeQuery = true) //
    Page<Map> selectItineraryDetailsByCity(@Param("city") String city, @Param("country") String country,Pageable pageable);
}
