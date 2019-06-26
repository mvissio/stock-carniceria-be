package com.svcg.StockCustom.service.impl;

import java.util.Date;

import com.svcg.StockCustom.entity.Provider;
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
	public Provider saveProvider(
			Provider provider) {
		if (provider == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_PROVIDER));
		}
		if (providerNameExist(provider.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_PROVIDER_EXISTS), provider.getName()));
		}
		if (emailExist(provider.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), provider.getEmail()));
		}

		/**
		 * Guardo el provider
		 */
		provider.setCreateDate(new Date());
		provider.setDisabled(false);
		logger.info("provider was saved successfully {}", provider);
		provider = saveProviderObject(provider, true);
		return provider;

	}

	/**
	 * Guardo el usuario con sus roles
	 */

	private Provider saveProviderObject(Provider provider, Boolean isSave) {
		try {
			provider = providerRepository.save(provider);
			/**
			 * Devuelvo el user creado con el rol seteado
			 */
		} catch (Exception e) {
			logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_PROVIDER) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_PROVIDER);
			throw new ResponseStatusException(HttpStatus.CONFLICT, message);
		}
		return provider;
	}

	@Override
	public Provider updateProvider(
			Provider provider) {
		if (provider == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_PROVIDER));
		}
		Provider previousProvider = providerRepository
				.findByName(provider.getName());
		if (previousProvider == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}
		if (!previousProvider.getEmail().equals(provider.getEmail())
				&& emailExist(provider.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), provider.getEmail()));
		}
		provider = saveProviderObject(provider, false);
		return provider;
	}

	@Override
	public Page<Provider> getProviders(
			Pageable pageable) {
		Page<Provider> providers = providerRepository
				.findAll(pageable);
		if (providers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDERS));
		}
		return providers;
	}

	private boolean emailExist(String email) {
		Provider provider = providerRepository
				.findByEmail(email);
		return provider != null;
	}

	private boolean providerNameExist(String name) {
		Provider provider = providerRepository
				.findByName(name);
		return provider != null;
	}

	@Override
	public Provider getProviderByName(String name) {
		Provider provider = providerRepository
				.findByName(name);
		if (provider == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}

		return provider;
	}

	@Override
	public Provider getProviderById(Long id) {
		Provider provider = providerRepository
				.findByProviderId(id);
		if (provider == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}

		return provider;
	}
	
	@Override
	public Provider deleteProvider(Long id) {
		Provider provider = providerRepository.findByProviderId(id);
		provider.setDisabled(true);
		provider.setDisabledDate(new Date());		
		return providerRepository.save(provider);
	}
	
	@Override
	public Page<Provider> findByOnlyEnabledProvider(
			Pageable pageable) {
		Page<Provider> providers = providerRepository
				.findByDisabledIsFalse(pageable);
		if (providers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}
		return providers;
	}

}
