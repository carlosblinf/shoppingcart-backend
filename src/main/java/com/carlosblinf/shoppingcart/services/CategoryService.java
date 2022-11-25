package com.carlosblinf.shoppingcart.services;

import com.carlosblinf.shoppingcart.entities.Category;
import com.carlosblinf.shoppingcart.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            throw new RuntimeException("Category not found");

        return category.get();
    }

    public Category createCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent())
            throw new RuntimeException("Category already exists");

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category newCategory) {
        Category category = getCategory(id);
        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());

        return categoryRepository.save(category);
    }
}
