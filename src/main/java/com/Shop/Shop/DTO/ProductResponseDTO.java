package com.Shop.Shop.DTO;


import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private  String productName;
    private  Double productPrice;
    private Integer quantity;
    private  Long  categoryId;
}
