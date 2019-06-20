package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.service.dto.UserDTO;

@Component("userConverter")
public class UserConverter {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public UserDTO convertToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public List<UserDTO> convertToListDTO(List<User> users) {
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(user -> userDTOs.add(modelMapper.map(user, UserDTO.class)));
		return userDTOs;
	}
	
	public User convertToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}
	
	public List<User> convertToListEntity(List<UserDTO> userDTOs) {
		List<User> users = new ArrayList<>();
		userDTOs.forEach(userModel -> users.add(modelMapper.map(userDTOs, User.class)));
		return users;
	}

}
