package com.svcg.StockCustom.service;

import com.svcg.StockCustom.entity.TypePerson;
import java.util.List;

public interface TypePersonService {
    TypePerson getTypePerson(Long id);
    List<TypePerson> getAllTypes();
}
