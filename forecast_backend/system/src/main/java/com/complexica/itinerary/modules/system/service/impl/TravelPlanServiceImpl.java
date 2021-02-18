package com.complexica.itinerary.modules.system.service.impl;


import com.complexica.itinerary.modules.system.mapper.ItineraryDetailsMapper;
import com.complexica.itinerary.modules.system.mapper.ItineraryMapper;
import com.complexica.itinerary.modules.system.model.Itinerary;
import com.complexica.itinerary.modules.system.model.PlanDetail;
import com.complexica.itinerary.modules.system.repository.PlanDetailRespository;
import com.complexica.itinerary.modules.system.repository.TravelPlanRespository;
import com.complexica.itinerary.modules.system.service.TravelPlanService;
import com.complexica.itinerary.modules.system.service.dto.ItineraryCriteria;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDTO;
import com.complexica.itinerary.modules.system.mapper.PlanDetailMapper;
import com.complexica.itinerary.modules.system.service.dto.ItineraryDetailDTO;
import com.complexica.itinerary.modules.system.service.dto.PlanDetailCriteria;
import com.complexica.utils.QueryHelp;
import com.complexica.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Travel Plan Service Implement class for Itinerary and plan detail function
 * @author Li He
 * @date 2021-02-10
 */
@Service("travelPlanService")
public class TravelPlanServiceImpl implements TravelPlanService {
    @Autowired
    private TravelPlanRespository travelPlanRespository;

    @Autowired
    private PlanDetailRespository planDetailRespository;


    @Resource
    private PlanDetailMapper planDetailMapper;
    @Resource
    private ItineraryMapper itineraryMapper;

    @Resource
    private ItineraryDetailsMapper itineraryDetailsMapper;


    /*@Override
    public Map findAll(int page, int pageSize) {
//        List<ItineraryDetailDTO> views = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");
        Page<Map> pages = travelPlanRespository.selectAllItineraryAndDetails(pageable);
        List<Map> contents = pages.getContent();
        List<ItineraryDetailDTO> dtos = new ArrayList<ItineraryDetailDTO>();
        for (Map ele: contents
             ) {
            ItineraryDetailDTO idto = new ItineraryDetailDTO();
            idto.setId((Long)ele.get("id"));
         idto.setName((String)ele.get("name"));
           idto.setUserId((Long)ele.get("userId"));
           idto.setStatus((String)ele.get("status"));
         idto.setCreateTime((Date)ele.get("createTime"));
           idto.setCityId((String)ele.get("cityId"));
         idto.setCity((String)ele.get("city"));
           idto.setCountry((String)ele.get("country"));
         idto.setWeatherDate((Date)ele.get("weatherDate"));
         idto.setWeatherTime((String)ele.get("weatherTime"));
           idto.setTempture((String)ele.get("tempture"));
           idto.setWeather((String)ele.get("weather"));
           idto.setOrderId((Integer)ele.get("orderId"));
            dtos.add(idto);
        }
        Map map = new HashMap();
//        List<ItineraryDetailDTO> dtos = itineraryDetailsMapper.toDto(pages.getContent());

        map.put("list",  dtos);//数据列表
        map.put("total", pages.getTotalElements());//记录总条数
        map.put("current_page", pages.getNumber());//当前页码
        return map;
    }*/

    @Override
    public Map findAllItinerarys(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");
        Page  result = travelPlanRespository.findAll(pageable);
        Map map = new HashMap();
        map.put("list",  result.getContent());//data list
        map.put("total", result.getTotalElements());//total number of record
        map.put("current_page", result.getNumber());//currrent pageno
        return map;
    }


    public List<Itinerary> findItinerarysByName(ItineraryCriteria criteria){
        return itineraryMapper.toDto(travelPlanRespository.findAll(
                (root, criteriaQuery, criteriaBuilder) ->
                        QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public Map getItineraryDetail(Long itineraryId, int page, int pageSize) {
        Specification<PlanDetail> planDetailSpecification = new Specification<PlanDetail>() {
            @Override
            public Predicate toPredicate(Root<PlanDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("itineraryId"),itineraryId);
            }
        };

        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");
        Page result=  planDetailRespository.findAll(planDetailSpecification,pageable);
        Map map = new HashMap();
        map.put("list",  result.getContent());//data list
        map.put("total", result.getTotalElements());//total number of record
        map.put("current_page", result.getNumber());//currrent pageno
        return map;
    }

    @Override
    public Map getItineraryDetailByCriteria(PlanDetailCriteria criteria, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "weatherDate","city","weatherTime");
        Page result=planDetailRespository.findAll(
                (root, criteriaQuery, criteriaBuilder) ->
                        QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        Map map = new HashMap();
        map.put("list",  result.getContent());//data list
        map.put("total", result.getTotalElements());//total number of record
        map.put("current_page", result.getNumber());//currrent pageno
        return map;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ItineraryDTO create(Itinerary resources) {
        System.out.println("-----------"+resources.toString());
        return itineraryMapper.toDto(travelPlanRespository.save(resources));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Itinerary resources) {
        Optional<Itinerary> optionalItinerary = travelPlanRespository.findById(resources.getId());
        ValidationUtil.isNull( optionalItinerary,"Itinerary","id",resources.getId());

        Itinerary itinerary = optionalItinerary.get();
        resources.setId(itinerary.getId());
        travelPlanRespository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<Itinerary> optionalItinerary= travelPlanRespository.findById(id);
        ValidationUtil.isNull( optionalItinerary,"Itinerary","id",id);
        travelPlanRespository.deleteById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<PlanDetail> planDetails) {
        planDetailRespository.saveAll(planDetails);
    }



}
