package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.MeasurementUnit;
import com.svcg.StockCustom.service.MeasurementUnitService;

@RestController
@RequestMapping(value = "/measurementUnits")
public class MeasurementUnitController {

	@Autowired
	@Qualifier("measurementUnitServiceImpl")
	private MeasurementUnitService measurementUnitService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@GetMapping("")
	public Page<MeasurementUnit> getMeasurementUnits(Pageable pageable) {
		return measurementUnitService.getMeasurementUnits(pageable);
	}
	
	@GetMapping("/enabled")
    public Page<MeasurementUnit> getEnabledMeasurementUnits(Pageable pageable) {
        return measurementUnitService.findByOnlyEnabledMeasurementUnit(pageable);
    }

	@PostMapping("")
	public MeasurementUnit addMeasurementUnit(
			@Valid @RequestBody MeasurementUnit measurementUnit,
			BindingResult bindingResult) throws MethodArgumentNotValidException {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(MeasurementUnit.class.getDeclaredConstructors()[1],0), bindingResult);
		}
		return measurementUnitService.saveMeasurementUnit(measurementUnit);

	}

	@PutMapping("")
	public MeasurementUnit updateMeasurementUnit(
			@Valid @RequestBody MeasurementUnit measurementUnit,
			BindingResult bindingResult) throws MethodArgumentNotValidException {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(MeasurementUnit.class.getDeclaredConstructors()[1],0), bindingResult);
		}
		return measurementUnitService.updateMeasurementUnit(measurementUnit);

	}

	@GetMapping("/name/{name}")
	public MeasurementUnit getMeasurementUnitByNombre(
			@PathVariable("name") String name) {
		logger.info(String.format(Constant.CONCAT, name));
		return measurementUnitService.getMeasurementUnitByName(name);
	}

	@GetMapping("/id/{id}")
	public MeasurementUnit getMeasurementUnitById(@PathVariable("id") Long id) {
		return measurementUnitService.getMeasurementUnitById(id);
	}

	@DeleteMapping("/id/{id}")
	public com.svcg.StockCustom.entity.MeasurementUnit deleteMeasurementUnit(
			@PathVariable("id") Long id) {
		return measurementUnitService.deleteMeasurementUnit(id);
	}

}
