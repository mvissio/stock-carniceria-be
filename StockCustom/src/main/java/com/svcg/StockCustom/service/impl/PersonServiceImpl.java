package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.Person;
import com.svcg.StockCustom.repository.ArticleRepository;
import com.svcg.StockCustom.repository.PersonRepository;
import com.svcg.StockCustom.service.ArticleService;
import com.svcg.StockCustom.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {

	@Autowired
	@Qualifier("personRepository")
	private PersonRepository personRepository;

	@Override
	public Person getPerson(Long id) {
		return null;
	}

	@Override
	public List<Person> getAllTypes() {
		return null;
	}
}
