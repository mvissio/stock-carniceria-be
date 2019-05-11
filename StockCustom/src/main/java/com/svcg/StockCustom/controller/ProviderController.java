
package com.svcg.StockCustom.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.svcg.StockCustom.entity.Provider;
import com.svcg.StockCustom.service.ProviderService;


@RestController
@RequestMapping(value = "/providers")
public class ProviderController {


    @Autowired
    @Qualifier("providerServiceImpl")
    private ProviderService providerService;


    private static final Logger logger = LoggerFactory
            .getLogger(ProviderController.class);

    @GetMapping("")
    public Page<Provider> getProviders(Pageable pageable) {
        return providerService.getProviders(pageable);
    }

    @PostMapping("")
    public Provider addProvider(@Valid @RequestBody Provider provider, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return providerService.saveProvider(provider);

    }

    @PutMapping("")
    public Provider updateProvider(@Valid @RequestBody Provider provider, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return providerService.updateProvider(provider);

    }

    
    @GetMapping("/name/{name}")
    public Provider getProviderByName(@PathVariable("name")String name) {
        return providerService.getProviderByName(name);
    }

    @GetMapping("id/{id}")
    public Provider getProviderById(@PathVariable("id")Long id) {
        return providerService.getProviderById(id);
    }
}
