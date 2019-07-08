package com.svcg.StockCustom.service.converter;

import java.util.List;

/**
 * Contract for a generic dto to entity converter.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityConverter <D, E> {

    E toEntity(D dto);

    D toDTO(E entity);

    List <E> toEntity(List<D> dtoList);

    List <D> toDTO(List<E> entityList);
}
