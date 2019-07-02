package com.svcg.StockCustom.service;

import java.util.List;

import com.svcg.StockCustom.service.dto.OperationDetailDTO;

public interface DetailOperationService {

	OperationDetailDTO saveDetailOperation(OperationDetailDTO detailOperationDTO);

	// me devuelve todos los detalles de la operacion
	List<OperationDetailDTO> findDetailsOperation(Long operationId);

}
