package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.Client;
import com.svcg.StockCustom.service.dto.ClientDTO;

@Component("clientConverter")
public class ClientConverter implements EntityConverter <ClientDTO, Client>  {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public ClientDTO toDTO(Client client) {
		return modelMapper.map(client, ClientDTO.class);
	}
	
	public List<ClientDTO> toDTO(List<Client> clients) {
		List<ClientDTO> clientsDTO = new ArrayList<>();
		clients.forEach(client -> clientsDTO.add(modelMapper.map(client, ClientDTO.class)));
		return clientsDTO;
	}
	
	public Client toEntity(ClientDTO clientDTO) {
		return modelMapper.map(clientDTO, Client.class);
	}
	
	public List<Client> toEntity(List<ClientDTO> clientsDTO) {
		List<Client> clients = new ArrayList<>();
		clientsDTO.forEach(clientDTO -> clients.add(modelMapper.map(clientDTO, Client.class)));
		return clients;
	}

}
