package com.Shop.Shop.Repository;

import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.Model.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomer_Id(Long customerId);
}
