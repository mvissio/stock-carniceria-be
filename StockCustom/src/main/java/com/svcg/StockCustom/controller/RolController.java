package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import com.svcg.StockCustom.service.RolService;
import com.svcg.StockCustom.service.dto.RolDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RolController {

    @Autowired
    @Qualifier("rolServiceImpl")
    private RolService rolService;

    @PostMapping("")
    public ResponseEntity<RolDTO> addRol(@Valid @RequestBody RolDTO rolDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(rolService.addRol(rolDTO));

    }
}
