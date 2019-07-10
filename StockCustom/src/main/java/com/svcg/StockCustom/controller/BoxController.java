package com.svcg.StockCustom.controller;

import java.util.List;

import javax.validation.Valid;

import com.svcg.StockCustom.service.dto.BoxDTO;
import com.svcg.StockCustom.service.dto.OperationDTO;
import com.svcg.StockCustom.service.impl.BoxServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/boxs")
public class BoxController {


    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @Autowired
    @Qualifier("boxServiceImpl")
    private BoxServiceImpl boxService;

    @GetMapping("")
    public ResponseEntity<Page<BoxDTO>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(boxService.getBoxs(pageable));
    }

    @PostMapping("")
    public ResponseEntity<BoxDTO> addBox(@Valid @RequestBody BoxDTO boxDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(boxService.saveBox(boxDTO));
    }

    @GetMapping("/boxsOpens")
    public ResponseEntity<Page<BoxDTO>> getOpenBoxs(Pageable pageable) {
        return ResponseEntity.ok(boxService.getBoxsOpen(pageable));
    }

    @GetMapping("/checkBoxOpen")
    public ResponseEntity<List<BoxDTO>> getOpenBoxsList(Pageable pageable) {
        return ResponseEntity.ok(boxService.existOpenBox());
    }

    @GetMapping("/operationsBox")
    public ResponseEntity<Page<OperationDTO>> getOperations(Long boxId, Pageable pageable) {
        return ResponseEntity.ok(boxService.getAllOperationByBoxId(boxId, pageable));
    }

    @PostMapping("/closeBox")
    public ResponseEntity<BoxDTO> closeBox(@Valid @RequestBody BoxDTO boxDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(boxService.closeBox(boxDTO.getBoxId()));
    }

}
