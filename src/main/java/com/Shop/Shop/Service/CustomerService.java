package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CustomerRequestDTO;
import com.Shop.Shop.DTO.CustomerResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Customer;
import com.Shop.Shop.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceTemp {
    private final CustomerRepository customerRepository;
    private final ModelMapper  modelMapper;
    @Override

    public Page<CustomerResponseDTO> findAll(int page, int size,String name,String sort,boolean ispage) {
        if (page < 0) {
            throw new IllegalArgumentException("Page must be >= 0");
            }
        Pageable pageable;
        if(ispage) {
            List<Sort.Order> orders = new ArrayList<>();
            for (String item : sort.split(",")) {
                String plit[] = item.split(":");
                String field = plit[0];
                if (plit.length != 2) {
                    throw new RuntimeException("Invalid sort parameter");
                }
                String deriction = plit[1].toLowerCase();
                log.info(field + " " + deriction + " " + plit.length);

                orders.add(new Sort.Order(deriction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, field));
            }
            Sort sorting = Sort.by(orders);
             pageable = PageRequest.of(page, size, sorting);
        }
        else {
            pageable  = Pageable.unpaged();
        }


        if  (name != null) {
            Page<Customer> customerPage = customerRepository.findAllCustomerByusernameContainingIgnoreCase(pageable,name);
            return  customerPage.map(customer->modelMapper.map(customer,CustomerResponseDTO.class));
        }
        else {
            Page<Customer> customerPage = customerRepository.findAllCustomerByusernameContainingIgnoreCase(pageable,"");
            return customerPage
                    .map( customer->modelMapper.map(customer,CustomerResponseDTO.class));
         }
    }


    @Override
    public CustomerResponseDTO CreateCustomer(CustomerRequestDTO customerRequestDTO) throws ExceptionHandlerNotFound {
        if(customerRepository.existsByusernameAndemail(customerRequestDTO.getUsername(),customerRequestDTO.getEmail())) {
            throw new ExceptionHandlerNotFound("Username already exists!");
        }
        var customer = modelMapper.map(customerRequestDTO, Customer.class);
        var save = customerRepository.save(customer);
        return modelMapper.map(save , CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO)
            throws ExceptionHandlerNotFound {
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
    public class CustomerSpecification {

        public static Specification<Customer> hasName(String name) {
            return (root, query, builder) ->
                    name == null ? null : builder.equal(root.get("name"), name);
        }

        public static Specification<Customer> hasAge(Integer age) {
            return (root, query, builder) ->
                    age == null ? null : builder.equal(root.get("age"), age);
        }
    }

}
