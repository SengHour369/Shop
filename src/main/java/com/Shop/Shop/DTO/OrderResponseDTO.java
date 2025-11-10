package com.Shop.Shop.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseDTO {
    private  Long Id;
    private  Long  customerId;
    private  String customerName;
    private  Double totalPrice;
    private  List<OrderitemResponseDTO> orderItem;
}
