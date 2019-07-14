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
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Category;
import com.svcg.StockCustom.repository.CategoryRepository;
import com.svcg.StockCustom.service.CategoryService;
import com.svcg.StockCustom.service.converter.CategoryConverter;
import com.svcg.StockCustom.service.dto.CategoryDTO;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(CategoryServiceImpl.class);

	@Autowired
	@Qualifier("categoryRepository")
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_CATEGORY));
        }
        if (categoryNameExist(categoryDTO.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_CATEGORY_EXISTS), categoryDTO.getName()));
        }        

        /**
         * Guardo la categoria
         */
        
        categoryDTO.setCreateDate(new Date());
        categoryDTO.setDisabled(false);
        categoryDTO = saveObjectCategory(categoryDTO, true);
		logger.info("category was saved successfully {}", categoryDTO);        

        return categoryDTO;
    }

    /**
     * Guardo la categoria
     */

    private CategoryDTO saveObjectCategory(CategoryDTO categoryDTO, Boolean isSave) {
        try {
            categoryDTO = this.categoryConverter.toDTO(this.categoryRepository.save(this.categoryConverter.toEntity(categoryDTO)));
            return categoryDTO;
            
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_CATEGORY) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_CATEGORY);

            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
        
    }

   
    
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_CATEGORY));
        }
        Category previousCategory = categoryRepository.findByCategoryId(categoryDTO.getCategoryId());
        if (previousCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CATEGORY));
        }
        categoryDTO = saveObjectCategory(categoryDTO, false);
        return categoryDTO;
    }

    @Override
    public Page<CategoryDTO> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, this.messages.get(Constant.MESSAGE_NOT_FOUND_CATEGORIES));
        }
        return categories.map(this.categoryConverter::toDTO);
    }
    
    @Override
	public Page<CategoryDTO> findByOnlyEnabledCategory(Pageable pageable) {
		Page<Category> categories = categoryRepository.findByDisabledIsFalse(pageable);
		if (categories.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CATEGORIES));
		}
		return categories.map(this.categoryConverter::toDTO);	
	}
        
    
    private Boolean categoryNameExist(String name) {
        Category category = categoryRepository.findByName(name);
        return category != null;
    }

    
    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CATEGORY));
        }

        return this.categoryConverter.toDTO(category);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findByCategoryId(id);
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_CATEGORY));
        }

        return this.categoryConverter.toDTO(category);
    }
    
    @Override
	public CategoryDTO deleteCategory(Long id) {
		CategoryDTO categoryDTO = this.getCategoryById(id);
		categoryDTO.setDisabled(true);
        categoryDTO.setDisabledDate(new Date());
        categoryDTO = saveObjectCategory(categoryDTO, false);
        		
		return categoryDTO;
	}
    
    


}
