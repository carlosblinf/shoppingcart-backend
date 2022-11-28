package com.carlosblinf.shoppingcart.services;

import com.carlosblinf.shoppingcart.dto.AddCartDto;
import com.carlosblinf.shoppingcart.dto.CartItemsDto;
import com.carlosblinf.shoppingcart.entities.Cart;
import com.carlosblinf.shoppingcart.entities.Product;
import com.carlosblinf.shoppingcart.exceptions.NotFoundException;
import com.carlosblinf.shoppingcart.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ProductService productService;

    public CartItemsDto getCartItems(Long user_id) {
        List<Cart> cartList = cartRepository.findAllByUserId(user_id);
        if (cartList.isEmpty())
            return new CartItemsDto(new ArrayList<>(), 0);

        List<AddCartDto> cartItems = cartList.stream()
                .map(cart -> new AddCartDto(
                        cart.getQuantity(),cart.getProduct().getId(), cart.getUserId()))
                .collect(Collectors.toList());

        double subtotal = cartList.stream()
                .reduce(0.0, (suma , item) -> suma + item.getPrice() * item.getQuantity(), Double::sum);

        return new CartItemsDto(cartList, subtotal);
    }

    public Cart addToCart(AddCartDto cartDto) {
        List<Cart> carts = cartRepository
                .findByProductIdAndUserId(cartDto.getProductId(), cartDto.getUserId());

        if (carts.isEmpty()){
            System.out.println("empty");
            Product product = productService.getProduct(cartDto.getProductId());
            Cart cart = new Cart(null, product.getPrice(), cartDto.getQuantity(),
                    cartDto.getUserId(), product, LocalDateTime.now());

            return cartRepository.save(cart);
        }
        Cart cart = carts.get(0);
        cart.setQuantity(cartDto.getQuantity());

        return cartRepository.save(cart);
    }

    public boolean deleteCartItem(Long product_id, Long user_id) {
        List<Cart> carts = cartRepository.findByProductIdAndUserId(product_id,user_id);
        if(carts.isEmpty())
            throw new NotFoundException("Not found cart item");

        cartRepository.deleteById(carts.get(0).getId());

        return true;
    }

    public boolean clearCart(Long user_id) {
        cartRepository.deleteByUserId(user_id);

        return true;
    }

}
