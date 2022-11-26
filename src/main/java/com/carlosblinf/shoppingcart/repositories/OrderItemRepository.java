package com.carlosblinf.shoppingcart.repositories;

import com.carlosblinf.shoppingcart.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
