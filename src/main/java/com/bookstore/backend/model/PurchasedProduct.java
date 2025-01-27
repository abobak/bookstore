package com.bookstore.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private Long productId;

    @Column
    private String title;

    @Column
    private Double purchasePrice;

    @ManyToOne
    private Purchase purchase;

}
