package com.svcg.StockCustom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.repository.ArticleRepository;
import com.svcg.StockCustom.service.ArticleService;
import com.svcg.StockCustom.service.converter.ArticleConverter;
import com.svcg.StockCustom.service.dto.ArticleDTO;

@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier("articleRepository")
	private ArticleRepository articleRepository;

	@Autowired
	Messages messages;
	
	@Autowired
    private ArticleConverter articleConverter;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	public ArticleDTO saveArticle(ArticleDTO articleDTO) {
		if (articleDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_ARTICLE));
		}
		if (articleNameExist(articleDTO.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_ARTICLE_EXISTS), articleDTO.getName()));
		}
		articleDTO.setCreateDate(new Date());
		articleDTO.setDisabled(false);

		if(articleDTO.getExpirationDate() != null){
			articleDTO.setExpirationDate(articleDTO.getExpirationDate());
		} else {
			articleDTO.setExpirationDate(null);
		}
		ArticleDTO newArticleDTO = saveArticleObjet(articleDTO, true);
		logger.info("article was saved successfully {}", newArticleDTO);
		return newArticleDTO;

	}


	@Override
	public Page<ArticleDTO> getArticles(Pageable pageable) {

		Page<Article> articles = this.articleRepository
				.findAll(pageable);
		if (articles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLES));
		}
		return articles.map(this.articleConverter::toDTO);

	}
	
	@Override
	public Page<ArticleDTO> findByOnlyEnabledArticle(Pageable pageable) {
		Optional<Page<Article>> articles = this.articleRepository.findByDisabledIsFalse(pageable);
		if (!articles.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLES));
		}
		return articles.get().map(this.articleConverter::toDTO);	
	}
	
	@Override
	public Page<ArticleDTO> getArticleByName(String name, Pageable pageable) {

		Optional<Page<Article>> articles= this.articleRepository
				.findByNameContaining(name, pageable);
		if (!articles.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		return articles.get().map(this.articleConverter::toDTO);

	}
	
	@Override
	public List<ArticleDTO> getArticlesByNameOrBrandOrCodeLike(String search) {
		Optional<List<Article>> articles = this.articleRepository
				.findByNameContainingOrBrandContaining(search, search);
		if (!articles.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		return this.articleConverter.toDTO(articles.get());
	}
	
	@Override
	public ArticleDTO getArticleById(Long id) {
		Optional<Article> article = this.articleRepository.findByArticleId(id);
		if (article.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		return this.articleConverter.toDTO(article.get());
	}

	@Override
	public ArticleDTO updateArticle(ArticleDTO articleDTO) {

		if (articleDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_UPDATE_ARTICLE));
		}
		Optional<Article> previousArticle = this.articleRepository.findByArticleId(articleDTO.getArticleId());
		if (!previousArticle.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}
		return saveArticleObjet(articleDTO, false);
	}

	private ArticleDTO saveArticleObjet(ArticleDTO articleDTO, Boolean isSave) {
		try {
			return  this.articleConverter.toDTO(this.articleRepository.save(this.articleConverter.toEntity(articleDTO)));

		} catch (Exception e) {
			logger.error(Constant.EXCEPTION, e);
            String message = isSave ? this.messages.get(Constant.MESSAGE_CANT_CREATE_ARTICLE) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_ARTICLE);
			throw new ResponseStatusException(HttpStatus.CONFLICT, message);
		}
	}
		
	private Boolean articleNameExist(String name) {
		Optional<Article> article = this.articleRepository
				.findByName(name);
		return article.isPresent();
	}

	@Override
	public ArticleDTO deleteArticle(Long id) {
		Optional<Article> article = this.articleRepository.findByArticleId(id);
		if (!article.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}
		article.get().setDisabled(true);
		article.get().setDisabledDate(new Date());		
		return this.articleConverter.toDTO(this.articleRepository.save(article.get()));
	}

}
