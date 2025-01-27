package com.bookstore.backend.repository;

import com.bookstore.backend.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>, JpaRepository<Purchase, Long> {

    @Query("select distinct p from Purchase p left join fetch p.purchasedProducts where p.purchaseDate >= :fromDate and p.purchaseDate <= :toDate")
    List<Purchase> findAllByPurchaseDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
