package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svcg.StockCustom.entity.OperationDetail;

public interface OperationDetailRepository extends JpaRepository<OperationDetail,Serializable> {

	OperationDetail findByOperationDetailId(Long operationDetailId); 
	
	//me devuelve todos los detalles de la operacion
	List<OperationDetail> findByOperationId(Long operationId); 
	
	
	
}
