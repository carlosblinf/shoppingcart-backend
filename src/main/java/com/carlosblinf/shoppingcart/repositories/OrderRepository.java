package com.carlosblinf.shoppingcart.repositories;

import com.carlosblinf.shoppingcart.entities.Order;
import com.carlosblinf.shoppingcart.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findAllByStatusAndUserId(OrderStatus status, Long userId);
}
