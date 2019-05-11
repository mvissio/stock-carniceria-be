package com.svcg.StockCustom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.MeasurementUnit;

public interface MeasurementUnitService {

	MeasurementUnit saveMeasurementUnit(MeasurementUnit measurementUnit);

	Page<MeasurementUnit> getMeasurementUnits(Pageable pageable);

	MeasurementUnit getMeasurementUnitByName(String name);

	MeasurementUnit getMeasurementUnitById(Long id);

	MeasurementUnit updateMeasurementUnit(MeasurementUnit measurementUnit);

}
