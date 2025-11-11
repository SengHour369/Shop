package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;

import java.util.List;

public interface CustomerServiceTemp {
    List<CustomerResponseDTO> findAll(Long id);
    CustomerResponseDTO CreateCustomer(CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound;
    CustomerResponseDTO UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound;
    CustomerResponseDTO DeleteCustomer(Long id) throws ExceptionHandlerNotFound;

}
