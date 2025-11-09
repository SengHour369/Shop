package com.Shop.Shop.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDTO {
    @NotNull(message = "quantity is Require")
    private Integer quantity;
    @NotNull(message = "productId is Require")
    private Long productId;

}
