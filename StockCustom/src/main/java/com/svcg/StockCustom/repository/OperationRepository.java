package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
        
    
    
    Optional<Operation> findByOperationId(Long operationId); 
    
    //*********************BUSQUEDA POR TIPO, PAYMENT METHOD***************************
    
    //Para busqueda de operaciones por tipo     
    Optional<Page<Operation>> findByOperationType(OperationType operationType, Pageable pageable);
    
    //Para busqueda de operaciones de pago en efectivo o con tc
    Optional<Page<Operation>> findByPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);
    
    //*********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT METHOD***************************
    
    //Para busqueda de todas las operaciones del dia
    Optional<Page<Operation>> findByCreateDate(Date createDate, Pageable pageable);   
    
    //Para busqueda de las operaciones por día y tipo de operacion
    Optional<Page<Operation>> findByCreateDateAndOperationType(Date createDate, OperationType operationType, Pageable pageable);
        
    //Para busqueda de las operaciones por día y tipo de pago
    Optional<Page<Operation>> findByCreateDateAndPaymentMethod(Date createDate, PaymentMethod paymentMethod, Pageable pageable);
        
    //Para busqueda de las operaciones por día tipo de pago y tipo de operacion
    Optional<Page<Operation>> findByCreateDateAndPaymentMethodAndOperationType(Date createDate, PaymentMethod paymentMethod,OperationType operationType, Pageable pageable);
        
    
    //*********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT METHOD***************************
    
    //Para busqueda de operaciones por intervalo de tiempo dado 
    Optional<Page<Operation>> findByCreateDateBetween(Date fromDate, Date toDate, Pageable pageable);
    
    //Para busqueda de operaciones por tipo en un intervalo de tiempo dado 
    Optional<Page<Operation>> findByOperationTypeAndCreateDateBetween(OperationType operationType, Date fromDate, Date toDate, Pageable pageable);

    //Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado 
    Optional<Page<Operation>> findByPaymentMethodAndCreateDateBetween(PaymentMethod paymentMethod, Date fromDate, Date toDate, Pageable pageable);
    
    //Para busqueda de operaciones por medio de pago en un intervalo de tiempo dado 
    Optional<Page<Operation>> findByPaymentMethodAndOperationTypeAndCreateDateBetween(PaymentMethod paymentMethod,OperationType operationType, Date fromDate, Date toDate, Pageable pageable);
        
    
    //*********************BUSQUEDA POR CLIENTE Y PROVEEDORES***************************
    
    //Para busqueda de operaciones de venta al mismo cliente
    Optional<Page<Operation>> findByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable);

    //Para busqueda de operaciones de compra al mismo proveedor
    Optional<Page<Operation>> findByProviderIdAndOperationType(Long providerId, OperationType operationType, Pageable pageable);
    
    //Para busqueda de operaciones de pago en efectivo o con tc por cliente 
    Optional<Page<Operation>> findByClientIdAndPaymentMethod(Long clientId,PaymentMethod paymentMethod, Pageable pageable);

    //Para la busqueda de operaciones por operacion con paginacion
    Optional<Page<Operation>> findAllByBoxId(Long boxId, Pageable pageable);

    //Para la busqueda de operaciones por operacion
    List<Operation> findAllByBoxId(Long boxId);
        
    //Query para informe mensual
    @Query("SELECT SUM(o.total) FROM Operation o where o.createDate BETWEEN :fromDate AND :toDate AND o.operationType = :operationType")
    Optional<Double> sumTotalOperation(Date fromDate,Date toDate, OperationType operationType);
    
       
    
    
}
