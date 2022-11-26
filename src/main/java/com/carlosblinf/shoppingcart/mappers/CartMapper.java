package com.carlosblinf.shoppingcart.mappers;

import com.carlosblinf.shoppingcart.dto.AddCartDto;
import com.carlosblinf.shoppingcart.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    Cart toCart(AddCartDto addCartDto);

    AddCartDto toCartItem(Cart cart);
}
