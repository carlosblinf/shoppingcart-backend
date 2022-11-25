package com.carlosblinf.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private @NotBlank String name;
    private @NotBlank String description;
    private @NotNull Long stock;
    private @NotNull double price;
    private @NotBlank String imageUrl;
    private @NotNull Long categoryId;
}
