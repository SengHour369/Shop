package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerServiceTemp {
    Page<CustomerResponseDTO> findAll(int page, int size,String name,String sort,boolean ispage);
    CustomerResponseDTO CreateCustomer(CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound;
    CustomerResponseDTO UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound;
    CustomerResponseDTO DeleteCustomer(Long id) throws ExceptionHandlerNotFound;

}
