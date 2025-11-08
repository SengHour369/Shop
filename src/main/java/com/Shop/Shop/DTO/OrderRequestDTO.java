package com.Shop.Shop.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
@Data
@NotBlank(message = " Cannot to Blank")
public class OrderRequestDTO {
    private Long  customerId;
    private List<OrderItemDTO> orderItemDTOList;
}
