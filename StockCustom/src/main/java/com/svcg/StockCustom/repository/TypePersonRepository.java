package com.svcg.StockCustom.repository;

import com.svcg.StockCustom.entity.TypePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("typePersonRepository")
public interface TypePersonRepository extends JpaRepository<TypePerson,Serializable> {

    TypePerson findByName(String name);

    TypePerson findById(Long id);
}
