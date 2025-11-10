package com.Shop.Shop.Model.Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "totalPrice")
    @NotNull(message = "Price name is required")
    private Double totalPrice;
    @NotNull(message = "customer name is required")
    @ManyToOne
    @JoinColumn(name = "Customer_id",nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrder> itemOrders;

}
