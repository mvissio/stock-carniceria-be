package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.svcg.StockCustom.entity.Category;
import com.svcg.StockCustom.service.dto.CategoryDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("categoryConverter")
public class CategoryConverter implements EntityConverter <CategoryDTO, Category>  {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public CategoryDTO toDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}
	
	public List<CategoryDTO> toDTO(List<Category> categories) {
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categories.forEach(category -> categoriesDTO.add(modelMapper.map(category, CategoryDTO.class)));
		return categoriesDTO;
	}
	
	public Category toEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}
	
	public List<Category> toEntity(List<CategoryDTO> categoriesDTO) {
		List<Category> categories = new ArrayList<>();
		categoriesDTO.forEach(categoryDTO -> categories.add(modelMapper.map(categoryDTO, Category.class)));
		return categories;
	}

}
