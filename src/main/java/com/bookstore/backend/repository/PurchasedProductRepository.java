package com.bookstore.backend.repository;

import com.bookstore.backend.model.PurchasedProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedProductRepository extends CrudRepository<PurchasedProduct, Long> {
}
