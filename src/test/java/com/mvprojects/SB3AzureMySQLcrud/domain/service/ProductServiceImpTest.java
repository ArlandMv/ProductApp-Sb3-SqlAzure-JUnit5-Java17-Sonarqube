package com.mvprojects.SB3AzureMySQLcrud.domain.service;

import com.mvprojects.SB3AzureMySQLcrud.domain.repository.ProductRepository;
import com.mvprojects.SB3AzureMySQLcrud.exceptions.ResourceNotFoundException;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/*
class ProductServiceImpTest {

    @Test
    void testUpdateProduct() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setIdProduct(1L);

        Product updatedProduct = new Product();
        updatedProduct.setIdProduct(1L);
        updatedProduct.setName("UpdatedName");
        updatedProduct.setPrice(20.0);

        when(productRepository.findById(updatedProduct.getIdProduct())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // Act
        Product result = productService.updateProduct(updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        verify(productRepository, times(1)).findById(updatedProduct.getIdProduct());
        verify(productRepository, times(1)).save(existingProduct);
    }
}
*
* */

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
        assertNotNull(productList);
        assertEquals(2, productList.size());
        verify(productRepository, times(1)).findAll();
    }

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
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(productId));
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void updateProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setIdProduct(1L);
        when(productRepository.findById(updatedProduct.getIdProduct())).thenReturn(Optional.empty());
        // Act & Assert
        assertNull(productService.updateProduct(updatedProduct));
        verify(productRepository, times(1)).findById(updatedProduct.getIdProduct());
        verify(productRepository, never()).save(any());
    }
    @Test
    void updateProduct() {
        // Arrange
        Product updatedProduct = product2;
        Product existingProduct = product;
        given(productRepository.findById(updatedProduct.getIdProduct())).willReturn(Optional.of(existingProduct));
        given(productRepository.save(any(Product.class))).willReturn(updatedProduct);
        // Act
        Product result = productService.updateProduct(updatedProduct);
        // Assert
        assertNotNull(result);
        //assertEquals(updatedProduct, result); //timestamps
        assertEquals(updatedProduct.getIdProduct(), result.getIdProduct()); //check
        assertEquals(updatedProduct.getSku(), result.getSku());
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        // Verify
        then(productRepository).should().findById(updatedProduct.getIdProduct());
        then(productRepository).should().save(any(Product.class));
        //verify(productRepository, times(1)).findById(updatedProduct.getIdProduct());
        //verify(productRepository, times(1)).save(existingProduct);
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
