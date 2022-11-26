package com.carlosblinf.shoppingcart.dto;

import com.carlosblinf.shoppingcart.entities.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartItemsDto {
    private List<Cart> cartItems;
    private double totalCost;
}
