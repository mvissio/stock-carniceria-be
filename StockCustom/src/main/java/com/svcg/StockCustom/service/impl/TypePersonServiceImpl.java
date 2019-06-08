package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.entity.Person;
import com.svcg.StockCustom.entity.TypePerson;
import com.svcg.StockCustom.repository.PersonRepository;
import com.svcg.StockCustom.repository.TypePersonRepository;
import com.svcg.StockCustom.service.PersonService;
import com.svcg.StockCustom.service.TypePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typePersonServiceImpl")
public class TypePersonServiceImpl implements TypePersonService {

	@Autowired
	@Qualifier("typePersonRepository")
	private TypePersonRepository typePersonRepository;

	@Override
	public TypePerson getTypePerson(Long id) {
		return null;
	}

	@Override
	public List<TypePerson> getAllTypes() {
		return null;
	}
}
