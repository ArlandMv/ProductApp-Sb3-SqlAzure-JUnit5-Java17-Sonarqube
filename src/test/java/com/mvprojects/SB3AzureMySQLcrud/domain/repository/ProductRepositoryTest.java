package com.mvprojects.SB3AzureMySQLcrud.domain.repository;

import static org.junit.jupiter.api.Assertions.*;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;

@DataJdbcTest
@AutoConfigureTestDatabase
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    //Structure resource_method_reactiom()
    //save
    //findall
    //findById
    //update
    //delete

    @Test
    void ProductRepository_Save_ReturnsSavedProduct() {
        // Arrange
        Product product = Product.builder().name("Harina").price(1000.0).build();

        // Act
        Product savedProduct = productRepository.save(product);

        // Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getIdProduct()).isGreaterThan(0);
        Assertions.assertThat(savedProduct.getPrice()).isGreaterThan(0);
        //Assertions.assertThat(savedProduct.getLastModified()).isBeforeOrEqualTo(new Date());
    }

}
