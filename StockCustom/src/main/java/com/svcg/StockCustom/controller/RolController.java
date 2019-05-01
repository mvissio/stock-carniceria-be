package com.svcg.StockCustom.controller;

import com.svcg.StockCustom.entity.Rol;
import com.svcg.StockCustom.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
public class RolController {

    @Autowired
    @Qualifier("rolServiceImpl")
    private RolService rolService;

    @PostMapping("")
    public Rol addRol(@Valid @RequestBody Rol rol) {

        return rolService.addRol(rol);

    }
}
