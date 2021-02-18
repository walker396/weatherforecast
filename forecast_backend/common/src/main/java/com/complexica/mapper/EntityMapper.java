package com.complexica.mapper;

import java.util.List;

/**
 * Transformation between the entity and dto
 * @author Li He
 * @date 2018-11-23
 *
 */
public interface EntityMapper<D, E> {

    /**
     * DTO to Entity
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity to DTO
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * DTO list to Entity list
     * @param dtoList
     * @return
     */
    List <E> toEntity(List<D> dtoList);

    /**
     * Entity list to DTO list
     * @param entityList
     * @return
     */
    List <D> toDto(List<E> entityList);
}
