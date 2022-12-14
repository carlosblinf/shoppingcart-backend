package com.carlosblinf.shoppingcart.repositories;

import com.carlosblinf.shoppingcart.entities.Order;
import com.carlosblinf.shoppingcart.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatusAndUserId(OrderStatus status, Long userId);
    List<Order> findAllByStatus(OrderStatus status);

    List<Order> findAllByUserId(Long userId);
}
