package com.svcg.StockCustom.service;

import java.util.List;

import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;

public interface OperationService {

	Operation saveOperation(Operation operation);
	
	Operation getOperationById(Long id);
	
	Operation getCompleteOperationById(Long id);

	List<OperationType> getOperationTypes();

	List<PaymentMethod> getPaymentMethods();
	
	
}
