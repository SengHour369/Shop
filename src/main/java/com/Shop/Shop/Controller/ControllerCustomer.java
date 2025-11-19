package com.Shop.Shop.Controller;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ControllerCustomer {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO)
            throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.CreateCustomer(customerRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> UpdateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO,
                                                              @PathVariable Long id)
            throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.UpdateCustomer(id, customerRequestDTO));
    }

    @GetMapping
    public Page<CustomerResponseDTO> FindAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false,defaultValue = "username:desc")String sort,
                                                      @RequestParam(required = false,defaultValue = "false") String ipage



    ) {

        return customerService.findAll(page,size,name,sort,Objects.equals(ipage,"true"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> DeteleCustomerById(@PathVariable Long id) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(customerService.DeleteCustomer(id));
    }
}
