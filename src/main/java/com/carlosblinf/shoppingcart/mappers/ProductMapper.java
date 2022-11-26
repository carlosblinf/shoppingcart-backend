package com.carlosblinf.shoppingcart.mappers;

import com.carlosblinf.shoppingcart.dto.ProductDto;
import com.carlosblinf.shoppingcart.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    List<Product> toProductList(List<ProductDto> productDtoList);

    List<ProductDto> toProductDtoList(List<Product> products);
}
