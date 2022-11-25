package com.carlosblinf.shoppingcart.services;

import com.carlosblinf.shoppingcart.dto.ProductDto;
import com.carlosblinf.shoppingcart.entities.Category;
import com.carlosblinf.shoppingcart.entities.Product;
import com.carlosblinf.shoppingcart.exceptions.NotFoundException;
import com.carlosblinf.shoppingcart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final int PAGE_SIZE = 10;

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public List<Product> getProductList() {
        List<Product> products = productRepository.findAll();
//        List<Product> products = (List<Product>) productRepository.findAll(PageRequest.of(page, PAGE_SIZE));

        return products;
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new NotFoundException("Product not found");

        return product.get();
    }

    public Product createProduct(ProductDto productDto) {
        Category category = categoryService.getCategory(productDto.getCategoryId());

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);

        return productRepository.save(product);
    }

        public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProduct(id);
        Category category = categoryService.getCategory(productDto.getCategoryId());

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);

        return productRepository.save(product);
    }
}
