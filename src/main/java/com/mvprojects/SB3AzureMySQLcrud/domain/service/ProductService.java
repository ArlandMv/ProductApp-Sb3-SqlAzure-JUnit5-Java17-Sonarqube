package com.mvprojects.SB3AzureMySQLcrud.domain.service;

import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Product updatedProduct);

    void deleteById(Long id);

}
