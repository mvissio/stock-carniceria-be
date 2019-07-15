package com.svcg.StockCustom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.service.dto.CategoryDTO;

public interface CategoryService {

	CategoryDTO saveCategory(CategoryDTO categoryDTO);

	Page<CategoryDTO> getCategories(Pageable pageable);

	Page<CategoryDTO> getCategoryByName(String name, Pageable pageable);
	
	CategoryDTO getCategoryByName(String name);

	CategoryDTO getCategoryById(Long id);

	CategoryDTO updateCategory(CategoryDTO categoryDTO);
	
	CategoryDTO deleteCategory(Long id);
	
	Page<CategoryDTO> findByOnlyEnabledCategory(Pageable pageable);

}
