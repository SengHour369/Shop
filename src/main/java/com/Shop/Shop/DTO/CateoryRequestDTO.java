package com.Shop.Shop.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CateoryRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
}
