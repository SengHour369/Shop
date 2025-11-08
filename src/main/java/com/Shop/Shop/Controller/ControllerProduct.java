package com.Shop.Shop.Controller;

import com.Shop.Shop.DTO.ProductRequestDTO;
import com.Shop.Shop.DTO.ProductResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/product")
public class ControllerProduct {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }
    @PutMapping("/{id}")
    public  ResponseEntity<ProductResponseDTO> UpdateProoduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO productRequestDTO) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(productService.updateProduct(id,productRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findall(){
        return ResponseEntity.ok(productService.findProductAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProductById(@PathVariable Long id) throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }
}
