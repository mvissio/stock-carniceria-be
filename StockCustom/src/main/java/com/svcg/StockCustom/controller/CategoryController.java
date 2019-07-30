
package com.svcg.StockCustom.controller;

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
import com.svcg.StockCustom.entity.Category;
import com.svcg.StockCustom.service.CategoryService;
import com.svcg.StockCustom.service.dto.CategoryDTO;


@RestController
@RequestMapping(value = "/categories")
public class CategoryController {


    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;


    private static final Logger logger = LoggerFactory
            .getLogger(CategoryController.class);

    @GetMapping("")
    public ResponseEntity<Page<CategoryDTO>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }
    
    @GetMapping("/enabled")
    public ResponseEntity<Page<CategoryDTO>> getEnabledCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.findByOnlyEnabledCategory(pageable));
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO category, BindingResult bindingResult) throws MethodArgumentNotValidException {
    	if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(Category.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.saveCategory(category));

    }

    @PutMapping("")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    	if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(Category.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDTO));

    }
    
    @PatchMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> enabledCategory(Boolean disabled, @PathVariable("id")Long id) {
        return ResponseEntity.ok(this.categoryService.enabledCategory(disabled, id));

    }
    
    @GetMapping("/name")
    public ResponseEntity<Page<CategoryDTO>> getCategoryByName(String name, Pageable pageable) {
        return ResponseEntity.ok(this.categoryService.getCategoryByName(name, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }
    
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id")Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
}
