package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svcg.StockCustom.entity.DetailOperation;

public interface DetailOperationRepository extends JpaRepository<DetailOperation,Serializable> {

	DetailOperation findByOperationDetailId(Long operationDetailId); 
	
	//me devuelve todos los detalles de la operacion
	List<DetailOperation> findByOperationId(Long operationId); 
	
	
	
}
