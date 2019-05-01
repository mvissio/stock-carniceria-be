package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.svcg.StockCustom.entity.Articulo;
import com.svcg.StockCustom.entity.UnidadMedida;

@Repository("articuloRepository")
public interface ArticuloRepository extends JpaRepository<Articulo,Serializable> {

    Articulo findByNombre(String nombre);

    Articulo findByArticuloId(Long articuloId);    
    
    Optional<List<Articulo>> findByidUnidadMedidaIn(List<UnidadMedida> unidadesMedida);
	
}
