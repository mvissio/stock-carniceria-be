package com.svcg.StockCustom.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Serializable> {

	Category findByName(String name);
	
	Category findByCategoryId(Long id);
	
	
}
