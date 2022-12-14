package com.carlosblinf.shoppingcart.repositories;

import com.carlosblinf.shoppingcart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByProductIdAndUserId(Long productId, Long userId);

    List<Cart> findAllByUserId(Long userId);

    void deleteByUserId(Long user_id);
}
