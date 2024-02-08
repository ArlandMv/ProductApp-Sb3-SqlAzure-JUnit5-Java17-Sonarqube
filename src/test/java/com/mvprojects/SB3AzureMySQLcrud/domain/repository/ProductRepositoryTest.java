package com.mvprojects.SB3AzureMySQLcrud.domain.repository;

import static org.junit.jupiter.api.Assertions.*;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    private Product saved;

    @BeforeEach
    void setUp() {
        saved = createProductWithSku("ABC123");
        productRepository.save(saved);
    }

    /*
    @Test
    void findBySku_ExistingSku_ReturnsProduct() {
        // Arrange
        String sku = "ABC123";
        // Act
        Optional<Product> optionalProduct = productRepository.findBySku(sku);
        // Assert
        assertTrue(optionalProduct.isPresent());
        assertEquals(saved.getSku(), optionalProduct.get().getSku());
    }*/

    /*
    @Test
    void findBySku_NonExistingSku_ReturnsEmptyOptional() {
        // Arrange
        String sku = "NONEXISTENT";
        // Act
        Optional<Product> optionalProduct = productRepository.findBySku(sku);
        // Assert
        assertTrue(optionalProduct.isEmpty());
    }
*/

    private Product createProductWithSku(String sku) {
        Product product = Product.builder()
                .sku(sku)
                .name("Picture1")
                .description("A sample picture")
                .price(new BigDecimal("3000.00"))
                .imageUrl("https://example.com/picture1.jpg")
                .build();
        return product;
    }

    //@Test
    void ProductRepository_Save_ReturnsSavedProduct() {
        // Arrange
        Product product = createProductWithSku("ABC123");
        // Act
        Product savedProduct = productRepository.save(product);
        // Assert
        assertNotNull(savedProduct);
        //Assertions.assertThat(savedProduct).isNotNull();
        //Assertions.assertThat(savedProduct.getIdProduct()).isGreaterThan(0);
        //Assertions.assertThat(savedProduct.getPrice()).isGreaterThan(0);
        //Assertions.assertThat(savedProduct.getLastModified()).isBeforeOrEqualTo(new Date());
    }

}
