package com.bookstore.backend.api;

import com.bookstore.backend.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "product")
public interface ProductApi {

    @ApiOperation(value = "Get single product")
    ProductDto getProduct(Long id);

    @ApiOperation(value = "Get all products")
    List<ProductDto> getProducts();

    @ApiOperation(value = "Create product")
    ProductDto createProduct(ProductDto dto);

    @ApiOperation(value = "Update product")
    ProductDto updateProduct(Long productId, ProductDto dto);

}
