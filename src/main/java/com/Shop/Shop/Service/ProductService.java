package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.ProductRequestDTO;
import com.Shop.Shop.DTO.ProductResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Category;
import com.Shop.Shop.Model.Entity.Product;
import com.Shop.Shop.Repository.CateoryRepository;
import com.Shop.Shop.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements ProductServiceTemp {
    private final ProductRepository productRepository;
    private final CateoryRepository cateoryRepository;
    private final ModelMapper  modelMapper;
    @Override
    public ProductResponseDTO findProductById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) throws ExceptionHandlerNotFound {
        if(productRepository.existsByproductName(productRequestDTO.getProductName())) {
          throw new ExceptionHandlerNotFound("product name already exists!") ;
        }
        var category = cateoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ExceptionHandlerNotFound("Category not found"));
        log.info("password Category {}", productRequestDTO.getCategoryId());
        var product = Product.builder()
                .productName(productRequestDTO.getProductName())
                .price(productRequestDTO.getProductPrice())
                .quantity(productRequestDTO.getQuantity())
                .category(category)
                .build();
        var product1 = productRepository.save(product);
        ProductResponseDTO Response = modelMapper.map(product1, ProductResponseDTO.class);
        Response.setCategoryId(category.getId());
        return Response;
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) throws ExceptionHandlerNotFound {
        var product = productRepository.findById(id)
                .orElseThrow(()->new ExceptionHandlerNotFound("product Not Found"));
        log.info("password product  {}", productRequestDTO.getCategoryId());
        Category category = cateoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(()->new ExceptionHandlerNotFound("Category not found"));
        log.info("password_Category_in_product  {}", productRequestDTO.getCategoryId());
              product.setProductName(productRequestDTO.getProductName());
              product.setPrice(productRequestDTO.getProductPrice());
              product.setQuantity(productRequestDTO.getQuantity());
              product.setCategory(category);
              var save = productRepository.save(product);
              var Response = modelMapper.map(save, ProductResponseDTO.class);
              Response.setCategoryId(category.getId());
        return Response;
    }

    @Override
    public ProductResponseDTO deleteProductById(Long id) throws ExceptionHandlerNotFound {
        var product = productRepository.findById(id)
                .orElseThrow(()->new ExceptionHandlerNotFound("Product not found to  deleted"));
        productRepository.delete(product);
        return null;
    }

    @Override
    public List<ProductResponseDTO> findProductAll() {
        return productRepository.findAll()
                .stream()
                .map(product-> modelMapper.map(product,ProductResponseDTO.class))
                .collect(Collectors.toList());

    }
}
