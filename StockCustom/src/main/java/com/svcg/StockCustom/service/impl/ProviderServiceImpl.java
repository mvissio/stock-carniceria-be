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
import com.svcg.StockCustom.service.converter.ProviderConverter;
import com.svcg.StockCustom.service.dto.ProviderDTO;

@Service("providerServiceImpl")
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	@Qualifier("providerRepository")
	private ProviderRepository providerRepository;

	@Autowired
	Messages messages;

	@Autowired
    private ProviderConverter providerConverter;

	private static final Logger logger = LoggerFactory
			.getLogger(ProviderServiceImpl.class);

	@Override
	public ProviderDTO saveProvider(ProviderDTO providerDTO) {
		if (providerDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_PROVIDER));
		}
		if (providerNameExist(providerDTO.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_PROVIDER_EXISTS), providerDTO.getName()));
		}
		if (emailExist(providerDTO.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), providerDTO.getEmail()));
		}

		/**
		 * Guardo el provider
		 */
		providerDTO.setCreateDate(new Date());
		providerDTO.setDisabled(false);
		logger.info("provider was saved successfully {}", providerDTO);
		providerDTO = saveProviderObject(providerDTO, true);
		return providerDTO;

	}

	/**
	 * Guardo el usuario con sus roles
	 */

	private ProviderDTO saveProviderObject(ProviderDTO providerDTO, Boolean isSave) {
		try {
			providerDTO = this.providerConverter.toDTO(providerRepository.save(this.providerConverter.toEntity(providerDTO)));
			/**
			 * Devuelvo el user creado con el rol seteado
			 */
		} catch (Exception e) {
			logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_PROVIDER) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_PROVIDER);
			throw new ResponseStatusException(HttpStatus.CONFLICT, message);
		}
		return providerDTO;
	}

	@Override
	public ProviderDTO updateProvider(ProviderDTO providerDTO) {
		if (providerDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_PROVIDER));
		}
		Provider previousProvider = providerRepository
				.findByName(providerDTO.getName());
		if (previousProvider == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}
		if (!previousProvider.getEmail().equals(providerDTO.getEmail())
				&& emailExist(providerDTO.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), providerDTO.getEmail()));
		}
		providerDTO = saveProviderObject(providerDTO, false);
		return providerDTO;
	}

	@Override
	public Page<ProviderDTO> getProviders(Pageable pageable) {
		Page<Provider> providers = providerRepository
				.findAll(pageable);
		if (providers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDERS));
		}
		return providers.map(this.providerConverter::toDTO);
	}

	private Boolean emailExist(String email) {
		Provider provider = providerRepository
				.findByEmail(email);
		return provider != null;
	}

	private Boolean providerNameExist(String name) {
		Provider provider = providerRepository
				.findByName(name);
		return provider != null;
	}

	@Override
	public ProviderDTO getProviderByName(String name) {
		Provider provider = providerRepository
				.findByName(name);
		if (provider == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}

		return this.providerConverter.toDTO(provider);
	}

	@Override
	public ProviderDTO getProviderById(Long id) {
		Provider provider = providerRepository
				.findByProviderId(id);
		if (provider == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}

		return this.providerConverter.toDTO(provider);
	}
	
	@Override
	public ProviderDTO deleteProvider(Long id) {
		ProviderDTO providerDTO = this.getProviderById(id);
		providerDTO.setDisabled(true);
		providerDTO.setDisabledDate(new Date());		
		providerDTO = this.saveProviderObject(providerDTO, false);
		return providerDTO;
	}
	
	@Override
	public Page<ProviderDTO> findByOnlyEnabledProvider(
			Pageable pageable) {
		Page<Provider> providers = providerRepository
				.findByDisabledIsFalse(pageable);
		if (providers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_PROVIDER));
		}
		return providers.map(this.providerConverter::toDTO);
	}

}
