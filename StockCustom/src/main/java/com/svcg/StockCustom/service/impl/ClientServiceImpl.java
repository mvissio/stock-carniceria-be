package com.svcg.StockCustom.service.impl;

import java.util.Date;

import com.svcg.StockCustom.entity.Client;
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
import com.svcg.StockCustom.constant.Constant;
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
    public Client saveClient(Client client) {
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_CLIENT));
        }
        if (clientNameExist(client.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_CLIENT_EXISTS), client.getName()));
        }
        if (emailExist(client.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), client.getEmail()));
        }
        

        /**
         * Save the client 
         */
        client.setCreateDate(new Date());
        client.setDisabled(false);
        logger.info("client was saved successfully {}", client);
        client = saveObjectClient(client, true);  
        return client;
    }


    private Client saveObjectClient(Client client, Boolean isSave) {
        try {
            client = clientRepository.save(client);
            /**
             * Devuelvo el cliente creado 
             */
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_CLIENT) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_CLIENT);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
        return client;
    }

   
    
    @Override
    public Client updateClient(Client client) {
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_CLIENT));
        }
        Client previousClient = clientRepository.findByName(client.getName());
        if (previousClient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
        }
        if (!previousClient.getEmail().equals(client.getEmail()) && emailExist(client.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), client.getEmail()));
        }
        client = saveObjectClient(client, false);
        return client;
    }

    @Override
    public Page<Client> getClients(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        if (clients.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENTS));
        }
        return clients;
    }
    
    
    private boolean emailExist(String email) {
        Client client = clientRepository.findByEmail(email);
        return client != null;
    }

    private boolean clientNameExist(String name) {
        Client client = clientRepository.findByName(name);
        return client != null;
    }

    
    @Override
    public Client getClientByName(String name) {
        Client client = clientRepository.findByName(name);
        if(client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
        }

        return client;
    }

    @Override
    public Client getClientById(Long id) {
        Client client = clientRepository.findByClientId(id);
        if(client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
        }

        return client;
    }
    
    @Override
	public Client deleteClient(Long id) {
		Client client = clientRepository.findByClientId(id);
		client.setDisabled(true);
		client.setDisabledDate(new Date());		
		return clientRepository.save(client);
	}
    
    @Override
	public Page<Client> findByOnlyEnabledClient(Pageable pageable) {
		Page<Client> clients = clientRepository.findByDisabledIsFalse(pageable);
		if (clients.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
		}
		return clients;	
	}

    }
