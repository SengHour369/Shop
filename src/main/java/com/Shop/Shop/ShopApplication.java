package com.Shop.Shop;

import com.Shop.Shop.Model.Entity.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Function;
@Slf4j
@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
    public class CustomerSpecification {

        public static Specification<Customer> hasName(String name) {
            return (root, query, builder) ->
                    name == null ? null : builder.equal(root.get("name"), name);


        }
        public static Specification<Customer> hasAge(Integer age) {
            return (root, query, builder) ->
                    age == null ? null : builder.equal(root.get("age"), age)
        }
    }

}

