package com.svcg.StockCustom.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.entity.Articulo;
import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.service.ArticuloService;

@RestController
@RequestMapping(value = "/articulos")
public class ArticuloController {

	@Autowired
	@Qualifier("articuloServiceImpl")
	private ArticuloService articuloService;
	
	private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @GetMapping("")
    public Page<Articulo> getArticulos(Pageable pageable) {
        return articuloService.getArticulos(pageable);
    }

    @PostMapping("")
    public Articulo addArticulo(@Valid @RequestBody Articulo articulo, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return articuloService.saveArticulo(articulo);

    }

    @PutMapping("")
    public Articulo updateArticulo(@Valid @RequestBody Articulo articulo, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return articuloService.updateArticulo(articulo);

    }
    

    @GetMapping("/nombre")
    public Articulo getArticuloByNombre(String nombre) {
        return articuloService.getArticuloByNombre(nombre);
    }

    @GetMapping("id")
    public Articulo getArticuloById(Long id) {
        return articuloService.getArticuloById(id);
    }

}
