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

    Optional<Article> findByName(String name);

    Optional<Page<Article>>findByNameContaining(String name, Pageable pageable);

    Optional<Article> findByArticleId(Long articleId);    
    
    Optional<List<Article>> findByMeasurementUnitIdIn(List<MeasurementUnit> unidadesMedida);
    
    Optional<Page<Article>> findByDisabledIsFalse(Pageable pageable);

	Optional<List<Article>> findByNameContainingOrBrandContainingOrCodeContaining(String name, String brand, String code);
}
