package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.MeasurementUnit;

@Repository("measurementUnitRepository")
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Serializable> {

	 Optional<MeasurementUnit> findByName(String name);

	Optional<Page<MeasurementUnit>> findByNameContaining(String name, Pageable pageable);

	Optional<MeasurementUnit> findByMeasurementUnitId(Long measurementUnitId);
	 
	Optional<Page<MeasurementUnit>> findByDisabledIsFalse(Pageable pageable);
	 
	
}
