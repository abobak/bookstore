package com.bookstore.backend.api;

import com.bookstore.backend.dto.ProductDto;

import java.util.List;

public interface ProductApi {

    ProductDto getProduct(Long id);

    List<ProductDto> getProducts();

    ProductDto createProduct(ProductDto dto);

    ProductDto updateProduct(Long productId, ProductDto dto);

}
