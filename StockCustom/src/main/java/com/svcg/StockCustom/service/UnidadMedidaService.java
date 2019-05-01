package com.svcg.StockCustom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.UnidadMedida;

public interface UnidadMedidaService {

	UnidadMedida saveUnidadMedida(UnidadMedida unidadMedida);

	Page<UnidadMedida> getUnidadMedidas(Pageable pageable);

	UnidadMedida getUnidadMedidaByNombre(String nombre);

	UnidadMedida getUnidadMedidaById(Long id);

	UnidadMedida updateUnidadMedida(UnidadMedida unidadMedida);

}
