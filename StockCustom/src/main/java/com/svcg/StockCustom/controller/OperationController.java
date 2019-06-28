package com.svcg.StockCustom.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;
import com.svcg.StockCustom.service.OperationService;

@RestController
@RequestMapping(value = "/operation")
public class OperationController {

	@Autowired
	@Qualifier("operationServiceImpl")
	private OperationService operationService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@PostMapping("")
	public Operation addOperation(@Valid @RequestBody Operation operation,
			BindingResult bindingResult) throws MethodArgumentNotValidException {
		if (bindingResult.hasErrors()) {
			logger.error(String.valueOf(bindingResult.getAllErrors()));
			throw new MethodArgumentNotValidException(null, bindingResult);
		}
		return operationService.saveOperation(operation);

	}

    @PutMapping("")
    public Operation updateOperation(@Valid @RequestBody Operation operation,
                                  BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return operationService.updateOperation(operation);

    }
	
 	
	
	
	@GetMapping("operationTypes")
    public List<OperationType> getOperationTypes() {
        return operationService.getOperationTypes();
    }
	
	@GetMapping("paymentMethods")
    public List<PaymentMethod> getPaymentMethods() {
        return operationService.getPaymentMethods();
    }
	
	@GetMapping("id/{id}")
    public Operation getOperationById(@PathVariable("id")Long id) {
        return operationService.getOperationById(id);
    }

	@DeleteMapping("/cancel/id/{id}")
	public Operation cancelOperation(@PathVariable("id") Long id) {
		return operationService.cancelOperation(id);
	}
	
	
	/****************************************************
	 * METODOS PARA LOS FILTROS
	 */
	
	// *********************BUSQUEDA POR TIPO, PAYMENT METHOD***************************
	    
	    @GetMapping("/byOperationType")
		public Page<Operation> getOperationsByType(@Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return operationService.getOperationsByOperationType(operationType,pageable);
		}
	    
	    @GetMapping("/byPaymentMethod")
		public Page<Operation> getOperationsByPaymentMethod(@Valid @RequestParam PaymentMethod paymentMethod, Pageable pageable)  {
			return operationService.getOperationsPaymentMethod(paymentMethod,pageable);			
		}
	    
	    @GetMapping("/byOperationTypeAndPaymentMethod")
		public Page<Operation> getOperationsByTypeAndPaymentMethodAnd(@Valid @RequestParam Date createDate, @Valid @RequestParam PaymentMethod paymentMethod,
				Pageable pageable)  {
			return operationService.getOperationsByOperationTypeAndPaymentMethod(createDate, paymentMethod,pageable);			
		}
	    
	    
	    
	    
	 // *********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT METHOD***************************
	    
	    	    
		@GetMapping("/creationDate")
		public Page<Operation> getOperationsByCreationDate(Date creationDate, Pageable pageable) {
			return operationService.getOperationsByCreateDate(creationDate, pageable);
		}
					
		@GetMapping("/byDateAndOperationType")
		public Page<Operation> getOperationsByCreationDateAndOperationType(@Valid @RequestParam Date creationDate,@Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return operationService.getOperationsByCreateDateAndOperationType(creationDate,operationType,pageable);
		}
		
		@GetMapping("/byDateAndPaymentMethod")
		public Page<Operation> getOperationsByCreationDateAndPaymentMethod(@Valid @RequestParam Date creationDate,@Valid @RequestParam PaymentMethod paymentMethod, Pageable pageable)  {
			return operationService.getOperationsByCreateDateAndPaymentMethod(creationDate,paymentMethod,pageable);
		}
		
		@GetMapping("/byDateAndPaymentMethodAndOperationType")
		public Page<Operation> getOperationsByCreationDateAndPaymentMethodOperationType(@Valid @RequestParam Date creationDate,@Valid @RequestParam PaymentMethod paymentMethod,@Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return operationService.getOperationsByCreateDateAndPaymentMethodAndOperationType(creationDate, paymentMethod, operationType, pageable);
		}
		
		
		// *********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT 	METHOD***************************

		@GetMapping("/byPeriod")
		public Page<Operation> getOperationsByCreateDateBetween(@Valid @RequestParam Date fromDate, @Valid @RequestParam Date toDate, Pageable pageable) {
			return operationService.getOperationsByCreateDateBetween(fromDate, toDate, pageable);
		}
					
		@GetMapping("//byPeriodAndOperationType")
		public Page<Operation> getOperationsByCreateDateBetweenAndByOperationType(@Valid @RequestParam OperationType operationType, @Valid @RequestParam Date fromDate,@Valid @RequestParam Date toDate,
				Pageable pageable)  {
			return operationService.getOperationsByCreateDateBetweenAndByOperationType(operationType, fromDate,  toDate,
					pageable);
		}
		
		@GetMapping("/byPeriodAndPaymentMethod")
		public Page<Operation> getOperationsByCreateDateBetweenAndByPaymentMethod(@Valid @RequestParam PaymentMethod paymentMethod, @Valid @RequestParam Date fromDate, @Valid @RequestParam Date toDate, Pageable pageable)  {
			return operationService.getOperationsByCreateDateBetweenAndByPaymentMethod(paymentMethod, fromDate, toDate, pageable);
		}
		
		@GetMapping("/byPeriodAndPaymentMethodAndOperationType")
		public Page<Operation> getOperationsByCreateDateBetweenAndByPaymentMethodAndOperationType(@Valid @RequestParam PaymentMethod paymentMethod,@Valid @RequestParam OperationType operationType, @Valid @RequestParam Date fromDate, @Valid @RequestParam Date toDate, Pageable pageable)  {
			return operationService.getOperationsByPaymentMethodAndOperationTypeAndCreateDateBetween(paymentMethod,operationType,fromDate,toDate,pageable);
		}
	
		// *********************BUSQUEDA POR CLIENTE Y PROVEEDORES***************************
		
		@GetMapping("/byClientIdAndOperationType")
		public Page<Operation> getOperationsByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable) {
			return operationService.getOperationsByClientIdAndOperationType(clientId, operationType, pageable);
		}
					
		@GetMapping("//byProviderIdAndOperationType")
		public Page<Operation> getOperationsByProviderIdAndOperationType( @Valid @RequestParam Long providerId, @Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return operationService.getOperationsByProviderIdAndOperationType(providerId,  operationType,  pageable);
		}
		
		@GetMapping("/byClientIdAndPaymentMethod")
		public Page<Operation> getOperationsByClientIdAndPaymentMethod(@Valid @RequestParam Long clientId, @Valid @RequestParam PaymentMethod paymentMethod, Pageable pageable)  {
			return operationService.getOperationsByClientIdAndPaymentMethod(clientId, paymentMethod,  pageable);
		}

}
