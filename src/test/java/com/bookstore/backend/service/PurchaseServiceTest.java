package com.bookstore.backend.service;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.model.Purchase;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void shouldCorrectlyCalculateTotalPrice() {
        PurchaseDto dto = new PurchaseDto();
        Double purchasePrice = 11.2;
        Double expectedTotal = purchasePrice * 3;
        dto.setPurchasedProductDtos(new LinkedList<>());
        PurchasedProductDto productDto = new PurchasedProductDto();
        productDto.setPurchasePrice(11.2);
        for (int i = 0; i < 3; i++) {
            dto.getPurchasedProductDtos().add(productDto);
        }
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

    private Purchase createPurchase(LocalDateTime purchaseDate) {
        Purchase p = new Purchase();
        p.setPurchaseDate(purchaseDate);
        p.setPurchasedProducts(new LinkedList<>());
        p.setEmail("any@email.com");
        return p;
    }
}
