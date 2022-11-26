package com.carlosblinf.shoppingcart.services;

import com.carlosblinf.shoppingcart.dto.CartItemsDto;
import com.carlosblinf.shoppingcart.entities.Order;
import com.carlosblinf.shoppingcart.entities.OrderItem;
import com.carlosblinf.shoppingcart.entities.enums.OrderStatus;
import com.carlosblinf.shoppingcart.exceptions.CustomException;
import com.carlosblinf.shoppingcart.repositories.OrderItemRepository;
import com.carlosblinf.shoppingcart.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartService cartService;

    public Page<Order> getAllOrderList(int page) {
        return orderRepository.findAll(PageRequest.of(page, ProductService.PAGE_SIZE));
    }

    public List<Order> getAllOrderStatusList(OrderStatus status) {
        return orderRepository.findAllByStatus(status);
    }

    public List<Order> getUserOrderList(Long user_id) {
        return orderRepository.findAllByUserId(user_id);
    }

    @Transactional
    public Object createOrder(Long user_id) {
        CartItemsDto cartItemsDto = cartService.getCartItems(user_id);
        if (cartItemsDto.getCartItems().isEmpty())
            return cartItemsDto.getCartItems();

        Order order = new Order(null,cartItemsDto.getTotalCost(),
                LocalDateTime.now(), null, OrderStatus.NEW_ORDER, user_id, null);

        Order newOrder = orderRepository.save(order);

        List<OrderItem> items = cartItemsDto.getCartItems().stream().map(cart ->
                orderItemRepository.save(new OrderItem(
                        null, cart.getQuantity(), cart.getPrice(), LocalDateTime.now(), newOrder, cart.getProduct())))
                .collect(Collectors.toList());

        if (!cartService.clearCart(user_id))
            throw new CustomException("Can't create order", HttpStatus.CONFLICT);

        return newOrder;
    }

}
