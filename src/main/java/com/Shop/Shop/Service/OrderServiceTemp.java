package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.OrderRequestDTO;
import com.Shop.Shop.DTO.OrderResponseDTO;

import java.util.List;

public interface OrderServiceTemp {
    OrderResponseDTO  findOrderById(Long id);
    List<OrderResponseDTO> findOrderByAll();
    OrderResponseDTO CreateOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO UpdateOrder(Long id ,OrderRequestDTO orderRequestDTO);
    OrderResponseDTO DeleteOrder(Long id);
}
