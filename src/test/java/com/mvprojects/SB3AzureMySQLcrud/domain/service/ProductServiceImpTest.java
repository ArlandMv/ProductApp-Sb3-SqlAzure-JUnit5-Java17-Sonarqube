package com.mvprojects.SB3AzureMySQLcrud.domain.service;

import com.mvprojects.SB3AzureMySQLcrud.domain.repository.ProductRepository;
import com.mvprojects.SB3AzureMySQLcrud.exceptions.ResourceNotFoundException;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImp productService;

    private Product product;
    private Product product2;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = Product.builder()
                .idProduct(1L)
                .sku("SKU123")
                .name("Picture1")
                .description("A sample picture")
                .price(new BigDecimal("3000.00"))
                .imageUrl("https://example.com/picture1.jpg")
                .build();
        product2 = Product.builder()
                .idProduct(2L)
                .sku("SKU456")
                .name("Picture2")
                .description("A second sample picture")
                .price(new BigDecimal("6000.00"))
                .imageUrl("https://example.com/picture1.jpg")
                .build();
    }

    @Test
    @DisplayName("createProduct_Success")
    void createProduct() {
        given(productRepository.findBySku(product.getSku()))
                .willReturn(Optional.empty());
        given(productRepository.save(product))
                .willReturn(product);

        Product inputProduct = productService.createProduct(product);
        verify(productRepository, times(1)).save(inputProduct);

        assertNotNull(inputProduct);
    }

    @Test
    @DisplayName("createProduct_ThrowsException")
    void createProduct_DuplicateSku_ThrowsException() {
        given(productRepository.findBySku(product.getSku())).willReturn(Optional.of(product));
        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.createProduct(product));
        // Verify
        then(productRepository).should().findBySku(product.getSku());
        then(productRepository).should(never()).save(any(Product.class));
    }

    @Test
    @DisplayName("getAllProducts_Success")
    void getAllProducts() {
        // Arrange
        given(productRepository.findAll()).willReturn(List.of(product,product2));
        // Act
        List<Product> productList = productService.getAllProducts();
        // Assert
        assertAll(
                () -> assertNotNull(productList),
                () -> assertEquals(2, productList.size())
        );
        verify(productRepository, times(1)).findAll();
    } // Assert that the returned list is empty when there are no products

    @Test
    @DisplayName("getProductById_Success")
    void getProductById() {
        // Arrange
        Long productId = product.getIdProduct();
        given(productRepository.findById(productId)).willReturn(Optional.of(product));
        // Act
        Optional<Product> result = productService.getProductById(productId);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
        // Verify
        then(productRepository).should().findById(productId);
    }

    @Test
    @DisplayName("testGetProductById_ThrowsException")
    void getProductByIdNotFound() {
        // Arrange
        Long productId = 10L;
        given(productRepository.findById(productId)).willReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(productId);
        });

        assertEquals("Product not found with productId : '" + productId+"'", exception.getMessage());

        then(productRepository).should().findById(productId);
    }

    @Test
    void updateProduct() {
        // Arrange
        Product updatedProduct = product;
        Product existingProduct = product2;
        given(productRepository.findById(updatedProduct.getIdProduct())).willReturn(Optional.of(existingProduct));
        given(productRepository.save(any(Product.class))).willReturn(updatedProduct);
        // Act
        Product result = productService.updateProduct(updatedProduct);
        // Assert
        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName()); // Add more assertions based on your actual update logic.
        // Verify
        then(productRepository).should().findById(updatedProduct.getIdProduct());
        then(productRepository).should().save(any(Product.class));
        then(productRepository).shouldHaveNoMoreInteractions();
    }


    @Test
    void updateProductNotFound() {
        // Arrange
        Product updatedProduct = product;
        Long productId = updatedProduct.getIdProduct();
        given(productRepository.findById(productId)).willReturn(Optional.empty());
        // Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            productService.updateProduct(updatedProduct);
        });
        // Assert
        assertEquals("Product not found with productId : '" + productId + "'", exception.getMessage());
        // Verify
        then(productRepository).should().findById(productId);
        then(productRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        // Arrange
        Long productId = 1L;
        // Act
        productService.deleteById(productId);
        // Verify
        then(productRepository).should().deleteById(productId);
    }
}
