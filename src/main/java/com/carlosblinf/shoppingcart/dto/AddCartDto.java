package com.carlosblinf.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddCartDto {
    private @NotNull int quantity;
    private @NotNull Long productId;
    private @NotNull Long userId;
}
