package com.bookstore.backend.service;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.exception.NotFoundException;
import com.bookstore.backend.mapper.ProductMapper;
import com.bookstore.backend.model.Product;
import com.bookstore.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductDto getProductDto(Long productId) {
        Product p = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Couldn't find product with id " + productId)
        );
        return productMapper.productToDto(p);
    }

    public List<ProductDto> getProducts() {
        return null;
    }

    public ProductDto createProduct(ProductDto dto) {
        return null;
    }

    public ProductDto updateProduct(ProductDto dto) {
        return null;
    }

}
