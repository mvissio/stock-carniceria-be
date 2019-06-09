package com.svcg.StockCustom.service.impl;

import java.util.Date;
import java.util.List;

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
import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.repository.ArticleRepository;
import com.svcg.StockCustom.service.ArticleService;

@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier("articleRepository")
	private ArticleRepository articleRepository;

	@Autowired
	Messages messages;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	public Article saveArticle(Article article) {

		/**
		 * Save the article
		 */

		if (article == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}
		if (articleNameExist(article.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_ARTICULO_EXISTS")
							+ article.getName(), null);
		}
		article.setCreateDate(new Date());
		article.setDisabled(false);
		article.setExpirationDate(null);
		article = saveArticleObjet(article);
		logger.info("article was saved successfully " + article );
		return article;

	}


	@Override
	public Page<Article> getArticles(Pageable pageable) {

		Page<Article> articles = articleRepository
				.findAll(pageable);
		if (articles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULOS"), null);
		}
		return articles;

	}
	
	@Override
	public Page<Article> findByOnlyEnabledArticle(Pageable pageable) {
		Page<Article> articles = articleRepository.findByDisabledIsFalse(pageable);
		if (articles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULOS"), null);
		}
		return articles;	
	}
	
	@Override
	public Article getArticleByName(String name) {

		Article article = articleRepository
				.findByName(name);
		if (article == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		return article;

	}
	
	@Override
	public List<Article> getArticleByNameLike(String nameLike) {
		List<Article> articles = articleRepository
				.findByNameContaining(nameLike);
		if (articles == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		return articles;
	}
	
	@Override
	public Article getArticleById(Long id) {
		Article article = articleRepository.findByArticleId(id);
		if (article == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		return article;
	}

	@Override
	public Article updateArticle(Article article) {

		if (article == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}
		Article previousArticle = articleRepository.findByArticleId(article.getArticleId());
		if (previousArticle == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_ARTICULO"), null);
		}

		article = saveArticleObjet(article);
		return article;

	}

	private Article saveArticleObjet(
			Article article) {
		try {

			Article articleCreated = articleRepository.save(article);
			return articleCreated;

		} catch (Exception e) {
			logger.error("Exception: {} ", e);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					this.messages.get("MESSAGE_CANT_CREATE_ARTICULO"), null);
		}

	}
		
	private boolean articleNameExist(String name) {
		Article article = articleRepository
				.findByName(name);
		return article != null;
	}

	@Override
	public Article deleteArticle(Long id) {
		Article article = articleRepository.findByArticleId(id);
		article.setDisabled(true);
		article.setDisabledDate(new Date());		
		return articleRepository.save(article);
	}

}
