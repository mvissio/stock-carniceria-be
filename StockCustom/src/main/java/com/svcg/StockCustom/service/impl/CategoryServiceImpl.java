package com.svcg.StockCustom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.repository.CategoryRepository;
import com.svcg.StockCustom.repository.UnidadMedidaRepository;
import com.svcg.StockCustom.service.CategoryService;
import com.svcg.StockCustom.service.UnidadMedidaService;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(CategoryServiceImpl.class);

	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;

    @Override
    public com.svcg.StockCustom.entity.Category saveCategory(com.svcg.StockCustom.entity.Category category) {
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_CLIENT"), null);
        }
        if (categoryNameExist(category.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CLIENT_EXISTS") + category.getName(), null);
        }        

        /**
         * Guardo el usuario con sus roles
         */
        category = saveObjectCategory(category);

        return category;
    }

    /**
     * Guardo el usuario con sus roles
     */

    private com.svcg.StockCustom.entity.Category saveObjectCategory(com.svcg.StockCustom.entity.Category category) {
        try {
            category = categoryRepository.save(category);
            /**
             * Devuelvo el user creado con el rol seteado
             */
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_CREATE_USER"), null);
        }
        return category;
    }

   
    
    @Override
    public com.svcg.StockCustom.entity.Category updateCategory(com.svcg.StockCustom.entity.Category category) {
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_PROVIDER"), null);
        }
        com.svcg.StockCustom.entity.Category previousCategory = categoryRepository.findByName(category.getName());
        if (previousCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_PROVIDER"), null);
        }
        category = saveCategory(category);
        return category;
    }

    @Override
    public Page<com.svcg.StockCustom.entity.Category> getCategories(Pageable pageable) {
        Page<com.svcg.StockCustom.entity.Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get("MESSAGE_NOT_FOUND_PROVIDERS"), null);
        }
        return categories;
    }
        
    
    private boolean categoryNameExist(String name) {
        com.svcg.StockCustom.entity.Category category = categoryRepository.findByName(name);
        return category != null;
    }

    
    @Override
    public com.svcg.StockCustom.entity.Category getCategoryByName(String name) {
        com.svcg.StockCustom.entity.Category category = categoryRepository.findByName(name);
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_PROVIDER"), null);
        }

        return category;
    }

    @Override
    public com.svcg.StockCustom.entity.Category getCategoryById(Long id) {
        com.svcg.StockCustom.entity.Category category = categoryRepository.findByCategoryId(id);
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_PROVIDER"), null);
        }

        return category;
    }

}
