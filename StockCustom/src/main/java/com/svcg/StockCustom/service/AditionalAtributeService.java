package com.svcg.StockCustom.service;

import com.svcg.StockCustom.entity.AditionalAtribute;

import java.util.List;

public interface AditionalAtributeService {
    AditionalAtribute getAditionalAtribute(Long id);
    List<AditionalAtribute> getAllTypes();
}
