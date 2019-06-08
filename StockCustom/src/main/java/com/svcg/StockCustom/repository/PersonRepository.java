package com.svcg.StockCustom.repository;

import com.svcg.StockCustom.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person,Serializable> {

    Person findByName(String name);

    Person findById(Long id);
}
