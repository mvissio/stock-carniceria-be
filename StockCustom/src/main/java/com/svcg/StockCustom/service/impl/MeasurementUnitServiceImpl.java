package com.svcg.StockCustom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.svcg.StockCustom.entity.MeasurementUnit;
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
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.repository.MeasurementUnitRepository;
import com.svcg.StockCustom.service.MeasurementUnitService;
import com.svcg.StockCustom.service.converter.MeasurementUnitConverter;
import com.svcg.StockCustom.service.dto.MeasurementUnitDTO;

@Service("measurementUnitServiceImpl")
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	@Qualifier("measurementUnitRepository")
	private MeasurementUnitRepository measurementUnitRepository;

	@Autowired
    private MeasurementUnitConverter measurementUnitConverter;

	@Override
	public MeasurementUnitDTO saveMeasurementUnit(MeasurementUnitDTO measurementUnitDTO) {
		/**
		 * Save the measurementUnit
		 */

		if (measurementUnitDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_MEASUREMENT_UNIT));
		}
		if (measurementUnitNameExist(measurementUnitDTO.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_ARTICLE_EXISTS), measurementUnitDTO.getName()));
		}
		measurementUnitDTO.setCreateDate(new Date());
		measurementUnitDTO.setDisabled(false);
		measurementUnitDTO = saveMeasurementUnitObjet(measurementUnitDTO, true);
		logger.info("measurementUnit was saved successfully {}", measurementUnitDTO);
		return measurementUnitDTO;

	}

	@Override
	public Page<MeasurementUnitDTO> getMeasurementUnits(Pageable pageable) {
		Page<MeasurementUnit> measurementUnits = this.measurementUnitRepository
				.findAll(pageable);
		if (measurementUnits.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, this.messages.get(Constant.MESSAGE_NOT_FOUND_MEASUREMENT_UNITS));
		}
		return measurementUnits.map(this.measurementUnitConverter::toDTO);

	}

	@Override
	public List<MeasurementUnitDTO> getMeasurementUnitByName(String name) {
		List<MeasurementUnit> measurementUnits = this.measurementUnitRepository
				.findByNameContaining(name);
		if (measurementUnits == null|| measurementUnits.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_MEASUREMENT_UNIT));
		}

		return measurementUnits.stream().map(this.measurementUnitConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public MeasurementUnitDTO getMeasurementUnitById(Long id) {
		MeasurementUnit measurementUnit = this.measurementUnitRepository
				.findByMeasurementUnitId(id);
		if (measurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_MEASUREMENT_UNIT));
		}
		return this.measurementUnitConverter.toDTO(measurementUnit);
	}

	@Override
	public MeasurementUnitDTO updateMeasurementUnit(MeasurementUnitDTO measurementUnitDTO) {
		if (measurementUnitDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_NOT_FOUND_MEASUREMENT_UNIT));
		}
		MeasurementUnit previousMeasurementUnit = this.measurementUnitRepository
				.findByName(measurementUnitDTO.getName());
		if (previousMeasurementUnit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_MEASUREMENT_UNIT));
		}
		measurementUnitDTO = saveMeasurementUnitObjet(measurementUnitDTO, false);
		return measurementUnitDTO;
	}

	private Boolean measurementUnitNameExist(String name) {
		MeasurementUnit measurementUnit = this.measurementUnitRepository
				.findByName(name);
		return measurementUnit != null;
	}

	private MeasurementUnitDTO saveMeasurementUnitObjet(MeasurementUnitDTO measurementUnitDTO, Boolean isSave) {
		try {
			MeasurementUnitDTO measurementUnitCreated = this.measurementUnitConverter.toDTO(this.measurementUnitRepository.save(this.measurementUnitConverter.toEntity(measurementUnitDTO)));
			return measurementUnitCreated;

		} catch (Exception e) {
			logger.error(Constant.EXCEPTION, e);
			String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_ARTICLE) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_ARTICLE);

			throw new ResponseStatusException(HttpStatus.CONFLICT, message);
		}

	}

	@Override
	public MeasurementUnitDTO deleteMeasurementUnit(Long id) {
		MeasurementUnit measurementUnit = this.measurementUnitRepository
				.findByMeasurementUnitId(id);
		measurementUnit.setDisabled(true);
		measurementUnit.setDisabledDate(new Date());
		return this.measurementUnitConverter.toDTO(this.measurementUnitRepository.save(measurementUnit));
	}

	@Override
	public Page<MeasurementUnitDTO> findByOnlyEnabledMeasurementUnit(
			Pageable pageable) {
		Page<MeasurementUnit> measurementUnits = this.measurementUnitRepository
				.findByDisabledIsFalse(pageable);
		if (measurementUnits.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_MEASUREMENT_UNITS));
		}
		return measurementUnits.map(this.measurementUnitConverter::toDTO);
	}

}
