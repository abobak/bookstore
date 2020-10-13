package com.bookstore.backend.service;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

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

}
