package com.svcg.StockCustom.service.impl;

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
import com.svcg.StockCustom.entity.UnidadMedida;
import com.svcg.StockCustom.repository.UnidadMedidaRepository;
import com.svcg.StockCustom.service.UnidadMedidaService;

@Service("unidadMedidaServiceImpl")
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	@Qualifier("unidadMedidaRepository")
	private UnidadMedidaRepository unidadMedidaRepository;

	@Override
	public UnidadMedida saveUnidadMedida(UnidadMedida unidadMedida) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UnidadMedida> getUnidadMedidas(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnidadMedida getUnidadMedidaByNombre(String nombre) {
		com.svcg.StockCustom.entity.UnidadMedida unidadMedida = unidadMedidaRepository
				.findByNombre(nombre);
		if (unidadMedida == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_USUARIO"), null);
		}

		return unidadMedida;
	}

	@Override
	public UnidadMedida getUnidadMedidaById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnidadMedida updateUnidadMedida(UnidadMedida unidadMedida) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean unidadMedidaNameExist(String nombre) {
		com.svcg.StockCustom.entity.UnidadMedida unidadMedida = unidadMedidaRepository
				.findByNombre(nombre);
		return unidadMedida != null;
	}

}
