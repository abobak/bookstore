package com.bookstore.backend.mapper;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    private static ProductMapper mapper;

    private static ProductDto sourceDto;

    private static Product sourceProduct;

    @BeforeAll
    static void setUp() {
        mapper = new ProductMapperImpl();
        Double productPrice = 10.3;
        String productTitle = "A story";
        Long productId = 1L;
        sourceDto = new ProductDto(productId, productTitle, productPrice);
        sourceProduct = new Product(productId, productTitle, productPrice);
    }

    @Test
    void shouldCorrectlyMapDtoToEntity() {
        ProductDto mappedDto = mapper.productToDto(sourceProduct);
        assertEquals(sourceDto, mappedDto);
    }

    @Test
    void shouldCorrectlyMapEntityToDto() {
        Product mapped = mapper.dtoToProduct(sourceDto);
        assertEquals(sourceProduct, mapped);
    }

    @Test
    void shouldConvertListOfProductsToListOfDtos() {
        List<ProductDto> dtos = mapper.productsListToDtoList(Arrays.asList(sourceProduct, sourceProduct));
        dtos.forEach(dto -> assertEquals(sourceDto, dto));
    }

}
