package com.bookstore.backend.repository;

import com.bookstore.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long>, JpaRepository<Product, Long> {

}
