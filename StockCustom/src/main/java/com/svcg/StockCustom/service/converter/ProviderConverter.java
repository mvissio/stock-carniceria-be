package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.Provider;
import com.svcg.StockCustom.service.dto.ProviderDTO;

@Component("providerConverter")
public class ProviderConverter implements EntityConverter <ProviderDTO, Provider>  {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public ProviderDTO toDTO(Provider provider) {
        return modelMapper.map(provider, ProviderDTO.class);
    }
    
    public List<ProviderDTO> toDTO(List<Provider> providers) {
        List<ProviderDTO> providersDTO = new ArrayList<>();
        providers.forEach(provider -> providersDTO.add(modelMapper.map(provider, ProviderDTO.class)));
        return providersDTO;
    }
    
    public Provider toEntity(ProviderDTO providerDTO) {
        return modelMapper.map(providerDTO, Provider.class);
    }
    
    public List<Provider> toEntity(List<ProviderDTO> providersDTO) {
        List<Provider> providers = new ArrayList<>();
        providersDTO.forEach(providerDTO -> providers.add(modelMapper.map(providerDTO, Provider.class)));
        return providers;
    }
}
