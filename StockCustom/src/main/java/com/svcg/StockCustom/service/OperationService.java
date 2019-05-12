package com.svcg.StockCustom.service;

import com.svcg.StockCustom.entity.Operation;

public interface OperationService {

	Operation saveOperation(Operation operation);
	
	Operation getOperationById(Long id);
	
	Operation getCompleteOperationById(Long id);
	
	
}
