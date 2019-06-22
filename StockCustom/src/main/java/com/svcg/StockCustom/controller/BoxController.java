package com.svcg.StockCustom.controller;

import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.service.impl.BoxServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/boxs")
public class BoxController {


    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @Autowired
    @Qualifier("boxServiceImpl")
    private BoxServiceImpl boxService;

    @GetMapping("")
    public Page<Box> getCategories(Pageable pageable) {
        return boxService.getBoxs(pageable);
    }

    @PostMapping("")
    public Box addBox(@Valid @RequestBody Box box, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return boxService.saveBox(box);
    }

    @GetMapping("/boxsOpens")
    public Page<Box> getOpenBoxs(Pageable pageable) {
        return boxService.getBoxsOpen(pageable);
    }

    @GetMapping("/checkBoxOpen")
    public List<Box> getOpenBoxsList(Pageable pageable) {
        return boxService.existOpenBox();
    }
}
