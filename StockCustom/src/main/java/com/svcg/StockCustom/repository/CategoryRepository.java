package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.svcg.StockCustom.entity.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Serializable> {

	Optional<Category> findByName(String name);
	
	Optional<Page<Category>> findByNameContaining(String name, Pageable pageable);
	
	Category findByCategoryId(Long id);
	
	Page<Category> findByDisabledIsFalse(Pageable pageable);
	
	
}
