package com.carlosblinf.shoppingcart.services;

import com.carlosblinf.shoppingcart.dto.ProductDto;
import com.carlosblinf.shoppingcart.entities.Category;
import com.carlosblinf.shoppingcart.entities.Product;
import com.carlosblinf.shoppingcart.exceptions.NotFoundException;
import com.carlosblinf.shoppingcart.mappers.ProductMapper;
import com.carlosblinf.shoppingcart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    public static final int PAGE_SIZE = 10;

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final ProductMapper productMapper;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Page<Product> getProductList(int page) {
        return productRepository.findAll(PageRequest.of(page, PAGE_SIZE));
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new NotFoundException("Product not found");

        return product.get();
    }

    public Product createProduct(ProductDto productDto) {
        Category category = categoryService.getCategory(productDto.getCategoryId());

        Product product = productMapper.toProduct(productDto);
        product.setCategory(category);

        return productRepository.save(product);
    }

        public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProduct(id);
        Category category = categoryService.getCategory(productDto.getCategoryId());

        product = productMapper.toProduct(productDto);
        product.setCategory(category);

        return productRepository.save(product);
    }
}
