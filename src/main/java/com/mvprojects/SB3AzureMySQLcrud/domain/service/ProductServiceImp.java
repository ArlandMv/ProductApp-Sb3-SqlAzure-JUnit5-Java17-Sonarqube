package com.mvprojects.SB3AzureMySQLcrud.domain.service;

import com.mvprojects.SB3AzureMySQLcrud.domain.repository.ProductRepository;
import com.mvprojects.SB3AzureMySQLcrud.exceptions.ResourceNotFoundException;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(@Valid Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productRepository.findById(id)
                //.orElseThrow(() -> new ResourceNotFoundException()));
                //.orElseThrow(() -> new ResourceNotFoundException("ID:"+id+" not valid")));
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", id)));
    }

    @Override
    public Product updateProduct(@Valid Product updatedProduct) {
        Product existing = productRepository.findById(updatedProduct.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException());
        if(existing!=null){
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.prePersist();
            //existing.setLastModified(new Date());
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
