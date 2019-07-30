package com.svcg.StockCustom.controller;


import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.service.ArticleService;
import com.svcg.StockCustom.service.dto.ArticleDTO;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

	@Autowired
	@Qualifier("articleServiceImpl")
	private ArticleService articleService;
	
	private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<Page<ArticleDTO>> getArticles(Pageable pageable) {
        return ResponseEntity.ok(this.articleService.getArticles(pageable));
    }
    
    @GetMapping("/enabled")
    public ResponseEntity<Page<ArticleDTO>> getEnabledArticles(Pageable pageable) {
        return ResponseEntity.ok(this.articleService.findByOnlyEnabledArticle(pageable));
    }

    @PostMapping("")
    public ResponseEntity<ArticleDTO> addArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    	if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(ArticleDTO.class.getDeclaredConstructors()[1],0), bindingResult);
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(this.articleService.saveArticle(articleDTO));

    }

    @PutMapping("")
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    	if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(ArticleDTO.class.getDeclaredConstructors()[1],0), bindingResult);
		}
        return ResponseEntity.ok(this.articleService.updateArticle(articleDTO));

    }
    
    @PatchMapping("/id/{id}")
    public ResponseEntity<ArticleDTO> enabledArticle(Boolean disabled, @PathVariable("id")Long id) {
        return ResponseEntity.ok(this.articleService.enabledArticle(disabled, id));

    }

    @GetMapping("/name")
    public ResponseEntity<Page<ArticleDTO>> getArticleByNombre(String name, Pageable pageable) {
        return ResponseEntity.ok(this.articleService.getArticleByName(name, pageable));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ArticleDTO>> getArticlesByNameOrBrandOrCodeLike(String search) {
        return ResponseEntity.ok(this.articleService.getArticlesByNameOrBrandOrCodeLike(search));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(this.articleService.getArticleById(id));
    }
    
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id")Long id) {
    	this.articleService.deleteArticle(id);
    	return ResponseEntity.noContent().build();
    }
    

}
