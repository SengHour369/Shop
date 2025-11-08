package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
