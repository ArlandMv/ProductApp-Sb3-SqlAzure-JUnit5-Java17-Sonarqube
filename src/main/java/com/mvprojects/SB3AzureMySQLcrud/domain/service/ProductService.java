package com.mvprojects.SB3AzureMySQLcrud.domain.service;

import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    //public Product updateProduct(Product updatedProduct);
    Product updateProduct(Product updatedProduct);

    public void deleteById(Long id);

}
