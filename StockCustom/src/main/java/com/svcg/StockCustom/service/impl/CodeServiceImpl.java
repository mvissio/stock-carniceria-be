package com.svcg.StockCustom.service.impl;

import org.springframework.stereotype.Service;

import com.svcg.StockCustom.model.Code;
import com.svcg.StockCustom.service.CodeService;

@Service("codeServiceImpl")
public class CodeServiceImpl implements CodeService {

	@Override
	public Code createCode(Code codeData) {
		Code code = new Code();
		code.setMeasurementUnitId(7654389L);
		code.setNameMeasurementUnit("Tartas");
		code.setPrice(250.75);
		code.setWeight(500);		
		return code;
	}

}
