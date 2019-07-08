package com.svcg.StockCustom.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.service.dto.ClientDTO;



public interface ClientService {

    ClientDTO saveClient(ClientDTO clientDTO);

    Page<ClientDTO> getClients(Pageable pageable);
    
    ClientDTO getClientByName(String name);

    ClientDTO getClientById(Long id);

    ClientDTO updateClient(ClientDTO clientDTO);
    
    ClientDTO deleteClient(Long id);
    
    Page<ClientDTO> findByOnlyEnabledClient(Pageable pageable);
    
}
