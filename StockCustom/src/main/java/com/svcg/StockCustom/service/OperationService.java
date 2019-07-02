package com.svcg.StockCustom.service;

import java.util.Date;
import java.util.List;

import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;
import com.svcg.StockCustom.service.dto.OperationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationService {

	OperationDTO saveOperation(OperationDTO operationDTO);
	
	OperationDTO getOperationById(Long id);

	List<OperationType> getOperationTypes();

	List<PaymentMethod> getPaymentMethods();

	Page<OperationDTO> getOperationsByCreationDate(Date createDate, Pageable pageable);
	
	Page<OperationDTO> getOperationsByCreationDateAndOperationType(Date createDate, Pageable pageable);

    OperationDTO cancelOperation(Long id);

	OperationDTO updateOperation(OperationDTO operationDTO);
}
