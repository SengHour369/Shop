package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerServiceTemp {
    List<CustomerResponseDTO> findAll(int page, int size,String name);
    CustomerResponseDTO CreateCustomer(CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound;
    CustomerResponseDTO UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound;
    CustomerResponseDTO DeleteCustomer(Long id) throws ExceptionHandlerNotFound;

}
