package com.Shop.Shop.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequestDTO {
    @NotNull(message = "customerId is require")
    private Long  customerId;
    private List<OrderItemRequestDTO> orderItemDTOList;
}
