package com.Shop.Shop.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderitemResponseDTO {
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
}
