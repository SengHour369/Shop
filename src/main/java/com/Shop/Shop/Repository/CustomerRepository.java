package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByusername(String usersname);
}
