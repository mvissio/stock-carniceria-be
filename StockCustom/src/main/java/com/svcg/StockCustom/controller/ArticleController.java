package com.svcg.StockCustom.controller;


import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.service.ArticleService;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

	@Autowired
	@Qualifier("articleServiceImpl")
	private ArticleService articleService;
	
	private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @GetMapping("")
    public Page<Article> getArticles(Pageable pageable) {
        return articleService.getArticles(pageable);
    }
    
    @GetMapping("/enabled")
    public Page<Article> getEnabledArticles(Pageable pageable) {
        return articleService.findByOnlyEnabledArticle(pageable);
    }

    @PostMapping("")
    public Article addArticle(@Valid @RequestBody Article article, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return articleService.saveArticle(article);

    }

    @PutMapping("")
    public Article updateArticle(@Valid @RequestBody Article article, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return articleService.updateArticle(article);

    }

    @GetMapping("/name/{name}")
    public Article getArticleByNombre(@PathVariable("name")String name) {
        return articleService.getArticleByName(name);
    }
    
    @GetMapping("/nameLike")
    public List<Article> getArticleByNombreLike(String nameLike) {
        return articleService.getArticleByNameLike(nameLike);
    }

   
    
    @GetMapping("/id/{id}")
    public Article getArticleById(@PathVariable("id")Long id) {
        return articleService.getArticleById(id);
    }
    
    @DeleteMapping("/id/{id}")
    public Article deleteArticle(@PathVariable("id")Long id){
    	return articleService.deleteArticle(id);
    }
    

}
