package com.mvprojects.SB3AzureMySQLcrud.domain.repository;

import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}