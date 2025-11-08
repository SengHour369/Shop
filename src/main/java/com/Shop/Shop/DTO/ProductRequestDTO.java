package com.Shop.Shop.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "productName is required")
    private  String productName;
    @NotNull(message = "productPrice is required")
    private  Double productPrice;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @NotNull(message = "categoryId is required")
    private  Long  categoryId;
}
