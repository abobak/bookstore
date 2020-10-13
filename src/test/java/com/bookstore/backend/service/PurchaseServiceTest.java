package com.bookstore.backend.service;

import com.bookstore.backend.dto.NewPurchaseDto;
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
        Double purchasePrice = 11.2;
        Double expectedTotal = purchasePrice * 3;
        List<PurchasedProduct> products = new LinkedList<>();
        PurchasedProduct p = new PurchasedProduct();
        p.setPurchasePrice(purchasePrice);
        IntStream.range(0, 3).forEach(i -> products.add(p));
        assertEquals(expectedTotal, purchaseService.getTotal(products));
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
        NewPurchaseDto newPurchaseDto = new NewPurchaseDto();
        newPurchaseDto.setEmail("buyer@frominternet.com");
        newPurchaseDto.setPurchasedProducts(new LinkedList<>());
        newPurchaseDto.getPurchasedProducts().add(p1.getId());
        // when
        PurchaseDto purchaseDto = purchaseService.submitPurchase(newPurchaseDto);
        // then
        assertTrue(purchaseDto.getPurchasedProductDtos().size() == 1);
    }

    private Purchase createPurchase(LocalDateTime purchaseDate) {
        Purchase p = new Purchase();
        p.setPurchaseDate(purchaseDate);
        p.setPurchasedProducts(new LinkedList<>());
        p.setEmail("any@email.com");
        return p;
    }
}
