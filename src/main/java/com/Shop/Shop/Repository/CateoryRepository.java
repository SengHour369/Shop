package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CateoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
