package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;

public interface OperationRepository extends
		JpaRepository<Operation, Serializable> {

	
	Operation findByOperationId(Long operationId); 
	
	//Para busqueda de operaciones del dia
	Page<Operation> findByCreateDate(Date createDate, Pageable pageable);
	
	//Para busqueda de operaciones de venta al mismo cliente
	Page<Operation> findByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable);

	//Para busqueda de operaciones de compra al mismo proveedor
	Page<Operation> findByProviderIdAndOperationType(Long providerId, OperationType operationType, Pageable pageable);
	
	//Para busqueda de las ventas del dia y de las compras del dia
	Page<Operation> findByCreateDateAndOperationType(Date createDate, OperationType operationType, Pageable pageable);
	
	//Para busqueda de operaciones de pago en efectivo o con tc
	Page<Operation> findByPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);
	
	//Para busqueda de operaciones de pago en efectivo o con tc por cliente 
	Page<Operation> findByClientIdAndPaymentMethod(Long clientId,PaymentMethod paymentMethod, Pageable pageable);
	
	//Para busqueda de operaciones por tipo en un intervalo de tiempo dado 
	Page<Operation> findByOperationTypeAndCreateDateBetween(OperationType operationType, Date fromDate, Date toDate, Pageable pageable);

	//Para la busqueda de operaciones por operacion
	Page<Operation> findAllByBoxId(Long boxId, Pageable pageable);
}
