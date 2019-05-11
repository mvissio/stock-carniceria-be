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
import com.svcg.StockCustom.repository.ProviderRepository;
import com.svcg.StockCustom.service.ProviderService;

@Service("providerServiceImpl")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    @Qualifier("providerRepository")
    private ProviderRepository providerRepository;
    
    @Autowired
    Messages messages;

    private static final Logger logger = LoggerFactory
            .getLogger(ProviderServiceImpl.class);

    
    @Override
    public com.svcg.StockCustom.entity.Provider saveProvider(com.svcg.StockCustom.entity.Provider provider) {
        if (provider == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_CLIENT"), null);
        }
        if (providerNameExist(provider.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CLIENT_EXISTS") + provider.getName(), null);
        }
        if (emailExist(provider.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_MAIL_EXISTS") + provider.getEmail(), null);
        }
        

        /**
         * Guardo el provider
         */
        provider.setCreateDate(new Date());
        provider.setDisabled(false);
        logger.info("provider was saved successfully " + provider );
        provider = saveProviderObject(provider);        
		return provider;
       
    }

    /**
     * Guardo el usuario con sus roles
     */

    private com.svcg.StockCustom.entity.Provider saveProviderObject(com.svcg.StockCustom.entity.Provider provider) {
        try {
            provider = providerRepository.save(provider);
            /**
             * Devuelvo el user creado con el rol seteado
             */
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_CREATE_USER"), null);
        }
        return provider;
    }

   
    
    @Override
    public com.svcg.StockCustom.entity.Provider updateProvider(com.svcg.StockCustom.entity.Provider provider) {
        if (provider == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_PROVIDER"), null);
        }
        com.svcg.StockCustom.entity.Provider previousProvider = providerRepository.findByName(provider.getName());
        if (previousProvider == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_PROVIDER"), null);
        }
        if (!previousProvider.getEmail().equals(provider.getEmail()) && emailExist(provider.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_MAIL_EXISTS") + provider.getEmail());
        }
        provider = saveProvider(provider);
        return provider;
    }

    @Override
    public Page<com.svcg.StockCustom.entity.Provider> getProviders(Pageable pageable) {
        Page<com.svcg.StockCustom.entity.Provider> providers = providerRepository.findAll(pageable);
        if (providers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get("MESSAGE_NOT_FOUND_PROVIDERS"), null);
        }
        return providers;
    }
    
    
    private boolean emailExist(String email) {
        com.svcg.StockCustom.entity.Provider provider = providerRepository.findByEmail(email);
        return provider != null;
    }

    private boolean providerNameExist(String name) {
        com.svcg.StockCustom.entity.Provider provider = providerRepository.findByName(name);
        return provider != null;
    }

    
    @Override
    public com.svcg.StockCustom.entity.Provider getProviderByName(String name) {
        com.svcg.StockCustom.entity.Provider provider = providerRepository.findByName(name);
        if(provider == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_PROVIDER"), null);
        }

        return provider;
    }

    @Override
    public com.svcg.StockCustom.entity.Provider getProviderById(Long id) {
        com.svcg.StockCustom.entity.Provider provider = providerRepository.findByProviderId(id);
        if(provider == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_PROVIDER"), null);
        }

        return provider;
    }

    }
