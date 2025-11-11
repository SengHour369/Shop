package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select c from Customer c order by c.username asc")
    List<Customer> findByAllOrderByNameAsc();
    @Query("select (count(c) > 0) from Customer c where c.username = :name")
    boolean existsByusername(@Param("name") String usersname);
    @Query("SELECT c FROM Customer c WHERE c.id = :customerId")
   List <Customer> findByCustomer_Id(@Param("customerId") Long customerId);


}

