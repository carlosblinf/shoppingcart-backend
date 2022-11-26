package com.carlosblinf.shoppingcart.controllers;

import com.carlosblinf.shoppingcart.entities.Order;
import com.carlosblinf.shoppingcart.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrderList(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(orderService.getAllOrderList(page));
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Order>> getUserOrderList(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(orderService.getUserOrderList(user_id));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestParam("user_id") Long user_id) {
        return ResponseEntity.created(URI.create("/api/orders/user/"+user_id)).body(orderService.createOrder(user_id));
    }
}
