package com.svcg.StockCustom.service;

import com.svcg.StockCustom.service.dto.MeasurementUnitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MeasurementUnitService {

    MeasurementUnitDTO saveMeasurementUnit(MeasurementUnitDTO measurementUnitDTO);

    Page<MeasurementUnitDTO> getMeasurementUnits(Pageable pageable);

    List<MeasurementUnitDTO> getMeasurementUnitByName(String name);

    MeasurementUnitDTO getMeasurementUnitById(Long id);

    MeasurementUnitDTO updateMeasurementUnit(MeasurementUnitDTO measurementUnitDTO);
    
    MeasurementUnitDTO deleteMeasurementUnit(Long id);
    
    Page<MeasurementUnitDTO> findByOnlyEnabledMeasurementUnit(Pageable pageable);

}
