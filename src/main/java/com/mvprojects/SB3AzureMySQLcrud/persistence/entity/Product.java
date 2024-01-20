package com.mvprojects.SB3AzureMySQLcrud.persistence.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="products")
@Validated
public class Product {
    @Valid

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="id_product")
    private Long  idProduct;

    @NotBlank(message = "Product sku is required")
    @Column(nullable = false)
    private String sku;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    private String description;

    @Positive(message = "Price must be a positive value")
    private BigDecimal price;

    @Column(name= "image_url")
    private String imageUrl;

    @Column(name= "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @PrePersist
    public void prePersist(){
        this.lastModified=new Date();
    }

}
