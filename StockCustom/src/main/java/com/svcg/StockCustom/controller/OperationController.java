package com.svcg.StockCustom.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;
import com.svcg.StockCustom.service.OperationService;
import com.svcg.StockCustom.service.dto.OperationDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "/operation")
public class OperationController {

    @Autowired
    @Qualifier("operationServiceImpl")
    private OperationService operationService;

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @PostMapping("")
    public ResponseEntity<OperationDTO> addOperation(@Valid @RequestBody OperationDTO operationDTO,
            BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(OperationDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.operationService.saveOperation(operationDTO));

    }

    @PutMapping("")
    public ResponseEntity<OperationDTO> updateOperation(@Valid @RequestBody OperationDTO operationDTO,
                                  BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(OperationDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.ok(this.operationService.updateOperation(operationDTO));

    }
    
    @GetMapping("operationTypes")
    public ResponseEntity<List<OperationType>> getOperationTypes() {
        return ResponseEntity.ok(this.operationService.getOperationTypes());
    }
    
    @GetMapping("paymentMethods")
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        return ResponseEntity.ok(this.operationService.getPaymentMethods());
    }
    
    @GetMapping("id/{id}")
    public ResponseEntity<OperationDTO> getOperationById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(this.operationService.getOperationById(id));
    }
		
	/****************************************************
	 * METODOS PARA LOS FILTROS
	 */
	
	// *********************BUSQUEDA POR TIPO, PAYMENT METHOD***************************
	    
	    @GetMapping("/byOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByType(@Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByOperationType(operationType,pageable));
		}
	    
	    @GetMapping("/byPaymentMethod")
		public ResponseEntity<Page<OperationDTO>> getOperationsByPaymentMethod(@Valid @RequestParam PaymentMethod paymentMethod, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsPaymentMethod(paymentMethod,pageable));			
		}
	    
	    @GetMapping("/byOperationTypeAndPaymentMethod")
		public ResponseEntity<Page<OperationDTO>> getOperationsByTypeAndPaymentMethodAnd(@Valid @RequestParam Date createDate, @Valid @RequestParam PaymentMethod paymentMethod,
				Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByOperationTypeAndPaymentMethod(createDate, paymentMethod,pageable));			
		}
	    
	    
	    
	    
	 // *********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT METHOD***************************
		
		@GetMapping("/createDate")
	    public ResponseEntity<Page<OperationDTO>> getOperationsByCreateDate(Date createDate, Pageable pageable) {
	        return ResponseEntity.ok(this.operationService.getOperationsByCreateDate(createDate, pageable));
	    }
					
		@GetMapping("/byDateAndOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreationDateAndOperationType(@Valid @RequestParam Date createDate,@Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByCreateDateAndOperationType(createDate,operationType,pageable));
		}
		
		@GetMapping("/byDateAndPaymentMethod")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreationDateAndPaymentMethod(@Valid @RequestParam Date createDate,@Valid @RequestParam PaymentMethod paymentMethod, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByCreateDateAndPaymentMethod(createDate,paymentMethod,pageable));
		}
		
		@GetMapping("/byDateAndPaymentMethodAndOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreationDateAndPaymentMethodOperationType(@Valid @RequestParam Date createDate,@Valid @RequestParam PaymentMethod paymentMethod,@Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByCreateDateAndPaymentMethodAndOperationType(createDate, paymentMethod, operationType, pageable));
		}
		
		
		// *********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT 	METHOD***************************

		@GetMapping("/byPeriod")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreateDateBetween(@Valid @RequestParam Date fromDate, @Valid @RequestParam Date toDate, Pageable pageable) {
			return ResponseEntity.ok(operationService.getOperationsByCreateDateBetween(fromDate, toDate, pageable));
		}
					
		@GetMapping("//byPeriodAndOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreateDateBetweenAndByOperationType(@Valid @RequestParam OperationType operationType, @Valid @RequestParam Date fromDate,@Valid @RequestParam Date toDate,
				Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByCreateDateBetweenAndByOperationType(operationType, fromDate,  toDate,
					pageable));
		}
		
		@GetMapping("/byPeriodAndPaymentMethod")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreateDateBetweenAndByPaymentMethod(@Valid @RequestParam PaymentMethod paymentMethod, @Valid @RequestParam Date fromDate, @Valid @RequestParam Date toDate, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByCreateDateBetweenAndByPaymentMethod(paymentMethod, fromDate, toDate, pageable));
		}
		
		@GetMapping("/byPeriodAndPaymentMethodAndOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByCreateDateBetweenAndByPaymentMethodAndOperationType(@Valid @RequestParam PaymentMethod paymentMethod,@Valid @RequestParam OperationType operationType, @Valid @RequestParam Date fromDate, @Valid @RequestParam Date toDate, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByPaymentMethodAndOperationTypeAndCreateDateBetween(paymentMethod,operationType,fromDate,toDate,pageable));
		}
	
		// *********************BUSQUEDA POR CLIENTE Y PROVEEDORES***************************
		
		@GetMapping("/byClientIdAndOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByClientIdAndOperationType(Long clientId, OperationType operationType, Pageable pageable) {
			return ResponseEntity.ok(operationService.getOperationsByClientIdAndOperationType(clientId, operationType, pageable));
		}
					
		@GetMapping("//byProviderIdAndOperationType")
		public ResponseEntity<Page<OperationDTO>> getOperationsByProviderIdAndOperationType( @Valid @RequestParam Long providerId, @Valid @RequestParam OperationType operationType, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByProviderIdAndOperationType(providerId,  operationType,  pageable));
		}
		
		@GetMapping("/byClientIdAndPaymentMethod")
		public ResponseEntity<Page<OperationDTO>> getOperationsByClientIdAndPaymentMethod(@Valid @RequestParam Long clientId, @Valid @RequestParam PaymentMethod paymentMethod, Pageable pageable)  {
			return ResponseEntity.ok(operationService.getOperationsByClientIdAndPaymentMethod(clientId, paymentMethod,  pageable));
		}
    @DeleteMapping("/cancel/id/{id}")
    public ResponseEntity<Void> cancelOperation(@PathVariable("id") Long id) {
		this.operationService.cancelOperation(id);
		return ResponseEntity.noContent().build();
    }

}
