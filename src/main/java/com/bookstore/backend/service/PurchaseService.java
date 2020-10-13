package com.bookstore.backend.service;

import com.bookstore.backend.dto.NewPurchaseDto;
import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.mapper.ProductMapper;
import com.bookstore.backend.mapper.PurchaseMapper;
import com.bookstore.backend.model.Product;
import com.bookstore.backend.model.Purchase;
import com.bookstore.backend.model.PurchasedProduct;
import com.bookstore.backend.repository.ProductRepository;
import com.bookstore.backend.repository.PurchaseRepository;
import com.bookstore.backend.repository.PurchasedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ProductRepository productRepository;

    private final PurchasedProductRepository purchasedProductRepository;

    private final PurchaseMapper purchaseMapper;

    private final ProductMapper productMapper;

    @Transactional
    public PurchaseDto submitPurchase(NewPurchaseDto purchaseDto) {
        Purchase p = new Purchase();
        List<Product> boughtProducts = productRepository.findAllById(purchaseDto.getPurchasedProducts());
        Map<Long, Product> products = new HashMap<>();
        List<PurchasedProduct> purchasedProducts = new LinkedList<>();
        for (Product pp : boughtProducts) {
            products.put(pp.getId(), pp);
        }
        for (Long id : purchaseDto.getPurchasedProducts()) {
            PurchasedProduct pp = productMapper.productToPurchasedProduct(products.get(id));
            pp.setPurchase(p);
            purchasedProducts.add(pp);
        }
        p.setTotal(getTotal(purchasedProducts));
        p.setEmail(purchaseDto.getEmail());

        p.setPurchasedProducts(purchasedProducts);
        p.setPurchaseDate(LocalDateTime.now());
        purchasedProductRepository.saveAll(purchasedProducts);
        p = purchaseRepository.save(p);
        return purchaseMapper.purchaseToDto(p);
    }

    public List<PurchaseDto> viewPurchases(LocalDateTime from, LocalDateTime to) {
        List<Purchase> purchases = purchaseRepository.findAllByPurchaseDateBetween(from, to);
        return purchaseMapper.purchasesToDtos(purchases);
    }

    Double getTotal(List<PurchasedProduct> products) {
        return products.stream().mapToDouble(PurchasedProduct::getPurchasePrice).sum();
    }

}
