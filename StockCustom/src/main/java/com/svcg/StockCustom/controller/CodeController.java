package com.svcg.StockCustom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.model.Code;
import com.svcg.StockCustom.service.CodeService;

@RestController
@RequestMapping(value = "/codes")
public class CodeController {

	@Autowired
	@Qualifier("codeServiceImpl")
	private CodeService codeService;

	private static final Logger logger = LoggerFactory
			.getLogger(ClientController.class);

	@PostMapping("/")
	public Code createCode(Code codeDate) {
		logger.info("realizo creacion del codigo");
		return codeService.createCode(codeDate);
	}

}
