
package com.svcg.StockCustom.controller;

import com.svcg.StockCustom.entity.Client;
import com.svcg.StockCustom.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


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

    @PostMapping("")
    public Client addClient(@Valid @RequestBody Client client, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return clientService.saveClient(client);

    }

    @PutMapping("")
    public Client updateClient(@Valid @RequestBody Client client, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return clientService.updateClient(client);

    }

    
    @GetMapping("/name")
    public Client getClientByName(String name) {
        return clientService.getClientByName(name);
    }

    @GetMapping("id")
    public Client getClientById(Long id) {
        return clientService.getClientById(id);
    }
}
