package com.bookstore.backend.service;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldPersistNewProductAndAssignItId() {
        String name = "A name";
        Double price = 10.2;
        ProductDto dto = new ProductDto(null, name, price);
        dto = productService.createProduct(dto);
        assertNotNull(dto.getId());
        assertEquals(name, dto.getTitle());
        assertEquals(price, dto.getPrice());
    }

    @Test
    void shouldNotPersistNewProductIfItHasNonNullId() {
        String name = "A name";
        Double price = 10.2;
        ProductDto dto = new ProductDto(9L, name, price);
        assertThrows(BadRequestException.class, () -> productService.createProduct(dto));
    }
}
