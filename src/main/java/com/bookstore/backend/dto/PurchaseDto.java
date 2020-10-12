package com.bookstore.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Long id;

    private String email;

    private LocalDateTime purchaseDate;

    private List<PurchasedProductDto> purchasedProductDtos;

    public Double getTotal() {
        return purchasedProductDtos.stream().mapToDouble(PurchasedProductDto::getPurchasePrice).sum();
    }
}
