package com.mvprojects.SB3AzureMySQLcrud.persistence.controller;

import com.mvprojects.SB3AzureMySQLcrud.domain.service.ProductService;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Validated
//@CrossOrigin(origins = "http://localhost:4200/products")
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;
    // crud createPOST readGET  updatePUT deleteDELETE

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //
    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long productID) {
        Optional<Product> oProduct = productService.getProductById(productID);
        if (oProduct.isPresent()){
            return ResponseEntity.ok(oProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Validated @RequestBody Product updatedProduct){
        Optional<Product> newProduct = Optional.ofNullable(productService.updateProduct(updatedProduct));
        //update functional style: return newProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        if(newProduct.isPresent()){
            return ResponseEntity.ok(newProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.noContent().build(); //204
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        /*productService.deleteById(id);
        return ResponseEntity.noContent().build();  */
    }



}