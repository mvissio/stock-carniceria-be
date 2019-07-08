package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.repository.BoxRepository;
import com.svcg.StockCustom.repository.OperationRepository;
import com.svcg.StockCustom.service.BoxService;
import com.svcg.StockCustom.service.converter.BoxConverter;
import com.svcg.StockCustom.service.converter.OperationConverter;
import com.svcg.StockCustom.service.dto.BoxDTO;
import com.svcg.StockCustom.service.dto.OperationDTO;

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
    
    @Autowired
    Messages messages;

    @Autowired
    private BoxConverter boxConverter;

    @Autowired
    private OperationConverter operationConverter;

    private static final Logger logger = LoggerFactory.getLogger(BoxServiceImpl.class);

    @Override
    public BoxDTO saveBox(BoxDTO boxDTO) {
    	BoxDTO newBox;
        try {
            newBox  = saveBoxObjet(boxDTO, true);
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION, e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get(Constant.MESSAGE_CANT_CREATE_BOX));
        }
        return newBox;
    }

    @Override
    public Page<BoxDTO> getBoxs(Pageable pageable) {
        Page<Box> boxs = boxRepository.findAll(pageable);
        if (boxs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOXS));
        }
        return boxs.map(this.boxConverter::toDTO);
    }

    @Override
    public BoxDTO getBoxById(Long id) {
        Box box = boxRepository.findByBoxId(id);
        if (box == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOX));
        }
        return this.boxConverter.toDTO(box);
    }

    @Override
    public List<BoxDTO> existOpenBox() {
        return this.boxConverter.toDTO(boxRepository.findByDateCloseIsNullOrOpenIsTrue());
    }

    @Override
    public Page<BoxDTO> getBoxsOpen(Pageable pageable) {
        Page<Box> boxs = boxRepository.findByDateCloseIsNull(pageable);
        if (boxs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOXS_OPEN));
        }
        return boxs.map(this.boxConverter::toDTO);
    }

    @Override
    public Page<OperationDTO> getAllOperationByBoxId(Long boxId, Pageable pageable) {
        Page<Operation> operations = operationRepository.findAllByBoxId(boxId, pageable);
        if (operations == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
        }
        return operations.map(this.operationConverter::toDTO);
    }

    @Override
    public BoxDTO updateBox(BoxDTO boxDTO) {
        
        if (boxDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_UPDATE_BOX));
		}
		Box previousBox = boxRepository.findByBoxId(boxDTO.getBoxId());
		if (previousBox == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_BOX));
		}

		boxDTO = saveBoxObjet(boxDTO, false);
		return boxDTO;
    }

    private BoxDTO saveBoxObjet(BoxDTO boxDTO, Boolean isSave) {
		try {
			return  this.boxConverter.toDTO(boxRepository.save(this.boxConverter.toEntity(boxDTO)));

		} catch (Exception e) {
			logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_BOX) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_BOX);
			throw new ResponseStatusException(HttpStatus.CONFLICT, message);
		}
	}

    @Override
    public BoxDTO getBoxDateOpen(Date date) {
        return null;
    }

    @Override
    public BoxDTO getBoxDateClose(Date date) {
        return null;
    }
}
