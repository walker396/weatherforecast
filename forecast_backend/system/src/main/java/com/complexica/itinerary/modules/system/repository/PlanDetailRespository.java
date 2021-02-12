package com.complexica.itinerary.modules.system.repository;

import com.complexica.itinerary.modules.system.model.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * Repository for Plan Detail
 * extend JPA adopt default CRUD.
 * @author Li He
 * @date 2021-02-10
 */
public interface PlanDetailRespository extends JpaRepository<PlanDetail, Long>, JpaSpecificationExecutor {
}
