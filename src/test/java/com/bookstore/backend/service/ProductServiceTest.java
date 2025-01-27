package com.bookstore.backend.service;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.IntStream;

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

    @Test
    void shouldRetrieveAllSavedProducts() {
        ProductDto dto = new ProductDto(null, "Anything", 1.0);
        IntStream.range(0, 3).forEach(i -> productService.createProduct(dto));
        List<ProductDto> products = productService.getProducts();
        assertEquals(3, products.size());
    }

    @Test
    void shouldRetrieveProductDtoWhenValidIdIsProvided() {
        // given
        String name = "A name";
        Double price = 10.2;
        ProductDto dto = new ProductDto(null, name, price);
        ProductDto dto2 = new ProductDto(null, "Other name", 3.0);
        // when
        productService.createProduct(dto2);
        dto = productService.createProduct(dto);
        // then
        dto = productService.getProductDto(dto.getId());
        assertEquals(name, dto.getTitle());
        assertEquals(price, dto.getPrice());
        assertNotNull(dto.getId());
    }

    @Test
    void shouldUpdateProductWhenValidDataIsProvided() {
        // given
        String name = "A name";
        Double price = 10.2;
        ProductDto dto = new ProductDto(null, "Anything", 3.0);
        dto = productService.createProduct(dto);
        // when
        dto.setPrice(price);
        dto.setTitle(name);
        productService.updateProduct(dto);
        // then
        dto = productService.getProductDto(dto.getId());
        assertEquals(price, dto.getPrice());
        assertEquals(name, dto.getTitle());

    }
}
