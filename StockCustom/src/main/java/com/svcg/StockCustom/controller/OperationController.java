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
import org.springframework.web.bind.annotation.*;

import com.svcg.StockCustom.entity.Article;
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
	
	@GetMapping("/creationDate")
	public Page<Operation> getOperationsByCreationDate(Date creationDate, Pageable pageable) {
		return operationService.getOperationsByCreationDate(creationDate, pageable);
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

}
