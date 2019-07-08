package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.repository.RolRepository;
import com.svcg.StockCustom.service.RolService;
import com.svcg.StockCustom.service.converter.RolConverter;
import com.svcg.StockCustom.service.dto.RolDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("rolServiceImpl")
public class RolServiceImpl implements RolService {

    @Autowired
    @Qualifier("rolRepository")
    private RolRepository rolRepository;

    @Autowired
    Messages messages;

    @Autowired
	private RolConverter rolConverter;

    private static final Logger logger = LoggerFactory
            .getLogger(RolServiceImpl.class);

    @Override
    public RolDTO addRol(RolDTO rolDTO) {
        if (rolDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_ROL));
        }
        if (!RolName.existsRol(rolDTO.getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_NOT_FOUND_ROLE), rolDTO.getName()));
        }
        try {
            rolDTO = this.rolConverter.toDTO(rolRepository.save(this.rolConverter.toEntity(rolDTO)));
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get(Constant.MESSAGE_CANT_CREATE_ROL));
        }
        return rolDTO;
    }
}
