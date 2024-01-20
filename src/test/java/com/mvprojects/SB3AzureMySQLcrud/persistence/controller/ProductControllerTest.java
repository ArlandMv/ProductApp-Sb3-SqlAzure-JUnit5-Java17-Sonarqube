package com.mvprojects.SB3AzureMySQLcrud.persistence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvprojects.SB3AzureMySQLcrud.domain.service.ProductService;
import com.mvprojects.SB3AzureMySQLcrud.persistence.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class )
@AutoConfigureMockMvc( addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;
    private Product product2;
    //private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = Product.builder().idProduct(1L).name("productX").price(new BigDecimal(1000)).build();
        product2 = Product.builder().idProduct(2L).name("productY").price(new BigDecimal(2000)).build();
    }

    @Test
    public void productController_createProduct_ReturnCreated() throws Exception {
        //when(productService.createProduct(product)).thenReturn(product);
        BDDMockito.given(productService.createProduct(any(Product.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                //.content(objectMapper.writeValueAsString(productDto)));
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(status().isCreated());
    }


    @Test
    void productController_getAllProducts_ReturnsAll() throws Exception {
        List<Product> productList = Arrays.asList(product, product2);

        // Mock the ProductService behavior to return a list of products
        BDDMockito.given(productService.getAllProducts()).willReturn(productList);

        ResultActions response = mockMvc.perform(get("/api/v1/products"));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(productList)));
    }

    @Test
    void productController_getProductById_ReturnFound() throws Exception {

        BDDMockito.given(productService.getProductById(1L)).willReturn(Optional.ofNullable(product));
        ResultActions response = mockMvc.perform(get("/api/v1/products/1"));
        //HttpStatus.FOUND = 302
        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(product)));
                //.andExpect(jsonPath("$.name").value(product.getName()));
    }

    @Test
    public void productController_getProductById_ProductNotFound_ReturnNotFound() throws Exception {
        BDDMockito.given(productService.getProductById(anyLong())).willReturn(Optional.empty());

        ResultActions response = mockMvc.perform(get("/api/v1/products/{id}", 2L));
        response.andExpect(status().isNotFound());
    }


    @Test
    void productController_updateProduct_ReturnUpdated() throws Exception {
        Product updatedProduct = Product.builder()
                .idProduct(1L)
                .name("Updated Product")
                .price(new BigDecimal(20.0))
                .build();

        BDDMockito.given(productService.updateProduct(any(Product.class)))
                .willReturn(updatedProduct);

        ResultActions response = mockMvc.perform(put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedProduct)));;
    }

    @Test
    void productController_deleteProduct() throws Exception {
        // Given
        Long productId = 1L;
        // When
        mockMvc.perform(delete("/api/v1/products/{id}", productId))
                .andExpect(status().isNoContent());
        // Then
        BDDMockito.then(productService).should().deleteById(productId);
    }
}
