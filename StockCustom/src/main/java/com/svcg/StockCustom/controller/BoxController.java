package com.svcg.StockCustom.controller;

import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.service.impl.BoxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/boxs")
public class BoxController {


    @Autowired
    @Qualifier("boxServiceImpl")
    private BoxServiceImpl boxService;

    @GetMapping("")
    public Page<Box> getCategories(Pageable pageable) {
        return boxService.getBoxs(pageable);
    }

}
