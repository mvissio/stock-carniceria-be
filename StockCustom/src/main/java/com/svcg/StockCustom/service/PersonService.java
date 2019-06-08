package com.svcg.StockCustom.service;

import com.svcg.StockCustom.entity.Person;

import java.util.List;

public interface PersonService {
    Person getPerson(Long id);
    List<Person> getAllTypes();
}
