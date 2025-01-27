package com.bookstore.backend.controller;

import com.bookstore.backend.api.ProductApi;
import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.exception.BadRequestException;
import com.bookstore.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    @GetMapping(path = "/api/v1/products/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProductDto(id);
    }

    @Override
    @GetMapping(path = "/api/v1/products")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @Override
    @PostMapping(path = "/api/v1/products")
    public ProductDto createProduct(@RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @Override
    @PutMapping(path = "/api/v1/products/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto dto) {
        if (!productId.equals(dto.getId())) {
            throw new BadRequestException("Changing id of existing product is not allowed");
        }
        return productService.updateProduct(dto);
    }
}
