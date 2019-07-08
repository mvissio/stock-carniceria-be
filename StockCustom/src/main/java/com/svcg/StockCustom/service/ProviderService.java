package com.svcg.StockCustom.service;

import com.svcg.StockCustom.service.dto.ProviderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ProviderService {

    ProviderDTO saveProvider(ProviderDTO provider);

    Page<ProviderDTO> getProviders(Pageable pageable);
    
    ProviderDTO getProviderByName(String name);

    ProviderDTO getProviderById(Long id);

    ProviderDTO updateProvider(ProviderDTO provider);
    
    ProviderDTO deleteProvider(Long id);
    
    Page<ProviderDTO> findByOnlyEnabledProvider(Pageable pageable);

    
}
