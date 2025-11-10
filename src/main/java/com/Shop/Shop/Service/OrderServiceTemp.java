package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.OrderRequestDTO;
import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;

import java.util.List;

public interface OrderServiceTemp {
    List<OrderResponseDTO> findOrderByAll(Long id);
    OrderResponseDTO CreateOrder(OrderRequestDTO orderRequestDTO) throws ExceptionHandlerNotFound;
    OrderResponseDTO UpdateOrder(Long id ,OrderRequestDTO orderRequestDTO) throws ExceptionHandlerNotFound;
    OrderResponseDTO DeleteOrder(Long id) throws ExceptionHandlerNotFound;
}
