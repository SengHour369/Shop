package com.Shop.Shop.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@NotBlank(message = " Cannot to Blank")
public class OrderItemDTO {
    private Integer quantity;
    private Long productId;

}
