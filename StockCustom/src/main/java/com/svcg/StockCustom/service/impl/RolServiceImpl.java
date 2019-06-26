package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Rol;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.repository.RolRepository;
import com.svcg.StockCustom.service.RolService;
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

    private static final Logger logger = LoggerFactory
            .getLogger(RolServiceImpl.class);

    @Override
    public Rol addRol(Rol rol) {
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_ROL));
        }
        if (!RolName.existsRol(rol.getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_NOT_FOUND_ROLE), rol.getName()));
        }
        try {
            rol = rolRepository.save(rol);
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get(Constant.MESSAGE_CANT_CREATE_ROL));
        }
        return rol;
    }
}
