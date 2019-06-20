package com.svcg.StockCustom.service.impl;

import java.util.Date;

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
import com.svcg.StockCustom.entity.Category;
import com.svcg.StockCustom.repository.CategoryRepository;
import com.svcg.StockCustom.service.CategoryService;

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
    public Category saveCategory(Category category) {
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_CATEGORY"), null);
        }
        if (categoryNameExist(category.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CATEGORY_EXISTS") + category.getName(), null);
        }        

        /**
         * Guardo la categoria
         */
        
        category.setCreateDate(new Date());
        category.setDisabled(false);
        category = saveObjectCategory(category);
		logger.info("category was saved successfully " + category );        

        return category;
    }

    /**
     * Guardo la categoria
     */

    private Category saveObjectCategory(Category category) {
        try {
            category = categoryRepository.save(category);
            return category;
            
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_CREATE_CATEGORY"), null);
        }
        
    }

   
    
    @Override
    public Category updateCategory(Category category) {
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_CATEGORY"), null);
        }
        Category previousCategory = categoryRepository.findByCategoryId(category.getCategoryId());
        if (previousCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CATEGORY"), null);
        }
        category = saveObjectCategory(category);
        return category;
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get("MESSAGE_NOT_FOUND_CATEGORIES"), null);
        }
        return categories;
    }
    
    @Override
	public Page<Category> findByOnlyEnabledCategory(Pageable pageable) {
		Page<Category> categories = categoryRepository.findByDisabledIsFalse(pageable);
		if (categories.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_CATEGORIES"), null);
		}
		return categories;	
	}
        
    
    private boolean categoryNameExist(String name) {
        Category category = categoryRepository.findByName(name);
        return category != null;
    }

    
    @Override
    public Category getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CATEGORY"), null);
        }

        return category;
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findByCategoryId(id);
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_CATEGORY"), null);
        }

        return category;
    }
    
    @Override
	public Category deleteCategory(Long id) {
		Category category = categoryRepository.findByCategoryId(id);
		category.setDisabled(true);
		category.setDisabledDate(new Date());		
		return categoryRepository.save(category);
	}
    
    


}
