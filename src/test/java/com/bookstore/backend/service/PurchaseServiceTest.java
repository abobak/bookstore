package com.bookstore.backend.service;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.mapper.ProductMapper;
import com.bookstore.backend.model.Product;
import com.bookstore.backend.model.Purchase;
import com.bookstore.backend.model.PurchasedProduct;
import com.bookstore.backend.repository.ProductRepository;
import com.bookstore.backend.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void shouldCorrectlyCalculateTotalPrice() {
        PurchaseDto dto = new PurchaseDto();
        Double purchasePrice = 11.2;
        Double expectedTotal = purchasePrice * 3;
        dto.setPurchasedProductDtos(new LinkedList<>());
        PurchasedProductDto productDto = new PurchasedProductDto();
        productDto.setPurchasePrice(11.2);
        IntStream.range(0, 3).forEach(i -> dto.getPurchasedProductDtos().add(productDto));
        assertEquals(expectedTotal, purchaseService.getTotal(dto));
    }

    @Test
    void shouldFetchAllPurchasesFromGivenDateRange() {
        LocalDateTime fromDate = LocalDateTime.of(LocalDate.parse("2020-02-01"), LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.MAX);
        purchaseRepository.save(createPurchase(LocalDateTime.of(LocalDate.parse("2020-02-01"), LocalTime.of(0, 0, 0))));
        purchaseRepository.save(createPurchase(LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.of(23, 59, 59))));
        purchaseRepository.save(createPurchase(LocalDateTime.of(LocalDate.parse("2020-02-03"), LocalTime.of(0, 0, 0))));
        List<PurchaseDto> purchases = purchaseService.viewPurchases(fromDate, toDate);
        assertEquals(2, purchases.size());
    }

    @Test
    void shouldPersistPurchaseWithPurchasedProducts() {
        // given
        Product p1 = new Product(null, "A book", 2.0);
        p1 = productRepository.save(p1);
        PurchasedProductDto ppd = productMapper.productToPurchasedProductDto(p1);
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setEmail("buyer@frominternet.com");
        purchaseDto.setPurchasedProductDtos(new LinkedList<>());
        purchaseDto.getPurchasedProductDtos().add(ppd);
        // when
        purchaseDto = purchaseService.submitPurchase(purchaseDto);
        // then
        assertTrue(purchaseDto.getPurchasedProductDtos().contains(ppd));
    }

    private Purchase createPurchase(LocalDateTime purchaseDate) {
        Purchase p = new Purchase();
        p.setPurchaseDate(purchaseDate);
        p.setPurchasedProducts(new LinkedList<>());
        p.setEmail("any@email.com");
        return p;
    }
}
