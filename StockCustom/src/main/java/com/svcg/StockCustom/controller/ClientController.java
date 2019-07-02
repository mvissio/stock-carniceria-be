
package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.service.ClientService;
import com.svcg.StockCustom.service.dto.ClientDTO;

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
@RequestMapping(value = "/clients")
public class ClientController {


    @Autowired
    @Qualifier("clientServiceImpl")
    private ClientService clientService;


    private static final Logger logger = LoggerFactory
            .getLogger(ClientController.class);

    @GetMapping("")
    public ResponseEntity<Page<ClientDTO>> getClients(Pageable pageable) {
        return ResponseEntity.ok(this.clientService.getClients(pageable));
    }
    
    @GetMapping("/enabled")
    public ResponseEntity<Page<ClientDTO>> getEnabledClients(Pageable pageable) {
        return ResponseEntity.ok(this.clientService.findByOnlyEnabledClient(pageable));
    }

    @PostMapping("")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(ClientDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.saveClient(clientDTO));

    }

    @PutMapping("")
    public ResponseEntity<ClientDTO> updateClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));          
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(ClientDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.ok(this.clientService.updateClient(clientDTO));

    }

    
    @GetMapping("/name/{name}")
    public ResponseEntity<ClientDTO> getClientByName(@PathVariable("name")String name) {
        return ResponseEntity.ok(this.clientService.getClientByName(name));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(this.clientService.getClientById(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id")Long id){
        this.clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
