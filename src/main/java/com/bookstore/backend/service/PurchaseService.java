package com.bookstore.backend.service;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.mapper.PurchaseMapper;
import com.bookstore.backend.model.Purchase;
import com.bookstore.backend.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

    public PurchaseDto submitPurchase(PurchaseDto purchaseDto) {
        return null;
    }

    public List<PurchaseDto> viewPurchases(LocalDateTime from, LocalDateTime to) {
        List<Purchase> purchases = purchaseRepository.findAllByPurchaseDateIsBetween(from, to);
        return purchaseMapper.purchasesToDtos(purchases);
    }

    Double getTotal(PurchaseDto dto) {
        return dto.getPurchasedProductDtos().stream().mapToDouble(PurchasedProductDto::getPurchasePrice).sum();
    }

}
