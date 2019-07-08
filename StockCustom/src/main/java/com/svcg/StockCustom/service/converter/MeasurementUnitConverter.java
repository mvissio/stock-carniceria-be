package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.MeasurementUnit;
import com.svcg.StockCustom.service.dto.MeasurementUnitDTO;

@Component("measurementUnitConverter")
public class MeasurementUnitConverter implements EntityConverter <MeasurementUnitDTO, MeasurementUnit>  {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public MeasurementUnitDTO toDTO(MeasurementUnit measurementUnit) {
        return modelMapper.map(measurementUnit, MeasurementUnitDTO.class);
    }
    
    public List<MeasurementUnitDTO> toDTO(List<MeasurementUnit> measurementUnits) {
        List<MeasurementUnitDTO> measurementUnitsDTO = new ArrayList<>();
        measurementUnits.forEach(measurementUnit -> measurementUnitsDTO.add(modelMapper.map(measurementUnit, MeasurementUnitDTO.class)));
        return measurementUnitsDTO;
    }
    
    public MeasurementUnit toEntity(MeasurementUnitDTO measurementUnitDTO) {
        return modelMapper.map(measurementUnitDTO, MeasurementUnit.class);
    }
    
    public List<MeasurementUnit> toEntity(List<MeasurementUnitDTO> measurementUnitsDTO) {
        List<MeasurementUnit> measurementUnits = new ArrayList<>();
        measurementUnitsDTO.forEach(measurementUnitDTO -> measurementUnits.add(modelMapper.map(measurementUnitDTO, MeasurementUnit.class)));
        return measurementUnits;
    }
}
