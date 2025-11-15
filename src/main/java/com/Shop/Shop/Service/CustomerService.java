package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Customer;
import com.Shop.Shop.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceTemp {
    private final CustomerRepository customerRepository;
    private final ModelMapper  modelMapper;
    @Override

    public List<CustomerResponseDTO> findAll(int page, int size,String name) {
        if  (!(page < 0) || name.isBlank()) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Customer> customerPage = customerRepository.findAllCustomerByusernameContainingIgnoreCase(pageable,name);
            return  customerPage.getContent()
                    .stream()
                    .map(customer -> modelMapper.map(customer, CustomerResponseDTO.class))
                    .collect(Collectors.toList());
        }
        else {
            Pageable pageable = PageRequest.of(page, size);
            Page<Customer> customerPage = customerRepository.findAllCustomerByusernameContainingIgnoreCase(pageable,"");
            return customerPage.getContent()
                    .stream()
                    .map(customer -> modelMapper.map(customer, CustomerResponseDTO.class))
                    .collect(Collectors.toList());
         }
    }


    @Override
    public CustomerResponseDTO CreateCustomer(CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound {
        if(customerRepository.existsByusername(customerRequestDTO.getUsername())) {
            throw new ExceptionHandlerNotFound("Username already exists!");
        }
        var customer = modelMapper.map(customerRequestDTO, Customer.class);
        var save = customerRepository.save(customer);
        return modelMapper.map(save , CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound {
        var customer = customerRepository.findById(id)
                .orElseThrow(()->new ExceptionHandlerNotFound("customer not found"));
        customer.setUsername(customerRequestDTO.getUsername());
        customer.setEmail(customerRequestDTO.getEmail());
        var save = customerRepository.save(customer);
        return modelMapper.map(save , CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO DeleteCustomer(Long id) throws ExceptionHandlerNotFound {
        var customer = customerRepository.findById(id)
                .orElseThrow(()->new ExceptionHandlerNotFound("customer not found"));
        customerRepository.delete(customer);
        return null;
    }
}
