package com.svcg.StockCustom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.svcg.StockCustom.entity.OperationDetail;
import com.svcg.StockCustom.repository.OperationDetailRepository;
import com.svcg.StockCustom.service.DetailOperationService;

@Service("detailOperationService")
public class DetailOperationServiceImpl implements DetailOperationService {

	@Autowired
	@Qualifier("operationDetailRepository")
	private OperationDetailRepository operationDetailRepository;
	
	@Override
	public OperationDetail saveDetailOperation(OperationDetail detailOperation) {
		return operationDetailRepository.save(detailOperation);
	}

	@Override
	public List<OperationDetail> findDetailsOperation(Long operationId) {
		return operationDetailRepository.findByOperationId(operationId);
	}

}
