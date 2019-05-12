package com.svcg.StockCustom.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.repository.OperationRepository;
import com.svcg.StockCustom.service.OperationService;

@Service("operationServiceImpl")
public class OperationServiceImpl implements OperationService{

	@Autowired
	@Qualifier("operationRepository")
	private OperationRepository operationRepository;
	
	@Override
	public Operation saveOperation(Operation operation) {
		operation.setCreateDate(new Date());
		return operationRepository.save(operation);
	}

}
