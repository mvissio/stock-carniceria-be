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
import com.svcg.StockCustom.constant.Constant;
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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_ARTICLE));
		}
		if (articleNameExist(article.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_ARTICLE_EXISTS), article.getName()));
		}
		article.setCreateDate(new Date());
		article.setDisabled(false);
		article.setExpirationDate(null);
		article = saveArticleObjet(article, true);
		logger.info("article was saved successfully {}", article);
		return article;

	}


	@Override
	public Page<Article> getArticles(Pageable pageable) {

		Page<Article> articles = articleRepository
				.findAll(pageable);
		if (articles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLES));
		}
		return articles;

	}
	
	@Override
	public Page<Article> findByOnlyEnabledArticle(Pageable pageable) {
		Page<Article> articles = articleRepository.findByDisabledIsFalse(pageable);
		if (articles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLES));
		}
		return articles;	
	}
	
	@Override
	public Article getArticleByName(String name) {

		Article article = articleRepository
				.findByName(name);
		if (article == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		return article;

	}
	
	@Override
	public List<Article> getArticleByNameLike(String nameLike) {
		List<Article> articles = articleRepository
				.findByNameContaining(nameLike);
		if (articles == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		return articles;
	}
	
	@Override
	public Article getArticleById(Long id) {
		Article article = articleRepository.findByArticleId(id);
		if (article == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		return article;
	}

	@Override
	public Article updateArticle(Article article) {

		if (article == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_UPDATE_ARTICLE));
		}
		Article previousArticle = articleRepository.findByArticleId(article.getArticleId());
		if (previousArticle == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_ARTICLE));
		}

		article = saveArticleObjet(article, false);
		return article;

	}

	private Article saveArticleObjet(Article article, Boolean isSave) {
		try {
			return  articleRepository.save(article);

		} catch (Exception e) {
			logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_ARTICLE) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_ARTICLE);
			throw new ResponseStatusException(HttpStatus.CONFLICT, message);
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
