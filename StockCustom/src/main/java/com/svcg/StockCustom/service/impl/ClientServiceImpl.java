package com.svcg.StockCustom.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.repository.ClientRepository;
import com.svcg.StockCustom.service.ClientService;

@Service("clientServiceImpl")
public class ClientServiceImpl implements ClientService {

    @Autowired
    @Qualifier("clientRepository")
    private ClientRepository clientRepository;
    
    @Autowired
    Messages messages;

    private static final Logger logger = LoggerFactory
            .getLogger(ClientServiceImpl.class);

    
    @Override
    public com.svcg.StockCustom.entity.Client saveClient(com.svcg.StockCustom.entity.Client client) {
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_CLIENT"), null);
        }
        if (clientNameExist(client.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CLIENT_EXISTS") + client.getName(), null);
        }
        if (emailExist(client.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_MAIL_EXISTS") + client.getEmail(), null);
        }
        

        /**
         * Save the client 
         */
        client.setCreateDate(new Date());
        client.setDisabled(false);
        logger.info("client was saved successfully " + client );
        client = saveObjectClient(client);  
        return client;
    }

    /**
     * Guardo el usuario con sus roles
     */

    private com.svcg.StockCustom.entity.Client saveObjectClient(com.svcg.StockCustom.entity.Client client) {
        try {
            client = clientRepository.save(client);
            /**
             * Devuelvo el cliente creado 
             */
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_CREATE_USER"), null);
        }
        return client;
    }

   
    
    @Override
    public com.svcg.StockCustom.entity.Client updateClient(com.svcg.StockCustom.entity.Client client) {
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_CLIENT"), null);
        }
        com.svcg.StockCustom.entity.Client previousClient = clientRepository.findByName(client.getName());
        if (previousClient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CLIENT"), null);
        }
        if (!previousClient.getEmail().equals(client.getEmail()) && emailExist(client.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_MAIL_EXISTS") + client.getEmail());
        }
        client = saveClient(client);
        return client;
    }

    @Override
    public Page<com.svcg.StockCustom.entity.Client> getClients(Pageable pageable) {
        Page<com.svcg.StockCustom.entity.Client> clients = clientRepository.findAll(pageable);
        if (clients.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get("MESSAGE_NOT_FOUND_CLIENTS"), null);
        }
        return clients;
    }
    
    
    private boolean emailExist(String email) {
        com.svcg.StockCustom.entity.Client client = clientRepository.findByEmail(email);
        return client != null;
    }

    private boolean clientNameExist(String name) {
        com.svcg.StockCustom.entity.Client client = clientRepository.findByName(name);
        return client != null;
    }

    
    @Override
    public com.svcg.StockCustom.entity.Client getClientByName(String name) {
        com.svcg.StockCustom.entity.Client client = clientRepository.findByName(name);
        if(client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CLIENT"), null);
        }

        return client;
    }

    @Override
    public com.svcg.StockCustom.entity.Client getClientById(Long id) {
        com.svcg.StockCustom.entity.Client client = clientRepository.findByClientId(id);
        if(client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CLIENT"), null);
        }

        return client;
    }

    }
