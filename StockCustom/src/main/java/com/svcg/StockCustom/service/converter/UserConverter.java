package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.service.dto.UserDTO;

@Component("userConverter")
public class UserConverter implements EntityConverter <UserDTO, User>  {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public UserDTO toDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public List<UserDTO> toDTO(List<User> users) {
		List<UserDTO> usersDTO = new ArrayList<>();
		users.forEach(user -> usersDTO.add(modelMapper.map(user, UserDTO.class)));
		return usersDTO;
	}
	
	public User toEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}
	
	public List<User> toEntity(List<UserDTO> usersDTO) {
		List<User> users = new ArrayList<>();
		usersDTO.forEach(userDTO -> users.add(modelMapper.map(userDTO, User.class)));
		return users;
	}

}
