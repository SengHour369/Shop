package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CateoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.name = ?1")
    Optional<Category> findByName(String name);

}
