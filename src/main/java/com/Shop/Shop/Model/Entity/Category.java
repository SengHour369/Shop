package com.Shop.Shop.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Table(name = "Category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false,unique = true)
     @NotBlank(message = "Category name is required")
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Product> products;
}
