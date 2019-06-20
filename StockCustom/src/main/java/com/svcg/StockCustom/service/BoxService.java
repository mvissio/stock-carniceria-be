package com.svcg.StockCustom.service;

import com.svcg.StockCustom.entity.Box;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;


public interface BoxService {
    Box saveBox(Box box);

    Page<Box> getBoxs(Pageable pageable);

    Box getBoxById(Long id);

    Box getBoxDateOpen(Date date);

    Box getBoxDateClose(Date date);

    Box updateBox(Box box);

}
