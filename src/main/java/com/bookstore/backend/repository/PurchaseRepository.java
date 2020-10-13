package com.bookstore.backend.repository;

import com.bookstore.backend.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>, JpaRepository<Purchase, Long> {

    List<Purchase> findAllByPurchaseDateIsBetween(LocalDateTime from, LocalDateTime to);
}
