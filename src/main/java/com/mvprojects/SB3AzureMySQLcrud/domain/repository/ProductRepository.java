package com.mvprojects.SB3AzureMySQLcrud.domain.repository;

import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}