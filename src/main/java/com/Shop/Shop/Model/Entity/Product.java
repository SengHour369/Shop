package com.Shop.Shop.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(name = "productName", nullable = false,unique = true)
    private String productName;
     @Column(name = "price")
     @NotNull(message = "Price is required")
    private Double price;
    @NotNull(message = "quantity is required")
    @NotNull(message = "quantity name is required")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "Category_id",nullable = false)
    private Category category;

}
