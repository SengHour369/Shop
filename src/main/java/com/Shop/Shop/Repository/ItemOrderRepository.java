package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder,Long> {
}
