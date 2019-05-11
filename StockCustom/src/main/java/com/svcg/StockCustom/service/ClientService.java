package com.svcg.StockCustom.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.Client;



public interface ClientService {

    Client saveClient(Client client);

    Page<Client> getClients(Pageable pageable);
    
    Client getClientByName(String name);

    Client getClientById(Long id);

    Client updateClient(Client client);
    
    Client deleteClient(Long id);
    
}
