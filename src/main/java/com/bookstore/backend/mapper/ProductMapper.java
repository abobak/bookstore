package com.bookstore.backend.mapper;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToProduct(ProductDto dto);

    ProductDto productToDto(Product product);

    List<ProductDto> productsListToDtoList(List<Product> products);

}
