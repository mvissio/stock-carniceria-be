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
import com.svcg.StockCustom.service.converter.ClientConverter;
import com.svcg.StockCustom.service.dto.ClientDTO;

@Service("clientServiceImpl")
public class ClientServiceImpl implements ClientService {

    @Autowired
    @Qualifier("clientRepository")
    private ClientRepository clientRepository;
    
    @Autowired
    Messages messages;

    @Autowired
    private ClientConverter clientConverter;

    private static final Logger logger = LoggerFactory
            .getLogger(ClientServiceImpl.class);

    
    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        if (clientDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_CLIENT));
        }
        if (clientNameExist(clientDTO.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_CLIENT_EXISTS), clientDTO.getName()));
        }
        if (emailExist(clientDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), clientDTO.getEmail()));
        }
        

        /**
         * Save the client 
         */
        clientDTO.setCreateDate(new Date());
        clientDTO.setDisabled(false);
        logger.info("client was saved successfully {}", clientDTO);
        clientDTO = saveObjectClient(clientDTO, true);  
        return clientDTO;
    }


    private ClientDTO saveObjectClient(ClientDTO clientDTO, Boolean isSave) {
        try {
            clientDTO = this.clientConverter.toDTO(this.clientRepository.save(this.clientConverter.toEntity(clientDTO)));
            /**
             * Devuelvo el cliente creado 
             */
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_CLIENT) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_CLIENT);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
        return clientDTO;
    }

   
    
    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        if (clientDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_CLIENT));
        }
        Client previousClient = this.clientRepository.findByName(clientDTO.getName());
        if (previousClient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
        }
        if (!previousClient.getEmail().equals(clientDTO.getEmail()) && emailExist(clientDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), clientDTO.getEmail()));
        }
        clientDTO = saveObjectClient(clientDTO, false);
        return clientDTO;
    }

    @Override
    public Page<ClientDTO> getClients(Pageable pageable) {
        Page<Client> clients = this.clientRepository.findAll(pageable);
        if (clients.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENTS));
        }
        return clients.map(this.clientConverter::toDTO);
    }
    
    
    private Boolean emailExist(String email) {
        Client client = this.clientRepository.findByEmail(email);
        return client != null;
    }

    private Boolean clientNameExist(String name) {
        Client client = this.clientRepository.findByName(name);
        return client != null;
    }

    
    @Override
    public ClientDTO getClientByName(String name) {
        Client client = this.clientRepository.findByName(name);
        if(client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
        }

        return this.clientConverter.toDTO(client);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = this.clientRepository.findByClientId(id);
        if(client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
        }

        return this.clientConverter.toDTO(client);
    }
    
    @Override
	public ClientDTO deleteClient(Long id) {
		ClientDTO clientDTO = this.getClientById(id);
		clientDTO.setDisabled(true);
		clientDTO.setDisabledDate(new Date());		
        clientDTO = this.saveObjectClient(clientDTO, false);
        
        return clientDTO;
	}
    
    @Override
	public Page<ClientDTO> findByOnlyEnabledClient(Pageable pageable) {
		Page<Client> clients = this.clientRepository.findByDisabledIsFalse(pageable);
		if (clients.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CLIENT));
		}
		return clients.map(this.clientConverter::toDTO);	
	}

    }
