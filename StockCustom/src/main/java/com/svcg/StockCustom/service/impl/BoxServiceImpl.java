package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.repository.BoxRepository;
import com.svcg.StockCustom.service.BoxService;
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

@Service("boxServiceImpl")
public class BoxServiceImpl implements BoxService {

    @Autowired
    @Qualifier("boxRepository")
    private BoxRepository boxRepository;
    Messages messages;

    private static final Logger logger = LoggerFactory
            .getLogger(BoxServiceImpl.class);

    @Override
    public Box saveBox(Box box) {
        return null;
    }

    @Override
    public Page<Box> getBoxs(Pageable pageable) {
        Page<Box> boxs = boxRepository.findAll(pageable);
        if (boxs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get("MESSAGE_NOT_FOUND_BOXS"), null);
        }
        return boxs;
    }
    @Override
    public Box getBoxById(Long id) {
        Box box = boxRepository.findByBoxId(id);
        if(box == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CAJAS"), null);
        }
        return box;
    }

    @Override
    public Box updateBox(Box box) {
        return null;
    }

    @Override
    public Box getBoxDateOpen(Date date) {
        return null;
    }

    @Override
    public Box getBoxDateClose(Date date) {
        return null;
    }
}
