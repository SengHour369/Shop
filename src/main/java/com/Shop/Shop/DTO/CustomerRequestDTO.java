package com.Shop.Shop.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class CustomerRequestDTO {
    @NotBlank(message = "Customer name is required ")
    private String username;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
}
