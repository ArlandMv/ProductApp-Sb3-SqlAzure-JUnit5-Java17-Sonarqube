package com.mvprojects.SB3AzureMySQLcrud.domain.service;

import com.mvprojects.SB3AzureMySQLcrud.domain.repository.ProductRepository;
import com.mvprojects.SB3AzureMySQLcrud.exceptions.ResourceNotFoundException;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(@Valid Product product) {
        Optional<Product> saveProduct = productRepository.findBySku(product.getSku());
        if (saveProduct.isPresent()){
            throw new ResourceNotFoundException("Already exists with sku:"+ product.getSku());
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
        /* return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", id)));*/
    }

    @Override
    public Product updateProduct(@Valid Product updatedProduct) {
        Product existing = productRepository.findById(updatedProduct.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException());
        if(existing!=null){
            existing.setSku(updatedProduct.getSku());
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setImageUrl(updatedProduct.getImageUrl());
            existing.prePersist();
            return productRepository.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
