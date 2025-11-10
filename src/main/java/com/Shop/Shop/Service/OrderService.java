package com.Shop.Shop.Service;


import com.Shop.Shop.DTO.OrderRequestDTO;
import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.DTO.OrderitemResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Customer;
import com.Shop.Shop.Model.Entity.ItemOrder;
import com.Shop.Shop.Model.Entity.Order;
import com.Shop.Shop.Model.Entity.Product;
import com.Shop.Shop.Repository.CustomerRepository;
import com.Shop.Shop.Repository.ItemOrderRepository;
import com.Shop.Shop.Repository.OrderRepository;
import com.Shop.Shop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements OrderServiceTemp {
 private final OrderRepository orderRepository;
 private final CustomerRepository customerRepository;
 private final ProductRepository productRepository;
    @Override
    public List<OrderResponseDTO> findOrderByAll(Long id) {
        if(id == null){
            return orderRepository.findAll()
                    .stream()
                    .map(this::mapperToDTO
                    ).collect(Collectors.toList());
        }
        return orderRepository.findByCustomer_Id(id).
                stream().map(this::mapperToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public OrderResponseDTO CreateOrder(OrderRequestDTO orderRequestDTO) throws ExceptionHandlerNotFound {
        Customer customer = customerRepository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(() -> new ExceptionHandlerNotFound("customer not found"));
        Order order = Order.builder()
                .customer(customer)
                .build();
        List <ItemOrder>  orderitem = orderRequestDTO.getOrderItemDTOList()
                .stream().map(
                        request ->{
                            Product product = new Product();
                            try {
                                product = productRepository
                                        .findById(request.getProductId())
                                        .orElseThrow(() -> new ExceptionHandlerNotFound("product not found"));
                            } catch (ExceptionHandlerNotFound e) {
                                throw new RuntimeException(e);
                            }
                            if (product.getQuantity() <= request.getQuantity()) {
                                    try {
                                        throw new ExceptionHandlerNotFound("product not found");
                                    } catch (ExceptionHandlerNotFound e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                          Double totalPrice =product.getPrice()*product.getQuantity();
                            return   ItemOrder.builder()
                                    .order(order)
                                    .quantity(request.getQuantity())
                                    .product(product)
                                    .subprice(totalPrice)
                                    .build();
                        }
                ).collect(Collectors.toList());
            order.setItemOrders(orderitem);
            order.setCustomer(customer);
            Double totalPrice =orderitem.stream()
                    .mapToDouble(ItemOrder::getSubprice)
                    .sum();
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);


        return  mapperToDTO(order)  ;
    }

    @Override
    public OrderResponseDTO UpdateOrder(Long id, OrderRequestDTO orderRequestDTO) throws ExceptionHandlerNotFound {
        var customer = customerRepository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(()-> new ExceptionHandlerNotFound("customer not found"));
        var order = orderRepository.findById(id)
                .orElseThrow(()-> new ExceptionHandlerNotFound("order not found"));
        order.setCustomer(customer);

        return null;
    }

    @Override
    public OrderResponseDTO DeleteOrder(Long id) throws ExceptionHandlerNotFound {
        var order = orderRepository.findById(id)
                .orElseThrow(()-> new ExceptionHandlerNotFound("order not found"));
        orderRepository.delete(order);
        return null ;
    }
    private OrderResponseDTO mapperToDTO(Order order) {
        List<OrderitemResponseDTO> itemOrders = order.getItemOrders()
                .stream()
                .map(itemOrder -> OrderitemResponseDTO.builder()
                        .productId(itemOrder.getProduct().getId())
                        .productName(itemOrder.getProduct().getProductName())
                        .quantity(itemOrder.getQuantity())
                        .price(itemOrder.getSubprice())
                        .build())
                .collect(Collectors.toList());
        return OrderResponseDTO.builder()
                .Id(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getUsername())
                .totalPrice(order.getTotalPrice())
                .orderItem(itemOrders)
                .build();
    }
}
