package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.service.dto.OperationDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("operationConverter")
public class OperationConverter implements EntityConverter <OperationDTO, Operation>  {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public OperationDTO toDTO(Operation operation) {
		return modelMapper.map(operation, OperationDTO.class);
	}
	
	public List<OperationDTO> toDTO(List<Operation> operations) {
		List<OperationDTO> operationsDTO = new ArrayList<>();
		operations.forEach(operation -> operationsDTO.add(modelMapper.map(operation, OperationDTO.class)));
		return operationsDTO;
	}
	
	public Operation toEntity(OperationDTO operationDTO) {
		return modelMapper.map(operationDTO, Operation.class);
	}
	
	public List<Operation> toEntity(List<OperationDTO> operationsDTO) {
		List<Operation> operations = new ArrayList<>();
		operationsDTO.forEach(operationDTO -> operations.add(modelMapper.map(operationDTO, Operation.class)));
		return operations;
	}

}
