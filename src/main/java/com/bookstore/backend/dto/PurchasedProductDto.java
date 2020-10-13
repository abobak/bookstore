package com.bookstore.backend.dto;

import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PurchasedProductDto {

    private Long id;

    private Long productId;

    private String title;

    private Double purchasePrice;
}
