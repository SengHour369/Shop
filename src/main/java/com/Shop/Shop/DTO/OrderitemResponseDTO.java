package com.Shop.Shop.DTO;

import lombok.Data;

@Data
public class OrderitemResponseDTO {
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
}
