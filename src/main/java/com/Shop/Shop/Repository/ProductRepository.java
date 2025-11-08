package com.Shop.Shop.Repository;

import com.Shop.Shop.DTO.CateoryResponseDTO;
import com.Shop.Shop.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {


      boolean existsByproductName(String name);
}
