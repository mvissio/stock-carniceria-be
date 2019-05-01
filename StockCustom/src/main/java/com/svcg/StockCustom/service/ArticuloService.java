package com.svcg.StockCustom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.Articulo;
import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.enums.RolName;

public interface ArticuloService {

	Articulo saveArticulo(Articulo articulo);

    Page<Articulo> getArticulos(Pageable pageable);
    
    Articulo getArticuloByNombre(String nombre);

    Articulo getArticuloById(Long id);

    Articulo updateArticulo(Articulo articulo);

	
}
