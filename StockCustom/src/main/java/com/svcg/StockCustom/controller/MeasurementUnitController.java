package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.svcg.StockCustom.service.dto.MeasurementUnitDTO;

@RestController
@RequestMapping(value = "/measurementUnits")
public class MeasurementUnitController {

    @Autowired
    @Qualifier("measurementUnitServiceImpl")
    private MeasurementUnitService measurementUnitService;

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<Page<MeasurementUnitDTO>> getMeasurementUnits(Pageable pageable) {
        return ResponseEntity.ok(this.measurementUnitService.getMeasurementUnits(pageable));
    }
    
    @GetMapping("/enabled")
    public ResponseEntity<Page<MeasurementUnitDTO>> getEnabledMeasurementUnits(Pageable pageable) {
        return ResponseEntity.ok(this.measurementUnitService.findByOnlyEnabledMeasurementUnit(pageable));
    }

    @PostMapping("")
    public ResponseEntity<MeasurementUnitDTO> addMeasurementUnit(
            @Valid @RequestBody MeasurementUnitDTO measurementUnitDTO,
            BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(MeasurementUnit.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.measurementUnitService.saveMeasurementUnit(measurementUnitDTO));

    }

    @PutMapping("")
    public ResponseEntity<MeasurementUnitDTO> updateMeasurementUnit(
            @Valid @RequestBody MeasurementUnitDTO measurementUnitDTO,
            BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(MeasurementUnit.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.ok(this.measurementUnitService.updateMeasurementUnit(measurementUnitDTO));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MeasurementUnitDTO> getMeasurementUnitByNombre(
            @PathVariable("name") String name) {
        logger.info(String.format(Constant.CONCAT, name));
        return ResponseEntity.ok(this.measurementUnitService.getMeasurementUnitByName(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MeasurementUnitDTO> getMeasurementUnitById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.measurementUnitService.getMeasurementUnitById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteMeasurementUnit(
            @PathVariable("id") Long id) {
		this.measurementUnitService.deleteMeasurementUnit(id);
		return ResponseEntity.noContent().build();
    }

}
