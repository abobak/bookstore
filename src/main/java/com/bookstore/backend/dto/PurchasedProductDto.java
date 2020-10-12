package com.bookstore.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedProductDto {

    private Long id;

    private String title;

    private Double purchasePrice;
}
