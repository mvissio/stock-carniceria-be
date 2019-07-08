package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.svcg.StockCustom.entity.Rol;
import com.svcg.StockCustom.service.dto.RolDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rolConverter")
public class RolConverter implements EntityConverter <RolDTO, Rol>  {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public RolDTO toDTO(Rol rol) {
		return modelMapper.map(rol, RolDTO.class);
	}
	
	public List<RolDTO> toDTO(List<Rol> users) {
		List<RolDTO> rolesDTO = new ArrayList<>();
		users.forEach(user -> rolesDTO.add(modelMapper.map(user, RolDTO.class)));
		return rolesDTO;
	}
	
	public Rol toEntity(RolDTO rolDTO) {
		return modelMapper.map(rolDTO, Rol.class);
	}
	
	public List<Rol> toEntity(List<RolDTO> rolesDTO) {
		List<Rol> roles = new ArrayList<>();
		rolesDTO.forEach(rolDTO -> roles.add(modelMapper.map(rolDTO, Rol.class)));
		return roles;
	}

}
