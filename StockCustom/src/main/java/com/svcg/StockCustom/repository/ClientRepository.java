package com.svcg.StockCustom.repository;


import com.svcg.StockCustom.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Serializable> {

	Client findByName(String name);
	
	Client findByEmail(String email);
	
	Client findByClientId(Long clientId);

    

}
