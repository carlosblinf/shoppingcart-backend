package com.carlosblinf.shoppingcart.controllers;

import com.carlosblinf.shoppingcart.dto.AddCartDto;
import com.carlosblinf.shoppingcart.dto.CartItemsDto;
import com.carlosblinf.shoppingcart.entities.Cart;
import com.carlosblinf.shoppingcart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartItemsDto> getCartItems(@RequestParam Long user_id) {
        return ResponseEntity.ok(cartService.getCartItems(user_id));
    }

    @PostMapping
    public ResponseEntity<Cart> addToCart(@Valid @RequestBody AddCartDto cartDto) {
        return ResponseEntity.created(URI.create("/api/carts")).body(cartService.addToCart(cartDto));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> addToCart(@RequestParam Long product_id, @RequestParam Long user_id) {
        return ResponseEntity.ok(cartService.deleteCartItem(product_id, user_id));
    }
}
