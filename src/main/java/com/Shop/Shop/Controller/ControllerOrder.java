package com.Shop.Shop.Controller;

import com.Shop.Shop.DTO.OrderRequestDTO;
import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Order")
public class ControllerOrder {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(orderService.CreateOrder(orderRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrderAll(@RequestParam(required = false ) Long id) {
        return ResponseEntity.ok(orderService.findOrderByAll(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> deleteOrder(@PathVariable Long id) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(orderService.DeleteOrder(id));
    }
}
