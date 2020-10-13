package com.bookstore.backend.service;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.mapper.ProductMapper;
import com.bookstore.backend.mapper.PurchaseMapper;
import com.bookstore.backend.model.Purchase;
import com.bookstore.backend.model.PurchasedProduct;
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

    private final ProductMapper productMapper;

    public PurchaseDto submitPurchase(PurchaseDto purchaseDto) {
        Purchase p = new Purchase();
        p.setTotal(getTotal(purchaseDto));
        p.setEmail(purchaseDto.getEmail());
        List<PurchasedProduct> purchasedProducts = productMapper.purchasedProductDtosToPurchasedProducts(purchaseDto.getPurchasedProductDtos());
        for (PurchasedProduct pp : purchasedProducts) {
            pp.setPurchase(p);
        }
        p.setPurchasedProducts(purchasedProducts);
        p.setPurchaseDate(LocalDateTime.now());
        p = purchaseRepository.save(p);
        return purchaseMapper.purchaseToDto(p);
    }

    public List<PurchaseDto> viewPurchases(LocalDateTime from, LocalDateTime to) {
        List<Purchase> purchases = purchaseRepository.findAllByPurchaseDateBetween(from, to);
        return purchaseMapper.purchasesToDtos(purchases);
    }

    Double getTotal(PurchaseDto dto) {
        return dto.getPurchasedProductDtos().stream().mapToDouble(PurchasedProductDto::getPurchasePrice).sum();
    }

}
