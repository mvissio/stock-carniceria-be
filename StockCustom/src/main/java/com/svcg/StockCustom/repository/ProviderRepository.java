package com.svcg.StockCustom.repository;


import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.Provider;

@Repository("providerRepository")
public interface ProviderRepository extends JpaRepository<Provider, Serializable> {

	Provider findByName(String name);
	
	Provider findByEmail(String email);
	
	Provider findByProviderId(Long clientId);
	
	Page<Provider> findByDisabledIsFalse(Pageable pageable);

    

}
