package com.svcg.StockCustom.service;

import java.util.List;

import com.svcg.StockCustom.entity.DetailOperation;

public interface DetailOperationService {

	DetailOperation saveDetailOperation(DetailOperation detailOperation);

	// me devuelve todos los detalles de la operacion
	List<DetailOperation> findDetailsOperation(Long operationId);

}
