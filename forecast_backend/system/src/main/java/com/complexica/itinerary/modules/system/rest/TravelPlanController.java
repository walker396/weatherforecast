package com.complexica.itinerary.modules.system.rest;

import com.complexica.itinerary.modules.system.mapper.ItineraryMapper;
import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import com.complexica.itinerary.modules.system.service.TravelPlanService;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.itinerary.modules.system.mapper.PlanDetailMapper;
import com.complexica.itinerary.modules.system.service.dto.PlanDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for Travel plan
 * accept Rest request
 * @author Li He
 * @date 2021-02-10
 */
@RestController
@RequestMapping("travelPlan")
public class TravelPlanController {

    @Autowired
    private TravelPlanService travelPlanService;
    @Resource
    private PlanDetailMapper planDetailMapper;

    @Resource
    private ItineraryMapper itineraryMapper;

    /**
     * query all itinerary detail
      */
    @RequestMapping("/getAll/{currentpage}/{pageSize}")
    public ResponseEntity getItineraryDetails(@PathVariable("currentpage") int currentpage,
                                              @PathVariable("pageSize") int pageSize){
        return new ResponseEntity(travelPlanService.findAllItinerarys(currentpage,pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/getDetailById/{itineraryId}/{currentpage}/{pageSize}")
    public ResponseEntity getDictDetailMaps(@PathVariable("itineraryId") Long itineraryId,
                                            @PathVariable("currentpage") int currentpage,
                                            @PathVariable("pageSize") int pageSize){
        return new ResponseEntity(travelPlanService.getItineraryDetail(itineraryId,currentpage,pageSize), HttpStatus.OK);
    }


    /**
     *
     * @param resources Itinerary data transform obj
     * @return ResponseEntity Encapsulate the returned result to the ResponseEntity object
     */
    @PostMapping(value = "/add")
    @Transactional
    public ResponseEntity create(@Validated @RequestBody ItineraryDTO resources){
        try{
            //Itinerary Mappter trasform ItineraryDTO to Itinerary model
            Itinerary itinerary = itineraryMapper.toEntity(resources);
            itinerary.setId(-1L);
            //save Itinerary to db
            ItineraryDTO newIt = travelPlanService.create(itinerary);

            //set parent id(Itinerary id) to the plan detail object
            List<PlanDetailDTO> planDetailsDTO = resources.getPlanDetails();
            List<PlanDetail> planDetails = planDetailMapper.toEntity(planDetailsDTO);
            for (PlanDetail pd:planDetails
            ) {
                pd.setItineraryId(newIt.getId());
            }
            travelPlanService.save(planDetails);
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


    /**
     * delete the plan detail by id
     * @param id plan detail id
     * @return ResponseEntity for http response
     */
    @DeleteMapping(value = "/deleteDetail/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        travelPlanService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
