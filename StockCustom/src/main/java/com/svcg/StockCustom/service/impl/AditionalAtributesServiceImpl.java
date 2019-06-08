package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.entity.AditionalAtribute;
import com.svcg.StockCustom.entity.Person;
import com.svcg.StockCustom.repository.AditionalAtributeRepository;
import com.svcg.StockCustom.repository.PersonRepository;
import com.svcg.StockCustom.service.AditionalAtributeService;
import com.svcg.StockCustom.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("aditionalAtributesServiceImpl")
public class AditionalAtributesServiceImpl implements AditionalAtributeService {

    @Autowired
    @Qualifier("aditionalAtributeRepository")
    private AditionalAtributeRepository aditionalAtributeRepository;

    @Override
    public AditionalAtribute getAditionalAtribute(Long id) {
        return null;
    }

    @Override
    public List<AditionalAtribute> getAllTypes() {
        return null;
    }
}
