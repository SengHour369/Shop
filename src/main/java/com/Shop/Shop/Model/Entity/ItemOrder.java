package com.Shop.Shop.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Entity
@Table(name = "item_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subprice", nullable = false)
    @NotNull(message = "subprice is required")
    private Double subprice;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "quantity is required")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false,referencedColumnName = "id")
    private Product product;
}

