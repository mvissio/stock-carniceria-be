package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.MeasurementUnit;

@Repository("measurementUnitRepository")
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Serializable> {

	 MeasurementUnit findByName(String name);

	List<MeasurementUnit> findByNameContaining(String name);

	MeasurementUnit findByMeasurementUnitId(Long measurementUnitId );
	 
	 Page<MeasurementUnit> findByDisabledIsFalse(Pageable pageable);
	 
	
}
