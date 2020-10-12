package com.bookstore.backend.service;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.exception.BadRequestException;
import com.bookstore.backend.exception.NotFoundException;
import com.bookstore.backend.mapper.ProductMapper;
import com.bookstore.backend.model.Product;
import com.bookstore.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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
        if (!isNull(dto.getId())) {
            throw new BadRequestException("New product can't have a predefined id");
        }
        Product p  = productRepository.save(productMapper.dtoToProduct(dto));
        return productMapper.productToDto(p);
    }

    public ProductDto updateProduct(ProductDto dto) {
        return null;
    }

}
