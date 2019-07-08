package com.svcg.StockCustom.service;

import java.util.Date;
import java.util.List;

import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;
import com.svcg.StockCustom.service.dto.OperationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationService {

	OperationDTO saveOperation(OperationDTO operationDTO);
	
	OperationDTO getOperationById(Long id);

	List<OperationType> getOperationTypes();

	List<PaymentMethod> getPaymentMethods();

	OperationDTO cancelOperation(Long id);

	OperationDTO updateOperation(OperationDTO operationDTO);

	// *********************BUSQUEDA POR TIPO, PAYMENT METHOD***************************

	Page<OperationDTO> getOperationsByOperationType(OperationType operationType, Pageable pageable);

	Page<OperationDTO> getOperationsPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);
	
	Page<OperationDTO> getOperationsByOperationTypeAndPaymentMethod(Date createDate, PaymentMethod paymentMethod,
			Pageable pageable);

	
	
	// *********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT METHOD***************************

	// Para busqueda de todas las operaciones del dia
	Page<OperationDTO> getOperationsByCreateDate(Date createDate, Pageable pageable);

	// Para busqueda de las operaciones por día y tipo de operacion
	Page<OperationDTO> getOperationsByCreateDateAndOperationType(Date createDate, OperationType operationType, Pageable pageable);

	// Para busqueda de las operaciones por día y tipo de pago
	Page<OperationDTO> getOperationsByCreateDateAndPaymentMethod(Date createDate, PaymentMethod paymentMethod, Pageable pageable);

	//Para busqueda de las operaciones por día tipo de pago y tipo de operacion
	Page<OperationDTO> getOperationsByCreateDateAndPaymentMethodAndOperationType(Date createDate, PaymentMethod paymentMethod,OperationType operationType, Pageable pageable);
		
	
	// *********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT METHOD***************************

	// Para busqueda de operaciones por intervalo de tiempo dado
	Page<OperationDTO> getOperationsByCreateDateBetween(Date fromDate, Date toDate, Pageable pageable);

	// Para busqueda de operaciones por tipo en un intervalo de tiempo dado
	Page<OperationDTO> getOperationsByCreateDateBetweenAndByOperationType(OperationType operationType, Date fromDate, Date toDate,
			Pageable pageable);

	// Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado
	Page<OperationDTO> getOperationsByCreateDateBetweenAndByPaymentMethod(PaymentMethod paymentMethod, Date fromDate, Date toDate,
			Pageable pageable);
	
	//Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado 
	Page<OperationDTO> getOperationsByPaymentMethodAndOperationTypeAndCreateDateBetween(PaymentMethod paymentMethod,OperationType operationType, Date fromDate, Date toDate, Pageable pageable);
		

	// *********************BUSQUEDA POR CLIENTE Y PROVEEDORES***************************

	// Para busqueda de operaciones de venta al mismo cliente
	Page<OperationDTO> getOperationsByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable);

	// Para busqueda de operaciones de compra al mismo proveedor
	Page<OperationDTO> getOperationsByProviderIdAndOperationType(Long providerId, OperationType operationType, Pageable pageable);

	// Para busqueda de operaciones de pago en efectivo o con tc por cliente
	Page<OperationDTO> getOperationsByClientIdAndPaymentMethod(Long clientId, PaymentMethod paymentMethod, Pageable pageable);

}
