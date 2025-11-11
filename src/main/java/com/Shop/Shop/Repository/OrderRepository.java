package com.Shop.Shop.Repository;

import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.Model.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {
    @Query("select o from Order o where o.customer.id = :customerId")
    List<Order> findByCustomer_Id(@Param("customerId") Long customerId);
}
