package com.svcg.StockCustom.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.svcg.StockCustom.entity.UnidadMedida;

@Repository("unidadMedidaRepository")
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Serializable> {

	 UnidadMedida findByNombre(String nombre);
	
}
