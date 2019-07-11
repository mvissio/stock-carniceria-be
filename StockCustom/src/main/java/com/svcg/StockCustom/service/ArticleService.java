package com.svcg.StockCustom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.service.dto.ArticleDTO;

public interface ArticleService {

	ArticleDTO saveArticle(ArticleDTO articleDTO);

    Page<ArticleDTO> getArticles(Pageable pageable);

    List<ArticleDTO> getArticleByName(String name);

    ArticleDTO getArticleById(Long id);

    ArticleDTO updateArticle(ArticleDTO articleDTO);
    
    ArticleDTO deleteArticle(Long id);
    
    Page<ArticleDTO> findByOnlyEnabledArticle(Pageable pageable);

	List<ArticleDTO> getArticlesByNameOrBrandOrCodeLike(String search);
	
}
