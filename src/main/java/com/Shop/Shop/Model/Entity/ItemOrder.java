package com.Shop.Shop.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ItemOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subprice")
    @NotNull(message = "subprice name is required")
    private Double subprice;
    @Column(name = "quatity")
    @NotNull(message = "quatity name is required")
    private Integer quatity;
    @ManyToOne
    @JoinColumn(name = "Order_id")
    private Order order;

}
