package com.carlosblinf.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartItemsDto {
    private List<AddCartDto> cartItems;
    private double totalCost;
}
