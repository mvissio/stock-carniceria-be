
package com.svcg.StockCustom.controller;

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
import com.svcg.StockCustom.entity.Category;
import com.svcg.StockCustom.service.CategoryService;


@RestController
@RequestMapping(value = "/categories")
public class CategoryController {


    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;


    private static final Logger logger = LoggerFactory
            .getLogger(CategoryController.class);

    @GetMapping("")
    public Page<Category> getCategories(Pageable pageable) {
        return categoryService.getCategories(pageable);
    }
    
    @GetMapping("/enabled")
    public Page<Category> getEnabledCategories(Pageable pageable) {
        return categoryService.findByOnlyEnabledCategory(pageable);
    }

    @PostMapping("")
    public Category addCategory(@Valid @RequestBody Category provider, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return categoryService.saveCategory(provider);

    }

    @PutMapping("")
    public Category updateCategory(@Valid @RequestBody Category category, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getAllErrors()));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return categoryService.updateCategory(category);

    }

    
    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable("name")String name) {
        return categoryService.getCategoryByName(name);
    }

    @GetMapping("id/{id}")
    public Category getCategoryById(@PathVariable("id")Long id) {
        return categoryService.getCategoryById(id);
    }
    
    @DeleteMapping("/{id}")
    public com.svcg.StockCustom.entity.Category deleteCategory(@PathVariable("id")Long id){
    	return categoryService.deleteCategory(id);
    }
    
}
