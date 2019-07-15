package com.svcg.StockCustom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.service.dto.MeasurementUnitDTO;

public interface MeasurementUnitService {

    MeasurementUnitDTO saveMeasurementUnit(MeasurementUnitDTO measurementUnitDTO);

    Page<MeasurementUnitDTO> getMeasurementUnits(Pageable pageable);

    Page<MeasurementUnitDTO> getMeasurementUnitByName(String name, Pageable pageable);

    MeasurementUnitDTO getMeasurementUnitById(Long id);

    MeasurementUnitDTO updateMeasurementUnit(MeasurementUnitDTO measurementUnitDTO);
    
    MeasurementUnitDTO deleteMeasurementUnit(Long id);
    
    Page<MeasurementUnitDTO> findByOnlyEnabledMeasurementUnit(Pageable pageable);

}
