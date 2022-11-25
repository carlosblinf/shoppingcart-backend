package com.carlosblinf.shoppingcart.repositories;

import com.carlosblinf.shoppingcart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
