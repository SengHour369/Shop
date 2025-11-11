package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Customer;
import com.Shop.Shop.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceTemp {
    private final CustomerRepository customerRepository;
    private final ModelMapper  modelMapper;
    @Override
    public List<CustomerResponseDTO> findAll(Long id) {
        if (id == null) {
            return this.customerRepository.findByAllOrderByNameAsc()
                    .stream()
                    .map(customer -> modelMapper
                            .map(customer, CustomerResponseDTO.class))
                    .collect(Collectors.toList());
        }
        else{
            return this.customerRepository.findByCustomer_Id(id)
                    .stream()
                    .map(customer -> modelMapper
                            .map(customer, CustomerResponseDTO.class))
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
