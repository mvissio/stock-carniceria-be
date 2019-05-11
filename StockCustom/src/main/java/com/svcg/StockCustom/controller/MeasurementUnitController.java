package com.svcg.StockCustom.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.svcg.StockCustom.entity.MeasurementUnit;
import com.svcg.StockCustom.service.MeasurementUnitService;

@RestController
@RequestMapping(value = "/measurementUnit")
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

	@PostMapping("")
	public MeasurementUnit addMeasurementUnit(
			@Valid @RequestBody MeasurementUnit measurementUnit,
			BindingResult bindingResult) throws MethodArgumentNotValidException {
		if (bindingResult.hasErrors()) {
			logger.error(String.valueOf(bindingResult.getAllErrors()));
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		return measurementUnitService.saveMeasurementUnit(measurementUnit);

	}

	@PutMapping("")
	public MeasurementUnit updateMeasurementUnit(
			@Valid @RequestBody MeasurementUnit measurementUnit,
			BindingResult bindingResult) throws MethodArgumentNotValidException {
		if (bindingResult.hasErrors()) {
			logger.error(String.valueOf(bindingResult.getAllErrors()));
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		return measurementUnitService.updateMeasurementUnit(measurementUnit);

	}

	@GetMapping("/name/{name}")
	public MeasurementUnit getMeasurementUnitByNombre(
			@PathVariable("name") String name) {
		logger.info("El name recivido es " + name);
		return measurementUnitService.getMeasurementUnitByName(name);
	}

	@GetMapping("/id/{id}")
	public MeasurementUnit getMeasurementUnitById(@PathVariable("id") Long id) {		
		return measurementUnitService.getMeasurementUnitById(id);
	}

}