package com.svcg.StockCustom.service;

import java.util.List;

import com.svcg.StockCustom.entity.OperationDetail;

public interface DetailOperationService {

	OperationDetail saveDetailOperation(OperationDetail detailOperation);

	// me devuelve todos los detalles de la operacion
	List<OperationDetail> findDetailsOperation(Long operationId);

}
