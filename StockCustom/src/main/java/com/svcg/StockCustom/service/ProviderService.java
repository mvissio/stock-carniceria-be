package com.svcg.StockCustom.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.Provider;



public interface ProviderService {

    Provider saveProvider(Provider provider);

    Page<Provider> getProviders(Pageable pageable);
    
    Provider getProviderByName(String name);

    Provider getProviderById(Long id);

    Provider updateProvider(Provider provider);
    
    Provider deleteProvider(Long id);
    
    Page<Provider> findByOnlyEnabledProvider(Pageable pageable);

    
}
