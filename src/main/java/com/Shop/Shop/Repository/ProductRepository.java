package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
      boolean existsByproductName(String name);
}
