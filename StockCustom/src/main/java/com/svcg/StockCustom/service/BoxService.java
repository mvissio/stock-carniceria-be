package com.svcg.StockCustom.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.svcg.StockCustom.service.dto.BoxDTO;
import com.svcg.StockCustom.service.dto.OperationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoxService {
    BoxDTO saveBox(@Valid BoxDTO boxDTO);

    Page<BoxDTO> getBoxs(Pageable pageable);

    Page<BoxDTO> getBoxsOpen(Pageable pageable);

    BoxDTO getBoxById(Long id);

    BoxDTO closeBox(Long id);

    BoxDTO getBoxDateOpen(Date date);

    BoxDTO getBoxDateClose(Date date);

    BoxDTO updateBox(@Valid BoxDTO boxDTO);

    List<BoxDTO> existOpenBox();

    Page<OperationDTO> getAllOperationByBoxId(Long id, Pageable pageable);
    List<OperationDTO> getAllOperationByBoxId(Long id);
}
