package com.complexica.itinerary.modules.system.rest;

import com.complexica.annotation.Limit;
import com.complexica.aspect.LimitType;
import com.complexica.exception.BadRequestException;
import com.complexica.itinerary.modules.system.mapper.ItineraryMapper;
import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import com.complexica.itinerary.modules.system.service.TravelPlanService;
import com.complexica.itinerary.modules.system.service.dto.ItineraryCriteria;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.itinerary.modules.system.mapper.PlanDetailMapper;
import com.complexica.itinerary.modules.system.service.dto.PlanDetailCriteria;
import com.complexica.itinerary.modules.system.service.dto.PlanDetailDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Itinerary Management")
@RestController
@RequestMapping("travelPlan")
public class TravelPlanController {

    @Autowired
    private TravelPlanService travelPlanService;
    @Resource
    private PlanDetailMapper planDetailMapper;

    @Resource
    private ItineraryMapper itineraryMapper;

    private static final String ENTITY_NAME = "itinerary";


    /**
     * query all itineraries and it's plan details
     * @param currentpage current page no for paging
     * @param pageSize page size for paging
     * @return
     */
//    @Limit(period = 6000,limitType= LimitType.IP, count = 10, name = "testLimit", prefix = "limit")
    @ApiOperation(value = "query all itinerary detail and support paging")
    @GetMapping("/getAll/{currentpage}/{pageSize}")
    public ResponseEntity getItineraryDetails(@PathVariable("currentpage") int currentpage,
                                              @PathVariable("pageSize") int pageSize){
        return new ResponseEntity(travelPlanService.findAllItinerarys(currentpage,pageSize), HttpStatus.OK);
    }

    /**
     * query itinerary by name
     * @param name itinerary name
     * @return
     */
    @ApiOperation(value = "query itinerary by name")
    @GetMapping("/getByName/{name}")
    public ResponseEntity getItineraryByName(@PathVariable("name") String name){
        ItineraryCriteria criteria = new ItineraryCriteria();
        criteria.setName(name);
        return new ResponseEntity(travelPlanService.findItinerarysByName(criteria), HttpStatus.OK);
    }

    /**
     * query itinerary detail by itinerary id
     * @param itineraryId  itinerary id
     * @param currentpage current page no for paging
     * @param pageSize page size for paging
     * @return ResponseEntity
     */
    @ApiOperation(value = "query itinerary detail by itinerary id")
    @GetMapping(value = "/getDetailById/{itineraryId}/{currentpage}/{pageSize}")
    public ResponseEntity getDictDetailMaps(@PathVariable("itineraryId") Long itineraryId,
                                            @PathVariable("currentpage") int currentpage,
                                            @PathVariable("pageSize") int pageSize){
        PlanDetailCriteria planDetailCriteria = new PlanDetailCriteria();
        Itinerary itinerary = new Itinerary();
        itinerary.setId(itineraryId);
        planDetailCriteria.setItinerary(itinerary);
        return new ResponseEntity(travelPlanService.getItineraryDetailByCriteria(planDetailCriteria,currentpage,pageSize), HttpStatus.OK);
    }


    /**
     * add itinerary and it's plan detail
     * @param resources
     * @return ResponseEntity contains ItineraryDTO
     */
    @ApiOperation(value = "add itinerary and it's plan detail")
    @PostMapping(value = "/add")
    public ResponseEntity create(@Validated @RequestBody ItineraryDTO resources){
        try{
            if (resources.getId() != null) {
                throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
            }
            //Itinerary Mappter trasform ItineraryDTO to Itinerary model
            Itinerary itinerary = itineraryMapper.toEntity(resources);
            itinerary.getPlanDetails().forEach(planDetail -> {
                planDetail.setItinerary(itinerary);
                planDetail.setId(null);
            });

            ItineraryDTO newIt = travelPlanService.create(itinerary);
            return new ResponseEntity(newIt, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * update the itinerary
     * @param resources Itinerary
     * @return ResponseEntity
     */
    @ApiOperation(value = "update itinerary and it's plan detail")
    @PutMapping(value = "/itinerary")
    public ResponseEntity update(@Validated @RequestBody ItineraryDTO resources){
        Itinerary itinerary = itineraryMapper.toEntity(resources);
        travelPlanService.update(itinerary);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    /**
     * delete the plan detail by id
     * @param id plan detail id
     * @return ResponseEntity for http response
     */
    @ApiOperation(value = "delete itinerary and it's plan detail")
    @DeleteMapping(value = "/itinerary/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        travelPlanService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
