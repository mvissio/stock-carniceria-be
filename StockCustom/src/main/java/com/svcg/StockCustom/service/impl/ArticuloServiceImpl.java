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
import com.svcg.StockCustom.entity.Articulo;
import com.svcg.StockCustom.repository.ArticuloRepository;
import com.svcg.StockCustom.service.ArticuloService;

@Service("articuloServiceImpl")
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	@Qualifier("articuloRepository")
	private ArticuloRepository articuloRepository;

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	public Articulo saveArticulo(Articulo articulo) {

		/**
		 * Guardo el articulo
		 */

		if (articulo == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}
		if (articuloNameExist(articulo.getNombre())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_ARTICULO_EXISTS")
							+ articulo.getNombre(), null);
		}
		/**
		 * Guardo el articulo
		 */
		articulo = saveObjetoArticulo(articulo);

		return articulo;

	}

	@Override
	public Page<Articulo> getArticulos(Pageable pageable) {

		Page<com.svcg.StockCustom.entity.Articulo> articulos = articuloRepository
				.findAll(pageable);
		if (articulos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULOS"), null);
		}
		return articulos;

	}

	@Override
	public Articulo getArticuloByNombre(String nombre) {

		com.svcg.StockCustom.entity.Articulo articulo = articuloRepository
				.findByNombre(nombre);
		if (articulo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		return articulo;

	}

	@Override
	public Articulo getArticuloById(Long id) {
		com.svcg.StockCustom.entity.Articulo articulo = articuloRepository
				.findByArticuloId(id);
		if (articulo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		return articulo;
	}

	@Override
	public Articulo updateArticulo(Articulo articulo) {

		if (articulo == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}
		com.svcg.StockCustom.entity.Articulo previousArticulo = articuloRepository
				.findByNombre(articulo.getNombre());
		if (previousArticulo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		articulo = saveObjetoArticulo(articulo);
		return articulo;

	}

	private com.svcg.StockCustom.entity.Articulo saveObjetoArticulo(
			com.svcg.StockCustom.entity.Articulo articulo) {
		try {

			Articulo articuloCreado = articuloRepository.save(articulo);
			return articuloCreado;

		} catch (Exception e) {
			logger.error("Exception: {} ", e);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}

	}

	private boolean articuloNameExist(String nombre) {
		com.svcg.StockCustom.entity.Articulo articulo = articuloRepository
				.findByNombre(nombre);
		return articulo != null;
	}

}
