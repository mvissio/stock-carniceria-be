package com.svcg.StockCustom.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.MeasurementUnit;
import com.svcg.StockCustom.repository.MeasurementUnitRepository;
import com.svcg.StockCustom.service.MeasurementUnitService;

@Service("unidadMedidaServiceImpl")
public class UnidadMedidaServiceImpl implements MeasurementUnitService {

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	@Qualifier("measurementUnitRepository")
	private MeasurementUnitRepository measurementUnitRepository;

	@Override
	public MeasurementUnit saveMeasurementUnit(MeasurementUnit measurementUnit) {
		/**
		 * Save the article
		 */

		if (measurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}
		if (measurementUnitNameExist(measurementUnit.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_ARTICULO_EXISTS")
							+ measurementUnit.getName(), null);
		}
		measurementUnit.setCreateDate(new Date());
		measurementUnit.setDisabled(false);
		measurementUnit = saveMeasurementUnit(measurementUnit);
		logger.info("measurementUnit was saved successfully " + measurementUnit );
		return measurementUnit;

	}

	@Override
	public Page<MeasurementUnit> getMeasurementUnits(Pageable pageable) {
		Page<com.svcg.StockCustom.entity.MeasurementUnit> measurementUnit = measurementUnitRepository
				.findAll(pageable);
		if (measurementUnit.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULOS"), null);
		}
		return measurementUnit;

	}

	@Override
	public MeasurementUnit getMeasurementUnitByName(String name) {
		com.svcg.StockCustom.entity.MeasurementUnit measurementUnit = measurementUnitRepository
				.findByName(name);
		if (measurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_USUARIO"), null);
		}

		return measurementUnit;
	}

	@Override
	public MeasurementUnit getMeasurementUnitById(Long id) {
		com.svcg.StockCustom.entity.MeasurementUnit measurementUnit = measurementUnitRepository
				.findByMeasurementUnitId(id);
		if (measurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}
		return measurementUnit;
	}

	@Override
	public MeasurementUnit updateMeasurementUnit(MeasurementUnit measurementUnit) {
		if (measurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}
		com.svcg.StockCustom.entity.MeasurementUnit previousMeasurementUnit = measurementUnitRepository
				.findByName(measurementUnit.getName());
		if (previousMeasurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		measurementUnit = saveMeasurementUnitObjet(previousMeasurementUnit);
		return measurementUnit;
	}

	private boolean measurementUnitNameExist(String name) {
		com.svcg.StockCustom.entity.MeasurementUnit measurementUnit = measurementUnitRepository
				.findByName(name);
		return measurementUnit != null;
	}
	
	private com.svcg.StockCustom.entity.MeasurementUnit saveMeasurementUnitObjet(
			com.svcg.StockCustom.entity.MeasurementUnit measurementUnit) {
		try {

			MeasurementUnit measurementUnitCreated = measurementUnitRepository.save(measurementUnit);
			return measurementUnitCreated;

		} catch (Exception e) {
			logger.error("Exception: {} ", e);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}

	}

}
