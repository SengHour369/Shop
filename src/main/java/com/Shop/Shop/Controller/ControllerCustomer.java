package com.Shop.Shop.Controller;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ControllerCustomer {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.CreateCustomer(customerRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> UpdateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO, @PathVariable Long id) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.UpdateCustomer(id, customerRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> FindAllCustomers(@RequestParam(required = false) Long id) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.findAll(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> DeteleCustomerById(@PathVariable Long id) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.DeleteCustomer(id));
    }
}
