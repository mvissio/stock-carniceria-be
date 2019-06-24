package com.svcg.StockCustom.service;

import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface BoxService {
    Box saveBox(Box box);

    Page<Box> getBoxs(Pageable pageable);

    Page<Box> getBoxsOpen(Pageable pageable);

    Box getBoxById(Long id);

    Box getBoxDateOpen(Date date);

    Box getBoxDateClose(Date date);

    Box updateBox(Box box);

    List<Box> existOpenBox();

    Page<Operation> getAllOperationByBoxId(Long box, Pageable pageable);
}
