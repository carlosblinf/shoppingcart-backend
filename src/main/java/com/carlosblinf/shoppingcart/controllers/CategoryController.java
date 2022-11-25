package com.carlosblinf.shoppingcart.controllers;

import com.carlosblinf.shoppingcart.entities.Category;
import com.carlosblinf.shoppingcart.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategoryList() {
        return new ResponseEntity<>(categoryService.getCategoryList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> newCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }
}
