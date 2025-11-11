package com.Shop.Shop.Repository;

import com.Shop.Shop.DTO.OrderResponseDTO;
import com.Shop.Shop.Model.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {
    @Query("select o from Order o where o.customer.id = :customerId")
    List<Order> findByCustomer_Id(@Param("customerId") Long customerId);
=======
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomer_Id(Long customerId);
>>>>>>> a1a3445bf4e3dc9cc1ed81ba46a9729b5bc24e96
}
