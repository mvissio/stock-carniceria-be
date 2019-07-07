package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.MeasurementUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article,Serializable> {

    Article findByName(String name);

    Article findByArticleId(Long articleId);    
    
    Optional<List<Article>> findByMeasurementUnitIdIn(List<MeasurementUnit> unidadesMedida);
    
    Page<Article> findByDisabledIsFalse(Pageable pageable);

	List<Article> findByNameContainingOrBrandContaining(String name, String brand);
	
	
}
