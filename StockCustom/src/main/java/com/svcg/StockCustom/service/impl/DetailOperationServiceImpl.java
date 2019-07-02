package com.svcg.StockCustom.service.impl;

import java.util.List;

import com.svcg.StockCustom.repository.OperationDetailRepository;
import com.svcg.StockCustom.service.DetailOperationService;
import com.svcg.StockCustom.service.converter.OperationDetailConverter;
import com.svcg.StockCustom.service.dto.OperationDetailDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("detailOperationService")
public class DetailOperationServiceImpl implements DetailOperationService {

	@Autowired
	@Qualifier("operationDetailRepository")
	private OperationDetailRepository operationDetailRepository;

	@Autowired
    private OperationDetailConverter operationDetailConverter;
	
	@Override
	public OperationDetailDTO saveDetailOperation(OperationDetailDTO operationDetailDTO) {
		return this.operationDetailConverter.toDTO(this.operationDetailRepository.save(this.operationDetailConverter.toEntity(operationDetailDTO)));
	}

	@Override
	public List<OperationDetailDTO> findDetailsOperation(Long operationId) {
		return  this.operationDetailConverter.toDTO(this.operationDetailRepository.findByOperationId(operationId));
	}

}
