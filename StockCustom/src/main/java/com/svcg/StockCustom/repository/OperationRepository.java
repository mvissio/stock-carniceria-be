package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;

public interface OperationRepository extends
		JpaRepository<Operation, Serializable> {

	/* OperationType
	 *SALE:0
	 *BUY: 1
	 */
	
	/* PaymentMethod
	 *CC:0
	 *CASH: 1
	 */
		
	
	
	Operation findByOperationId(Long operationId); 
	
	//*********************BUSQUEDA POR TIPO, PAYMENT METHOD***************************
	
	//Para busqueda de operaciones por tipo	 	
	Page<Operation> findByOperationType(OperationType operationType, Pageable pageable);
	
	//Para busqueda de operaciones de pago en efectivo o con tc
	Page<Operation> findByPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);
	
	//*********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT METHOD***************************
	
	//Para busqueda de todas las operaciones del dia
	Page<Operation> findByCreateDate(Date createDate, Pageable pageable);	
	
	//Para busqueda de las operaciones por día y tipo de operacion
	Page<Operation> findByCreateDateAndOperationType(Date createDate, OperationType operationType, Pageable pageable);
		
	//Para busqueda de las operaciones por día y tipo de pago
	Page<Operation> findByCreateDateAndPaymentMethod(Date createDate, PaymentMethod paymentMethod, Pageable pageable);
		
	//*********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT METHOD***************************
	
	//Para busqueda de operaciones por intervalo de tiempo dado 
	Page<Operation> findByCreateDateBetween(Date fromDate, Date toDate, Pageable pageable);
	
	//Para busqueda de operaciones por tipo en un intervalo de tiempo dado 
	Page<Operation> findByOperationTypeAndCreateDateBetween(OperationType operationType, Date fromDate, Date toDate, Pageable pageable);

	//Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado 
	Page<Operation> findByPaymentMethodAndCreateDateBetween(PaymentMethod paymentMethod, Date fromDate, Date toDate, Pageable pageable);
	
	
	//*********************BUSQUEDA POR CLIENTE Y PROVEEDORES***************************
	
	//Para busqueda de operaciones de venta al mismo cliente
	Page<Operation> findByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable);

	//Para busqueda de operaciones de compra al mismo proveedor
	Page<Operation> findByProviderIdAndOperationType(Long providerId, OperationType operationType, Pageable pageable);
	
	//Para busqueda de operaciones de pago en efectivo o con tc por cliente 
	Page<Operation> findByClientIdAndPaymentMethod(Long clientId,PaymentMethod paymentMethod, Pageable pageable);
	
	
}
