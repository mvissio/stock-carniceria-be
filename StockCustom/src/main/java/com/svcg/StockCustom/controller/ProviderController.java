
package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.service.ProviderService;
import com.svcg.StockCustom.service.dto.ProviderDTO;

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
@RequestMapping(value = "/providers")
public class ProviderController {

    @Autowired
    @Qualifier("providerServiceImpl")
    private ProviderService providerService;


    private static final Logger logger = LoggerFactory
            .getLogger(ProviderController.class);

    @GetMapping("")
    public ResponseEntity<Page<ProviderDTO>> getProviders(Pageable pageable) {
        return ResponseEntity.ok(this.providerService.getProviders(pageable));
    }
    
    @GetMapping("/enabled")
    public ResponseEntity<Page<ProviderDTO>> getEnabledProviders(Pageable pageable) {
        return ResponseEntity.ok(this.providerService.findByOnlyEnabledProvider(pageable));
    }

    @PostMapping("")
    public ResponseEntity<ProviderDTO> addProvider(@Valid @RequestBody ProviderDTO providerDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(ProviderDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.providerService.saveProvider(providerDTO));

    }

    @PutMapping("")
    public ResponseEntity<ProviderDTO> updateProvider(@Valid @RequestBody ProviderDTO providerDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(ProviderDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.ok(this.providerService.updateProvider(providerDTO));

    }

    
    @GetMapping("/name/{name}")
    public ResponseEntity<ProviderDTO> getProviderByName(@PathVariable("name")String name) {
        return ResponseEntity.ok(this.providerService.getProviderByName(name));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProviderDTO> getProviderById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(this.providerService.getProviderById(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(
            @PathVariable("id") Long id) {
        this.providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }    
}
