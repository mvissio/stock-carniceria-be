package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.svcg.StockCustom.entity.OperationDetail;
import com.svcg.StockCustom.service.dto.OperationDetailDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("operationDetailConverter")
public class OperationDetailConverter implements EntityConverter <OperationDetailDTO, OperationDetail>  {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public OperationDetailDTO toDTO(OperationDetail operationDetail) {
		return modelMapper.map(operationDetail, OperationDetailDTO.class);
	}
	
	public List<OperationDetailDTO> toDTO(List<OperationDetail> operationDetails) {
		List<OperationDetailDTO> operationDetailsDTO = new ArrayList<>();
		operationDetails.forEach(operationDetail -> operationDetailsDTO.add(modelMapper.map(operationDetail, OperationDetailDTO.class)));
		return operationDetailsDTO;
	}
	
	public OperationDetail toEntity(OperationDetailDTO operationDetailDTO) {
		return modelMapper.map(operationDetailDTO, OperationDetail.class);
	}
	
	public List<OperationDetail> toEntity(List<OperationDetailDTO> operationDetailsDTO) {
		List<OperationDetail> operationDetails = new ArrayList<>();
		operationDetailsDTO.forEach(operationDetailDTO -> operationDetails.add(modelMapper.map(operationDetailDTO, OperationDetail.class)));
		return operationDetails;
	}

}
