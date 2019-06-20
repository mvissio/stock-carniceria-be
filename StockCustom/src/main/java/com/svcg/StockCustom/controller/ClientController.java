
package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
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
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Client;
import com.svcg.StockCustom.service.ClientService;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {


    @Autowired
    @Qualifier("clientServiceImpl")
    private ClientService clientService;


    private static final Logger logger = LoggerFactory
            .getLogger(ClientController.class);

    @GetMapping("")
    public Page<Client> getClients(Pageable pageable) {
        return clientService.getClients(pageable);
    }
    
    @GetMapping("/enabled")
    public Page<Client> getEnabledClients(Pageable pageable) {
        return clientService.findByOnlyEnabledClient(pageable);
    }

    @PostMapping("")
    public Client addClient(@Valid @RequestBody Client client, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(Client.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return clientService.saveClient(client);

    }

    @PutMapping("")
    public Client updateClient(@Valid @RequestBody Client client, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(Client.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return clientService.updateClient(client);

    }

    
    @GetMapping("/name/{name}")
    public Client getClientByName(@PathVariable("name")String name) {
        return clientService.getClientByName(name);
    }

    @GetMapping("id/{id}")
    public Client getClientById(@PathVariable("id")Long id) {
        return clientService.getClientById(id);
    }
    
    @DeleteMapping("/{id}")
    public com.svcg.StockCustom.entity.Client deleteClient(@PathVariable("id")Long id){
    	return clientService.deleteClient(id);
    }
}
