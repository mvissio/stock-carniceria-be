package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.repository.BoxRepository;
import com.svcg.StockCustom.repository.OperationRepository;
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
import java.util.List;

@Service("boxServiceImpl")
public class BoxServiceImpl implements BoxService {

    @Autowired
    @Qualifier("boxRepository")
    private BoxRepository boxRepository;

    @Autowired
    @Qualifier("operationRepository")
    private OperationRepository operationRepository;
    Messages messages;

    private static final Logger logger = LoggerFactory
            .getLogger(BoxServiceImpl.class);

    @Override
    public Box saveBox(Box box) {
        Box newBox  = boxRepository.save(box);
        return newBox;
    }

    @Override
    public Page<Box> getBoxs(Pageable pageable) {
        Page<Box> boxs = boxRepository.findAll(pageable);
        if (boxs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOXS));
        }
        return boxs;
    }

    @Override
    public Box getBoxById(Long id) {
        Box box = boxRepository.findByBoxId(id);
        if (box == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOX));
        }
        return box;
    }

    @Override
    public List<Box> existOpenBox() {
        return boxRepository.findByDateCloseIsNullOrOpenIsTrue();
    }

    @Override
    public Page<Box> getBoxsOpen(Pageable pageable) {
        Page<Box> boxs = boxRepository.findByDateCloseIsNull(pageable);
        if (boxs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOXS_OPEN));
        }
        return boxs;
    }

    @Override
    public Page<Operation> getAllOperationByBoxId(Long boxId, Pageable pageable) {
        Page<Operation> operations = operationRepository.findAllByBoxId(boxId, pageable);
        if (operations == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
        }
        return operations;
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
