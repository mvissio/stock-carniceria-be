package com.svcg.StockCustom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.entity.Article;

public interface ArticleService {

	Article saveArticle(Article article);

    Page<Article> getArticles(Pageable pageable);
    
    Article getArticleByName(String name);

    Article getArticleById(Long id);

    Article updateArticle(Article article);
    
    Article deleteArticle(Long id);
    
    Page<Article> findByOnlyEnabledArticle(Pageable pageable);

	List<Article> getArticleByNameLike(String nameLike);
	
}
