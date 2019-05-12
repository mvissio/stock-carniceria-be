package com.svcg.StockCustom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.svcg.StockCustom.entity.DetailOperation;
import com.svcg.StockCustom.repository.DetailOperationRepository;
import com.svcg.StockCustom.service.DetailOperationService;

@Service("detailOperationService")
public class DetailOperationServiceImpl implements DetailOperationService {

	@Autowired
	@Qualifier("detailOperationRepository")
	private DetailOperationRepository detailOperationRepository;
	
	@Override
	public DetailOperation saveDetailOperation(DetailOperation detailOperation) {
		return detailOperationRepository.save(detailOperation);
	}

	@Override
	public List<DetailOperation> findDetailsOperation(Long operationId) {
		return detailOperationRepository.findByOperationId(operationId);
	}

}
