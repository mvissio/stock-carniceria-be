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
    
    @GetMapping("/creationDate")
    public ResponseEntity<Page<OperationDTO>> getOperationsByCreationDate(Date creationDate, Pageable pageable) {
        return ResponseEntity.ok(this.operationService.getOperationsByCreationDate(creationDate, pageable));
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

    @DeleteMapping("/cancel/id/{id}")
    public ResponseEntity<Void> cancelOperation(@PathVariable("id") Long id) {
		this.operationService.cancelOperation(id);
		return ResponseEntity.noContent().build();
    }

}
