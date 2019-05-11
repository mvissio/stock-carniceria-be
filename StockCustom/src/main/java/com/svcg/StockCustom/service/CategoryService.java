package com.svcg.StockCustom.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.Category;

public interface CategoryService {

	Category saveCategory(Category category);

	Page<Category> getCategories(Pageable pageable);

	Category getCategoryByName(String name);

	Category getCategoryById(Long id);

	Category updateCategory(Category category);
	
	Category deleteCategory(Long id);

}
