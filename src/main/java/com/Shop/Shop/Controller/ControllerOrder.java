package com.Shop.Shop.Controller;

import com.Shop.Shop.DTO.OrderRequestDTO;
import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Order;
import com.Shop.Shop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Order")
public class ControllerOrder {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(orderService.CreateOrder(orderRequestDTO));
    }
}
