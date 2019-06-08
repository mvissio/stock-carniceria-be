package com.svcg.StockCustom.repository;

import com.svcg.StockCustom.entity.AditionalAtribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("aditionalAtributeRepository")
public interface AditionalAtributeRepository extends JpaRepository<AditionalAtribute,Serializable> {

    AditionalAtribute findByName(String name);

    AditionalAtribute findById(Long id);
}
