package com.Shop.Shop.Repository;

import com.Shop.Shop.Model.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select (count(c) > 0) from Customer c where c.username = :name and c.email = :email")
    boolean existsByusernameAndemail(@Param("name") String usersname,
                                       @Param("email")String email);
    @Query("SELECT c FROM Customer c WHERE c.id = :customerId")
   List <Customer> findByCustomer_Id(@Param("customerId") Long customerId);
    @Query("select c from Customer c where upper(c.username) like upper(concat('%', ?1, '%'))")
    Page<Customer> findAllCustomerByusernameContainingIgnoreCase(Pageable pageable, String name);





}

