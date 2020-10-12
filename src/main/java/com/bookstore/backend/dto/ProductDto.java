package com.bookstore.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDto {

    private Long id;

    private String title;

    private Double price;

}
