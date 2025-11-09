package com.Shop.Shop.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDTO {
    private  Long Id;
    private  Long  customerId;
    private  String customerName;
    private  Double totalPrice;
    private  List<OrderItemRequestDTO> orderItem;
}
