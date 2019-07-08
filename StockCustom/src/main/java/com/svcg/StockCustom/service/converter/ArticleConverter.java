package com.svcg.StockCustom.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.service.dto.ArticleDTO;

@Component("articleConverter")
public class ArticleConverter implements EntityConverter <ArticleDTO, Article> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public ArticleDTO toDTO(Article article) {
		return modelMapper.map(article, ArticleDTO.class);
	}
	
	public List<ArticleDTO> toDTO(List<Article> articles) {
		List<ArticleDTO> articlesDTO = new ArrayList<>();
		articles.forEach(article -> articlesDTO.add(modelMapper.map(article, ArticleDTO.class)));
		return articlesDTO;
	}
	
	public Article toEntity(ArticleDTO articleDTO) {
		return modelMapper.map(articleDTO, Article.class);
	}
	
	public List<Article> toEntity(List<ArticleDTO> articlesDTO) {
		List<Article> articles = new ArrayList<>();
		articlesDTO.forEach(articleDTO -> articles.add(modelMapper.map(articleDTO, Article.class)));
		return articles;
	}

}
