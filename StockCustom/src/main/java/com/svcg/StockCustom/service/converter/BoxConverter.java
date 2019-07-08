package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.service.dto.BoxDTO;

@Component("boxConverter")
public class BoxConverter implements EntityConverter <BoxDTO, Box> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public BoxDTO toDTO(Box box) {
		return modelMapper.map(box, BoxDTO.class);
	}
	
	public List<BoxDTO> toDTO(List<Box> boxes) {
		List<BoxDTO> boxesDTO = new ArrayList<>();
		boxes.forEach(box -> boxesDTO.add(modelMapper.map(box, BoxDTO.class)));
		return boxesDTO;
	}
	
	public Box toEntity(BoxDTO boxDTO) {
		return modelMapper.map(boxDTO, Box.class);
	}
	
	public List<Box> toEntity(List<BoxDTO> boxesDTO) {
		List<Box> boxes = new ArrayList<>();
		boxesDTO.forEach(boxDTO -> boxes.add(modelMapper.map(boxDTO, Box.class)));
		return boxes;
	}

}
