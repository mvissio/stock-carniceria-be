package com.svcg.StockCustom.service;

import java.util.Date;
import java.util.List;

import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationService {

	Operation saveOperation(Operation operation);

	Operation getOperationById(Long id);

	List<OperationType> getOperationTypes();

	List<PaymentMethod> getPaymentMethods();

	Operation cancelOperation(Long id);

	Operation updateOperation(Operation operation);

	// *********************BUSQUEDA POR TIPO, PAYMENT METHOD***************************

	Page<Operation> getOperationsByOperationType(OperationType operationType, Pageable pageable);

	Page<Operation> getOperationsPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);
	
	Page<Operation> getOperationsByOperationTypeAndPaymentMethod(Date createDate, PaymentMethod paymentMethod,
			Pageable pageable);

	
	
	// *********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT METHOD***************************

	// Para busqueda de todas las operaciones del dia
	Page<Operation> getOperationsByCreateDate(Date createDate, Pageable pageable);

	// Para busqueda de las operaciones por día y tipo de operacion
	Page<Operation> getOperationsByCreateDateAndOperationType(Date createDate, OperationType operationType, Pageable pageable);

	// Para busqueda de las operaciones por día y tipo de pago
	Page<Operation> getOperationsByCreateDateAndPaymentMethod(Date createDate, PaymentMethod paymentMethod, Pageable pageable);

	//Para busqueda de las operaciones por día tipo de pago y tipo de operacion
	Page<Operation> getOperationsByCreateDateAndPaymentMethodAndOperationType(Date createDate, PaymentMethod paymentMethod,OperationType operationType, Pageable pageable);
		
	
	// *********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT METHOD***************************

	// Para busqueda de operaciones por intervalo de tiempo dado
	Page<Operation> getOperationsByCreateDateBetween(Date fromDate, Date toDate, Pageable pageable);

	// Para busqueda de operaciones por tipo en un intervalo de tiempo dado
	Page<Operation> getOperationsByCreateDateBetweenAndByOperationType(OperationType operationType, Date fromDate, Date toDate,
			Pageable pageable);

	// Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado
	Page<Operation> getOperationsByCreateDateBetweenAndByPaymentMethod(PaymentMethod paymentMethod, Date fromDate, Date toDate,
			Pageable pageable);
	
	//Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado 
	Page<Operation> getOperationsByPaymentMethodAndOperationTypeAndCreateDateBetween(PaymentMethod paymentMethod,OperationType operationType, Date fromDate, Date toDate, Pageable pageable);
		

	// *********************BUSQUEDA POR CLIENTE Y PROVEEDORES***************************

	// Para busqueda de operaciones de venta al mismo cliente
	Page<Operation> getOperationsByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable);

	// Para busqueda de operaciones de compra al mismo proveedor
	Page<Operation> getOperationsByProviderIdAndOperationType(Long providerId, OperationType operationType, Pageable pageable);

	// Para busqueda de operaciones de pago en efectivo o con tc por cliente
	Page<Operation> getOperationsByClientIdAndPaymentMethod(Long clientId, PaymentMethod paymentMethod, Pageable pageable);

}
