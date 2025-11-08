package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.ProductRequestDTO;
import com.Shop.Shop.DTO.ProductResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;

import java.util.List;

public interface ProductServiceTemp {
    ProductResponseDTO findProductById(Long id);
    ProductResponseDTO  createProduct(ProductRequestDTO productRequestDTO) throws ExceptionHandlerNotFound;
    ProductResponseDTO  updateProduct(Long id,ProductRequestDTO productRequestDTO) throws ExceptionHandlerNotFound;
    ProductResponseDTO  deleteProductById(Long id) throws ExceptionHandlerNotFound;
    List<ProductResponseDTO> findProductAll();
}
