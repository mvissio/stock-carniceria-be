package com.svcg.StockCustom.repository;


import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.Client;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Serializable> {

	Client findByName(String name);
	
	Client findByEmail(String email);
	
	Client findByClientId(Long clientId);
	
	Page<Client> findByDisabledIsFalse(Pageable pageable);

    

}
